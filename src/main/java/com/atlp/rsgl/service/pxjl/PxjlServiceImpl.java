package com.atlp.rsgl.service.pxjl;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBPxjlEntity;
import com.atlp.rsgl.repository.PxjlRepository;
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
 * @CreateTime: 2018-09-19 11:01
 * @Decription:
 */
@Service
@Transactional
public class PxjlServiceImpl implements IPxjlService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PxjlRepository pxjlRepository;

    @Override
    public Page<RsglBPxjlEntity> getPxPage(PageModel page) throws Exception {
        return pxjlRepository.findAll(PageRequest.of(page.getPage(), page.getLimit(),
                new Sort(Sort.Direction.DESC, "firsttime")));
    }

    @Override
    public RsglBPxjlEntity getPxInfoById(String key) throws Exception {
        return pxjlRepository.findByPxjlid(key);
    }

    @Override
    public List<RsglBPxjlEntity> getPxList() throws Exception {
        return pxjlRepository.findAll();
    }

    @Override
    public List<RsglBPxjlEntity> getPxListByYhid(String yhid) throws Exception {
        return pxjlRepository.findAllByPxyhid(yhid);
    }

    @Override
    public boolean doSavePx(RsglBPxjlEntity entity) throws Exception {
        try {
            RsglBPxjlEntity px = pxjlRepository.save(entity);

            if (null == px || null == px.getPxjlid()) {
                logger.debug("添加培训记录失败...培训信息==={}", px.toString());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }

    @Override
    public boolean doUpdatePx(RsglBPxjlEntity entity) throws Exception {
        // 查询该培训记录是都存在
        RsglBPxjlEntity pxjlEntity = pxjlRepository.findByPxjlid(entity.getPxjlid());
        if (null == pxjlEntity) {
            logger.debug("查询培训记录失败...培训id==={}", entity.getPxjlid());
            return false;
        }

        // 对象复制
        BeanUtils.copyProperties(entity, pxjlEntity, AtlpUtil.getNullPropertyNames(entity));
        pxjlEntity.setLasttime(new Timestamp(new Date().getTime()));

        return this.doSavePx(pxjlEntity);
    }

    @Override
    public boolean doDeletePx(RsglBPxjlEntity entity) throws Exception {
        try {
            pxjlRepository.delete(entity);
        } catch (Exception e) {
            logger.debug("删除培训记录失败...培训信息==={}", entity.toString());
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }
}
