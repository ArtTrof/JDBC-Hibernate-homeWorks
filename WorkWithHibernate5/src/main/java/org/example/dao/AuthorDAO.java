package org.example.dao;

import org.example.models.Author;
import org.example.models.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AuthorDAO {
    private SessionFactory sessionFactory;
    private Session session;

    private Configuration configuration = new Configuration().addAnnotatedClass(Book.class);

    public AuthorDAO() {

    }

    public void updateAuthorById(int id, String newName) {
        sessionBegin();
        Author author = session.get(Author.class, id);
        author.setName(newName);
        session.save(author);
        sessionClose();
    }

    public void insert200Times(){
        sessionBegin();
        for (int i = 0; i < 20; i++) {
            Author author = new Author("Author"+i);
            if (i%10==0)
            session.flush();
        }

        sessionClose();
    }

    private void sessionClose() {
        session.getTransaction().commit();
        sessionFactory.close();
    }

    private void sessionBegin() {
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
    }
}
