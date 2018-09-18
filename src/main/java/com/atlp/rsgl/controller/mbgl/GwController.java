package com.atlp.rsgl.controller.mbgl;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBQypygwEntity;
import com.atlp.rsgl.service.gw.IGwService;
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
@RequestMapping(value = "/gw")
public class GwController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IGwService iGwService;

    /**
     * 分页查询全员聘用岗位
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getGwPage")
    @ResponseBody
    public Page<RsglBQypygwEntity> getGwPage(PageModel page) throws Exception {
        return iGwService.getGwPage(page);
    }

    /**
     * 岗位信息编辑UI
     * @param map
     * @param gwid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editUI/{gwid}")
    public String gwEditUI(ModelMap map, @PathVariable(name = "gwid") String gwid) throws Exception {
        RsglBQypygwEntity gwEntity = new RsglBQypygwEntity();

        if (!AtlpUtil.isEmpty(gwid)) {
            // 修改
            gwEntity = iGwService.getGwInfoById(gwid);
        }

        map.put("entity", gwEntity);
        return "/rsgl/mbgl/rysx/gw_edit";
    }

    /**
     * form保存方法
     * 添加或修改职务
     * @param gwEntity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    @ResponseBody
    public boolean doSave(RsglBQypygwEntity gwEntity, HttpServletRequest request) throws Exception {
        if (null == gwEntity) {
            logger.debug("参数异常,传入全员聘用岗位信息为空.岗位信息==={}", gwEntity.toString());
            // throw new Exception("参数异常,传入状态信息为空.");
            return false;
        }

        if (AtlpUtil.isEmpty(gwEntity.getQypygwid())) {
            // 添加
            RsglBQypygwEntity saveEntity = new RsglBQypygwEntity();
            BeanUtils.copyProperties(gwEntity, saveEntity, AtlpUtil.getNullPropertyNames(gwEntity));
            AtlpUtil.setUserInfo(saveEntity, request);
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");

            iGwService.doSaveGw(saveEntity);
        } else {
            // 修改
            iGwService.doUpdateGw(gwEntity);
        }

        return true;
    }

    /**
     * 删除职务
     * @param gwid
     * @return
     */
    @RequestMapping(value = "/doDelete/{gwid}", method = RequestMethod.POST)
    @ResponseBody
    public boolean doDelete(@PathVariable(value = "gwid", required = true) String gwid) throws Exception {
        // 查询该单位是否存在
        RsglBQypygwEntity gwEntity = iGwService.getGwInfoById(gwid);
        if (null == gwEntity) {
            logger.debug("传入参数错误，全员聘用岗位信息为空...岗位id==={}", gwid);
            return false;
        }

        return iGwService.doDeleteGw(gwEntity);
    }

    /**
     * 查询所有职务列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getGwList", method = RequestMethod.POST)
    @ResponseBody
    public List<RsglBQypygwEntity> getGwList() throws Exception {
        return iGwService.getGwList();
    }
}
