package homework;

import com.mysql.cj.xdevapi.SessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.*;

import java.util.List;

public class BookHelper {

    private org.hibernate.SessionFactory sessionFactory;

    public BookHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Book> getBooksList() {
        Session session = sessionFactory.openSession();
        Criteria cb = session.createCriteria(Book.class);
        List<Book> books = cb.list();
        session.close();
        return books;
    }

    public Book addBook(Book book){

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(book); // сгенерит ID и вставит в объект

        session.getTransaction().commit();

        session.close();

        return book;

    }
}


class HibernateUtil {

    private static org.hibernate.SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new AnnotationConfiguration().
                    configure("hibernate.xml").buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public static org.hibernate.SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
