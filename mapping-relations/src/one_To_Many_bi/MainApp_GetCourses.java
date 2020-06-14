package one_To_Many_bi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp_GetCourses {

	public static void main(String[] args) {

		// note that you should annotaed both classes
		// .addAnnotatedClass(Instructor.class)
		// .addAnnotatedClass(InstructorDetail.class)

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		Session session;

		session = factory.getCurrentSession();

		try {

			// start the transaction
			System.out.println("\n\n Begin tranaction \n\n");
			session.beginTransaction();

			// get instructor from DB
			int id = 4;
			Instructor instructor = session.get(Instructor.class, id);

			// display instructor name
			System.out.println(" \n\n Instructor obj : " + instructor + " \n\n");

			// get courses with instructor
			System.out.println("\n\n Courses : " + instructor.getCourses() + " \n\n");

			// get course from DB
			int id1 = 4;
			Course course = session.get(Course.class, id1);

			// display course name
			System.out.println("\n\n Course is : " + course + " \n\n");

			// get the instructor of the course
			System.out.println(
					"\n\n the Instructor of the " + course.getTitle() + " is " + course.getInstructor() + "\n\n");

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
