package com.atlp.rsgl.repository;

import com.atlp.rsgl.entity.RsglBRylbEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-17 10:30
 * @Decription:
 */
public interface RylbRepository extends JpaRepository<RsglBRylbEntity, Long> {

    /**
     * 分页查询对应上级类别下的子级类别
     * @param pageable
     * @param plbid
     * @return
     */
    public Page<RsglBRylbEntity> findAllByPlbid(Pageable pageable, String plbid);

    /**
     * 通过主键id查询人员类别
     * @param lbid
     * @return
     */
    public RsglBRylbEntity findByLbid(String lbid);

    /**
     * 通过上级类别id查询下级类别
     * @param pid
     * @return
     * @throws Exception
     */
    public List<RsglBRylbEntity> findByPlbid(String pid, Sort sort);
}
