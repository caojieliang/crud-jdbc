package com.landian.crud.jdbc.test.main;

import com.landian.crud.core.dao.ProxyDaoSupport;
import com.landian.crud.jdbc.test.dao.TestSQLProvider;
import com.landian.crud.jdbc.test.entity.SysUserEntity;
import com.landian.crud.jdbc.test.service.SysUserEntityQueryService;
import com.landian.crud.jdbc.test.service.SysUserEntityUpdateService;
import com.landian.crud.jdbc.test.utils.OpenSourceUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class CrudJdbcInActionTest extends BaseServiceTest {

    private static final Logger logger = Logger.getLogger(CrudJdbcInActionTest.class);

    @Autowired
    private SysUserEntityQueryService sysUserEntityQueryService;
    @Autowired
    private SysUserEntityUpdateService sysUserEntityUpdateService;
    @Autowired
    private ProxyDaoSupport proxyDaoSupport;

    @Test
    public void testIntegration(){
        logger.info(sysUserEntityQueryService);
    }

    @Test
    public void queryBeanAll(){
        List<SysUserEntity> sysUserEntities = sysUserEntityQueryService.queryBeanAll();
        logger.info(sysUserEntities);
    }

    @Test
    public void save(){
        SysUserEntity sysUserEntity = SysUserEntity.builder().userName("Ajdbc").loginName("李").build();
        SysUserEntity save = sysUserEntityUpdateService.save(sysUserEntity);
        logger.info(save);
    }

    @Test
    public void queryAsListValue(){
        String sql = TestSQLProvider.queryAsIntValueSQL();
        List<Integer> intList = proxyDaoSupport.queryAsIntValue(sql);
        List<Long> longList = proxyDaoSupport.queryAsLongValue(sql);
        logger.info(intList);
        logger.info(longList);
    }

    @Test
    public void queryAsListValuePage(){
        String sql = TestSQLProvider.queryAsIntValueSQL();
        List<Integer> list = proxyDaoSupport.queryAsIntValue(sql, 0, 2);
        logger.info(list);
    }

    @Test
    public void deleteSysUserEntityByIds(){
        List<Integer> ids = OpenSourceUtils.asList(4,7,8);
        int result = sysUserEntityUpdateService.deleteSysUserEntityByIds(ids);
        logger.info(result);
    }


}
