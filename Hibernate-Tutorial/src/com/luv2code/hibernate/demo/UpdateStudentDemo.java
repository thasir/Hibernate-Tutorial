package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {
	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();

		try {

			int studentId = 3;
			// now get a new session start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			// retreive the student based on id:primary key
			System.out.println("\ngetting student with id :" + studentId);
			Student myStudent = session.get(Student.class, studentId);
			System.out.println("Updating Student...");
			myStudent.setFirstName("Scooby");
			myStudent.setLastName("Doo");
			myStudent.setEmail("scooby@gmail.com");
			System.out.println(myStudent);
			// commit the transaction
			session.getTransaction().commit();
			// NEW CODE
			session = factory.getCurrentSession();
			session.beginTransaction();
			// update email for all students
			System.out.println("updating emails for all students");
			session.createQuery("update Student set email = 'foo@gmail.com'").executeUpdate();

			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!...");
		} finally {
			factory.close();
		}
	}
}
