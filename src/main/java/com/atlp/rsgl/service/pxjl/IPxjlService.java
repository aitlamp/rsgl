package com.atlp.rsgl.service.pxjl;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.entity.RsglBPxjlEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-19 10:58
 * @Decription:
 */
public interface IPxjlService {

    /**
     * 分页查询培训记录
     * @param page
     * @return
     * @throws Exception
     */
    public Page<RsglBPxjlEntity> getPxPage(PageModel page) throws Exception;

    /**
     * 主键查询培训记录
     * @param key
     * @return
     * @throws Exception
     */
    public RsglBPxjlEntity getPxInfoById(String key) throws Exception;

    /**
     * 查询所有的培训记录
     * @return
     * @throws Exception
     */
    public List<RsglBPxjlEntity> getPxList() throws Exception;

    /**
     * 查询用户的所有培训记录
     * @param yhid
     * @return
     * @throws Exception
     */
    public List<RsglBPxjlEntity> getPxListByYhid(String yhid) throws Exception;

    /**
     * 添加培训记录
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doSavePx(RsglBPxjlEntity entity) throws Exception;

    /**
     * 修改培训记录
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doUpdatePx(RsglBPxjlEntity entity) throws Exception;

    /**
     * 删除培训记录
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doDeletePx(RsglBPxjlEntity entity) throws Exception;

}
