package com.fallen.springboot;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.support.SpringBootServletInitializer;  //1.5.6
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.fallen.springboot.dao.BaseDaoImpl;

/**
 * 该注解指定项目为springboot，由此类当作程序入口 自动装配 web 依赖的环境
 * 
 **/

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories(repositoryBaseClass = BaseDaoImpl.class)
public class SpringbootApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringbootApplication.class);
	}
	 

	// spring boot 1.5.6
//	@Bean  
//    public HibernateJpaSessionFactoryBean sessionFactory() {  
//        return new HibernateJpaSessionFactoryBean();  
//    }  
	
	
//	  @Bean
//	    public SessionFactory sessionFactory(@Qualifier("entityManagerFactory") EntityManagerFactory emf){
//	         return emf.unwrap(SessionFactory.class);
//	     }
//	@Autowired
//	@PersistenceContext
//	private EntityManager entityManager;
//
//	   @Bean
//	   public SessionFactory sessionFactory() {
//	      if (entityManager.unwrap(SessionFactory.class) == null) {
//	         throw new NullPointerException("factory is not a hibernate factory");
//	      }
//	      return entityManager.unwrap(SessionFactory.class);
//	   }
}
