package one_To_One;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainAppDelete {

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
			System.out.println("\n\n Begin tranaction \n\n");
			session.beginTransaction();

			// note that it will save the both objects to 2 tables because of cascade type =
			// ALL
			Instructor ins = session.get(Instructor.class, 1);
			if (ins != null) {

				System.out.println("\n\n will remove this object \n\\n" + ins);

				session.delete(ins);
				System.out.println("\n\n removed successfully! \n\n" + ins);
				
			} else {

				System.out.println("\n\n Object Not Found!!! \n\n" );
			}
			// commit the transaction
			session.getTransaction().commit();

		} finally {

			// close the factory
			System.out.println("\n\n Done! \n\n");
			factory.close();

		}

	}

}
