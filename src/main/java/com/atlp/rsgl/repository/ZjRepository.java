package com.atlp.rsgl.repository;

import com.atlp.rsgl.entity.RsglBZjEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-17 14:49
 * @Decription:
 */
public interface ZjRepository extends JpaRepository<RsglBZjEntity, Long> {

    /**
     * 主键查询职级信息
     * @param zjid
     * @return
     */
    public RsglBZjEntity findByZjid(String zjid);

    /**
     * 事业编专用状态查询职级list
     * @param sybbs
     * @return
     */
    public List<RsglBZjEntity> findAllBySybzybs(String sybbs);

}
