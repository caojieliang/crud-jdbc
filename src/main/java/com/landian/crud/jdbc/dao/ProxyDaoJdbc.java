package com.landian.crud.jdbc.dao;

import com.landian.crud.core.dao.ProxyDao;
import com.landian.crud.core.dao.SQLPageUtils;
import com.landian.crud.core.result.SingleValue;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Statement;
import java.util.*;
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

    public int doInsertWidthId(String sql) {
        jdbcTemplate.execute(sql);
        return 1;
    }

    @Override
    public Object doInsertAndReturnId(String sql) {
        final String sqlFinal = sql;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator =
                (con) -> jdbcTemplate.getDataSource().getConnection().prepareStatement(sqlFinal, Statement.RETURN_GENERATED_KEYS);
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public int doUpdate(String sql) {
        jdbcTemplate.update(sql);
        return 1;
    }

    @Override
    public List<HashMap<String, Object>> doFind(String sql) {
        List<Map<String, Object>> dataMapList = jdbcTemplate.queryForList(sql);
        return mapList2HashMapList(dataMapList);
    }

    private List<HashMap<String, Object>> mapList2HashMapList(List<Map<String, Object>> mapList){
        if(CollectionUtils.isEmpty(mapList)){
            return Collections.EMPTY_LIST;
        }
        List<HashMap<String, Object>> targetList = new ArrayList<>();
        mapList.forEach(b -> {
            if(MapUtils.isNotEmpty(b)){
                HashMap<String, Object> cruMap = new HashMap<>();
                b.entrySet().forEach(set -> cruMap.put(set.getKey(),set.getValue()));
                targetList.add(cruMap);
            }
        });
        return targetList;
    }

    @Override
    public List<HashMap<String, Object>> doFindPage(String sql, int start, int pageSize) {
        String sqlTarget = SQLPageUtils.appendLimit(sql, start, pageSize);
        return doFind(sqlTarget);
    }

    @Override
    public int doDelete(String sql) {
        jdbcTemplate.update(sql);
        return 1;
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
