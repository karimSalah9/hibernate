package demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		// create a session
		Session session = factory.getCurrentSession();

		try {
			// save java object to database
			System.out.println("create student Object!");
			// create student object
			Student student1 = new Student("koko", "kaka", "kimo@ist.com");
			// Student student2 = new Student("eslam", "hamza", "eslam@ist.com");
			// Student student3 = new Student("sameh", "saeed", "sameh@ist.com");
			// start the transaction
			System.out.println("Begin tranaction");
			session.beginTransaction();

			// save object to database
			System.out.println("save Object to DB!");
			session.save(student1);
			System.out.println("Student Object saved! " + student1.getId());

			// session.save(student2);
			// session.save(student3);

			// commit transaction
			System.out.println("commit tranaction");
			session.getTransaction().commit();

			// retrieve object from database

			// get new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			// retrieve student based on primary key
			System.out.println("Getting student with id : " + student1.getId());

			Student retrievedStudent = session.get(Student.class, student1.getId());
			System.out.println("your object " + retrievedStudent);
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
