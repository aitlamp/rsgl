package com.atlp.rsgl.repository;

import com.atlp.rsgl.entity.RsglBZwEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-17 14:49
 * @Decription:
 */
public interface ZwRepository extends JpaRepository<RsglBZwEntity, Long> {

    /**
     * 主键查询职务信息
     * @param zwid
     * @return
     */
    public RsglBZwEntity findByZwid(String zwid);

}
