package com.atlp.rsgl.controller.mbgl;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBRylbEntity;
import com.atlp.rsgl.service.rylb.IRylbService;
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
import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-17 10:06
 * @Decription:
 */
@Controller
@RequestMapping(value = "/rylb")
public class RylbController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IRylbService iRylbService;

    /**
     * 人员类别维护listUI
     * @return
     */
    @RequestMapping(value = "/listUI")
    public String rylbListUI() {
        return "/rsgl/mbgl/rylb/rylb_main";
    }

    /**
     * 分页查询人员类别
     * @param page
     * @param plbid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getRylbPage", method = RequestMethod.GET)
    @ResponseBody
    public Page getPage(PageModel page, @RequestParam String plbid) throws Exception {
        return iRylbService.getRylbPage(page, plbid);
    }

    /**
     * 人员类别信息维护UI
     * @param map
     * @param plbid
     * @param lbid
     * @return
     */
    @RequestMapping(value = "/editUI/{plbid}/{lbid}")
    public String rylbEditUI(ModelMap map, @PathVariable(name = "plbid") String plbid,
                             @PathVariable(name = "lbid") String lbid) throws Exception {
        RsglBRylbEntity rylbEntity = new RsglBRylbEntity();

        if (!AtlpUtil.isEmpty(lbid)) {
            // 类别id不为空，修改类别UI
            rylbEntity = iRylbService.getRylbInfoById(lbid);
            // 如果查询单位父级id不等于传入id，参数错误
            if (!plbid.equals(rylbEntity.getPlbid())) {
                logger.debug("单位修改传入pdwid错误,pdwid==={},查询单位信息==={}", plbid, rylbEntity.toString());
                throw new Exception("参数错误.");
            }
        } else {
            // 添加，判断plbid
            if ("root".equals(plbid)) {
                rylbEntity.setPlbmc("根");
            } else {
                RsglBRylbEntity pRylbEntity = iRylbService.getRylbInfoById(plbid);
                rylbEntity.setPlbmc(pRylbEntity.getLbmc());
            }
        }

        map.put("plbid", plbid);
        map.put("entity", rylbEntity);
        return "/rsgl/mbgl/rylb/rylb_edit";
    }

    /**
     * form保存
     * 添加或修改人员类别
     * @param rylbEntity
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSave")
    @ResponseBody
    public boolean doSave(RsglBRylbEntity rylbEntity, HttpServletRequest request) throws Exception {
        if (null == rylbEntity) {
            logger.debug("参数异常,传入人员类别为空.人员类别信息==={}", rylbEntity.toString());
            // throw new Exception("参数异常,传入人员类别为空.");
            return false;
        }

        if (AtlpUtil.isEmpty(rylbEntity.getLbid())) {
            // 添加
            RsglBRylbEntity saveEntity = new RsglBRylbEntity();
            BeanUtils.copyProperties(rylbEntity, saveEntity, AtlpUtil.getNullPropertyNames(rylbEntity));
            AtlpUtil.setUserInfo(saveEntity, request);
            // saveEntity.setPdwid("root");
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");

            iRylbService.doSaveRylb(saveEntity);
        } else {
            // 修改
            iRylbService.doUpdateRylb(rylbEntity);
        }

        return true;
    }

    /**
     * 删除类别
     * @param lbid
     * @return
     */
    @RequestMapping(value = "/doDelete/{lbid}")
    @ResponseBody
    public boolean doDelete(@PathVariable(value = "lbid", required = true) String lbid) throws Exception {
        // 查询该单位是否存在
        RsglBRylbEntity rylbEntity = iRylbService.getRylbInfoById(lbid);
        if (null == rylbEntity) {
            logger.debug("传入参数错误，类别信息为空...类别id==={}", lbid);
            return false;
        }

        return iRylbService.doDeleteRylb(rylbEntity);
    }

    /**
     * 父级id查询人员类别
     * @return
     */
    @RequestMapping(value = "/getRylbListByPid/{plbid}", method = RequestMethod.POST)
    @ResponseBody
    public List<RsglBRylbEntity> getRylbListByPid(@PathVariable(value = "plbid", required = true) String plbid)
        throws Exception {
        return iRylbService.getRylbListByPid(plbid);
    }

    /**
     * 查询人员类别树--已作废
     * @return
     */
    @RequestMapping(value = "/getRylbTree/{plbid}", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> getRylbTree(@PathVariable(value = "plbid", required = true) String plbid)
            throws Exception {
        return iRylbService.getRylbMapListByPid(plbid);
    }

    /**
     * 查询人员类别树
     * @return
     */
    @RequestMapping("/getRylbTree")
    @ResponseBody
    public List<RsglBRylbEntity> getRylbTree() throws Exception {
        return iRylbService.getRylbList();
    }

    /**
     * 选择单位UI
     * @return
     */
    @RequestMapping(value = "/selectUI")
    public String selectRylb() {
        return "/rsgl/mbgl/rylb/rylb_select";
    }
}
