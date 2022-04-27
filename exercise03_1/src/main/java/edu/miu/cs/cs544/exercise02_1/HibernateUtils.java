package edu.miu.cs.cs544.exercise02_1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

    public static SessionFactory getSessionFactory(Class entityClasses){
        SessionFactory sessionFactory=null;

        Map<String,Object> settings=new HashMap<>();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/cs544");
        settings.put(Environment.USER, "root");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        settings.put(Environment.SHOW_SQL, "true");
        //settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "update");

        try{
            ServiceRegistry standardRegistry=new StandardServiceRegistryBuilder()
                    .applySettings(settings).build();

            Metadata metadata=new MetadataSources(standardRegistry)
                    .addAnnotatedClass(entityClasses)
                    .getMetadataBuilder()
                    .build();

            sessionFactory= metadata.getSessionFactoryBuilder().build();
            return sessionFactory;
        }catch (Throwable ex){
            throw new ExceptionInInitializerError(ex);
        }
    }
}
