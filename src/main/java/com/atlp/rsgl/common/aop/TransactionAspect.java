package com.atlp.rsgl.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.SessionHolder;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 自定义事务
 *
 * @author CTC
 * @created 2018年8月29日 22:26:42
 */

//@Aspect
//@Component
//@Configuration
public class TransactionAspect {
    private Logger log = LoggerFactory.getLogger(getClass());

//    @Bean
//    public HibernateJpaSessionFactoryBean SessionFactory() {
//        return new HibernateJpaSessionFactoryBean();
//    }

    @Autowired
    private SessionFactory sessionFactory;

    public TransactionAspect() {
        log.info("事务切面创建成功！");
    }

    /**
     * 事务切入点声明
     */
    @Pointcut("execution(* com.atlp.rsgl.service..*(..))")
    public void txPointcut() {

    }

    /**
     * 事务环绕通知
     */
    @Around(value = "txPointcut()")
    public Object txAround(ProceedingJoinPoint pjp) throws Exception {
        log.debug("事务开启中...");
        Transaction tx = null;
        Session session = null;
        try {
            //事务开启
            //for (Map.Entry<String, SessionFactory> entry : factoryMap.entrySet()) {
            session = sessionFactory.openSession();
            //sessions.add(session);
            //将本线程中所有的数据库集中进行事务管理   某个库失败，其余库全部回滚
            //SessionHolder.addSession(entry.getKey(), session, entry.getValue());
            session.beginTransaction();
            //}
            //目标方法执行
            Object obj = pjp.proceed();
            //事务提交
            //for (Transaction tx : txs) {
            tx.commit();
            //}
            log.debug("事务提交完成...");
            return obj;
        } catch (Throwable e) {
            //发生异常  所有的库都进行回滚
            //for (Transaction tx : txs) {
            tx.rollback();
            //}
            e.printStackTrace();
            log.info("数据库访问失败！...");
            log.debug("事务回滚完毕...");
            throw new Exception(e.getMessage(), e);
        } finally {
            //连接关闭
            //for (Session session : sessions) {
            session.close();
            //}
        }
    }
}
