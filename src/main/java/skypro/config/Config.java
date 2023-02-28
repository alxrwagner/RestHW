package skypro.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "skypro")
@EnableWebMvc
@EnableTransactionManagement
public class Config {


    @Bean
    public DataSource getDataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try{
            dataSource.setDriverClass("org.postgresql.Driver"); // устанавливаем драйвер
            dataSource.setJdbcUrl("jdbc:postgresql://localhost/hwdb"); // устанавливаем url для подключения к БД
            dataSource.setUser("postgres"); // устанавливаем пользователя БД
            dataSource.setPassword("0000"); // устанавливаем пароль БД
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getLocalSession(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource()); // устанавливаем dataSource
        sessionFactory.setPackagesToScan("skypro.entity"); // устанавливаем пакет для сканирования на предмет entity

        Properties properties = new Properties(); // добавляем пакет property из пакета java.util
        properties.setProperty("dialect", "org.hibernate.dialect.PostgreSQLDialect"); // устанавливаем диалект
        properties.setProperty("show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");

        sessionFactory.setHibernateProperties(properties); // устанавливаем настройки в sessionFactory

        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();

        transactionManager.setSessionFactory(getLocalSession().getObject()); // устанавливаем sessionFactory, прописывая getObject(), мы получаем сам sessionFactory, а не бин localSessionFactory

        return transactionManager;
    }
}
