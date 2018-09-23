package com.atlp.rsgl.repository;

import com.atlp.rsgl.entity.RsglBPxjlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-19 10:55
 * @Decription:
 */
public interface PxjlRepository extends JpaRepository<RsglBPxjlEntity, Long> {

    /**
     * 主键id查询培训记录
     * @return
     */
    public RsglBPxjlEntity findByPxjlid(String pxjlid);

    /**
     * 查询用户所有培训记录
     * @param pxyhid
     * @return
     */
    public List<RsglBPxjlEntity> findAllByPxyhid(String pxyhid);

}
