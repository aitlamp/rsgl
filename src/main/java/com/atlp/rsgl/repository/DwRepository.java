package com.atlp.rsgl.repository;

import com.atlp.rsgl.entity.RsglBDwEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-14 11:49
 * @Decription:
 */
public interface DwRepository extends JpaRepository<RsglBDwEntity, Long> {

    /**
     * 通过父级id查询子级单位
     * @param pdwid
     * @param pageable
     * @return
     * @throws Exception
     */
    public Page<RsglBDwEntity> findAllByPdwid(Pageable pageable, String pdwid) throws Exception;

    /**
     * 通过主键查询单位信息
     * @param dwid
     * @return
     * @throws Exception
     */
    public RsglBDwEntity findByDwid(String dwid) throws Exception;

    /**
     * 通过上级单位id查询下级单位
     * @param pid
     * @return
     * @throws Exception
     */
    public List<RsglBDwEntity> findByPdwid(String pid, Sort sort) throws Exception;



}
