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
        return cdRepository.findByPcdid(pcdid, PageRequest.of(page.getPage(), page.getLimit(), Sort.by("xssx")));
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
                //saveEntity.setCdlx("2");//默认功能
                saveEntity.setDqzt("有效");
            } else {
                //修改
                saveEntity = cdRepository.findByCdid(cdEntity.getCdid());
                BeanUtils.copyProperties(cdEntity, saveEntity, AtlpUtil.getNullPropertyNames(cdEntity));
                saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            }
            //保存
            cdRepository.save(saveEntity);
            //修改上级菜单类型
            //RsglBCdEntity pEntity = cdRepository.findByCdid(saveEntity.getPcdid());
            //pEntity.setCdlx("1");//上级菜单为单元
            //cdRepository.save(pEntity);
            //设置返回值
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
            //判断是否有子集
            List<RsglBCdEntity> childList = cdRepository.findByPcdid(cdid, Sort.by("xssx"));
            for (RsglBCdEntity child : childList) {
                this.doDelete(child.getCdid());
            }
            cdRepository.delete(cdRepository.findByCdid(cdid));
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
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
        List<RsglBCdEntity> pCdList = cdRepository.findByPcdid(pcdid, Sort.by("xssx"));
        for (RsglBCdEntity pCd : pCdList) {
            Map menuMap = new HashMap();
            menuMap.put("id", pCd.getCdid());
            menuMap.put("pid", pCd.getPcdid());
            menuMap.put("text", pCd.getCdmc());
            menuMap.put("page", pCd.getPcpage());
            menuMap.put("nodes", null);
            //判断是否有子集
            int count = cdRepository.countByPcdid(pCd.getCdid());
            if (count > 0) {
                List<Map> childList = this.getMenus(pCd.getCdid());
                menuMap.put("nodes", childList);
            }
            menuList.add(menuMap);
        }
        return menuList;
    }
}
