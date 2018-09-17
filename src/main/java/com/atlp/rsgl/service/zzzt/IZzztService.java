package com.atlp.rsgl.service.zzzt;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.entity.RsglBZzztEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-17 13:55
 * @Decription:
 */
public interface IZzztService {

    /**
     * 分页查询状态在职
     * @param page
     * @return
     * @throws Exception
     */
    public Page<RsglBZzztEntity> getZzztPage(PageModel page) throws Exception;

    /**
     * 查询所有的在职状态list
     * @return
     * @throws Exception
     */
    public List<RsglBZzztEntity> getZzztList() throws Exception;

    /**
     * 主键查询状态
     * @param key
     * @return
     * @throws Exception
     */
    public RsglBZzztEntity getZzztInfoById(String key) throws Exception;

    /**
     * 新增状态
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doSaveZzzt(RsglBZzztEntity entity) throws Exception;

    /**
     * 修改状态
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doUpdateZzzt(RsglBZzztEntity entity) throws Exception;

    /**
     * 删除状态
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doDeleteZzzt(RsglBZzztEntity entity) throws Exception;
}
