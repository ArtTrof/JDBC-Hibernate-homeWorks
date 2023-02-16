package homework;

import homework.entity.Animal;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class AnimalHelper {
    private  SessionFactory sessionFactory;

    public AnimalHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    public List<Animal> getAnimals(){
        // открыть сессию - для манипуляции с персист. объектами
        Session session = sessionFactory.openSession();

        Criteria cb = session.createCriteria(Animal.class);// не использовать session.createCriteria, т.к. deprecated

        List<Animal> animalsList = cb.list();

        session.close();

        return animalsList;
    }

    public Animal getAnimalByID(long id) {
        Session session = sessionFactory.openSession();
        Animal animal = (Animal) session.get(Animal.class, id); // получение объекта по id
        session.close();
        return animal;
    }

    public Animal addAnimal(Animal animal){

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(animal);

        session.getTransaction().commit();

        session.close();

        return animal;

    }
}
