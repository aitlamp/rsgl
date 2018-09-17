package com.atlp.rsgl.repository;

import com.atlp.rsgl.entity.RsglBZjEntity;
import org.springframework.data.jpa.repository.JpaRepository;

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

}
