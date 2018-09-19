package com.atlp.rsgl.service.dw;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBDwEntity;
import com.atlp.rsgl.repository.DwRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Timestamp;
import java.util.*;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-14 12:36
 * @Decription:
 */
@Service
@Transactional
public class DwServiceImpl implements IDwService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DwRepository dwRepository;

    @Override
    public Page<RsglBDwEntity> getDwPage(PageModel page, String pdwid) throws Exception {
        // return dwRepository.findAll(PageRequest.of(page.getPage(), page.getLimit()));
        return dwRepository.findAllByPdwid(PageRequest.of(page.getPage(), page.getLimit(), Sort.by("dwpwsx")), pdwid);
    }

    @Override
    public RsglBDwEntity getDwInfoById(String key) throws Exception {
        return dwRepository.findByDwid(key);
    }

    @Override
    public List<RsglBDwEntity> getDwListByPid(String pid) throws Exception {
        return dwRepository.findByPdwid(pid, new Sort(Sort.Direction.ASC, "dwpwsx"));
    }

    @Override
    public List<Map<String, Object>> getDwMapListByPid(String pid) throws Exception {
        List<Map<String, Object>> dwMapList = new ArrayList<>();

        // 根据父级id查询子级单位
        List<RsglBDwEntity> dwEntityList = dwRepository.findByPdwid(pid, new Sort(Sort.Direction.ASC, "dwpwsx"));

        // 循环遍历，查询子级单位信息
        Map dwMap = null;
        for (RsglBDwEntity dwEntity : dwEntityList) {
            dwMap = new HashMap();
            dwMap.put("id", dwEntity.getDwid());
            dwMap.put("pid", dwEntity.getPdwid());
            dwMap.put("text", dwEntity.getDwmc());

            // 查询子级单位
            List<Map<String, Object>> subDwMapList = this.getDwMapListByPid(dwEntity.getDwid());
            if (AtlpUtil.isEmpty(subDwMapList)) {
                dwMap.put("nodes", null);
            } else {
                dwMap.put("nodes", subDwMapList);
            }

            dwMapList.add(dwMap);
        }

        return dwMapList;
    }

    @Override
    public List<RsglBDwEntity> getDwList() throws Exception {
        List dwList = dwRepository.findAll(Sort.by("dwpwsx"));

        Map<String, Object> map = new HashMap<>();
        map.put("dwid", "root");
        map.put("pdwid", "");
        map.put("dwmc", "单位树");
        map.put("dwjc", "单位树");
        dwList.add(map);

        return dwList;
    }

    @Override
    public boolean doSaveDw(RsglBDwEntity entity) throws Exception {
        try {
            RsglBDwEntity dw = dwRepository.save(entity);

            if (null == dw || null == dw.getDwid()) {
                logger.debug("添加单位失败...单位信息==={}", dw.toString());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }

    @Override
    public boolean doUpdateDw(RsglBDwEntity entity) throws Exception {
        // 查询数据对象是否存在
        RsglBDwEntity dwEntity = dwRepository.findByDwid(entity.getDwid());
        if (null == dwEntity) {
            logger.debug("查询单位失败...单位id==={}", entity.getDwid());
            return false;
        }

        // 对象复制
        BeanUtils.copyProperties(entity, dwEntity, AtlpUtil.getNullPropertyNames(entity));
        dwEntity.setLasttime(new Timestamp(new Date().getTime()));

        return this.doSaveDw(dwEntity);
    }

    @Override
    public boolean doDeleteDw(RsglBDwEntity entity) throws Exception {
        try {
            dwRepository.delete(entity);
        } catch (Exception e) {
            logger.debug("删除单位失败...单位信息==={}", entity.toString());
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }
}
