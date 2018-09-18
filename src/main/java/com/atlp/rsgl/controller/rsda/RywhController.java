package com.atlp.rsgl.controller.rsda;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.data.UserInfo;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.common.utils.JsonUtil;
import com.atlp.rsgl.entity.RsglBYhEntity;
import com.atlp.rsgl.entity.RsglBYhGzxxEntity;
import com.atlp.rsgl.service.rylb.IRylbService;
import com.atlp.rsgl.service.yh.IYhService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-18 11:01
 * @Decription:
 */
@Controller
@RequestMapping(value = "/rywh")
public class RywhController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IYhService iYhService;
    @Autowired
    private IRylbService iRylbService;

    /**
     * 人员维护UI
     * @return
     */
    @RequestMapping(value = "/listUI")
    public String rywhListUI() {
        return "/rsgl/rsda/rywh/rywh_main";
    }

    /**
     * 分页查询用户信息
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getYhPage", method = RequestMethod.GET)
    @ResponseBody
    public Page<RsglBYhEntity> getYhPage(PageModel page) throws Exception {
        return iYhService.getYhPage(page);
    }

    /**
     * 用户信息编辑UI
     * @param map
     * @param yhid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editUI/{yhid}")
    public String rywhEditUI(ModelMap map, @PathVariable(name = "yhid") String yhid) throws Exception {
        RsglBYhEntity yhEntity = new RsglBYhEntity();

        if (!AtlpUtil.isEmpty(yhid)) {
            yhEntity = iYhService.getYhInfoById(yhid);
        }

        map.put("entity", yhEntity);
        return "/rsgl/rsda/rywh/rywh_edit";
    }

    @RequestMapping(value = "/doSave")
    @ResponseBody
    public boolean doSave(HttpServletRequest request) throws Exception {
        // 接收参数
        String yhInfo = request.getParameter("yhxx");
        String gzInfo = request.getParameter("gzxx");
        //获取session中的用户信息
        UserInfo userinfo = (UserInfo) request.getSession().getAttribute("userinfo");

        if (AtlpUtil.isEmpty(yhInfo)) {
            logger.debug("传入参数异常,用户信息为空.用户信息==={}", yhInfo);
            return false;
        }
        if (AtlpUtil.isEmpty(gzInfo)) {
            logger.debug("传入参数异常,工作信息为空.工作信息==={}", gzInfo);
            return false;
        }

        // 对象转
        RsglBYhEntity yhEntity = JsonUtil.json2Obj(yhInfo, RsglBYhEntity.class);
        RsglBYhGzxxEntity gzxxEntity = JsonUtil.json2Obj(gzInfo, RsglBYhGzxxEntity.class);

        if (AtlpUtil.isEmpty(yhEntity.getYhid())) {
            // 新增用户
            RsglBYhEntity saveYhEntity = new RsglBYhEntity();
            BeanUtils.copyProperties(yhEntity, saveYhEntity, AtlpUtil.getNullPropertyNames(yhEntity));
            // AtlpUtil.setUserInfo(saveYhEntity, request);
            saveYhEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveYhEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveYhEntity.setDqzt("有效");
            saveYhEntity.setYhid2(userinfo.getYhid());
            saveYhEntity.setYhxm2(userinfo.getYhxm());
            saveYhEntity.setYhdwid(userinfo.getDwid());
            saveYhEntity.setYhdwmc(userinfo.getDwmc());

            // 新增用户
            RsglBYhGzxxEntity saveGzxxEntity = new RsglBYhGzxxEntity();
            BeanUtils.copyProperties(gzxxEntity, saveGzxxEntity, AtlpUtil.getNullPropertyNames(gzxxEntity));
            // AtlpUtil.setUserInfo(saveGzxxEntity, request);
            saveGzxxEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveGzxxEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveGzxxEntity.setDqzt("有效");
            saveGzxxEntity.setYhid2(userinfo.getYhid());
            saveGzxxEntity.setYhxm(userinfo.getYhxm());
            saveGzxxEntity.setYhdwid(userinfo.getDwid());
            saveGzxxEntity.setYhdwmc(userinfo.getDwmc());

            iYhService.doSaveYh(saveYhEntity, saveGzxxEntity);
        } else {
            // 编辑用户
            iYhService.doUpdateYh(yhEntity, gzxxEntity);
        }

        return true;
    }

    /**
     * 删除用户
     * @param yhid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doDelete/{yhid}", method = RequestMethod.POST)
    @ResponseBody
    public boolean doDelete(@PathVariable(name = "yhid") String yhid) throws Exception {
        // 查询该用户是否存在
        RsglBYhEntity yhEntity = iYhService.getYhInfoById(yhid);
        if (null == yhEntity) {
            logger.debug("查询用户信息失败...用户id==={}", yhEntity.getYhid());
            return false;
        }

        return iYhService.doDeleteYh(yhEntity);
    }
}
