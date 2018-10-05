package com.atlp.rsgl.common.filter;

import com.alibaba.fastjson.JSON;
import com.atlp.rsgl.common.prop.CustomProps;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.common.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 是否允许访问--过滤器
 *
 * @author 曹铁诚
 * @date 2018年8月22日 11:14:00
 */
@Order(1)
@WebFilter(filterName = "accessFilter", urlPatterns = "/*")
public class AccessFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

    @Autowired
    CustomProps customProps;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("AccessFilter过滤器初始化！");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        // 定义变量
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // 获取请求地址
        String requestUrl = httpServletRequest.getRequestURI();

        // 排除静态资源过滤
        if (requestUrl.endsWith(".js") || requestUrl.endsWith(".css")
                || requestUrl.endsWith(".png") || requestUrl.endsWith(".ioc")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        // 忽略验证
        boolean check = false;
        List<String> accessIgnore = customProps.getAccessIgnore();
        for (String ignore : accessIgnore) {
            if (requestUrl.contains(ignore)) {
                check = true;
                break;
            }
        }
        if (check || requestUrl.endsWith("/")) {
            // 忽略验证
            filterChain.doFilter(httpServletRequest, httpServletResponse); // 跳转页面
            return;
        }

        //判断是否登录
        Map<String, Object> checkLoginMap = this.checkLogin(httpServletRequest);
        String code = checkLoginMap.get("code").toString();
        if (code.equals("00")) {
            // 验证通过
            filterChain.doFilter(httpServletRequest, httpServletResponse); // 跳转页面
        } else {
            // 验证不通过
            String contextPath = httpServletRequest.getContextPath();
            httpServletResponse.sendRedirect(contextPath);
        }

        //filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("AccessFilter过滤器销毁！");
    }

    /**
     * 判断用户是否登录
     */
    private Map<String, Object> checkLogin(HttpServletRequest request) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("code", "01");
        retMap.put("msg", "未知错误！");
        try {
            // 判断session是否存在hhid
            if (StringUtils.isEmpty(request.getSession().getAttribute("hhid"))) {
                // 没有登录
                retMap.put("code", "02");
                retMap.put("msg", "您还没有登录！");
                return retMap;
            }
            String hhid = request.getSession().getAttribute("hhid").toString();

            /***** 验证hhid是否过期 *****/

            // 设置header
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json; charset=UTF-8");
            // 组织参数
            Map<String, Object> pmap = new HashMap<>();
            pmap.put("ip", AtlpUtil.getClientIP(request));
            pmap.put("hhid", hhid);
            pmap.put("method", "checkHhid");
            // 发送请求
            Map<String, Object> responseMap = HttpClientUtil.postJson(customProps.getTysqPath(), pmap, headers);
            log.debug("调用统一授权接口验证会话ID方法返回值：" + responseMap);
            // 处理参数
            int statusCode = (Integer) responseMap.get("statusCode");
            if (statusCode == 200) {
                String responseContent = responseMap.get("responseContent").toString();
                // 将返回值转为Map对象
                return JSON.parseObject(responseContent);
            } else {
                retMap.put("code", "03");
                retMap.put("msg", "调用接口失败！");
                return retMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retMap;
    }
}
