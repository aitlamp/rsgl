package com.atlp.rsgl.service.gd;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBGwdjDwEntity;
import com.atlp.rsgl.repository.GdRepository;
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
 * @CreateTime: 2018-09-17 18:18
 * @Decription:
 */
@Service
@Transactional
public class GdServiceImpl implements IGdService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GdRepository gdRepository;

    @Override
    public Page<RsglBGwdjDwEntity> getGdPage(PageModel page, String gwdjid) throws Exception {
        return gdRepository.findAllByGwdjid(PageRequest.of(page.getPage(), page.getLimit(), Sort.by("pwsx")), gwdjid);
    }

    @Override
    public RsglBGwdjDwEntity getGdInfoById(String key) throws Exception {
        return gdRepository.findByDwid(key);
    }

    @Override
    public List<RsglBGwdjDwEntity> getGdList() throws Exception {
        return gdRepository.findAll();
    }

    @Override
    public List<RsglBGwdjDwEntity> getGdListByGjid(String gjid) throws Exception {
        return gdRepository.findAllByGwdjid(gjid);
    }

    @Override
    public boolean doSaveGd(RsglBGwdjDwEntity entity) throws Exception {
        try {
            RsglBGwdjDwEntity gd = gdRepository.save(entity);

            if (null == gd || null == gd.getDwid()) {
                logger.debug("添加岗位等级挡位失败...挡位信息==={}", gd.toString());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }

    @Override
    public boolean doUpdateGd(RsglBGwdjDwEntity entity) throws Exception {
        // 查询对象是否存在
        RsglBGwdjDwEntity gdEntity = gdRepository.findByDwid(entity.getDwid());
        if (null == gdEntity) {
            logger.debug("查询岗位等级挡位信息失败...挡位id==={}", entity.getDwid());
            return false;
        }

        // 对象复制
        BeanUtils.copyProperties(entity, gdEntity, AtlpUtil.getNullPropertyNames(entity));
        gdEntity.setLasttime(new Timestamp(new Date().getTime()));

        return this.doSaveGd(gdEntity);
    }

    @Override
    public boolean doDeleteGd(RsglBGwdjDwEntity entity) throws Exception {
        try {
            gdRepository.delete(entity);
        } catch (Exception e) {
            logger.debug("删除岗位等级挡位失败...挡位信息==={}", entity.toString());
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }
}
