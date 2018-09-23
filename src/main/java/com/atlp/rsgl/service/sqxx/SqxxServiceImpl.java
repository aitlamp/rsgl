package com.atlp.rsgl.service.sqxx;

import com.atlp.rsgl.common.data.PageModel;
import com.atlp.rsgl.entity.RsglBSqxxEntity;
import com.atlp.rsgl.repository.SqxxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-19 17:04
 * @Decription:
 */
@Service
@Transactional
public class SqxxServiceImpl implements ISqxxService {

    private final Logger logger  = LoggerFactory.getLogger(getClass());

    @Autowired
    private SqxxRepository sqxxRepository;

    @Override
    public Page<RsglBSqxxEntity> getSqxxPage(PageModel page, String yhid) throws Exception {
        return sqxxRepository.findAllBySqyhid(PageRequest.of(page.getPage(), page.getLimit(),
                new Sort(Sort.Direction.DESC, "firsttime")), yhid);
    }

    @Override
    public RsglBSqxxEntity getSqxxInfoById(String key) throws Exception {
        return sqxxRepository.findBySqid(key);
    }

    @Override
    public boolean doSaveSqxx(RsglBSqxxEntity entity) throws Exception {
        try {
            RsglBSqxxEntity sqxx = sqxxRepository.save(entity);
            if (null == sqxx) {
                logger.debug("授权失败,授权信息==={}", entity.toString());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }

    @Override
    public boolean doDeleteSqxx(RsglBSqxxEntity entity) throws Exception {
        try {
            sqxxRepository.delete(entity);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("删除授权失败,授权信息==={}", entity.toString());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }
}
