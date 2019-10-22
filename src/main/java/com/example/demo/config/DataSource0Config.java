package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author zhouq
 * @version 1.0
 * @date 2019/10/22
 */
@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "spring.datasource.0")
public class DataSource0Config {

    protected String databaseName;
    protected String dbUrl;
    protected String username;
    protected String password;
    protected String publicKey;
    protected String driverClassName;
    protected int initialSize;
    protected int minIdle;
    protected int maxActive;
    protected int maxWait;
    protected int timeBetweenEvictionRunsMillis;
    protected int minEvictableIdleTimeMillis;
    protected String validationQuery;
    protected boolean testWhileIdle;
    protected boolean testOnBorrow;
    protected boolean testOnReturn;
    protected String filters;
    protected String logSlowSql;
    protected String connectionProperties;

    protected String druidMonitorUsername;
    protected String druidMonitorPassword;
    protected String druidMonitorBaseurl;


    @Bean
    public DataSource getDataSource(){
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(dbUrl);
        datasource.setUsername(username);
        //数据库密码加密的话使用这个
        //datasource.setPassword(ConfigTools.decrypt(publicKey, password));
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setConnectionProperties(connectionProperties);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            log.error("druid configuration initialization filter:{}", e);
        }
        return datasource;
    }
    @Bean
    public ServletRegistrationBean getServletRegistrationBean(){
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        //配置白名单ip
        //reg.addInitParameter("allow","192.168.6.195");
        //配置访问URL，必须以*结尾
        reg.addUrlMappings(druidMonitorBaseurl);
        //配置用户名
        reg.addInitParameter("loginUsername", druidMonitorUsername);
        //配置密码
        reg.addInitParameter("loginPassword", druidMonitorPassword);
        //是否启用慢sql
        reg.addInitParameter("logSlowSql", logSlowSql);
        return reg;
    }
    @Bean
    public FilterRegistrationBean getFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        //配置那些资源不被拦截
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

    @Bean
    public String getDatabaseName(){
        return databaseName;
    }

}
