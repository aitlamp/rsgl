package com.atlp.rsgl.service.gzxx;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.common.utils.AtlpUtil;
import com.atlp.rsgl.entity.RsglBYhGzxxEntity;
import com.atlp.rsgl.repository.YhGzxxRepository;
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
 * @CreateTime: 2018-09-19 14:42
 * @Decription:
 */
@Service
@Transactional
public class GzxxServiceImpl implements IGzxxService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private YhGzxxRepository yhGzxxRepository;

    @Override
    public Page<RsglBYhGzxxEntity> getYhGzxxPage(PageModel page) throws Exception {
        return yhGzxxRepository.findAll(PageRequest.of(page.getPage(), page.getLimit()));
    }

    @Override
    public RsglBYhGzxxEntity getYhGzxxByYhid(String yhid) throws Exception {
        return yhGzxxRepository.findByYid(yhid);
    }

    @Override
    public List<RsglBYhGzxxEntity> getYhGzxxList() throws Exception {
        return yhGzxxRepository.findAll();
    }

    @Override
    public boolean doSaveYhGzxx(RsglBYhGzxxEntity entity) throws Exception {
        try {
            RsglBYhGzxxEntity gzxx = yhGzxxRepository.save(entity);

            if (null == gzxx) {
                logger.debug("添加用户工作信息失败,工作信息==={}", gzxx.toString());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }

    @Override
    public boolean doUpdateYhGzxx(RsglBYhGzxxEntity entity) throws Exception {
        // 查询原用户工作信息是否存在
        RsglBYhGzxxEntity gzxxEntity = yhGzxxRepository.findByYid(entity.getYid());
        if (null == gzxxEntity) {
            logger.debug("查询用户工作信息为空,工作信息==={}", gzxxEntity.toString());
            return false;
        }

        // 对象赋值
        BeanUtils.copyProperties(entity, gzxxEntity, AtlpUtil.getNullPropertyNames(entity));
        gzxxEntity.setLasttime(new Timestamp(new Date().getTime()));

        return this.doSaveYhGzxx(gzxxEntity);
    }
}
