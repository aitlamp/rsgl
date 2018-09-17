package com.atlp.rsgl.repository;

import com.atlp.rsgl.entity.RsglBZdEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-17 14:49
 * @Decription:
 */
public interface ZdRepository extends JpaRepository<RsglBZdEntity, Long> {

    /**
     * 查询职级下的职等
     * @param pageable
     * @param zjid
     * @return
     */
    public Page<RsglBZdEntity> findAllByZjid(Pageable pageable, String zjid);

    /**
     * 主键查询职务信息
     * @param zdid
     * @return
     */
    public RsglBZdEntity findByZdid(String zdid);

}
