package com.landian.crud.jdbc.test.service;

import com.landian.crud.core.service.AbstractUpdateService;
import com.landian.crud.jdbc.test.entity.SysUserEntity;
import com.landian.crud.jdbc.test.mapperContext.SysUserEntityContext;
import com.landian.sql.jpa.context.BeanContext;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class SysUserEntityUpdateService extends AbstractUpdateService<SysUserEntity> {

    @Override
    public BeanContext<SysUserEntity> getBeanContext() {
        return SysUserEntityContext.getBeanContext();
    }

}
