package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {
	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();

		try {

			// start the transaction
			session.beginTransaction();
			// query the students
			List<Student> theStudents = session.createQuery("from Student").list();
			// display the students
			displayStudents(theStudents);
			// query student last name ahmed
			theStudents = session.createQuery("from Student s where s.lastName ='Ahmed'").list();
			System.out.println("\n\n the students whose last name is Ahmed");
			displayStudents(theStudents);
			// query last name mouse or duck
			theStudents = session.createQuery("from Student s where" + " s.lastName ='Mouse' OR s.lastName = 'Duck'")
					.list();
			System.out.println("\n\n the students whose last name is Duck or Mouse");
			displayStudents(theStudents);
			//query where email has gmail in it 
			theStudents = session.createQuery("from Student s where" +" s.email LIKE '%gmail%'").list();
			System.out.println("\n\n the students whose email has gmail word");
			displayStudents(theStudents);
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!...");
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}
}
