package labratorywork4.dao;

import labratorywork4.models.Musician;
import labratorywork4.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/** DAO (data access object) — один из наиболее распространенных паттернов проектирования.
 *  Его смысл — создать в приложении слой, который отвечает только за доступ к данным. */

public class MusicShopDao {

    public void saveMusician(Musician musician) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(musician);
        tx1.commit();
        session.close();
    }
}
