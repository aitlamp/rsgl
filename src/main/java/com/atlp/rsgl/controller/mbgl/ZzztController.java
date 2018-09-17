package com.atlp.rsgl.controller.mbgl;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBZzztEntity;
import com.atlp.rsgl.service.zzzt.IZzztService;
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
 * @CreateTime: 2018-09-17 13:43
 * @Decription: 在职状态controller
 */
@Controller
@RequestMapping(value = "/zzzt")
public class ZzztController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IZzztService iZzztService;

    /**
     * 分页查询在职状态UI
     * @return
     */
    @RequestMapping(value = "/listUI")
    public String zzztListUI() {
        return "/rsgl/mbgl/zzzt/zzzt_main";
    }

    /**
     * 分页查询人员类别
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getZzztPage", method = RequestMethod.GET)
    @ResponseBody
    public Page<RsglBZzztEntity> getZzztPage(PageModel page) throws Exception {
        return iZzztService.getZzztPage(page);
    }

    /**
     * 在职状态编辑UI
     * @return
     */
    @RequestMapping(value = "/editUI/{ztid}")
    public String zzztEditUI(ModelMap map, @PathVariable(name = "ztid") String ztid) throws Exception {
        RsglBZzztEntity zzztEntity = new RsglBZzztEntity();

        if (!AtlpUtil.isEmpty(ztid)) {
            // 修改状态信息
            zzztEntity = iZzztService.getZzztInfoById(ztid);
        }

        map.put("entity", zzztEntity);
        return "/rsgl/mbgl/zzzt/zzzt_edit";
    }

    /**
     * form保存方法
     * 添加或修改状态
     * @param zzztEntity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "doSave", method = RequestMethod.POST)
    @ResponseBody
    public boolean doSave(RsglBZzztEntity zzztEntity, HttpServletRequest request) throws Exception {
        if (null == zzztEntity) {
            logger.debug("参数异常,传入状态信息为空.状态信息信息==={}", zzztEntity.toString());
            // throw new Exception("参数异常,传入状态信息为空.");
            return false;
        }

        if (AtlpUtil.isEmpty(zzztEntity.getZtid())) {
            // 添加
            RsglBZzztEntity saveEntity = new RsglBZzztEntity();
            BeanUtils.copyProperties(zzztEntity, saveEntity, AtlpUtil.getNullPropertyNames(zzztEntity));
            AtlpUtil.setUserInfo(saveEntity, request);
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");

            iZzztService.doSaveZzzt(saveEntity);
        } else {
            // 修改
            iZzztService.doUpdateZzzt(zzztEntity);
        }

        return true;
    }

    /**
     * 删除状态
     * @param ztid
     * @return
     */
    @RequestMapping(value = "/doDelete/{ztid}", method = RequestMethod.POST)
    @ResponseBody
    public boolean doDelete(@PathVariable(value = "ztid", required = true) String ztid) throws Exception {
        // 查询该单位是否存在
        RsglBZzztEntity zzztEntity = iZzztService.getZzztInfoById(ztid);
        if (null == zzztEntity) {
            logger.debug("传入参数错误，状态信息为空...状态id==={}", ztid);
            return false;
        }

        return iZzztService.doDeleteZzzt(zzztEntity);
    }
}
