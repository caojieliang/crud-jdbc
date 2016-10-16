package com.landian.crud.jdbc.test.mapperContext;


import com.landian.crud.jdbc.test.entity.SysUserEntity;
import com.landian.sql.jpa.annotation.IdTypePolicy;
import com.landian.sql.jpa.context.BeanContext;

public class SysUserEntityContext {
	public static String tableName = "sys_user";
	public static String idFieldName = "id";
	public static IdTypePolicy idTypePolicy = IdTypePolicy.INTEGER;
	private static BeanContext<SysUserEntity> beanContext = null;
	
	static {
		beanContext = new BeanContext<SysUserEntity>(){
			@Override
			public String getTableName() {
				return tableName;
			}
			@Override
			public String getIdFieldName() {
				return idFieldName;
			}
			@Override
			public IdTypePolicy getIdType() {
				return idTypePolicy;
			}
			@Override
			public Class<SysUserEntity> getBeanClass() {
				return SysUserEntity.class;
			}
		};
	}
	
	public static BeanContext<SysUserEntity> getBeanContext(){
		return beanContext;
	}
}
