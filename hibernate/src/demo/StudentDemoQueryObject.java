package demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import demo.entity.Student;

public class StudentDemoQueryObject {

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

			// Query object from database
			System.out.println("Query Student object");
			List<Student> students = session.createQuery("from Student").list();
			for (Student st : students) {

				System.out.println(st);
			}

			// Query Student with lastName = kaka
			// note that you must use the field name in java class not column name in DB
			System.out.println("Query Student table to get students with last name =kaka");
			students = session.createQuery("from Student s where s.lastName ='kaka' ").list();
			for (Student st : students) {

				System.out.println(st);
			}

			// Query Object with OR condition first name = sameh and last name =hamza
			students = session.createQuery("from Student s" + " Where s.firstName='sameh' OR s.lastName='hamza'")
					.list();
			System.out.println("Query Object with OR condition first name = sameh and last name =hamza");
			for (Student st : students) {

				System.out.println(st);
			}

			// Query Object with like %

			students = session.createQuery("from Student s where" + " s.email like '%ist%'").list();
			System.out.println("student which their emails like @ist");
			for (Student st : students) {

				System.out.println(st);
			}

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
