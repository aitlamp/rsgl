package com.atlp.rsgl.service.cd;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBCdEntity;
import com.atlp.rsgl.repository.CdRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

/**
 * 菜单 Service
 *
 * @author ctc
 * @date 2018年8月9日 15:53:49
 */
@Service
@Transactional
public class CdServiceImpl implements ICdService {
    @Autowired
    private CdRepository cdRepository;

    /**
     * 获取分页数据
     */
    public Page<RsglBCdEntity> getPage(PageModel page, Map pmap) {
        //return cdRepository.findAll(PageRequest.of(page.getPage(), page.getLimit()));
        String pcdid = pmap.get("pcdid").toString();
        return cdRepository.findByPcdid(pcdid, PageRequest.of(page.getPage(), page.getLimit()));
    }

    /**
     * 保存
     */
    public boolean doSave(RsglBCdEntity cdEntity, HttpServletRequest request) {
        boolean ret = false;
        try {
            RsglBCdEntity saveEntity = new RsglBCdEntity();
            if (AtlpUtil.isEmpty(cdEntity.getCdid())) {
                //新增
                BeanUtils.copyProperties(cdEntity, saveEntity, AtlpUtil.getNullPropertyNames(cdEntity));
                AtlpUtil.setUserInfo(saveEntity, request);
                saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
                saveEntity.setLasttime(new Timestamp(new Date().getTime()));
                //saveEntity.setPcdid("root");
                saveEntity.setDqzt("有效");
            } else {
                //修改
                saveEntity = cdRepository.findByCdid(cdEntity.getCdid());
                BeanUtils.copyProperties(cdEntity, saveEntity, AtlpUtil.getNullPropertyNames(cdEntity));
                saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            }
            cdRepository.save(saveEntity);
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return ret;
    }

    /**
     * 获取数据
     */
    public RsglBCdEntity findByCdid(String cdid) {
        return cdRepository.findByCdid(cdid);
    }

    /**
     * 删除
     */
    public boolean doDelete(String cdid) {
        boolean ret = false;
        try {
            cdRepository.delete(cdRepository.findByCdid(cdid));
            //cdRepository.delete(cdRepository.findByCdid(cdid + 1));
            if (cdid != null) {
                //throw new RuntimeException("haha");
                throw new Exception("haha");
            }
            //ret = true;
        } catch (Exception e) {
            //} catch (RuntimeException e) {
            e.printStackTrace();
            //throw new RuntimeException();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚，这样上层就无需去处理异常
        }
        return ret;
    }

    /**
     * 获取数据
     */
    public List findAll() {
        return cdRepository.findAll();
    }

    /**
     * 获取菜单数据
     */
    public List<Map> getMenus(String pcdid) {
        List<Map> menuList = new ArrayList<>();
        //根据上级菜单ID获取子菜单
        List<RsglBCdEntity> pCdList = cdRepository.findByPcdid(pcdid, new Sort(Sort.Direction.DESC, "xssx"));
        for (RsglBCdEntity pCd : pCdList) {
            Map menuMap = new HashMap();
            menuMap.put("id", pCd.getCdid());
            menuMap.put("pid", pCd.getPcdid());
            menuMap.put("text", pCd.getCdmc());
            if (pCd.getCdlx().equals("1")) {
                //单元
                List<Map> childList = this.getMenus(pCd.getCdid());
                menuMap.put("nodes", childList);
            } else {
                //功能
                menuMap.put("page", pCd.getPcpage());
                menuMap.put("nodes", null);
            }
            menuList.add(menuMap);
        }
        return menuList;
    }
}
