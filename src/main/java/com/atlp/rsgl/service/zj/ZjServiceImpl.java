package com.atlp.rsgl.service.zj;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBZjEntity;
import com.atlp.rsgl.repository.ZjRepository;
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
public class ZjServiceImpl implements IZjService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ZjRepository zjRepository;

    @Override
    public Page<RsglBZjEntity> getZjPage(PageModel page) throws Exception {
        return zjRepository.findAll(PageRequest.of(page.getPage(), page.getLimit(), Sort.by("pwsx")));
    }

    @Override
    public RsglBZjEntity getZjInfoById(String key) throws Exception {
        return zjRepository.findByZjid(key);
    }

    @Override
    public List<RsglBZjEntity> getZjList() throws Exception {
        return zjRepository.findAll(Sort.by("pwsx"));
    }

    @Override
    public List<RsglBZjEntity> getZjList(String sybbs) throws Exception {
        return zjRepository.findAllBySybzybs(sybbs);
    }

    @Override
    public boolean doSaveZj(RsglBZjEntity entity) throws Exception {
        try {
            RsglBZjEntity zj = zjRepository.save(entity);

            if (null == zj || null == zj.getZjid()) {
                logger.debug("添加职级失败...职级信息==={}", zj.toString());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }

    @Override
    public boolean doUpdateZj(RsglBZjEntity entity) throws Exception {
        // 查询对象是否存在
        RsglBZjEntity zjEntity = zjRepository.findByZjid(entity.getZjid());
        if (null == zjEntity) {
            logger.debug("查询职级失败...职级id==={}", entity.getZjid());
            return false;
        }

        // 对象复制
        BeanUtils.copyProperties(entity, zjEntity, AtlpUtil.getNullPropertyNames(entity));
        zjEntity.setLasttime(new Timestamp(new Date().getTime()));

        return this.doSaveZj(zjEntity);
    }

    @Override
    public boolean doDeleteZj(RsglBZjEntity entity) throws Exception {
        try {
            zjRepository.delete(entity);
        } catch (Exception e) {
            logger.debug("删除职级失败...职级信息==={}", entity.toString());
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }
}
