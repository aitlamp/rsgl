package com.atlp.rsgl.service.cd;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.entity.RsglBCdEntity;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 菜单 Service 接口
 *
 * @author ctc
 * @date 2018年8月9日 15:53:49
 */
public interface ICdService {
    Page<RsglBCdEntity> getPage(PageModel page, Map pmap);

    boolean doSave(RsglBCdEntity cdEntity, HttpServletRequest request);

    RsglBCdEntity findByCdid(String cdid);

    boolean doDelete(String cdid);

    List findAll();

    List getMenus(String pcdid);
}
