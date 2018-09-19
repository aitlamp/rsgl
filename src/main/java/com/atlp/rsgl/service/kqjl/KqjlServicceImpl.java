package com.atlp.rsgl.service.kqjl;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBKqjlEntity;
import com.atlp.rsgl.repository.KqjlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-18 17:44
 * @Decription:
 */
@Service
@Transactional
public class KqjlServicceImpl implements IKqjlService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private KqjlRepository kqjlRepository;

    @Override
    public Page<RsglBKqjlEntity> getKqPage(PageModel page) throws Exception {
        return kqjlRepository.findAll(PageRequest.of(page.getPage(), page.getLimit()));
    }

    @Override
    public RsglBKqjlEntity getKqInfoById(String key) throws Exception {
        return kqjlRepository.findByKqjlid(key);
    }

    @Override
    public List<RsglBKqjlEntity> getKqList() throws Exception {
        return kqjlRepository.findAll();
    }

    @Override
    public List<RsglBKqjlEntity> getKqListByYhid(String yhid) throws Exception {
        return kqjlRepository.findAllByKqyhid(yhid);
    }

    @Override
    public boolean doSaveKqjl(RsglBKqjlEntity entity) throws Exception {
        try {
            RsglBKqjlEntity kq = kqjlRepository.save(entity);

            if (null == kq || null == kq.getKqjlid()) {
                logger.debug("添加考勤记录失败...烤漆信息==={}", kq.toString());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }

    @Override
    public boolean doUpdateKqjl(RsglBKqjlEntity entity) throws Exception {
        // 查询对象是否存在
        RsglBKqjlEntity kqjlEntity = kqjlRepository.findByKqjlid(entity.getKqjlid());
        if (null == kqjlEntity) {
            logger.debug("查询考勤记录失败...考勤id==={}", entity.getKqjlid());
            return false;
        }

        // 对象复制
        BeanUtils.copyProperties(entity, kqjlEntity, AtlpUtil.getNullPropertyNames(entity));
        kqjlEntity.setLasttime(new Timestamp(new Date().getTime()));

        return this.doSaveKqjl(kqjlEntity);
    }

    @Override
    public boolean doDeleteKqjl(RsglBKqjlEntity entity) throws Exception {
        try {
            kqjlRepository.delete(entity);
        } catch (Exception e) {
            logger.debug("删除考勤记录失败...考勤信息==={}", entity.toString());
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }
}
