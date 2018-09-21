package com.atlp.rsgl.repository;

import com.atlp.rsgl.entity.RsglBSqxxEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-19 16:54
 * @Decription:
 */
public interface SqxxRepository extends JpaRepository<RsglBSqxxEntity, Long> {

    /**
     * 查询用户已授权信息
     * @param sqyhid
     * @return
     */
    public Page<RsglBSqxxEntity> findAllBySqyhid(Pageable pageable, String sqyhid);

    /**
     * 主键查询授权信息
     * @param sqid
     * @return
     */
    public RsglBSqxxEntity findBySqid(String sqid);

}
