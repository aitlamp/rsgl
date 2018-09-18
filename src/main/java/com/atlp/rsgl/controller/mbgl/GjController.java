package com.atlp.rsgl.controller.mbgl;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBGwdjEntity;
import com.atlp.rsgl.service.gj.IGjService;
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
 * @CreateTime: 2018-09-17 18:23
 * @Decription:
 */
@Controller
@RequestMapping(value = "/gj")
public class GjController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IGjService iGjService;

    /**
     * 分页查询全员聘用岗位
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getGjPage")
    @ResponseBody
    public Page<RsglBGwdjEntity> getGjPage(PageModel page) throws Exception {
        return iGjService.getGjPage(page);
    }

    /**
     * 岗位信息编辑UI
     * @param map
     * @param gjid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editUI/{gjid}")
    public String gjEditUI(ModelMap map, @PathVariable(name = "gjid") String gjid) throws Exception {
        RsglBGwdjEntity gjEntity = new RsglBGwdjEntity();

        if (!AtlpUtil.isEmpty(gjid)) {
            // 修改
            gjEntity = iGjService.getGjInfoById(gjid);
        }

        map.put("entity", gjEntity);
        return "/rsgl/mbgl/rysx/gj_edit";
    }

    /**
     * form保存方法
     * 添加或修改职务
     * @param gjEntity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    @ResponseBody
    public boolean doSave(RsglBGwdjEntity gjEntity, HttpServletRequest request) throws Exception {
        if (null == gjEntity) {
            logger.debug("参数异常,传岗位等级信息为空.岗级信息==={}", gjEntity.toString());
            // throw new Exception("参数异常,传入状态信息为空.");
            return false;
        }

        if (AtlpUtil.isEmpty(gjEntity.getGwdjid())) {
            // 添加
            RsglBGwdjEntity saveEntity = new RsglBGwdjEntity();
            BeanUtils.copyProperties(gjEntity, saveEntity, AtlpUtil.getNullPropertyNames(gjEntity));
            AtlpUtil.setUserInfo(saveEntity, request);
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");

            iGjService.doSaveGj(saveEntity);
        } else {
            // 修改
            iGjService.doUpdateGj(gjEntity);
        }

        return true;
    }

    /**
     * 删除职务
     * @param gjid
     * @return
     */
    @RequestMapping(value = "/doDelete/{gjid}", method = RequestMethod.POST)
    @ResponseBody
    public boolean doDelete(@PathVariable(value = "gjid", required = true) String gjid) throws Exception {
        // 查询该单位是否存在
        RsglBGwdjEntity gjEntity = iGjService.getGjInfoById(gjid);
        if (null == gjEntity) {
            logger.debug("传入参数错误，岗位等级信息为空...岗级id==={}", gjid);
            return false;
        }

        return iGjService.doDeleteGj(gjEntity);
    }

    /**
     * 查询所有职务列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getGjList", method = RequestMethod.POST)
    @ResponseBody
    public List<RsglBGwdjEntity> getGjList() throws Exception {
        return iGjService.getGjList();
    }
}
