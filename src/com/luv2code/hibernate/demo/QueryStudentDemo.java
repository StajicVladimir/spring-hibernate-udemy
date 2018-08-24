package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {

			
			session.beginTransaction();
			
			List<Student> theStudents = session.createQuery("from Student").list();
			displayStudents(theStudents);
			
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").list();
			System.out.println("students with last name Doe");
			displayStudents(theStudents);
			
			
			theStudents = session.createQuery("from Student s where "
					+ " s.lastName='Doe'"
					+ " or s.firstName = 'Daffy'").list();
			System.out.println("students with last name Doe or firstname Daffy");
			displayStudents(theStudents);
			
			session.getTransaction().commit();
			System.out.println("Done!");
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		theStudents.stream()
			.forEach(student -> System.out.println(student));
	}

}
