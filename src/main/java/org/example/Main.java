package org.example;

import org.example.entity.Comment;
import org.example.entity.Darbuotojas;
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