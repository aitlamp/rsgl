package com.atlp.rsgl.controller.pxgl;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.controller.main.BaseController;
import com.atlp.rsgl.entity.RsglBPxjlEntity;
import com.atlp.rsgl.service.pxjl.IPxjlService;
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
 * @CreateTime: 2018-09-19 11:09
 * @Decription:
 */
@Controller
@RequestMapping(value = "/pxwh", method = {RequestMethod.GET, RequestMethod.POST})
public class PxwhController extends BaseController {

    @Autowired
    private IPxjlService iPxjlService;

    /**
     * 培训记录UI
     * @return
     */
    @RequestMapping(value = "/listUI")
    public String pxwhListUI() {
        return "/rsgl/pxgl/pxdj/pxwh_main";
    }

    /**
     * 分页查询培训记录
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getPxPage")
    @ResponseBody
    public Page<RsglBPxjlEntity> getPxPage(PageModel page) throws Exception {
        return iPxjlService.getPxPage(page);
    }

    /**
     * 培训记录编辑UI
     * @param map
     * @param pxid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editUI/{pxid}")
    public String pxwhEditUI(ModelMap map, @PathVariable(name = "pxid") String pxid) throws Exception {
        RsglBPxjlEntity pxjlEntity = new RsglBPxjlEntity();

        if (!AtlpUtil.isEmpty(pxid)) {
            // 修改
            pxjlEntity = iPxjlService.getPxInfoById(pxid);
        }

        map.put("entity", pxjlEntity);
        return "/rsgl/pxgl/pxdj/pxwh_edit";
    }

    /**
     * form保存
     * 添加或修改
     * @param pxjlEntity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    @ResponseBody
    public boolean doSave(RsglBPxjlEntity pxjlEntity, HttpServletRequest request) throws Exception {
        if (null == pxjlEntity) {
            logger.debug("传入培训记录为空,培训信息==={}", pxjlEntity.toString());
            return false;
        }

        if (AtlpUtil.isEmpty(pxjlEntity.getPxjlid())) {
            // 添加
            RsglBPxjlEntity saveEntity = new RsglBPxjlEntity();
            BeanUtils.copyProperties(pxjlEntity, saveEntity, AtlpUtil.getNullPropertyNames(pxjlEntity));
            AtlpUtil.setUserInfo(saveEntity, request);
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");

            iPxjlService.doSavePx(saveEntity);
        } else {
            // 修改
            iPxjlService.doUpdatePx(pxjlEntity);
        }

        return true;
    }

    /**
     * 删除培训记录
     * @param pxid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doDelete/{pxid}", method = RequestMethod.POST)
    @ResponseBody
    public boolean doDelete(@PathVariable(name = "pxid") String pxid) throws Exception {
        // 查询该记录是否存在
        RsglBPxjlEntity pxjlEntity = iPxjlService.getPxInfoById(pxid);
        if (null == pxjlEntity) {
            logger.debug("查询培训记录为空,培训id==={}", pxid);
            return false;
        }

        return iPxjlService.doDeletePx(pxjlEntity);
    }
}
