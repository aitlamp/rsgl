package com.atlp.rsgl.repository;

import com.atlp.rsgl.entity.RsglBYhGzxxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-18 16:26
 * @Decription:
 */
public interface YhGzxxRepository extends JpaRepository<RsglBYhGzxxEntity, Long> {

    /**
     * 用户id查询工作信息
     * @param yid
     * @return
     */
    public RsglBYhGzxxEntity findByYid(String yid);

}
