package com.landian.crud.jdbc.test.service;

import com.landian.crud.core.service.AbstractUpdateService;
import com.landian.crud.jdbc.test.entity.SysUserEntity;
import com.landian.crud.jdbc.test.mapperContext.SysUserEntityContext;
import com.landian.sql.jpa.context.BeanContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 */
@Service
public class SysUserEntityUpdateService extends AbstractUpdateService<SysUserEntity> {

    @Override
    public BeanContext getBeanContext() {
        return SysUserEntityContext.getBeanContext();
    }

    public int deleteSysUserEntityByIds(List<Integer> ids){
        return this.deleteById(ids);
    }

}
