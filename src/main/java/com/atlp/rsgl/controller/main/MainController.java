package com.atlp.rsgl.controller.main;

import com.alibaba.fastjson.JSON;
import com.atlp.rsgl.common.data.UserInfo;
import com.atlp.rsgl.common.filter.AccessFilter;
import com.atlp.rsgl.common.prop.CustomProps;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.common.utils.HttpClientUtil;
import com.atlp.rsgl.entity.RsglBCdEntity;
import com.atlp.rsgl.entity.RsglBYhEntity;
import com.atlp.rsgl.repository.YhRepository;
import com.atlp.rsgl.service.cd.ICdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 页面跳转控制类
 *
 * @author ctc
 * @date 2018年8月5日 15:38:42
 */

@Controller
public class MainController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ICdService cdService;
    @Autowired
    CustomProps customProps;
    @Autowired
    private YhRepository yhRepository;

    //主界面
    @RequestMapping({"/main"})
    public String main() {
        return "main/main";
    }

    //主界面
    @RequestMapping({"/main/{hhid}"})
    public String main(@PathVariable String hhid, HttpServletRequest request) {
        if (!AtlpUtil.isEmpty(hhid)) {
            // 设置header
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json; charset=UTF-8");
            // 组织参数
            Map<String, Object> pmap = new HashMap<>();
            //pmap.put("ip", AtlpUtil.getClientIP(request));
            pmap.put("ip", "127.0.0.1");
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
                Map retMap = JSON.parseObject(responseContent);

                // 获取接收到的用户信息
                Map<String, Object> userMap = (Map<String, Object>) retMap.get("userInfo");
                String dlid = userMap.get("dlid").toString();
                // 根据dlid获取本系统用户信息
                RsglBYhEntity yhEntity = yhRepository.findByDlid(dlid);
                if (yhEntity != null) {
                    UserInfo userinfo = new UserInfo(yhEntity);
                    // 清空前面的session变量
                    request.getSession().removeAttribute("hhid");
                    request.getSession().removeAttribute("userinfo");
                    // 给session重新赋值
                    request.getSession().setAttribute("hhid", hhid);
                    request.getSession().setAttribute("userinfo", userinfo);
                    // 移除用户信息后返回
                    retMap.remove("userInfo");
                }
            }
        }
        return "main/main";
    }

    //导航页面
    @RequestMapping({"/main/nav"})
    public String main_nav(ModelMap map) {
        map.addAttribute("cdList", cdService.getMenus("root"));
        return "main/main_nav";
    }

    //首页
    @RequestMapping({"/main/content"})
    public String main_content() {
        return "main/main_content";
    }
}
