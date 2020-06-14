package one_To_Many_bi;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp_Bi_Directional_Many_To_One {

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
			int id = 6;
			Instructor instructor = session.get(Instructor.class, id);

			// create course objects
			Course course1 = new Course("arabic");
			Course course2 = new Course("english");
			Course course3 = new Course("french");

//			the Add method in instructor entity can work like creating list
//			and push all course to it and then set it the property of List <Courses>
//			List<Course> x = new ArrayList<Course>();
//			x.add(course1);
//			x.add(course2);
//			x.add(course3);
//			instructor.setCourses(x);
//			course1.setInstructor(instructor);
//			course2.setInstructor(instructor);
//			course3.setInstructor(instructor);
			
			// add courses to instructor
			instructor.Add(course1);
			instructor.Add(course2);
			instructor.Add(course3);

			// save Courses to DB
			session.save(course1);
			session.save(course2);
			session.save(course3);

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
