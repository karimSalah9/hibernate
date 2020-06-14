package many_to_many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp_getCourseForStudent {

	public static void main(String[] args) {

		// note that you should annotaed both classes
		// .addAnnotatedClass(Instructor.class)
		// .addAnnotatedClass(InstructorDetail.class)

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session;

		session = factory.getCurrentSession();

		try {

			// start the transaction
			System.out.println("\n\n Begin tranaction \n\n");
			session.beginTransaction();

			// get course from DB
			int id1 = 5;
			Course course = session.get(Course.class, id1);

			// display course name
			System.out.println("\n\n Course is : " + course + " \n\n");

			// get the instructor of the course
			System.out.println(
					"\n\n the students of the " + course.getTitle() + " is " + course.getStudent() + "\n\n");

			// get student from DB
			int id = 4;
			Student student = session.get(Student.class, id);

			// display student name
			System.out.println("\n\n student is : " + student + " \n\n");
			
			System.out.println(
					"\n\n the course for the " + student.getFirstName() + " is " + student.getCourse() + "\n\n");

			
			
			
			
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("\n\n commit \n\n");

		} finally {

			// close the factory
			System.out.println("\n\n cleaning ... Done! \n\n");
			session.close();
			factory.close();

		}

	}

}
