package com.atlp.rsgl.service.gj;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.entity.RsglBGwdjEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-17 14:45
 * @Decription: 全员聘用岗位service
 */
public interface IGjService {

    /**
     * 分页查询职务list
     * @param page
     * @return
     * @throws Exception
     */
    public Page<RsglBGwdjEntity> getGjPage(PageModel page) throws Exception;

    /**
     * 主键查询职务信息
     * @param key
     * @return
     * @throws Exception
     */
    public RsglBGwdjEntity getGjInfoById(String key) throws Exception;

    /**
     * 查询所有职务list
     * @return
     * @throws Exception
     */
    public List<RsglBGwdjEntity> getGjList() throws Exception;

    /**
     * 添加职务
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doSaveGj(RsglBGwdjEntity entity) throws Exception;

    /**
     * 编辑职务
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doUpdateGj(RsglBGwdjEntity entity) throws Exception;

    /**
     * 删除职务
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doDeleteGj(RsglBGwdjEntity entity) throws Exception;
}
