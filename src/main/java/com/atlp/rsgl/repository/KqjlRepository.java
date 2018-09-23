package com.atlp.rsgl.repository;

import com.atlp.rsgl.common.base.BaseRepository;
import com.atlp.rsgl.entity.RsglBKqjlEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-18 17:39
 * @Decription:
 */
public interface KqjlRepository extends BaseRepository<RsglBKqjlEntity, Long> {

    /**
     * 主键id查询考勤记录
     *
     * @param kqjlid
     * @return
     */
    public RsglBKqjlEntity findByKqjlid(String kqjlid);

    /**
     * 查询用户的所有考勤记录
     *
     * @param kqyhid
     * @return
     */
    public List<RsglBKqjlEntity> findAllByKqyhid(String kqyhid);

}
