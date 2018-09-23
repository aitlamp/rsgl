package com.atlp.rsgl.service.khjl;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.entity.RsglBKhjlEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-19 12:21
 * @Decription:
 */
public interface IKhjlService {

    /**
     * 分页查询考核记录
     * @param page
     * @return
     * @throws Exception
     */
    public Page<RsglBKhjlEntity> getKhPage(PageModel page) throws Exception;

    /**
     * 主键查询考核记录
     * @param key
     * @return
     * @throws Exception
     */
    public RsglBKhjlEntity getKhInfoById(String key) throws Exception;

    /**
     * 查询考核记录list
     * @return
     * @throws Exception
     */
    public List<RsglBKhjlEntity> getKhList() throws Exception;

    /**
     * 查询用户考核记录
     * @param yhid
     * @return
     * @throws Exception
     */
    public List<RsglBKhjlEntity> getKhListByYhid(String yhid) throws Exception;

    /**
     * 添加考核记录
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doSaveKh(RsglBKhjlEntity entity) throws Exception;

    /**
     * 修改考核记录
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doUpdateKh(RsglBKhjlEntity entity) throws Exception;

    /**
     * 删除考核记录
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doDeleteKh(RsglBKhjlEntity entity) throws Exception;

}
