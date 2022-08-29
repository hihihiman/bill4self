package com.example.bill4self.base.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.bill4self.system.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author dujianghui
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    public static final String CREATE_TIME = "createTime";
    public static final String CREATE_ACCOUNT_ID = "createAccountId";
    public static final String MODIFIED_TIME = "modifiedTime";
    public static final String MODIFIED_ACCOUNT_ID = "modifiedAccountId";

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        if (metaObject.hasSetter(CREATE_TIME)){
            this.strictInsertFill(metaObject, CREATE_TIME, LocalDateTime::now, LocalDateTime.class);
        }
        if (metaObject.hasSetter(CREATE_ACCOUNT_ID)){
            final Account account = (Account) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).getAttribute("account", RequestAttributes.SCOPE_SESSION);
            if (account!=null){
                final Long accountId = account.getAccountId();
                this.strictInsertFill(metaObject, CREATE_ACCOUNT_ID, Long.class,accountId);
            }
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        if (metaObject.hasSetter(MODIFIED_TIME)){
            this.strictUpdateFill(metaObject, MODIFIED_TIME, LocalDateTime::now, LocalDateTime.class);
        }
        if (metaObject.hasSetter(MODIFIED_ACCOUNT_ID)){
            final Account account = (Account) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).getAttribute("account", RequestAttributes.SCOPE_SESSION);
            if (account!=null){
                final Long accountId = account.getAccountId();
                this.strictUpdateFill(metaObject, MODIFIED_ACCOUNT_ID, Long.class,accountId);
            }
        }
    }
}
