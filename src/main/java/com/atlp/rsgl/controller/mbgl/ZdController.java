package com.atlp.rsgl.controller.mbgl;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBZdEntity;
import com.atlp.rsgl.entity.RsglBZjEntity;
import com.atlp.rsgl.service.zd.IZdService;
import com.atlp.rsgl.service.zj.IZjService;
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

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-17 14:44
 * @Decription:
 */
@Controller
@RequestMapping(value = "/zd")
public class ZdController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IZdService iZdService;
    @Autowired
    private IZjService iZjService;

    /**
     * 分页查询职等信息
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getZdPage", method = RequestMethod.GET)
    @ResponseBody
    public Page<RsglBZdEntity> getZdPage(PageModel page, @RequestParam(name = "zjid") String zjid) throws Exception {
        return iZdService.getZdPage(page, zjid);
    }

    /**
     * 职等编辑UI
     * @return
     */
    @RequestMapping(value = "/editUI/{zjid}/{zdid}", method = RequestMethod.GET)
    public String zdEditUI(ModelMap map, @PathVariable(name = "zjid") String zjid,
                           @PathVariable(name = "zdid") String zdid) throws Exception {
        RsglBZdEntity zdEntity = new RsglBZdEntity();

        if (!AtlpUtil.isEmpty(zdid)) {
            // 修改信息
            zdEntity = iZdService.getZdInfoById(zdid);
        } else {
            // 添加职等
            RsglBZjEntity zjEntity = iZjService.getZjInfoById(zjid);
            zdEntity.setZjmc(zjEntity.getZjmc());
            zdEntity.setZjid(zjid);
        }

        map.put("entity", zdEntity);
        return "/rsgl/mbgl/rysx/zd_edit";
    }

    /**
     * form保存方法
     * 添加或修改职等
     * @param zdEntity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    @ResponseBody
    public boolean doSave(RsglBZdEntity zdEntity, HttpServletRequest request) throws Exception {
        if (null == zdEntity) {
            logger.debug("参数异常,传入职等信息为空.职等信息==={}", zdEntity.toString());
            // throw new Exception("参数异常,传入状态信息为空.");
            return false;
        }

        if (AtlpUtil.isEmpty(zdEntity.getZdid())) {
            // 添加
            RsglBZdEntity saveEntity = new RsglBZdEntity();
            BeanUtils.copyProperties(zdEntity, saveEntity, AtlpUtil.getNullPropertyNames(zdEntity));
            AtlpUtil.setUserInfo(saveEntity, request);
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");

            iZdService.doSaveZd(saveEntity);
        } else {
            // 修改
            iZdService.doUpdateZd(zdEntity);
        }

        return true;
    }

    /**
     * 删除职等
     * @param zdid
     * @return
     */
    @RequestMapping(value = "/doDelete/{zdid}", method = RequestMethod.POST)
    @ResponseBody
    public boolean doDelete(@PathVariable(value = "zdid", required = true) String zdid) throws Exception {
        // 查询该单位是否存在
        RsglBZdEntity zdEntity = iZdService.getZdInfoById(zdid);
        if (null == zdEntity) {
            logger.debug("传入参数错误，职等信息为空...职等id==={}", zdid);
            return false;
        }

        return iZdService.doDeleteZd(zdEntity);
    }

}
