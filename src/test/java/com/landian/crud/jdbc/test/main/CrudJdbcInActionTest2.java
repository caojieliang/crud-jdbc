package com.landian.crud.jdbc.test.main;

import com.landian.crud.jdbc.test.entity.SysUserEntity;
import com.landian.crud.jdbc.test.service.SysUserEntityQueryService;
import com.landian.crud.jdbc.test.service.SysUserEntityUpdateService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 */
public class CrudJdbcInActionTest2 extends BaseServiceTest {

    private static final Logger logger = Logger.getLogger(CrudJdbcInActionTest2.class);

    @Autowired
    private SysUserEntityQueryService sysUserEntityQueryService;
    @Autowired
    private SysUserEntityUpdateService sysUserEntityUpdateService;

    @Test
    public void save2(){
        SysUserEntity sysUserEntity = SysUserEntity.builder().userName("~`/><,.!@#$%^&*()_+'\"").loginName("李")
                .createTime(new Date()).build();
        SysUserEntity save = sysUserEntityUpdateService.save(sysUserEntity);
        logger.info(save);
    }

    @Test
    public void update2(){
        int id = 14;
        SysUserEntity bean = sysUserEntityQueryService.queryById(id);
        bean.setLoginName(bean.getLoginName() + "(改)");
        bean.setUserName(bean.getUserName() + "(改)");
        sysUserEntityUpdateService.update(bean);
        logger.info(bean);
    }

    @Test
    public void update3(){
        int id = 14;
        String loginName = null;
        String userName = null;
        Date createTime = null;
        int result = sysUserEntityUpdateService.updateSomeInfo(id, loginName, userName, createTime);
        logger.info(result);
    }

}
