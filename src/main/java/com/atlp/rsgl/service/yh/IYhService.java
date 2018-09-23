package com.atlp.rsgl.service.yh;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.entity.RsglBYhEntity;
import com.atlp.rsgl.entity.RsglBYhGzxxEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-18 11:10
 * @Decription:
 */
public interface IYhService {

    /**
     * 分页查询用户信息
     * @param page
     * @return
     * @throws Exception
     */
    public Page<RsglBYhEntity> getYhPage(PageModel page) throws Exception;

    /**
     * 分页查询用户信息
     * @param page
     * @return
     * @throws Exception
     */
    public Page<RsglBYhEntity> getYhPageByDwid(PageModel page, String dwid) throws Exception;

    /**
     * 主键查询用户信息
     * @param key
     * @return
     * @throws Exception
     */
    public RsglBYhEntity getYhInfoById(String key) throws Exception;

    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    public List<RsglBYhEntity> getYhList() throws Exception;

    /**
     * 新增用户
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doSaveYh(RsglBYhEntity entity, RsglBYhGzxxEntity gzxxEntity) throws Exception;

    /**
     * 修改用户信息
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doUpdateYh(RsglBYhEntity entity, RsglBYhGzxxEntity gzxxEntity) throws Exception;

    /**
     * 删除用户信息
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doDeleteYh(RsglBYhEntity entity) throws Exception;
}
