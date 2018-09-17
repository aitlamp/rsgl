package com.atlp.rsgl.service.dw;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.entity.RsglBDwEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-14 11:53
 * @Decription:
 */
public interface IDwService {

    /**
     * 获取分页单位信息
     * @param page
     * @return
     */
    public Page<RsglBDwEntity> getDwPage(PageModel page, String pdwid) throws Exception;

    /**
     * 通过主键查询单位信息
     * @param key
     * @return
     * @throws Exception
     */
    public RsglBDwEntity getDwInfoById(String key) throws Exception;

    /**
     * 通过上级单位id查询下级单位
     * @param pid
     * @return
     * @throws Exception
     */
    public List<RsglBDwEntity> getDwListByPid(String pid) throws Exception;

    /**
     * 通过上级单位id查询下级单位
     * 单位树
     * @param pid
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getDwMapListByPid(String pid) throws Exception;

    /**
     * 查询所有的单位信息
     * @return
     * @throws Exception
     */
    public List<RsglBDwEntity> getDwList() throws Exception;

    /**
     * 新增单位信息
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doSaveDw(RsglBDwEntity entity) throws Exception;

    /**
     * 编辑修改单位信息
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doUpdateDw(RsglBDwEntity entity) throws Exception;

    /**
     * 删除单位信息
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean doDeleteDw(RsglBDwEntity entity) throws Exception;
}
