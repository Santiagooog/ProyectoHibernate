package org.example;

import org.example.models.Author;
import org.example.models.ManageExe;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {

        update();
    }

    public static void getAuthors() {
        ArrayList<Author> authors = new ArrayList<>();

        Session session = ManageExe.getSessionFactory().openSession();
        Transaction transaction = null;

            session.createSelectionQuery("from Author", Author.class).getResultList().forEach(event -> out.println("Event (" + event.getName()+ event.getLastName()+ event.getEmail()+ event.getAge()));


    }
    public static void create() {
    Session session = ManageExe.getSessionFactory().openSession();
    Transaction transaction = null;

    try{
        transaction = session.beginTransaction();

        Author author = new Author();
        author.setName("Francisco");
        author.setLastName("Espinoza");
        author.setEmail("francisco@gmail.com");
        author.setAge(20);

        session.save(author);

        transaction.commit();

    }catch(Exception e) {
        if(transaction != null){
            transaction.rollback();
        }
        e.printStackTrace();
    }finally{
        session.close();
        }
        ManageExe.shutdown();

    }

    public static void update() {
        Session session = ManageExe.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try{
            Author author = new Author();
            author.setId(1);
            author.setName("Tutankamon");
            author.setLastName("Espinoza");
            author.setEmail("francisco@gmail.com");
            author.setAge(20);

            session.merge(author);
            transaction.commit();
        }catch(Exception e) {
            if(transaction != null){
                transaction.rollback();
            }e.printStackTrace();
        }finally{
            session.close();
        }
    }
}