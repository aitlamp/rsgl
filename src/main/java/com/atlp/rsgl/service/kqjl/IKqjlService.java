package com.atlp.rsgl.service.kqjl;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.entity.RsglBKqjlEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-18 17:40
 * @Decription:
 */
public interface IKqjlService {

    /**
     * 分页查询考勤记录
     * @param page
     * @return
     * @throws Exception
     */
    public Page<RsglBKqjlEntity> getKqPage(PageModel page) throws Exception;

    /**
     * 主键查询考勤记录
     * @param key
     * @return
     * @throws Exception
     */
    public RsglBKqjlEntity getKqInfoById(String key) throws Exception;

    /**
     * 查询所有的考勤记录
     * @return
     * @throws Exception
     */
    public List<RsglBKqjlEntity> getKqList() throws Exception;

    /**
     * 查询用户的所有考勤记录
     * @param yhid
     * @return
     * @throws Exception
     */
    public List<RsglBKqjlEntity> getKqListByYhid(String yhid) throws Exception;

    /**
     * 考勤等级
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doSaveKqjl(RsglBKqjlEntity entity) throws Exception;

    /**
     * 编辑修改
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doUpdateKqjl(RsglBKqjlEntity entity) throws Exception;

    /**
     * 删除
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doDeleteKqjl(RsglBKqjlEntity entity) throws Exception;

}
