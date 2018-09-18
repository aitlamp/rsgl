package com.atlp.rsgl.controller.mbgl;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBGwdjDwEntity;
import com.atlp.rsgl.entity.RsglBGwdjEntity;
import com.atlp.rsgl.service.gd.IGdService;
import com.atlp.rsgl.service.gj.IGjService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-17 18:23
 * @Decription: 岗位等级挡位
 */
@Controller
@RequestMapping(value = "/gd")
public class GdController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IGdService iGdService;
    @Autowired
    private IGjService iGjService;

    /**
     * 分页查询全员聘用岗位
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getGdPage")
    @ResponseBody
    public Page<RsglBGwdjDwEntity> getGdPage(PageModel page, @RequestParam String gwdjid) throws Exception {
        return iGdService.getGdPage(page, gwdjid);
    }

    /**
     * 岗位信息编辑UI
     * @param map
     * @param gdid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editUI/{gjid}/{gdid}")
    public String gdEditUI(ModelMap map, @PathVariable(name = "gjid") String gjid,
                           @PathVariable(name = "gdid") String gdid) throws Exception {
        RsglBGwdjDwEntity gdEntity = new RsglBGwdjDwEntity();

        if (!AtlpUtil.isEmpty(gdid)) {
            // 修改
            gdEntity = iGdService.getGdInfoById(gdid);
        } else {
            // 添加挡位，查询岗位等级信息
            RsglBGwdjEntity gjEntity = iGjService.getGjInfoById(gjid);
            gdEntity.setGwdjmc(gjEntity.getGwdjmc());
            gdEntity.setGwdjid(gjid);
        }

        map.put("entity", gdEntity);
        return "/rsgl/mbgl/rysx/gd_edit";
    }

    /**
     * form保存方法
     * 添加或修改职务
     * @param gdEntity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    @ResponseBody
    public boolean doSave(RsglBGwdjDwEntity gdEntity, HttpServletRequest request) throws Exception {
        if (null == gdEntity) {
            logger.debug("参数异常,传岗位等级挡位信息为空.挡位信息==={}", gdEntity.toString());
            // throw new Exception("参数异常,传入状态信息为空.");
            return false;
        }

        if (AtlpUtil.isEmpty(gdEntity.getDwid())) {
            // 添加
            RsglBGwdjDwEntity saveEntity = new RsglBGwdjDwEntity();
            BeanUtils.copyProperties(gdEntity, saveEntity, AtlpUtil.getNullPropertyNames(gdEntity));
            AtlpUtil.setUserInfo(saveEntity, request);
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");

            iGdService.doSaveGd(saveEntity);
        } else {
            // 修改
            iGdService.doUpdateGd(gdEntity);
        }

        return true;
    }

    /**
     * 删除职务
     * @param gdid
     * @return
     */
    @RequestMapping(value = "/doDelete/{gdid}", method = RequestMethod.POST)
    @ResponseBody
    public boolean doDelete(@PathVariable(value = "gdid", required = true) String gdid) throws Exception {
        // 查询该单位是否存在
        RsglBGwdjDwEntity gdEntity = iGdService.getGdInfoById(gdid);
        if (null == gdEntity) {
            logger.debug("传入参数错误，岗位等级挡位信息为空...挡位id==={}", gdid);
            return false;
        }

        return iGdService.doDeleteGd(gdEntity);
    }

    /**
     * 查询所有档位列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getGdList/{gjid}", method = RequestMethod.POST)
    @ResponseBody
    public List<RsglBGwdjDwEntity> getGdList(@PathVariable(name = "gjid") String gjid) throws Exception {
        return iGdService.getGdListByGjid(gjid);
    }
}
