package com.landian.crud.jdbc.test.service;

import com.landian.crud.core.service.AbstractQueryService;
import com.landian.crud.jdbc.test.entity.SysUserEntity;
import com.landian.crud.jdbc.test.mapperContext.SysUserEntityContext;
import com.landian.sql.jpa.context.BeanContext;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class SysUserEntityQueryService extends AbstractQueryService<SysUserEntity> {

    @Override
    public BeanContext getBeanContext() {
        return SysUserEntityContext.getBeanContext();
    }

}
