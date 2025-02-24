package org.example;

import org.example.entity.Comment;
import org.example.entity.Darbuotojas;
import org.example.entity.Projektas;
import org.example.entity.Skyrius;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        String baseAuthor = "Author";
        String letterToAdd = "s";
        String baseContent = "lorem impsum";
        String contentToAdd = " aha";
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                Transaction tx = session.beginTransaction();
                for (int i = 0; i < 5; i++) {
                    Darbuotojas darbuotojas = new Darbuotojas();
                    darbuotojas.setName("Jonas"+i);
                    darbuotojas.setLastName("Jonaitis"+i);
                    int rnd = 0+i;
                    darbuotojas.setPersonalCode(12345678900L +rnd);
                    session.save(darbuotojas);
                }
                Skyrius skyrius = new Skyrius();
                skyrius.setId(1);
                skyrius.setSkyrius("Programavimas");
                session.save(skyrius);
                Skyrius skyrius1 = new Skyrius();
                skyrius1.setId(2);
                skyrius1.setSkyrius("Testavimas");
                session.save(skyrius1);
                Skyrius skyrius2 = new Skyrius();
                skyrius2.setId(3);
                skyrius2.setSkyrius("HR");
                session.save(skyrius2);
                Skyrius skyrius3 = new Skyrius();
                skyrius3.setId(4);
                skyrius3.setSkyrius("Marketingas");
                session.save(skyrius3);
                Skyrius skyrius4 = new Skyrius();
                skyrius4.setId(5);
                skyrius4.setSkyrius("Dizainas");
                session.save(skyrius4);

                Projektas projektas = new Projektas();
                projektas.setId(3);
                projektas.setProjektas("RndProject");
                session.save(projektas);
                Projektas projektas1 = new Projektas();
                projektas1.setId(4);
                projektas1.setProjektas("Almanac");
                session.save(projektas1);
                Projektas projektas2 = new Projektas();
                projektas2.setId(5);
                projektas2.setProjektas("Grok");
                session.save(projektas2);
                Projektas projektas3 = new Projektas();
                projektas3.setId(6);
                projektas3.setProjektas("SEEKdeep");
                session.save(projektas3);
                Projektas projektas4 = new Projektas();
                projektas4.setId(7);
                projektas4.setProjektas("Clautsy");
                session.save(projektas4);


                tx.commit();
//                Transaction tx = session.beginTransaction();
//                for (int i = 0; i < 5; i++) {
//                    Comment comment = new Comment();
//                    comment.setAuthor(baseAuthor+=letterToAdd);
//                    comment.setContent(baseContent+=contentToAdd);
//                    session.save(comment);
//                }
//                printExistingPayemtns(session);
//                int count = updateCommentContentById(session,"Naujas komentaro turinys",1);
//                System.out.println(count + " comment updated!");
//                tx.commit();
            }
        }

    }

    private static int updateCommentContentById(Session session, String newContent, Integer id){
        Query<Comment> updateCommentById = session.createQuery(
                "UPDATE Comment SET content = :content WHERE id = :id");
        updateCommentById.setParameter("id", id);
        updateCommentById.setParameter("content",newContent);
        return updateCommentById.executeUpdate();
    }

    private static List<Comment> getAllComments(Session session) {
        Query<Comment> queryAllComments = session.createQuery("FROM Comment", Comment.class);
        return queryAllComments.list();
    }

    private static void printExistingPayemtns(Session session) {
        getAllComments(session).forEach(System.out::println);
    }
}