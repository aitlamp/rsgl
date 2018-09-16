package com.atlp.rsgl.repository;

import com.atlp.rsgl.entity.RsglBCdEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 菜单 Repository
 *
 * @author ctc
 * @date 2018年8月9日 15:39:46
 */
public interface CdRepository extends JpaRepository<RsglBCdEntity, Long> {
    RsglBCdEntity findByCdid(String cdid);

    List<RsglBCdEntity> findByPcdid(String pcdid, Sort sort);

    Page<RsglBCdEntity> findByPcdid(String pcdid, Pageable pageable);

    int countByPcdid(String pcdid);

//    List<RsglBCdEntity> findByCdLx(String cdlx, Sort sort);

//    @Query(value = "select new pers.zhuch.model.MyModel(u.userName, ui.name, ui.gender, ui.description) from UserInfo ui, User u where u.id = ui.userId")
//    List<RsglBCdEntity> getAllYwdy();
}
