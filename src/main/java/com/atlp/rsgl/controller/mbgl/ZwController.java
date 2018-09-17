package com.atlp.rsgl.controller.mbgl;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBZwEntity;
import com.atlp.rsgl.service.zw.IZwService;
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
 * @CreateTime: 2018-09-17 14:44
 * @Decription:
 */
@Controller
@RequestMapping(value = "/zw")
public class ZwController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IZwService iZwService;

    /**
     * 人员属性UI
     * @return
     */
    @RequestMapping(value = "/listUI")
    public String rysxListUI() {
        return "/rsgl/mbgl/rysx/rysx_main";
    }

    /**
     * 分页查询职务信息
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getZwPage")
    @ResponseBody
    public Page<RsglBZwEntity> getZwPage(PageModel page) throws Exception {
        return iZwService.getZwPage(page);
    }

    /**
     * 职务编辑UI
     * @return
     */
    @RequestMapping(value = "/editUI/{zwid}")
    public String zwEditUI(ModelMap map, @PathVariable(name = "zwid") String zwid) throws Exception {
        RsglBZwEntity zwEntity = new RsglBZwEntity();

        if (!AtlpUtil.isEmpty(zwid)) {
            // 修改信息
            zwEntity = iZwService.getZwInfoById(zwid);
        }

        map.put("entity", zwEntity);
        return "/rsgl/mbgl/rysx/zw_edit";
    }

    /**
     * form保存方法
     * 添加或修改职务
     * @param zwEntity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    @ResponseBody
    public boolean doSave(RsglBZwEntity zwEntity, HttpServletRequest request) throws Exception {
        if (null == zwEntity) {
            logger.debug("参数异常,传入职务信息为空.职务信息==={}", zwEntity.toString());
            // throw new Exception("参数异常,传入状态信息为空.");
            return false;
        }

        if (AtlpUtil.isEmpty(zwEntity.getZwid())) {
            // 添加
            RsglBZwEntity saveEntity = new RsglBZwEntity();
            BeanUtils.copyProperties(zwEntity, saveEntity, AtlpUtil.getNullPropertyNames(zwEntity));
            AtlpUtil.setUserInfo(saveEntity, request);
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");

            iZwService.doSaveZw(saveEntity);
        } else {
            // 修改
            iZwService.doUpdateZw(zwEntity);
        }

        return true;
    }

    /**
     * 删除职务
     * @param zwid
     * @return
     */
    @RequestMapping(value = "/doDelete/{zwid}", method = RequestMethod.POST)
    @ResponseBody
    public boolean doDelete(@PathVariable(value = "zwid", required = true) String zwid) throws Exception {
        // 查询该单位是否存在
        RsglBZwEntity zwEntity = iZwService.getZwInfoById(zwid);
        if (null == zwEntity) {
            logger.debug("传入参数错误，职务信息为空...职务id==={}", zwid);
            return false;
        }

        return iZwService.doDeleteZw(zwEntity);
    }

}
