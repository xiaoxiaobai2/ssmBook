package com.zhanghao.config;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;


/**
 *  主配置文件
 */
@Configuration
@ComponentScan("com.zhanghao.*")
//开启事务
@EnableTransactionManagement
public class RootConfig implements TransactionManagementConfigurer {


    private DataSource dataSource;

    /*
        配置数据源
     */
    @Bean("dataSource")
    public DataSource initDataSource(){
        if (dataSource!=null)
            return dataSource;
        try {

//            C3P0PooledDataSource c3P0PooledDataSource = new C3P0PooledDataSource();
//            c3P0PooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
//            c3P0PooledDataSource.setPassword("123456");
//            c3P0PooledDataSource.setUser("root");
//            c3P0PooledDataSource.setJdbcUrl("jdbc:mysql:///ssm");
            Properties properties = new Properties();
            properties.setProperty("driverClassName","com.mysql.jdbc.Driver");
            properties.setProperty("url","jdbc:mysql:///ssmbook");
            properties.setProperty("username","root");
            properties.setProperty("password","123456");
            dataSource = BasicDataSourceFactory.createDataSource(properties);
//            Class.forName("com.mysql.jdbc.Driver");
//            Driver driver = DriverManager.getDriver("com.mysql.jdbc.Driver");
//            dataSource =
//                    new SimpleDriverDataSource(driver,"jdbc:mysql:///ssm","root","123456");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    /*
        配置SqlSessionFactoryBean  加载mybatis配置文件
     */
    @Bean("sqlSessionFactory")
    public SqlSessionFactoryBean initSqlSessionFactoryBean(){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        Resource resource = new ClassPathResource("SqlMapConfig.xml");
        sqlSessionFactoryBean.setConfigLocation(resource);
        sqlSessionFactoryBean.setDataSource(initDataSource());
        return sqlSessionFactoryBean;
    }


    /*
        指定要扫描的映射文件路径
     */
    @Bean
    public MapperScannerConfigurer initMapperScannerConfigur(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.zhanghao.dao");
        mapperScannerConfigurer.setAnnotationClass(Repository.class);
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }

    /*
        配置事务管理器
     */
    @Bean(name="annotationDrivenTransactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(initDataSource());
        return dataSourceTransactionManager;
    }
}
