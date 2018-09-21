package com.atlp.rsgl.repository;

import com.atlp.rsgl.entity.RsglBYhEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户 Repository
 *
 * @author ctc
 * @date 2018年8月15日 20:34:43
 */
public interface YhRepository extends JpaRepository<RsglBYhEntity, Long> {

    /**
     * 登录id查询用户信息
     * @param dlid
     * @return
     */
    RsglBYhEntity findByDlid(String dlid);

    /**
     * 主键id查询用户信息
     * @param yhid
     * @return
     */
    public RsglBYhEntity findByYhid(String yhid);

    /**
     * 分页查询单位用户
     * @param dwid
     * @return
     */
    public Page<RsglBYhEntity> findAllByDwid(Pageable pageable, String dwid);
}
