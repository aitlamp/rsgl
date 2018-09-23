package com.atlp.rsgl.controller.kqgl;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.common.base.BaseController;
import com.atlp.rsgl.entity.RsglBKqjlEntity;
import com.atlp.rsgl.service.kqjl.IKqjlService;
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
 * @CreateTime: 2018-09-18 17:51
 * @Decription:
 */
@Controller
@RequestMapping(value = "/kqwh")
public class KqwhController extends BaseController {

    @Autowired
    private IKqjlService iKqjlService;

    /**
     * 考勤记录ListUI
     *
     * @return
     */
    @RequestMapping(value = "/listUI")
    public String kqjlListUI() {
        return "/rsgl/kqgl/kqdj/kqwh_main";
    }

    /**
     * 分页查询考勤信息
     *
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getKqPage", method = RequestMethod.GET)
    @ResponseBody
    public Page getKqPage(PageModel page) throws Exception {
        return iKqjlService.getKqPage(page);
    }

    /**
     * 考勤记录编辑UI
     *
     * @return
     */
    @RequestMapping(value = "/editUI/{kqid}")
    public String kqjiEditUI(ModelMap map, @PathVariable(name = "kqid") String kqid) throws Exception {
        RsglBKqjlEntity kqjlEntity = new RsglBKqjlEntity();

        if (!AtlpUtil.isEmpty(kqid)) {
            kqjlEntity = iKqjlService.getKqInfoById(kqid);
        }

        map.put("entity", kqjlEntity);
        return "/rsgl/kqgl/kqdj/kqwh_edit";
    }

    /**
     * form保存
     * 添加或修改考勤记录
     *
     * @param kqjlEntity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    @ResponseBody
    public boolean doSave(RsglBKqjlEntity kqjlEntity, HttpServletRequest request) throws Exception {
        if (null == kqjlEntity) {
            logger.debug("传入参数错误,考勤记录为空.考勤信息==={}", kqjlEntity.toString());
            return false;
        }

        if (AtlpUtil.isEmpty(kqjlEntity.getKqjlid())) {
            // 添加
            RsglBKqjlEntity saveEntity = new RsglBKqjlEntity();
            BeanUtils.copyProperties(kqjlEntity, saveEntity, AtlpUtil.getNullPropertyNames(kqjlEntity));
            AtlpUtil.setUserInfo(saveEntity, request);
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");

            iKqjlService.doSaveKqjl(saveEntity);
        } else {
            iKqjlService.doUpdateKqjl(kqjlEntity);
        }

        return true;
    }

    /**
     * 删除考勤记录
     *
     * @param kqid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doDelete/{kqid}", method = RequestMethod.POST)
    @ResponseBody
    public boolean doDelete(@PathVariable(name = "kqid") String kqid) throws Exception {
        // 查询该记录是否存在
        RsglBKqjlEntity kqjlEntity = iKqjlService.getKqInfoById(kqid);
        if (null == kqjlEntity) {
            logger.debug("查询考勤记录失败,考勤id==={}", kqid);
            return false;
        }

        return iKqjlService.doDeleteKqjl(kqjlEntity);
    }
}
