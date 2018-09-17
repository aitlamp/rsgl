package com.atlp.rsgl.repository;

import com.atlp.rsgl.entity.RsglBQypygwEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-17 14:49
 * @Decription: 全员聘用岗位
 */
public interface GwRepository extends JpaRepository<RsglBQypygwEntity, Long> {

    /**
     * 主键查询职务信息
     * @param qypygwid
     * @return
     */
    public RsglBQypygwEntity findByQypygwid(String qypygwid);

}
