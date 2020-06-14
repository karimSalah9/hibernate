package one_To_One;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainAppInsert {

	public static void main(String[] args) {

		// note that you should annotaed both classes
		// .addAnnotatedClass(Instructor.class)
		// .addAnnotatedClass(InstructorDetail.class)

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		Session session;

		// Create the object
		Instructor instructor = new Instructor("salah", "hamza", "Islam@ist");

		InstructorDetail detail = new InstructorDetail("salah_ch", "playing");
		// associate the object
		instructor.setInstructorDetail(detail);

		session = factory.getCurrentSession();

		try {

			// start the transaction
			System.out.println("Begin tranaction");
			session.beginTransaction();
			
			//note that it will save the both objects to 2 tables because of cascade type = ALL
			session.save(instructor);

			// commit the transaction
			session.getTransaction().commit();

			System.out.println("saved successfully! " + instructor);
		} finally {

			// close the factory
			System.out.println("Done!");
			factory.close();

		}

	}

}
