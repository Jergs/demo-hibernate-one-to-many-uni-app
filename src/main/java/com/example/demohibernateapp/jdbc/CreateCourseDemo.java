package com.example.demohibernateapp.jdbc;

import com.example.demohibernateapp.entity.Course;
import com.example.demohibernateapp.entity.Instructor;
import com.example.demohibernateapp.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        try (factory; Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Instructor instructor = session.get(Instructor.class, 1);
            Course course = new Course("Java course");
            Course courseCSharp = new Course("C# course");

            instructor.add(course);
            instructor.add(courseCSharp);

            session.save(course);
            session.save(courseCSharp);

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
