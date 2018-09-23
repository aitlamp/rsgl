package com.atlp.rsgl.controller.rsda;

import com.atlp.rsgl.controller.main.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-19 14:29
 * @Decription:
 */
@Controller
@RequestMapping(value = "/rydb", method = {RequestMethod.GET, RequestMethod.POST})
public class RybdController extends BaseController {

    /**
     * 人员变动page
     * @return
     */
    @RequestMapping(value = "/listUI")
    public String rybdListUI() {
        return "";
    }

}
