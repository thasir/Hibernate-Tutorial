package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {
	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();

		try {
			// create a student object
			System.out.println("Creating new student object...");
			Student tempStudent1 = new Student("Thasir", "Ahmed", "ahmedshaik93@gmail.com");
			Student tempStudent2 = new Student("Taseer", "Ahmed", "thasirahmed@gmail.com");
			Student tempStudent3 = new Student("Foo", "Bar", "foobar@gmail.com");
			System.out.println("created");
			// start the transaction
			session.beginTransaction();
			// save the student object
			System.out.println("saving the students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!...");
		} finally {
			factory.close();
		}
	}
}
