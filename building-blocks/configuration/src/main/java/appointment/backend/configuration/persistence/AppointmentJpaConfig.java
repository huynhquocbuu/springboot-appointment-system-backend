package appointment.backend.configuration.persistence;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "appointment.backend.infrastructure.persistence.appointment.repositories",
        entityManagerFactoryRef = "appointmentEntityManager",
        transactionManagerRef = "appointmentTransactionManager"
)
public class AppointmentJpaConfig {
    //@Autowired
    private Environment env; // Contains Properties Load by @PropertySources
    public AppointmentJpaConfig(Environment env){
        this.env = env;
    }
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean appointmentEntityManager(
            @Qualifier("appointmentDataSource") DataSource appointmentDataSource
    ){
        LocalContainerEntityManagerFactoryBean entityManager
                = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(appointmentDataSource);
        entityManager.setPackagesToScan(
                new String[] { env.getProperty("app.persistence.appointment.entities.package") });


        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(vendorAdapter);

        Map<String, String> appointmentJpaProperties = new HashMap<>();
        //appointmentJpaProperties.put("hibernate.dialect", env.getProperty("app.persistence.appointment.hibernate.dialect"));
        appointmentJpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("app.persistence.appointment.hibernate.hbm2ddl.auto"));
        appointmentJpaProperties.put("hibernate.show-sql", env.getProperty("app.persistence.appointment.hibernate.show-sql"));

        entityManager.setJpaPropertyMap(appointmentJpaProperties);
        return entityManager;
    }
    @Bean
    @Primary
    public PlatformTransactionManager appointmentTransactionManager(
            @Qualifier("appointmentEntityManager") EntityManagerFactory entityManagerFactory
    ) {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager(entityManagerFactory);

        return  transactionManager;
    }
}
