package com.atlp.rsgl.service.gd;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.entity.RsglBGwdjDwEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-17 14:45
 * @Decription: 全员聘用岗位service
 */
public interface IGdService {

    /**
     * 分页查询职务list
     * @param page
     * @return
     * @throws Exception
     */
    public Page<RsglBGwdjDwEntity> getGdPage(PageModel page, String gwdjid) throws Exception;

    /**
     * 主键查询职务信息
     * @param key
     * @return
     * @throws Exception
     */
    public RsglBGwdjDwEntity getGdInfoById(String key) throws Exception;

    /**
     * 查询所有职务list
     * @return
     * @throws Exception
     */
    public List<RsglBGwdjDwEntity> getGdList() throws Exception;

    /**
     * 查询所有职务list
     * @return
     * @throws Exception
     */
    public List<RsglBGwdjDwEntity> getGdListByGjid(String gjid) throws Exception;

    /**
     * 添加职务
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doSaveGd(RsglBGwdjDwEntity entity) throws Exception;

    /**
     * 编辑职务
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doUpdateGd(RsglBGwdjDwEntity entity) throws Exception;

    /**
     * 删除职务
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doDeleteGd(RsglBGwdjDwEntity entity) throws Exception;
}
