package com.atlp.rsgl.controller.rsda;

import com.atlp.rsgl.common.base.BaseController;
import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.entity.RsglBYhEntity;
import com.atlp.rsgl.entity.RsglBYhGzxxEntity;
import com.atlp.rsgl.service.gzxx.IGzxxService;
import com.atlp.rsgl.service.yh.IYhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-19 14:29
 * @Decription:
 */
@Controller
@RequestMapping(value = "/zzbd", method = {RequestMethod.GET, RequestMethod.POST})
public class ZzbdController extends BaseController {

    @Autowired
    private IYhService iYhService;
    @Autowired
    private IGzxxService iGzxxService;

    /**
     * 在职变动page
     *
     * @return
     */
    @RequestMapping(value = "/listUI")
    public String zzbdListUI() {
        return "rsgl/rsda/rybd/zzbd_main";
    }

    /**
     * 用户pagelist
     *
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getYhPage", method = RequestMethod.GET)
    @ResponseBody
    public Page<RsglBYhEntity> getYhPage(PageModel page) throws Exception {
        return iYhService.getYhPage(page);
    }

    /**
     * 在职变动编辑ui
     *
     * @param map
     * @param yhid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editUI/{yhid}")
    public String zzbdEditUI(ModelMap map, @PathVariable(name = "yhid") String yhid) throws Exception {
        return "";
    }

    /**
     * 更在在职变动
     *
     * @param gzxxEntity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
    @ResponseBody
    public boolean doUpdate(RsglBYhGzxxEntity gzxxEntity) throws Exception {
        if (null == gzxxEntity) {
            logger.debug("传入用户工作信息为空,工作信息==={}", gzxxEntity.toString());
            return false;
        }
        return iGzxxService.doUpdateYhGzxx(gzxxEntity);
    }

}
