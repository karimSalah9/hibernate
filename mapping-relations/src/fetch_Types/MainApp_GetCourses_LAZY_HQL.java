package fetch_Types;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class MainApp_GetCourses_LAZY_HQL {

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
			// using HQL to query DB for LAZY Issue related to session closed before
			// retrieving related entities
			int id = 4;
			// make sure you import import org.hibernate.query.Query;
			// FETCH JOIN between two tables instructor and course
			Query<Instructor> query = session.createQuery(
					"select i from Instructor i " + "JOIN FETCH i.courses " + "where i.id = :insId", Instructor.class);
			// set parameter in query above insId
			query.setParameter("insId", id);

			// execute the Query
			Instructor instructor = query.getSingleResult();

			// display instructor name
			System.out.println(" \n\n Instructor obj : " + instructor + " \n\n");

			// commit the transaction
			session.getTransaction().commit();
			System.out.println("\n\n commit \n\n");
			// close the session
			System.out.println("\n\n Closing session to test the HQL solution   \n\n");
			session.close();

			// retrieve course object after session closed to break the LAZY FETCH
			System.out.println("\n\n the course after session closed is : \n\n" + instructor.getCourses() + " \n\n");

		} finally {

			// close the factory
			System.out.println("\n\n cleaning ... Done! \n\n");

			factory.close();

		}

	}

}
