package org.example;

import org.example.entity.Comment;
import org.example.entity.Worker;
import org.example.entity.Project;
import org.example.entity.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.swing.*;
import java.awt.desktop.QuitResponse;
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

                Department department = new Department();
                department.setId(1);
                department.setDepartment("Programavimas");
                session.save(department);
                Department department1 = new Department();
                department1.setId(2);
                department1.setDepartment("Testavimas");
                session.save(department1);
                Department department2 = new Department();
                department2.setId(3);
                department2.setDepartment("HR");
                session.save(department2);
                Department department3 = new Department();
                department3.setId(4);
                department3.setDepartment("Marketingas");
                session.save(department3);
                Department department4 = new Department();
                department4.setId(5);
                department4.setDepartment("Dizainas");
                session.save(department4);

                Project project = new Project();
                project.setId(3);
                project.setProject("RndProject");
                session.save(project);
                Project project1 = new Project();
                project1.setId(4);
                project1.setProject("Almanac");
                session.save(project1);
                Project project2 = new Project();
                project2.setId(5);
                project2.setProject("Grok");
                session.save(project2);
                Project project3 = new Project();
                project3.setId(6);
                project3.setProject("SEEKdeep");
                session.save(project3);
                Project project4 = new Project();
                project4.setId(7);
                project4.setProject("Clautsy");
                session.save(project4);

                for (int i = 0; i < 5; i++) {
                    Worker worker = new Worker();
                    worker.setName("Jonas"+i);
                    worker.setLastName("Jonaitis"+i);
                    worker.setProject(project2);
                    int rnd = 0+i;
                    worker.setPersonalCode(12345678900L +rnd);
                    session.save(worker);
                }

                Worker worker = new Worker();
                worker.setName("Antanas");
                worker.setPersonalCode(99988874561L);
                worker.setProject(project3);
                session.save(worker);

                assingSameProjectToAllWorkers(session);

                System.out.println(worker.getProject());

                everyOtherPorjectNameUpdate(session);

                tx.commit();

                printExistingWorkers(session);
                printAllProjects(session);

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
    private static int assingSameProjectToAllWorkers(Session session){
        Query<Worker> assignProjectToAll = session.createQuery(
                "UPDATE Worker w SET w.project = (FROM Project p WHERE p.id = 3)"
        );
        return assignProjectToAll.executeUpdate();
    }
    private static List<Worker> getAllWorker(Session session){
        Query<Worker> queryAllWorkers = session.createQuery("FROM Worker", Worker.class);
        return queryAllWorkers.list();
    }
    private static void printExistingWorkers(Session session){
        getAllWorker(session).forEach(System.out::println);
    }

    private static int everyOtherPorjectNameUpdate(Session session){
        Query<Worker> updateProjectName = session.createQuery(
                "UPDATE Project SET project = :p WHERE id = :id");
        int getFirstId = gettAllProjects(session).getFirst().getId();
        for (int i = 0; i < gettAllProjects(session).size(); i++) {
            updateProjectName.setParameter("id",getFirstId+i);
            updateProjectName.setParameter("p",gettAllProjects(session).get(i).getProject().toUpperCase());
            updateProjectName.executeUpdate();
            i++;
        }
        return updateProjectName.executeUpdate();
    }

    private static void printAllProjects(Session session){
        gettAllProjects(session).forEach(System.out::println);
    }

    private static List<Project> gettAllProjects(Session session){
        Query<Project> projectQuery = session.createQuery("FROM Project", Project.class);
        return projectQuery.list();
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