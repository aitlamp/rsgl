package com.atlp.rsgl.service.gw;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBQypygwEntity;
import com.atlp.rsgl.repository.GwRepository;
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
public class GwServiceImpl implements IGwService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GwRepository gwRepository;

    @Override
    public Page<RsglBQypygwEntity> getGwPage(PageModel page) throws Exception {
        return gwRepository.findAll(PageRequest.of(page.getPage(), page.getLimit(), Sort.by("pwsx")));
    }

    @Override
    public RsglBQypygwEntity getGwInfoById(String key) throws Exception {
        return gwRepository.findByQypygwid(key);
    }

    @Override
    public List<RsglBQypygwEntity> getGwList() throws Exception {
        return gwRepository.findAll();
    }

    @Override
    public boolean doSaveGw(RsglBQypygwEntity entity) throws Exception {
        try {
            RsglBQypygwEntity gw = gwRepository.save(entity);

            if (null == gw || null == gw.getQypygwid()) {
                logger.debug("添加全员聘用岗位失败...岗位信息==={}", gw.toString());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }

    @Override
    public boolean doUpdateGw(RsglBQypygwEntity entity) throws Exception {
        // 查询对象是否存在
        RsglBQypygwEntity gwEntity = gwRepository.findByQypygwid(entity.getQypygwid());
        if (null == gwEntity) {
            logger.debug("查询全员聘用岗位信息失败...岗位id==={}", entity.getQypygwid());
            return false;
        }

        // 对象复制
        BeanUtils.copyProperties(entity, gwEntity, AtlpUtil.getNullPropertyNames(entity));
        gwEntity.setLasttime(new Timestamp(new Date().getTime()));

        return this.doSaveGw(gwEntity);
    }

    @Override
    public boolean doDeleteGw(RsglBQypygwEntity entity) throws Exception {
        try {
            gwRepository.delete(entity);
        } catch (Exception e) {
            logger.debug("删除全员聘用岗位失败...岗位信息==={}", entity.toString());
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }
}
