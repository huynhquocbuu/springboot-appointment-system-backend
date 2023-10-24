package appointment.backend.configuration.persistence;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class DatasourceConfig {
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.appointment")
    public DataSourceProperties appointmentDataSourceProperties() {
        log.info("----------DataSourceProperties-----------");
        return new DataSourceProperties();
    }

    //1-
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.appointment.hikari")
    public DataSource appointmentDataSource() {
        log.info("----------DataSource-----------");
        return appointmentDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
}
