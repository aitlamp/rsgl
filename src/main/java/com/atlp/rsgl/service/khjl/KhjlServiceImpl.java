package com.atlp.rsgl.service.khjl;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBKhjlEntity;
import com.atlp.rsgl.entity.RsglBYhEntity;
import com.atlp.rsgl.repository.KhjlRepository;
import com.atlp.rsgl.repository.YhRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-19 12:29
 * @Decription:
 */
@Service
@Transactional
public class KhjlServiceImpl implements IKhjlService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private KhjlRepository khjlRepository;
    @Autowired
    private YhRepository yhRepository;

    @Override
    public Page<RsglBKhjlEntity> getKhPage(PageModel page) throws Exception {
        Page<RsglBKhjlEntity> khjlEntityPage = khjlRepository.findAll(PageRequest.of(page.getPage(), page.getLimit()));

        List<RsglBKhjlEntity> khjlEntityList = khjlEntityPage.getContent();
        if (!CollectionUtils.isEmpty(khjlEntityList)) {
            for (RsglBKhjlEntity khjlEntity : khjlEntityList) {
                RsglBYhEntity yhEntity = yhRepository.findByYhid(khjlEntity.getKhyhid());
                khjlEntity.setKhyhxm(yhEntity.getYhxm());
            }
        }

        return khjlEntityPage;
    }

    @Override
    public RsglBKhjlEntity getKhInfoById(String key) throws Exception {
        return khjlRepository.findByKhjlid(key);
    }

    @Override
    public List<RsglBKhjlEntity> getKhList() throws Exception {
        return khjlRepository.findAll();
    }

    @Override
    public List<RsglBKhjlEntity> getKhListByYhid(String yhid) throws Exception {
        return khjlRepository.findAllByKhyhid(yhid);
    }

    @Override
    public boolean doSaveKh(RsglBKhjlEntity entity) throws Exception {
        try {
            RsglBKhjlEntity kh = khjlRepository.save(entity);

            if (null == kh || null == kh.getKhjlid()) {
                logger.debug("添加考核记录失败...考核信息==={}", kh.toString());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }

    @Override
    public boolean doUpdateKh(RsglBKhjlEntity entity) throws Exception {
        // 查询对象是否存在
        RsglBKhjlEntity khjlEntity = khjlRepository.findByKhjlid(entity.getKhjlid());
        if (null == khjlEntity) {
            logger.debug("查询考核记录失败...考核id==={}", entity.getKhjlid());
            return false;
        }

        // 对象复制
        BeanUtils.copyProperties(entity, khjlEntity, AtlpUtil.getNullPropertyNames(entity));
        khjlEntity.setLasttime(new Timestamp(new Date().getTime()));

        return this.doSaveKh(khjlEntity);
    }

    @Override
    public boolean doDeleteKh(RsglBKhjlEntity entity) throws Exception {
        try {
            khjlRepository.delete(entity);
        } catch (Exception e) {
            logger.debug("删除考核记录失败...考核信息==={}", entity.toString());
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }
}
