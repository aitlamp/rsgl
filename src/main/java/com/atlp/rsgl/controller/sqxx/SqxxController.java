package com.atlp.rsgl.controller.sqxx;

import com.atlp.rsgl.common.base.BaseController;
import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBSqxxEntity;
import com.atlp.rsgl.service.sqxx.ISqxxService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-19 17:10
 * @Decription:
 */
@Controller
@RequestMapping(value = "/sqxx", method = {RequestMethod.GET, RequestMethod.POST})
public class SqxxController extends BaseController {

    @Autowired
    private ISqxxService iSqxxService;

    /**
     * 授权UI
     *
     * @return
     */
    @RequestMapping(value = "/listUI")
    public String sqxxListUI() {
        return "/rsgl/sqxx/sqxx_main";
    }

    /**
     * 授权table
     *
     * @param page
     * @param yhid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getSqxxPage", method = RequestMethod.GET)
    @ResponseBody
    public Page<RsglBSqxxEntity> getSqxxPage(PageModel page, @RequestParam String yhid) throws Exception {
        return iSqxxService.getSqxxPage(page, yhid);
    }

    /**
     * 授权
     *
     * @param sqxxEntity
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    @ResponseBody
    public boolean doSave(RsglBSqxxEntity sqxxEntity, HttpServletRequest request) throws Exception {
        if (null == sqxxEntity) {
            logger.debug("传入参数授权信息为空,授权信息==={}", sqxxEntity.toString());
            return false;
        }

        RsglBSqxxEntity saveEntity = new RsglBSqxxEntity();
        BeanUtils.copyProperties(sqxxEntity, saveEntity, AtlpUtil.getNullPropertyNames(sqxxEntity));
        AtlpUtil.setUserInfo(saveEntity, request);
        saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
        saveEntity.setLasttime(new Timestamp(new Date().getTime()));
        saveEntity.setDqzt("有效");

        return iSqxxService.doSaveSqxx(saveEntity);
    }

    /**
     * 删除授权
     *
     * @param sqid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doDelete/{sqid}", method = RequestMethod.POST)
    @ResponseBody
    public boolean doDelete(@PathVariable(name = "sqid") String sqid) throws Exception {
        // 查询原授权信息是否存在
        RsglBSqxxEntity sqxxEntity = iSqxxService.getSqxxInfoById(sqid);
        if (null == sqxxEntity) {
            logger.debug("查询授权信息失败,授权id==={}", sqid);
            return false;
        }

        return iSqxxService.doDeleteSqxx(sqxxEntity);
    }
}
