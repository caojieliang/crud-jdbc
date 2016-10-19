package com.landian.crud.jdbc.test.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jie on 2016/10/19.
 */
public class OpenSourceUtils {
    /**
     * 开源Lists类工具好像没引入
     * @param values
     * @return
     */
    public static List<Integer> asList(int... values){
        List<Integer> list = new ArrayList<>();
        for (int value : values) {
            list.add(value);
        }
        return list;
    }
}
