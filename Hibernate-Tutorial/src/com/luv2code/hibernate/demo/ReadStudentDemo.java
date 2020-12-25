package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {
	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();

		try {
			// create a student object
			System.out.println("Creating new student object...");
			Student tempStudent = new Student("Donald", "Duck", "duck@gmail.com");
			System.out.println("created");
			// start the transaction
			session.beginTransaction();
			// save the student object
			System.out.println("saving the students...");
			System.out.println(tempStudent);
			session.save(tempStudent);
			// commit transaction
			session.getTransaction().commit();
			//MY NEW CODE
			// find out the students primary key
			System.out.println("Saved student generated id :" + tempStudent.getId());
			//	now get a new session start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			//retreive the student based on id:primary key
			System.out.println("\ngetting student with id :"+tempStudent.getId());
			Student myStudent = session.get(Student.class, tempStudent.getId()); 
			System.out.println("Get Complete :"+ myStudent);
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!...");
		} finally {
			factory.close();
		}
	}
}
