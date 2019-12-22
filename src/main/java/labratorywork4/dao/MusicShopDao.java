package labratorywork4.dao;

import labratorywork4.models.Album;
import labratorywork4.models.Composition;
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

    public void saveAlbum(Album album) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(album);
        tx1.commit();
        session.close();
    }

    public void saveComposition(Composition composition) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(composition);
        tx1.commit();
        session.close();
    }

    public Musician findMusicianById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Musician musician = session.get(Musician.class, id);
        session.close();
        return musician;
    }

    public void updateComposition(Composition composition) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(composition);
        tx1.commit();
        session.close();
    }

    public void deleteComposition(Composition composition) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(composition);
        tx1.commit();
        session.close();
    }

    public Composition findCompositionById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Composition composition = session.get(Composition.class, id);
        session.close();
        return composition;
    }
}
