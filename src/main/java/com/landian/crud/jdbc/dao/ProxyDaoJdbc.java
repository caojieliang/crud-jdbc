package com.landian.crud.jdbc.dao;

import com.landian.crud.core.dao.ProxyDao;
import com.landian.crud.core.result.SingleValue;
import com.landian.crud.core.sql.InsertSQL;
import com.landian.crud.core.sql.UpdateSQL;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ProxyDaoJdbc implements ProxyDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int doInsert(String sql) {
        jdbcTemplate.execute(sql);
        return 1;
    }

//    http://www.iteye.com/topic/1135650 这里有批量插入例子
//    public void addBuyBean(List<BuyBean> list)
//    {
//        final List<BuyBean> tempBpplist = list;
//        String sql="insert into buy_bean(id,bid,pid,s,datetime,mark,count)" +
//                " values(null,?,?,?,?,?,?)";
//        this.getJdbcTemplate().batchUpdate(sql,new BatchPreparedStatementSetter() {
//
//            @Override
//            public int getBatchSize() {
//                return tempBpplist.size();
//            }
//            @Override
//            public void setValues(PreparedStatement ps, int i)
//                    throws SQLException {
//                ps.setInt(1, tempBpplist.get(i).getBId());
//                ps.setInt(2, tempBpplist.get(i).getPId());
//                ps.setInt(3, tempBpplist.get(i).getS());
//                ps.setString(4, tempBpplist.get(i).getDatetime());
//                ps.setString(5, tempBpplist.get(i).getMark());
//                ps.setInt(6, tempBpplist.get(i).getCount());
//            }
//        });
//    }

    /**
     * @param insertSQL InsertSQL对象
     * @return
     */
    public int doInsertWidthId(InsertSQL insertSQL) {
        List<Object> params = insertSQL.getParams();
        final String insertSql = insertSQL.getSql().toString();
        int result = jdbcTemplate.update(insertSql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                if(CollectionUtils.isNotEmpty(params)){
                    for(int i=0; i<params.size(); i++){
                        Object value = params.get(i);
                        pstmt.setObject(i+1, value);
                    }
                }
            }
        });
        return result;
    }

    @Override
    public Object doInsertAndReturnId(InsertSQL insertSQL) {
        final String insertSql = insertSQL.getSql().toString();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        List<Object> params = insertSQL.getParams();
        jdbcTemplate.update(new PreparedStatementCreator(){
            public java.sql.PreparedStatement createPreparedStatement(Connection con) throws SQLException{
                java.sql.PreparedStatement ps = con.prepareStatement(insertSql);
                ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                if(CollectionUtils.isNotEmpty(params)){
                    for(int i=0; i<params.size(); i++){
                        Object value = params.get(i);
                        ps.setObject(i+1, value);
                    }
                }
                return ps;
            }
        },keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public int doUpdate(UpdateSQL updateSQL) {
        String sql = updateSQL.getSql().toString();
        List<Object> params = new ArrayList();
        params.addAll(updateSQL.getUpdateParams());
        params.addAll(updateSQL.getWhereParams());
        Object[] valArr = params.toArray();
        return jdbcTemplate.update(sql, valArr);
    }

    @Override
    public int doUpdate(String sql) {
        jdbcTemplate.update(sql);
        return 1;
    }

    @Override
    public List<Map<String, Object>> doFind(String sql) {
        List<Map<String, Object>> dataMapList = jdbcTemplate.queryForList(sql);
        return dataMapList;
    }

    @Override
    public int doDelete(String sql) {
        int result = jdbcTemplate.update(sql);
        return result;
    }

    @Override
    public List<Long> queryAsLongValue(String sql) {
        List<Object> objectList = queryAsSingleList(sql);
        if(CollectionUtils.isEmpty(objectList)){
            return Collections.EMPTY_LIST;
        }
        return objectList.stream().map(b -> SingleValue.newInstance(b).longValue()).collect(Collectors.toList());
    }

    @Override
    public List<Integer> queryAsIntValue(String sql) {
        List<Object> objectList = queryAsSingleList(sql);
        if(CollectionUtils.isEmpty(objectList)){
            return Collections.EMPTY_LIST;
        }
        return objectList.stream().map(b -> SingleValue.newInstance(b).integerValue()).collect(Collectors.toList());
    }

    private List<Object> queryAsSingleList(String sql){
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        if(CollectionUtils.isEmpty(mapList)){
            return Collections.EMPTY_LIST;
        }
        List<Object> targetList = new ArrayList<>();
        mapList.forEach(b -> {
            Object first = b.values().stream().findFirst().get();
            targetList.add(first);
        });
        return targetList;
    }
}
