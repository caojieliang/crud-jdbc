package com.landian.crud.jdbc.test.dao;

import com.landian.crud.jdbc.test.mapperContext.SysUserEntityContext;
import com.landian.sql.builder.SQL;

/**
 * Created by jie on 2016/10/16.
 */
public class TestSQLProvider {
    public static String queryAsIntValueSQL() {
        SQL sql = new SQL();
        sql.SELECT("id").FROM(SysUserEntityContext.tableName);
        return sql.toString();
    }
}
