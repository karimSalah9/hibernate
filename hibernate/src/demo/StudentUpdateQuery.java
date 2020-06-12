package demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import demo.entity.Student;

public class StudentUpdateQuery {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		// create a session
		Session session = factory.getCurrentSession();

		try {

			// start the transaction
			System.out.println("Begin tranaction");
			session.beginTransaction();
			
			// id to get the object from db
			int id = 1;
			
			// take whole object from db
			Student s = session.get(Student.class, id);
			System.out.println("Set First Name To karim Salah");
			
			// update first name using setter property
			s.setFirstName("Karim Salah");

			
			//update all mails of all students
			System.out.println("Update mails of all students");
			session.createQuery("update Student s"+" set email ='foo@gmail.com'"+" where s.id = '1'").executeUpdate();
			
			
			
			
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done retrieveing!");
		} finally {
			
			// close the factory
			System.out.println("Done!");
			factory.close();
		}
	}

}
