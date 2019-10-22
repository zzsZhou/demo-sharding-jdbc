package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingjdbc.core.jdbc.core.datasource.ShardingDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouq
 * @version 1.0
 * @date 2019/10/22
 */
@Configuration
@MapperScan(basePackages = "com.example.demo.mapper", sqlSessionTemplateRef = "testSqlSessionTemplate")
public class ShardingDataSourceConfig {

    @Autowired
    private DataSource0Config dataSource0Config;

    @Autowired
    private DataSource1Config dataSource1Config;

    /**
     * 配置分库分表策略
     *
     * @return
     * @throws SQLException
     */
    @Bean(name = "shardingDataSource")
    DataSource getShardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration());
        shardingRuleConfig.getBindingTableGroups().add("user_info");
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("group_id", DatabaseShardingAlgorithm.class.getName()));
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", TableShardingAlgorithm.class.getName()));
        return new ShardingDataSource(shardingRuleConfig.build(createDataSourceMap()));
    }


    /**
     * 设置表的node
     *
     * @return
     */
    @Bean
    TableRuleConfiguration getUserTableRuleConfiguration() {
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
        orderTableRuleConfig.setLogicTable("user_info");
        orderTableRuleConfig.setActualDataNodes("user_${0..1}.user_info_${0..1}");
        orderTableRuleConfig.setKeyGeneratorColumnName("user_id");
        return orderTableRuleConfig;
    }


    /**
     * 需要手动配置事务管理器
     *
     * @param shardingDataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactitonManager(DataSource shardingDataSource) {
        return new DataSourceTransactionManager(shardingDataSource);
    }

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource shardingDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(shardingDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    private Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put(dataSource0Config.getDatabaseName(), dataSource0Config.getDataSource());
        result.put(dataSource1Config.getDatabaseName(), dataSource1Config.getDataSource());
        return result;
    }

    private DataSource createDataSource(final String dataSourceName) {
        DruidDataSource datasource = new DruidDataSource();
//        BasicDataSource result = new BasicDataSource();
//        datasource.setPassword(ConfigTools.decrypt(publicKey, password));
        datasource.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        datasource.setUrl(String.format("jdbc:mysql://localhost:3306/%s", dataSourceName));
        datasource.setUsername("root");
        datasource.setPassword("");
        return datasource;
    }


}
