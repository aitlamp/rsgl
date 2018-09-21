package com.atlp.rsgl.controller.cd;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBCdEntity;
import com.atlp.rsgl.service.cd.ICdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 菜单 Controller
 *
 * @author ctc
 * @date 2018年8月9日 15:48:09
 */
@Controller
@RequestMapping(value = "/cd")
public class CdController {
    @Autowired
    private ICdService cdService;

    /*
     * main页面
     */
    @RequestMapping({"/main"})
    public String cdwh_main() {
        return "/rsgl/cdwh/cdwh_main";
    }

    /*
     * edit页面
     */
    @RequestMapping({"/edit/{cdid}/{pcdid}"})
    public String edit(@PathVariable String cdid, @PathVariable String pcdid, ModelMap map) {
        RsglBCdEntity cdEntity = new RsglBCdEntity();
        if (!AtlpUtil.isEmpty(cdid)) {
            cdEntity = cdService.findByCdid(cdid);
        } else {
            RsglBCdEntity pCdEntity = cdService.findByCdid(pcdid);
            if (!AtlpUtil.isEmpty(pCdEntity)) {
                cdEntity.setPcdid(pCdEntity.getCdid());
                cdEntity.setPcdmc(pCdEntity.getCdmc());
            } else {
                cdEntity.setPcdid("root");
                cdEntity.setPcdmc("根");
            }
        }
        map.addAttribute("cdEntity", cdEntity);
        return "/rsgl/cdwh/cdwh_edit";
    }

    /*
     * select页面
     */
    @RequestMapping({"/select/{type}"})
    public String cdwh_select(ModelMap map, @PathVariable(name = "type", required = true) String type) {
        map.put("type", type);
        return "/rsgl/cdwh/cdwh_select";
    }

    /*
     * 分页
     */
    @RequestMapping("/getPage")
    @ResponseBody
    public Page getPage(PageModel page, @RequestParam Map pmap) {
        return cdService.getPage(page, pmap);
    }

    /*
     * 树
     */
    @RequestMapping("/getCdTree")
    @ResponseBody
    public List getCdTree() {
        //return cdService.getMenus("root");
        return cdService.findAll();
    }

    /*
     * 保存
     */
    @RequestMapping("doSave")
    @ResponseBody
    public boolean doSave(RsglBCdEntity cdEntity, HttpServletRequest request) {
        return cdService.doSave(cdEntity, request);
    }

    /*
     * 删除
     */
    @RequestMapping("doDelete")
    @ResponseBody
    public boolean doDelete(String cdid) {
        return cdService.doDelete(cdid);
    }
}
