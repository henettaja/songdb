package hh.swd20.harjtyo.musicrepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "hh.swd20.harjtyo.musicrepo")
@PropertySource("H2JpaTestConfig.properties")
@EnableTransactionManagement
public class MusicAppTestJpaConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc:h2:mem:db"));
        dataSource.setUsername(env.getProperty("sa"));
        dataSource.setPassword(env.getProperty("sa"));

        return dataSource;
    }

    // configure entityManagerFactory

    // configure transactionManager

    // configure additional Hibernate Properties
}
