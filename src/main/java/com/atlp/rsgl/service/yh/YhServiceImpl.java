package com.atlp.rsgl.service.yh;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBYhEntity;
import com.atlp.rsgl.entity.RsglBYhGzxxEntity;
import com.atlp.rsgl.repository.YhGzxxRepository;
import com.atlp.rsgl.repository.YhRepository;
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
 * @CreateTime: 2018-09-18 11:14
 * @Decription:
 */
@Service
@Transactional
public class YhServiceImpl implements IYhService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private YhRepository yhRepository;
    @Autowired
    private YhGzxxRepository yhGzxxRepository;

    @Override
    public Page<RsglBYhEntity> getYhPage(PageModel page) throws Exception {
        return yhRepository.findAll(PageRequest.of(page.getPage(), page.getLimit(),
                new Sort(Sort.Direction.ASC, "yhpwsx")));
    }

    @Override
    public Page<RsglBYhEntity> getYhPageByDwid(PageModel page, String dwid) throws Exception {
        return yhRepository.findAllByDwid(PageRequest.of(page.getPage(), page.getLimit(),
                new Sort(Sort.Direction.ASC, "yhpwsx")), dwid);
    }

    @Override
    public RsglBYhEntity getYhInfoById(String key) throws Exception {
        return yhRepository.findByYhid(key);
    }

    @Override
    public List<RsglBYhEntity> getYhList() throws Exception {
        return yhRepository.findAll();
    }

    @Override
    public boolean doSaveYh(RsglBYhEntity entity, RsglBYhGzxxEntity gzxxEntity) throws Exception {
        try {
            // 添加用户
            RsglBYhEntity yh = yhRepository.save(entity);
            if (null == yh || AtlpUtil.isEmpty(yh.getYhid())) {
                logger.debug("添加用户失败...用户信息==={}", yh.toString());
                return false;
            }

            // 添加工作信息
            gzxxEntity.setYid(yh.getYhid());
            RsglBYhGzxxEntity gzxx = yhGzxxRepository.save(gzxxEntity);
            if (null == gzxx || AtlpUtil.isEmpty(gzxx.getYhid())) {
                logger.debug("添加用户工作信息失败...工作信息==={}", gzxx.toString());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }

    @Override
    public boolean doUpdateYh(RsglBYhEntity entity, RsglBYhGzxxEntity gzxxEntity) throws Exception {
        // 查询用户是否存在
        RsglBYhEntity yhEntity = yhRepository.findByYhid(entity.getYhid());
        if (null == yhEntity) {
            logger.debug("查询用户信息失败...用户id==={}", entity.getYhid());
            return false;
        }

        // 用户对象复制
        BeanUtils.copyProperties(entity, yhEntity, AtlpUtil.getNullPropertyNames(entity));
        yhEntity.setLasttime(new Timestamp(new Date().getTime()));

        // 查询用户工作信息
        RsglBYhGzxxEntity bYhGzxxEntity = yhGzxxRepository.findByYid(yhEntity.getYhid());
        if (null == bYhGzxxEntity) {
            logger.debug("查询用户工作信息失败...用户id==={}", yhEntity.getYhid());
            return false;
        } else {
            // 对象赋值
            BeanUtils.copyProperties(gzxxEntity, bYhGzxxEntity, AtlpUtil.getNullPropertyNames(gzxxEntity));
            bYhGzxxEntity.setLasttime(new Timestamp(new Date().getTime()));
        }

        return this.doSaveYh(yhEntity, bYhGzxxEntity);
    }

    @Override
    public boolean doDeleteYh(RsglBYhEntity entity) throws Exception {
        try {
            yhRepository.delete(entity);
        } catch (Exception e) {
            logger.debug("删除用户位失败...用户信息==={}", entity.toString());
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }
}
