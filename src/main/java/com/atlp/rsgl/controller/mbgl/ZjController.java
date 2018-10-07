package com.atlp.rsgl.controller.mbgl;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBZjEntity;
import com.atlp.rsgl.service.zj.IZjService;
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
import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-17 14:44
 * @Decription:
 */
@Controller
@RequestMapping(value = "/zj")
public class ZjController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IZjService iZjService;

    /**
     * 分页查询职务信息
     *
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getZjPage")
    @ResponseBody
    public Page<RsglBZjEntity> getZjPage(PageModel page) throws Exception {
        return iZjService.getZjPage(page);
    }

    /**
     * 职务编辑UI
     *
     * @return
     */
    @RequestMapping(value = "/editUI/{zjid}")
    public String ZjEditUI(ModelMap map, @PathVariable(name = "zjid") String zjid) throws Exception {
        RsglBZjEntity zjEntity = new RsglBZjEntity();

        if (!AtlpUtil.isEmpty(zjid)) {
            // 修改信息
            zjEntity = iZjService.getZjInfoById(zjid);
        }

        map.put("entity", zjEntity);
        return "rsgl/mbgl/rysx/zj_edit";
    }

    /**
     * form保存方法
     * 添加或修改职务
     *
     * @param zjEntity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    @ResponseBody
    public boolean doSave(RsglBZjEntity zjEntity, HttpServletRequest request) throws Exception {
        if (null == zjEntity) {
            logger.debug("参数异常,传入职务信息为空.职务信息==={}", zjEntity.toString());
            // throw new Exception("参数异常,传入状态信息为空.");
            return false;
        }

        if (AtlpUtil.isEmpty(zjEntity.getZjid())) {
            // 添加
            RsglBZjEntity saveEntity = new RsglBZjEntity();
            BeanUtils.copyProperties(zjEntity, saveEntity, AtlpUtil.getNullPropertyNames(zjEntity));
            AtlpUtil.setUserInfo(saveEntity, request);
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");

            iZjService.doSaveZj(saveEntity);
        } else {
            // 修改
            iZjService.doUpdateZj(zjEntity);
        }

        return true;
    }

    /**
     * 删除职务
     *
     * @param zjid
     * @return
     */
    @RequestMapping(value = "/doDelete/{zjid}", method = RequestMethod.POST)
    @ResponseBody
    public boolean doDelete(@PathVariable(value = "zjid", required = true) String zjid) throws Exception {
        // 查询该单位是否存在
        RsglBZjEntity zjEntity = iZjService.getZjInfoById(zjid);
        if (null == zjEntity) {
            logger.debug("传入参数错误，职务信息为空...职务id==={}", zjid);
            return false;
        }

        return iZjService.doDeleteZj(zjEntity);
    }

    /**
     * 查询所有职务列表
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getZjList/{sybbs}", method = RequestMethod.POST)
    @ResponseBody
    public List<RsglBZjEntity> getZjList(@PathVariable(name = "sybbs", required = true) String sybbs) throws Exception {
        return iZjService.getZjList(sybbs);
    }

    /**
     * 职级树
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getZjTree", method = RequestMethod.POST)
    @ResponseBody
    public List<RsglBZjEntity> getZjTree() throws Exception {
        return iZjService.getZjList();
    }

}
