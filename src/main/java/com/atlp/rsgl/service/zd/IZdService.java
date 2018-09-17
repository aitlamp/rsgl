package com.atlp.rsgl.service.zd;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.entity.RsglBZdEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-17 14:45
 * @Decription:
 */
public interface IZdService {

    /**
     * 分页查询职务list
     * @param page
     * @return
     * @thros Exception
     */
    public Page<RsglBZdEntity> getZdPage(PageModel page, String zjid) throws Exception;

    /**
     * 主键查询职务信息
     * @param key
     * @return
     * @throws Exception
     */
    public RsglBZdEntity getZdInfoById(String key) throws Exception;

    /**
     * 查询所有职务list
     * @return
     * @throws Exception
     */
    public List<RsglBZdEntity> getZdList() throws Exception;

    /**
     * 添加职务
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doSaveZd(RsglBZdEntity entity) throws Exception;

    /**
     * 编辑职务
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doUpdateZd(RsglBZdEntity entity) throws Exception;

    /**
     * 删除职务
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doDeleteZd(RsglBZdEntity entity) throws Exception;
}
