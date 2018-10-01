package com.atlp.rsgl.controller.dw;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBDwEntity;
import com.atlp.rsgl.service.dw.IDwService;
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
 * @CreateTime: 2018-09-14 13:59
 * @Decription:
 */
@Controller
@RequestMapping(value = "/dw")
public class DwController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IDwService iDwService;

    /**
     * 单位维护list
     *
     * @return
     */
    @RequestMapping(value = "/listUI")
    public String dwListUI() {
        return "rsgl/dwwh/dwwh_main";
    }

    /**
     * 获取单位分页数据
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/getDwPage", method = RequestMethod.GET)
    @ResponseBody
    public Page getPage(PageModel page, @RequestParam String pdwid) throws Exception {
        return iDwService.getDwPage(page, pdwid);
    }

    /**
     * 单位信息编辑页面
     *
     * @param map
     * @param dwid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editUI/{pdwid}/{dwid}")
    public String dwSaveUI(ModelMap map, @PathVariable(name = "pdwid") String pdwid,
                           @PathVariable(name = "dwid") String dwid) throws Exception {
        RsglBDwEntity dwEntity = new RsglBDwEntity();

        if (!AtlpUtil.isEmpty(dwid)) {
            // 单位id不为空，修改单位信息
            dwEntity = iDwService.getDwInfoById(dwid);
            // 如果查询单位父级id不等于传入id，参数错误
            if (!pdwid.equals(dwEntity.getPdwid())) {
                logger.debug("单位修改传入pdwid错误,pdwid==={},查询单位信息==={}", pdwid, dwEntity.toString());
                throw new Exception("参数错误.");
            }
        } else {
            // 添加，判断plbid
            if ("root".equals(pdwid)) {
                dwEntity.setPdwmc("根");
            } else {
                RsglBDwEntity pDwEntity = iDwService.getDwInfoById(pdwid);
                dwEntity.setPdwmc(pDwEntity.getDwmc());
            }
        }

        map.put("pdwid", pdwid);
        map.put("entity", dwEntity);
        return "rsgl/dwwh/dwwh_edit";
    }

    /**
     * 添加或修改单位信息
     *
     * @param dwEntity
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSave")
    @ResponseBody
    public boolean doSave(RsglBDwEntity dwEntity, HttpServletRequest request) throws Exception {
        if (null == dwEntity) {
            logger.debug("传入参数错误，单位信息为空...单位信息==={}", dwEntity.toString());
            return false;
        }

        if (AtlpUtil.isEmpty(dwEntity.getDwid())) {
            // 添加
            RsglBDwEntity saveEntity = new RsglBDwEntity();
            BeanUtils.copyProperties(dwEntity, saveEntity, AtlpUtil.getNullPropertyNames(dwEntity));
            AtlpUtil.setUserInfo(saveEntity, request);
            // saveEntity.setPdwid("root");
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");

            iDwService.doSaveDw(saveEntity);
        } else {
            // 修改
            iDwService.doUpdateDw(dwEntity);
        }

        return true;
    }

    /**
     * 删除单位
     *
     * @param dwid
     * @return
     */
    @RequestMapping(value = "/doDelete/{dwid}")
    @ResponseBody
    public boolean doDelete(@PathVariable(value = "dwid", required = true) String dwid) throws Exception {
        // 查询该单位是否存在
        RsglBDwEntity dwEntity = iDwService.getDwInfoById(dwid);
        if (null == dwEntity) {
            logger.debug("传入参数错误，单位信息为空...单位id==={}", dwid);
            return false;
        }

        return iDwService.doDeleteDw(dwEntity);
    }

    /**
     * 查询单位树----废弃
     *
     * @return
     */
    @RequestMapping("/getDwTree/{pdwid}")
    @ResponseBody
    public List<Map<String, Object>> getDwTree(@PathVariable(value = "pdwid", required = true) String pdwid)
            throws Exception {
        return iDwService.getDwMapListByPid(pdwid);
    }

    /**
     * 查询单位树
     *
     * @return
     */
    @RequestMapping("/getDwTree")
    @ResponseBody
    public List<RsglBDwEntity> getDwTree() throws Exception {
        return iDwService.getDwList();
    }

    /**
     * 选择单位UI
     *
     * @return
     */
    @RequestMapping(value = "/selectUI")
    public String selectDw() {
        return "rsgl/dwwh/dwwh_select";
    }

}
