package com.atlp.rsgl.controller.main;

import com.atlp.rsgl.service.cd.ICdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转控制类
 *
 * @author ctc
 * @date 2018年8月5日 15:38:42
 */

@Controller
public class MainController {
    @Autowired
    private ICdService cdService;

    //主界面
    @RequestMapping({"/main"})
    public String main() {
        return "/main/main";
    }

    //导航页面
    @RequestMapping({"/main/nav"})
    public String main_nav(ModelMap map) {
        map.addAttribute("cdList", cdService.getMenus("root"));
        return "/main/main_nav";
    }

    //首页
    @RequestMapping({"/main/content"})
    public String main_content() {
        return "/main/main_content";
    }
}
