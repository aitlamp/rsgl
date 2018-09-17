package com.atlp.rsgl.service.gw;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.entity.RsglBQypygwEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-17 14:45
 * @Decription: 全员聘用岗位service
 */
public interface IGwService {

    /**
     * 分页查询职务list
     * @param page
     * @return
     * @throws Exception
     */
    public Page<RsglBQypygwEntity> getGwPage(PageModel page) throws Exception;

    /**
     * 主键查询职务信息
     * @param key
     * @return
     * @throws Exception
     */
    public RsglBQypygwEntity getGwInfoById(String key) throws Exception;

    /**
     * 查询所有职务list
     * @return
     * @throws Exception
     */
    public List<RsglBQypygwEntity> getGwList() throws Exception;

    /**
     * 添加职务
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doSaveGw(RsglBQypygwEntity entity) throws Exception;

    /**
     * 编辑职务
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doUpdateGw(RsglBQypygwEntity entity) throws Exception;

    /**
     * 删除职务
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doDeleteGw(RsglBQypygwEntity entity) throws Exception;
}
