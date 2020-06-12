package demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import demo.entity.Student;

public class StudentDeleteQuery {

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
			int id = 2;

			// take whole object from db
			Student s = session.get(Student.class, id);

			// delete object using primary key ID
			session.delete(s);
			System.out.println("Deleted Successfully!");

			// you can simply delete it without retrieving object
			session.createQuery("delete from Student s where id = '3'").executeUpdate();

			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done commit!");
		} finally {

			// close the factory
			System.out.println("Done!");
			factory.close();
		}
	}

}
