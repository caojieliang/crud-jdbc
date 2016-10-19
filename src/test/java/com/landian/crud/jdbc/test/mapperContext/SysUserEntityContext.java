package com.landian.crud.jdbc.test.mapperContext;


import com.landian.crud.jdbc.test.entity.SysUserEntity;
import com.landian.sql.jpa.annotation.IdTypePolicy;
import com.landian.sql.jpa.context.BeanContext;
import com.landian.sql.jpa.context.BeanContextSupport;

public class SysUserEntityContext {
	public static String tableName = "sys_user";
	public static String idFieldName = "id";
	private static BeanContext beanContext = BeanContextSupport
			.newInstance(tableName, idFieldName, IdTypePolicy.INTEGER, SysUserEntity.class);

	public static BeanContext getBeanContext(){
		return beanContext;
	}
}
