package com.example.demohibernateapp.jdbc;

import com.example.demohibernateapp.entity.Course;
import com.example.demohibernateapp.entity.Instructor;
import com.example.demohibernateapp.entity.InstructorDetail;
import com.example.demohibernateapp.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class GetCoursesAndReviewsDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        try (factory; Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Course course = session.get(Course.class, 10);

            List<Review> reviews = course.getReviews();
            reviews.forEach(System.out::println);

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
