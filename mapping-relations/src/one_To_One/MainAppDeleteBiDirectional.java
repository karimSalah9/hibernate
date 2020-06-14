package one_To_One;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainAppDeleteBiDirectional {

	public static void main(String[] args) {

		// note that you should annotaed both classes
		// .addAnnotatedClass(Instructor.class)
		// .addAnnotatedClass(InstructorDetail.class)

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		Session session;

		session = factory.getCurrentSession();

		try {

			// start the transaction
			System.out.println("Begin tranaction");
			session.beginTransaction();

			// get the instructor detail object
			int id = 6;
			InstructorDetail detail = session.get(InstructorDetail.class, id);

			System.out.println("\n\n the retrieved object is : \n\n" + detail);

			// this is the associated object
			System.out.println("\n\n this is the associated object : \n\n" + detail.getInstructor());

			// You should remove the associated object reference from instructor detail!
			//Break bi-directional reference
			//it also based if you make restriction in DB table creation like cascading or not null for referenced column.
			detail.getInstructor().setInstructorDetail(null);
			
			
			// delete the both object from DB
			session.delete(detail);
			System.out.println("\n\n the both objects deleted successfully! \n\n");

			// commit the transaction
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			// close the factory
			System.out.println("Done!");
			factory.close();
			// close the session to handle connection leak issue
			// Cleaning up connection pool
			session.close();
		}

	}

}
