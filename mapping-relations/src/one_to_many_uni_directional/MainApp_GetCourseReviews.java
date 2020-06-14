package one_to_many_uni_directional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class MainApp_GetCourseReviews {

	public static void main(String[] args) {

		// note that you should annotaed both classes
		// .addAnnotatedClass(Instructor.class)
		// .addAnnotatedClass(InstructorDetail.class)

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).buildSessionFactory();

		Session session;

		session = factory.getCurrentSession();

		try {

			// start the transaction
			System.out.println("\n\n Begin tranaction \n\n");
			session.beginTransaction();

			// get course from DB
			int id = 8;
			Course course = session.get(Course.class, id);

			// display course name
			System.out.println(" \n\n course obj : " + course + " \n\n");

		
			
			// display reviews od course
			System.out.println(" \n\n Reviews of course :\n\n" + course.getReviews()+"\n\n");
			
			

			
			
			
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("\n\n commit \n\n");

			// close the session
			System.out.println("\n\n Closing session  \n\n");
			session.close();

		} finally {

			// close the factory
			System.out.println("\n\n cleaning ... Done! \n\n");

			factory.close();

		}

	}

}
