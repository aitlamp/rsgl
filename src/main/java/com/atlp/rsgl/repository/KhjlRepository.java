package com.atlp.rsgl.repository;

import com.atlp.rsgl.entity.RsglBKhjlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-19 12:18
 * @Decription:
 */
public interface KhjlRepository extends JpaRepository<RsglBKhjlEntity, Long> {

    /**
     * 主键查询考核记录
     * @param khjlid
     * @return
     */
    public RsglBKhjlEntity findByKhjlid(String khjlid);

    /**
     * 查询用户考核记录
     * @param khyhid
     * @return
     */
    public List<RsglBKhjlEntity> findAllByKhyhid(String khyhid);

}
