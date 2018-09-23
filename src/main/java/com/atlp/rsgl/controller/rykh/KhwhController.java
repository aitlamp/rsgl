package com.atlp.rsgl.controller.rykh;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.controller.main.BaseController;
import com.atlp.rsgl.entity.RsglBKhjlEntity;
import com.atlp.rsgl.service.khjl.IKhjlService;
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
 * @CreateTime: 2018-09-19 12:39
 * @Decription:
 */
@Controller
@RequestMapping(value = "/khwh", method = {RequestMethod.GET, RequestMethod.POST})
public class KhwhController extends BaseController {

    @Autowired
    private IKhjlService iKhjlService;

    /**
     * 考核tableUI
     * @return
     */
    @RequestMapping(value = "/listUI")
    public String khwhListUI() {
        return "/rsgl/rykh/khdj/khwh_main";
    }

    /**
     * 考核记录page
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getKhPage", method = RequestMethod.GET)
    @ResponseBody
    public Page<RsglBKhjlEntity> getKhPage(PageModel page) throws Exception {
        return iKhjlService.getKhPage(page);
    }

    /**
     * 考核编辑ui
     * @param map
     * @param khid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editUI/{khid}")
    public String khwhEditUI(ModelMap map, @PathVariable(name = "khid") String khid) throws Exception {
        RsglBKhjlEntity khjlEntity = new RsglBKhjlEntity();

        if (!AtlpUtil.isEmpty(khid)) {
            khjlEntity = iKhjlService.getKhInfoById(khid);
        }

        map.put("entity", khjlEntity);
        return "/rsgl/rykh/khdj/khwh_edit";
    }

    /**
     * form保存方法
     * 添加或修改考核记录
     * @param khjlEntity
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    @ResponseBody
    public boolean doSave(RsglBKhjlEntity khjlEntity, HttpServletRequest request) throws Exception {
        if (null == khjlEntity) {
            logger.debug("传入考核信息为空,考核信息==={}", khjlEntity.toString());
            return false;
        }

        if (AtlpUtil.isEmpty(khjlEntity.getKhjlid())) {
            // 添加
            RsglBKhjlEntity saveEntity = new RsglBKhjlEntity();
            BeanUtils.copyProperties(khjlEntity, saveEntity, AtlpUtil.getNullPropertyNames(khjlEntity));
            AtlpUtil.setUserInfo(saveEntity, request);
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");

            iKhjlService.doSaveKh(saveEntity);
        } else {
            // 修改
            iKhjlService.doUpdateKh(khjlEntity);
        }

        return true;
    }

    /**
     * 删除考核记录
     * @param khid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doDelete/{khid}", method = RequestMethod.POST)
    @ResponseBody
    public boolean doDelete(@PathVariable(name = "khid") String khid) throws Exception {
        // 查询原考核记录是否存在
        RsglBKhjlEntity khjlEntity = iKhjlService.getKhInfoById(khid);
        if (null == khjlEntity) {
            logger.debug("查询考核信息为空,考核id==={}", khid);
            return false;
        }

        return iKhjlService.doDeleteKh(khjlEntity);
    }

}
