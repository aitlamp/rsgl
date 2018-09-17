package com.atlp.rsgl.service.zd;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBZdEntity;
import com.atlp.rsgl.repository.ZdRepository;
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
import java.util.Date;
import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-17 14:49
 * @Decription:
 */
@Service
@Transactional
public class ZdServiceImpl implements IZdService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ZdRepository zdRepository;

    @Override
    public Page<RsglBZdEntity> getZdPage(PageModel page, String zjid) throws Exception {
        return zdRepository.findAllByZjid(PageRequest.of(page.getPage(), page.getLimit(), Sort.by("pwsx")), zjid);
    }

    @Override
    public RsglBZdEntity getZdInfoById(String key) throws Exception {
        return zdRepository.findByZdid(key);
    }

    @Override
    public List<RsglBZdEntity> getZdList() throws Exception {
        return zdRepository.findAll();
    }

    @Override
    public boolean doSaveZd(RsglBZdEntity entity) throws Exception {
        try {
            RsglBZdEntity zd = zdRepository.save(entity);

            if (null == zd || null == zd.getZdid()) {
                logger.debug("添加职等失败...职等信息==={}", zd.toString());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }

    @Override
    public boolean doUpdateZd(RsglBZdEntity entity) throws Exception {
        // 查询对象是否存在
        RsglBZdEntity zdEntity = zdRepository.findByZdid(entity.getZdid());
        if (null == zdEntity) {
            logger.debug("查询职等失败...职等id==={}", entity.getZdid());
            return false;
        }

        // 对象复制
        BeanUtils.copyProperties(entity, zdEntity, AtlpUtil.getNullPropertyNames(entity));
        zdEntity.setLasttime(new Timestamp(new Date().getTime()));

        return this.doSaveZd(zdEntity);
    }

    @Override
    public boolean doDeleteZd(RsglBZdEntity entity) throws Exception {
        try {
            zdRepository.delete(entity);
        } catch (Exception e) {
            logger.debug("删除职等失败...职等信息==={}", entity.toString());
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }
}
