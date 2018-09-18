package com.atlp.rsgl.repository;

import com.atlp.rsgl.entity.RsglBGwdjDwEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-17 14:49
 * @Decription: 全员聘用岗位
 */
public interface GdRepository extends JpaRepository<RsglBGwdjDwEntity, Long> {

    /**
     * 分页查询岗位等级下的档位
     * @param pageable
     * @param gwdjid
     * @return
     */
    public Page<RsglBGwdjDwEntity> findAllByGwdjid(Pageable pageable, String gwdjid);

    /**
     * 查询岗位等级下的所有档位
     * @param gwdjid
     * @return
     */
    public List<RsglBGwdjDwEntity> findAllByGwdjid(String gwdjid);

    /**
     * 主键查询职务信息
     * @param dwid
     * @return
     */
    public RsglBGwdjDwEntity findByDwid(String dwid);

}
