package org.example;

import org.example.model.Author;
import org.example.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Book.class).addAnnotatedClass(Author.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            updateAuthorByLastNameLength(session);

            deleteBookById(session, 2);

            deleteBookByAuthorId(session, 3);

            findAuthorByNameAndLastName(session, "Bob", "Big");

            deleteAuthorWhereNameAndLastNameLike(session, "Biba", "Be%");

            deleteAuthorWhereNameLike(session, "Viktor");

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
        //task5
    private static void deleteAuthorWhereNameLike(Session session, String name) {
        Query query = session.createQuery("delete  from Author a where name like(:newName) ");
        query.setParameter("newName", name);
        query.executeUpdate();
    }
    //task5
    private static void deleteAuthorWhereNameAndLastNameLike(Session session, String name, String lastName) {
        Query query = session.createQuery("delete  from Author a where name like(:newName) and lastNamelike(:newLastName)");
        query.setParameter("newName", name);
        query.setParameter("newLastName", lastName);
        query.executeUpdate();
    }
//task4
    private static void findAuthorByNameAndLastName(Session session, String newName, String newLastName) {
        Query query = session.createQuery("Select a.name,a.lastName from Author a where name = :newName and lastName=:newLastName");
        query.setParameter("newName", newName);
        query.setParameter("newLastName", newLastName);
        List<Author> authors = query.getResultList();
    }

    //task3.2
    private static void deleteBookByAuthorId(Session session, int id) {
        Author author = session.get(Author.class, id);
        List<Book> books = author.getBooks();
        for (Book book : books) {
            session.delete(book);
        }
    }

    //task3.1
    private static void deleteBookById(Session session, int id) {
        Book book = session.get(Book.class, id);
        session.delete(book);
    }

    //task2
    private static void updateAuthorByLastNameLength(Session session) {
        Query query = session.createQuery("UPDATE Author SET name = :newName WHERE LENGTH(lastName) > 7");
        query.setParameter("newName", 1);
        query.executeUpdate();
    }
}
