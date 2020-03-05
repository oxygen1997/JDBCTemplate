package com.zy.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;

import java.util.Properties;

public class JDBCUtil {
    private static DataSource dataSource = null;
    static {
        InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
        Properties pro = new Properties();
        try {
            pro.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DataSource getDataSource() throws Exception {
        return dataSource;
    }
}
