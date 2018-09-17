package com.atlp.rsgl.repository;

import com.atlp.rsgl.entity.RsglBZzztEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-17 13:50
 * @Decription:
 */
public interface ZzztRepository extends JpaRepository<RsglBZzztEntity, Long> {

    /**
     * 主键查询状态信息
     * @param ztid
     * @return
     */
    public RsglBZzztEntity findByZtid(String ztid);

}
