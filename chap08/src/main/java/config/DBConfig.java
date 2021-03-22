package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {

    @Bean(destroyMethod = "close")
    public DataSource dataSource(){
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
        dataSource.setUsername("spring5");
        dataSource.setPassword("spring5");
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(10);
        return dataSource;
    }
}
