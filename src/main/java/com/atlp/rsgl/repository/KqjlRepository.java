package com.atlp.rsgl.repository;

import com.atlp.rsgl.entity.RsglBKqjlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-18 17:39
 * @Decription:
 */
public interface KqjlRepository extends JpaRepository<RsglBKqjlEntity, Long> {

    /**
     * 主键id查询考勤记录
     * @param kqjlid
     * @return
     */
    public RsglBKqjlEntity findByKqjlid(String kqjlid);

    /**
     * 查询用户的所有考勤记录
     * @param kqyhid
     * @return
     */
    public List<RsglBKqjlEntity> findAllByKqyhid(String kqyhid);

}
