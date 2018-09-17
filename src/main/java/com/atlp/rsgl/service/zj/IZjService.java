package com.atlp.rsgl.service.zj;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.entity.RsglBZjEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-17 14:45
 * @Decription:
 */
public interface IZjService {

    /**
     * 分页查询职级list
     * @param page
     * @return
     * @throws Exception
     */
    public Page<RsglBZjEntity> getZjPage(PageModel page) throws Exception;

    /**
     * 主键查询职级信息
     * @param key
     * @return
     * @throws Exception
     */
    public RsglBZjEntity getZjInfoById(String key) throws Exception;

    /**
     * 查询所有职级list
     * @return
     * @throws Exception
     */
    public List<RsglBZjEntity> getZjList() throws Exception;

    /**
     * 添加职级
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doSaveZj(RsglBZjEntity entity) throws Exception;

    /**
     * 编辑职级
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doUpdateZj(RsglBZjEntity entity) throws Exception;

    /**
     * 删除职级
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doDeleteZj(RsglBZjEntity entity) throws Exception;
}
