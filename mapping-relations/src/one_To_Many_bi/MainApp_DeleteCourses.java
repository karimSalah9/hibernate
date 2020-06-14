package one_To_Many_bi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp_DeleteCourses {

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

			// get course from DB
			int id = 4;
			Course course = session.get(Course.class, id);

			// display course name
			System.out.println("\n\n Course is : " + course + " \n\n");

			// delete course without affect instructor according to cascading
			session.delete(course);
			System.out.println("\n\n Course  " + course.getTitle() + "Deleted \n\n");
			
//			// get instructor from DB
//			int id1 = 3;
//			Instructor instructor = session.get(Instructor.class, id1);
//
//			// display instructor name
//			System.out.println("\n\n Course is : " + instructor + " \n\n");
//
//			// delete instructor without affect courses according to cascading
//			session.delete(instructor);
//			System.out.println("\n\n Course  " + instructor.getCourses() + "Deleted \n\n");
//			
//			
//			
//			

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
