package com.atlp.rsgl.service.rylb;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.entity.RsglBRylbEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-17 10:23
 * @Decription:
 */
public interface IRylbService {

    /**
     * 获取分页人员类别信息
     * @param page
     * @return
     */
    public Page<RsglBRylbEntity> getRylbPage(PageModel page, String plbid) throws Exception;

    /**
     * 通过主键查询人员类别信息
     * @param key
     * @return
     * @throws Exception
     */
    public RsglBRylbEntity getRylbInfoById(String key) throws Exception;

    /**
     * 通过上级类别id查询类别列表
     * @param pid
     * @return
     * @throws Exception
     */
    public List<RsglBRylbEntity> getRylbListByPid(String pid) throws Exception;

    /**
     * 通过上级类别id查询类别列表
     * 类别树
     * @param pid
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getRylbMapListByPid(String pid) throws Exception;

    /**
     * 查询所有人员类别list
     * @return
     * @throws Exception
     */
    public List<RsglBRylbEntity> getRylbList() throws Exception;

    /**
     * 新增人员类别信息
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doSaveRylb(RsglBRylbEntity entity) throws Exception;

    /**
     * 编辑修改人员类别信息
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doUpdateRylb(RsglBRylbEntity entity) throws Exception;

    /**
     * 删除类别信息
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doDeleteRylb(RsglBRylbEntity entity) throws Exception;
}
