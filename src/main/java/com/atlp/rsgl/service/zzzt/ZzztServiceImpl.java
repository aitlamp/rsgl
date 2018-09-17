package com.atlp.rsgl.service.zzzt;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBZzztEntity;
import com.atlp.rsgl.repository.ZzztRepository;
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
 * @CreateTime: 2018-09-17 14:02
 * @Decription:
 */
@Service
@Transactional
public class ZzztServiceImpl implements IZzztService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ZzztRepository zzztRepository;

    @Override
    public Page<RsglBZzztEntity> getZzztPage(PageModel page) throws Exception {
        return zzztRepository.findAll(PageRequest.of(page.getPage(), page.getLimit(), Sort.by("xssx")));
    }

    @Override
    public List<RsglBZzztEntity> getZzztList() throws Exception {
        return zzztRepository.findAll();
    }

    @Override
    public RsglBZzztEntity getZzztInfoById(String key) throws Exception {
        return zzztRepository.findByZtid(key);
    }

    @Override
    public boolean doSaveZzzt(RsglBZzztEntity entity) throws Exception {
        try {
            RsglBZzztEntity zzzt = zzztRepository.save(entity);

            if (null == zzzt || null == zzzt.getZtid()) {
                logger.debug("添加在职状态失败...在职状态信息==={}", zzzt.toString());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }

    @Override
    public boolean doUpdateZzzt(RsglBZzztEntity entity) throws Exception {
        // 查询对象是否存在
        RsglBZzztEntity zzztEntity = zzztRepository.findByZtid(entity.getZtid());
        if (null == zzztEntity) {
            logger.debug("查询在职状态失败...状态id==={}", entity.getZtid());
            return false;
        }

        // 对象复制
        BeanUtils.copyProperties(entity, zzztEntity, AtlpUtil.getNullPropertyNames(entity));
        zzztEntity.setLasttime(new Timestamp(new Date().getTime()));

        return this.doSaveZzzt(zzztEntity);
    }

    @Override
    public boolean doDeleteZzzt(RsglBZzztEntity entity) throws Exception {
        try {
            zzztRepository.delete(entity);
        } catch (Exception e) {
            logger.debug("删除在职状态失败...状态信息==={}", entity.toString());
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }
}
