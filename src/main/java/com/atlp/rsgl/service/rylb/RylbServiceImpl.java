package com.atlp.rsgl.service.rylb;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBRylbEntity;
import com.atlp.rsgl.repository.RylbRepository;
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
 * @CreateTime: 2018-09-17 10:29
 * @Decription:
 */
@Service
@Transactional
public class RylbServiceImpl implements IRylbService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RylbRepository rylbRepository;

    @Override
    public Page<RsglBRylbEntity> getRylbPage(PageModel page, String plbid) throws Exception {
        return rylbRepository.findAllByPlbid(PageRequest.of(page.getPage(), page.getLimit(),
                new Sort(Sort.Direction.ASC, "pwsx")), plbid);
    }

    @Override
    public RsglBRylbEntity getRylbInfoById(String key) throws Exception {
        return rylbRepository.findByLbid(key);
    }

    @Override
    public List<RsglBRylbEntity> getRylbListByPid(String pid) throws Exception {
        return rylbRepository.findByPlbid(pid, new Sort(Sort.Direction.ASC, "pwsx"));
    }

    @Override
    public List<Map<String, Object>> getRylbMapListByPid(String pid) throws Exception {
        List<Map<String, Object>> lbMapList = new ArrayList<>();

        // 查询plbid下所有的类别
        List<RsglBRylbEntity> lbList = rylbRepository.findByPlbid(pid, new Sort(Sort.Direction.ASC, "pwsx"));

        // 循环遍历，查询子级单位信息
        Map lbMap = null;
        for (RsglBRylbEntity lbEntity : lbList) {
            lbMap = new HashMap();
            lbMap.put("id", lbEntity.getLbid());
            lbMap.put("pid", lbEntity.getPlbid());
            lbMap.put("text", lbEntity.getLbmc());

            // 查询子级单位
            List<Map<String, Object>> subLbMapList = this.getRylbMapListByPid(lbEntity.getLbid());
            if (AtlpUtil.isEmpty(subLbMapList)) {
                lbMap.put("nodes", null);
            } else {
                lbMap.put("nodes", subLbMapList);
            }

            lbMapList.add(lbMap);
        }

        return lbMapList;
    }

    @Override
    public List<RsglBRylbEntity> getRylbList() throws Exception {
        return rylbRepository.findAll();
    }

    @Override
    public boolean doSaveRylb(RsglBRylbEntity entity) throws Exception {
        try {
            RsglBRylbEntity lb = rylbRepository.save(entity);

            if (null == lb || null == lb.getLbid()) {
                logger.debug("添加人员类别失败...人员类别信息==={}", lb.toString());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }

    @Override
    public boolean doUpdateRylb(RsglBRylbEntity entity) throws Exception {
        // 查询对象是否存在
        RsglBRylbEntity rylbEntity = rylbRepository.findByLbid(entity.getLbid());
        if (null == rylbEntity) {
            logger.debug("查询人猿类别失败...类别id==={}", entity.getLbid());
            return false;
        }

        // 对象复制
        BeanUtils.copyProperties(entity, rylbEntity, AtlpUtil.getNullPropertyNames(entity));
        rylbEntity.setLasttime(new Timestamp(new Date().getTime()));

        return this.doSaveRylb(rylbEntity);
    }

    @Override
    public boolean doDeleteRylb(RsglBRylbEntity entity) throws Exception {
        try {
            rylbRepository.delete(entity);
        } catch (Exception e) {
            logger.debug("删除人员类别失败...类别信息==={}", entity.toString());
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }
}
