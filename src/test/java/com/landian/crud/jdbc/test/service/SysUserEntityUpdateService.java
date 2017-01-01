package com.landian.crud.jdbc.test.service;

import com.landian.crud.core.service.AbstractUpdateService;
import com.landian.crud.jdbc.test.entity.SysUserEntity;
import com.landian.crud.jdbc.test.mapperContext.SysUserEntityContext;
import com.landian.sql.jpa.context.BeanContext;
import com.landian.sql.jpa.sql.Update;
import com.landian.sql.jpa.sql.UpdateUnit;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public int updateSomeInfo(int id, String loginName,String userName,Date createTime){
        UpdateUnit set1 = Update.set("loginName", loginName);
        UpdateUnit set2 = Update.set("userName", userName);
        UpdateUnit set3 = Update.set("createTime", createTime);
        return this.update(id,set1,set2,set3);
    }

}
