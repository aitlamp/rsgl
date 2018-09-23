package com.atlp.rsgl.service.gzxx;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.entity.RsglBYhGzxxEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-19 14:38
 * @Decription:
 */
public interface IGzxxService {

    /**
     * 分页查询工作信息
     * @param page
     * @return
     * @throws Exception
     */
    public Page<RsglBYhGzxxEntity> getYhGzxxPage(PageModel page) throws Exception;

    /**
     * 查询用户工作信息
     * @param yhid
     * @return
     * @throws Exception
     */
    public RsglBYhGzxxEntity getYhGzxxByYhid(String yhid) throws Exception;

    /**
     * 查询所有用户工作信息
     * @return
     * @throws Exception
     */
    public List<RsglBYhGzxxEntity> getYhGzxxList() throws Exception;

    /**
     * 添加用户工作信息
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doSaveYhGzxx(RsglBYhGzxxEntity entity) throws Exception;

    /**
     * 修改用户工作信息
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doUpdateYhGzxx(RsglBYhGzxxEntity entity) throws Exception;


}
