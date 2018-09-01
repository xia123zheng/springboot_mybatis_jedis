package com.xz.springbootjedis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
 * @author vic
 * @desc datasrouce config and mybatis scan config
 * 
 */

@Configuration
@EnableAutoConfiguration
@MapperScan("com.xz.springbootjedis.dao")
public class DataSourceConfig {
	
	private static Logger logger = Logger.getLogger(DataSourceConfig.class);

	@Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public ComboPooledDataSource dataSource() {
        return new ComboPooledDataSource();
    }   //设置数据源

    //这个DataSourceConfig类的sqlSessionFactoryBean（）方法有找到mapper类和mybatis-config.xml的作用。不过还是建议写到application.properties中。
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {  //引用 datasource，MyBatis配置，sql的xml扫描，以及各个插件的添加
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        ComboPooledDataSource dataSource  = dataSource();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:/mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
        logger.info("SqlSessionFactory bean init success.");
        return sqlSessionFactoryBean.getObject();
    }
    
    @Bean
    public DataSourceTransactionManager transactionManager() {        //设置事务
        return new DataSourceTransactionManager(dataSource());
    }
   
}
