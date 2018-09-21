package com.atlp.rsgl.service.sqxx;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.entity.RsglBSqxxEntity;
import org.springframework.data.domain.Page;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-19 17:01
 * @Decription:
 */
public interface ISqxxService {

    /**
     * 查询用户授权信息
     * @param page
     * @return
     * @throws Exception
     */
    public Page<RsglBSqxxEntity> getSqxxPage(PageModel page, String yhid) throws Exception;

    /**
     * 主键查询授权信息
     * @param key
     * @return
     * @throws Exception
     */
    public RsglBSqxxEntity getSqxxInfoById(String key) throws Exception;

    /**
     * 用户授权
     * @return
     * @throws Exception
     */
    public boolean doSaveSqxx(RsglBSqxxEntity entity) throws Exception;

    /**
     * 删除授权
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doDeleteSqxx(RsglBSqxxEntity entity) throws Exception;


}
