package org.example.dao;

import org.example.models.Author;
import org.example.models.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class BookDao {
    private SessionFactory sessionFactory;
    private Session session;

    private Configuration configuration = new Configuration().addAnnotatedClass(Book.class);

    public BookDao() {
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.getCurrentSession();
    }

    public void updateBookById(int id, String newName) {
        sessionBegin();

        Book book = session.get(Book.class, id);
        book.setName(newName);
        session.save(book);
        sessionEnd();
    }
    public Map<String,String> getBookById(int id){
        sessionBegin();
        Map<String,String> nameAuthor = new HashMap<String, String>();
        Book book = session.get(Book.class,id);

        nameAuthor.put(book.getName(), book.getAuthor().getName());
        sessionEnd();
        return nameAuthor;
    }

    private void sessionEnd() {
        session.getTransaction().commit();
        sessionFactory.close();
    }

    private void sessionBegin() {
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
    }
}
