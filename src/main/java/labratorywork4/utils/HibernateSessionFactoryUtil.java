package labratorywork4.utils;

import labratorywork4.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/** У HibernateSessionFactoryUtil всего одна задача — создавать для нашего приложения фабрику сессий для работы с БД.
 * В этом классе мы создаем новый объект конфигураций Configuration, и передаем ему те классы, которые он должен
 * воспринимать как сущности. */

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Album.class);
                configuration.addAnnotatedClass(Musician.class);
                configuration.addAnnotatedClass(Composition.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}
