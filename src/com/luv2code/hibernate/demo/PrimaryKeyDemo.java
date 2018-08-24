package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			System.out.println("Creating 3 student objects...");
			Student tempStudent1 = new Student("Paul", "Wall", "paul@bla.com");
			Student tempStudent2 = new Student("Foo", "Bar", "foo-bar@bla.com");
			Student tempStudent3 = new Student("John", "Doe", "Jdoe@bla.com");

			session.beginTransaction();

			System.out.println("Saving the students....");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);

			session.getTransaction().commit();
			System.out.println("Done!");

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			factory.close();
		}
	}

}
