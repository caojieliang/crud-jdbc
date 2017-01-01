package com.landian.crud.jdbc.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import java.util.Date;

/**
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysUserEntity {

    /**
     * ID
     */
    private Integer id;
    /**
     * loginName
     */
    private String loginName;
    /**
     * userName
     */
    private String userName;
    /**
     * createTime
     */
    private Date createTime;


}
