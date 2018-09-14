package com.atlp.rsgl.controller.cd;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBCdEntity;
import com.atlp.rsgl.service.cd.ICdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    @RequestMapping({"/edit/{cdid}"})
    public String edit(@PathVariable String cdid, ModelMap map) {
        RsglBCdEntity cdEntity = new RsglBCdEntity();
        if (!AtlpUtil.isEmpty(cdid)) {
            cdEntity = cdService.findByCdid(cdid);
        }
        map.addAttribute("cdEntity", cdEntity);
        return "/rsgl/cdwh/cdwh_edit";
    }

    /*
     * 分页
     */
    @RequestMapping("/getPage")
    @ResponseBody
    public Page getPage(PageModel page) {
        return cdService.getPage(page);
    }

    /*
     * 树
     */
    @RequestMapping("/getCdTree")
    @ResponseBody
    public List getCdTree() {
        return cdService.getMenus("root");
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
