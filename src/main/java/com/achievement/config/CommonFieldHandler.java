package com.achievement.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 通用字段填充
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-08 17:40
 */
@Component
public class CommonFieldHandler implements MetaObjectHandler {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CommonFieldHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
//        this.strictInsertFill(metaObject, "createId", String.class,"ADMIN");
//        log.info("start insert fill ....");
        this.setFieldValByName("id", UUID.randomUUID().toString().replace("-", ""),metaObject );
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("lastUpdateTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        log.info("start update fill ....");
        this.setFieldValByName("lastUpdateTime", new Date(), metaObject);
    }

    public static synchronized String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}