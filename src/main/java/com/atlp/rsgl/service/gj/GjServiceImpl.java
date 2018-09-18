package com.atlp.rsgl.service.gj;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBGwdjEntity;
import com.atlp.rsgl.repository.GjRepository;
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
 * @CreateTime: 2018-09-17 18:18
 * @Decription:
 */
@Service
@Transactional
public class GjServiceImpl implements IGjService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GjRepository gjRepository;

    @Override
    public Page<RsglBGwdjEntity> getGjPage(PageModel page) throws Exception {
        return gjRepository.findAll(PageRequest.of(page.getPage(), page.getLimit()));
    }

    @Override
    public RsglBGwdjEntity getGjInfoById(String key) throws Exception {
        return gjRepository.findByGwdjid(key);
    }

    @Override
    public List<RsglBGwdjEntity> getGjList() throws Exception {
        return gjRepository.findAll();
    }

    @Override
    public boolean doSaveGj(RsglBGwdjEntity entity) throws Exception {
        try {
            RsglBGwdjEntity gj = gjRepository.save(entity);

            if (null == gj || null == gj.getGwdjid()) {
                logger.debug("添加岗位等级失败...岗级信息==={}", gj.toString());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }

    @Override
    public boolean doUpdateGj(RsglBGwdjEntity entity) throws Exception {
        // 查询对象是否存在
        RsglBGwdjEntity gjEntity = gjRepository.findByGwdjid(entity.getGwdjid());
        if (null == gjEntity) {
            logger.debug("查询岗位等级信息失败...岗级id==={}", entity.getGwdjid());
            return false;
        }

        // 对象复制
        BeanUtils.copyProperties(entity, gjEntity, AtlpUtil.getNullPropertyNames(entity));
        gjEntity.setLasttime(new Timestamp(new Date().getTime()));

        return this.doSaveGj(gjEntity);
    }

    @Override
    public boolean doDeleteGj(RsglBGwdjEntity entity) throws Exception {
        try {
            gjRepository.delete(entity);
        } catch (Exception e) {
            logger.debug("删除岗位等级失败...岗级信息==={}", entity.toString());
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }
}
