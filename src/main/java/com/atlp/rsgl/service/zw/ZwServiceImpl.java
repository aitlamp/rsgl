package com.atlp.rsgl.service.zw;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBZwEntity;
import com.atlp.rsgl.repository.ZwRepository;
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
public class ZwServiceImpl implements IZwService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ZwRepository zwRepository;

    @Override
    public Page<RsglBZwEntity> getZwPage(PageModel page) throws Exception {
        return zwRepository.findAll(PageRequest.of(page.getPage(), page.getLimit(), Sort.by("pwsx")));
    }

    @Override
    public RsglBZwEntity getZwInfoById(String key) throws Exception {
        return zwRepository.findByZwid(key);
    }

    @Override
    public List<RsglBZwEntity> getZwList() throws Exception {
        return zwRepository.findAll();
    }

    @Override
    public boolean doSaveZw(RsglBZwEntity entity) throws Exception {
        try {
            RsglBZwEntity zw = zwRepository.save(entity);

            if (null == zw || null == zw.getZwid()) {
                logger.debug("添加职务失败...职务信息==={}", zw.toString());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }

    @Override
    public boolean doUpdateZw(RsglBZwEntity entity) throws Exception {
        // 查询对象是否存在
        RsglBZwEntity zwEntity = zwRepository.findByZwid(entity.getZwid());
        if (null == zwEntity) {
            logger.debug("查询职务失败...职务id==={}", entity.getZwid());
            return false;
        }

        // 对象复制
        BeanUtils.copyProperties(entity, zwEntity, AtlpUtil.getNullPropertyNames(entity));
        zwEntity.setLasttime(new Timestamp(new Date().getTime()));

        return this.doSaveZw(zwEntity);
    }

    @Override
    public boolean doDeleteZw(RsglBZwEntity entity) throws Exception {
        try {
            zwRepository.delete(entity);
        } catch (Exception e) {
            logger.debug("删除职务失败...职务信息==={}", entity.toString());
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }
}
