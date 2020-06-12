package model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.Customer;

public class CrudImp implements CrudInterface {

	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class)
			.buildSessionFactory();

	Session session;

	@Override
	public Customer insert(Customer customer) {
		session = factory.getCurrentSession();
		try {
			// start the transaction
			System.out.println("Begin tranaction");
			session.beginTransaction();
			session.save(customer);
			// commit the transaction
			session.getTransaction().commit();
			return customer;
		} finally {
			// close the factory
			System.out.println("Done!");
			factory.close();
		}

	}

	@Override
	public Customer select(int id) {

		session = factory.getCurrentSession();
		try {
			// start the transaction
			System.out.println("Begin tranaction");
			session.beginTransaction();
			Customer c = session.get(Customer.class, id);

			// commit the transaction
			session.getTransaction().commit();
			return c;
		} finally {
			// close the factory
			System.out.println("Done!");
			factory.close();
		}
	}

	@Override
	public List<Customer> select() {

		session = factory.getCurrentSession();
		try {
			// start the transaction
			System.out.println("Begin tranaction");
			session.beginTransaction();

			List<Customer> c = session.createQuery("from Customer").list();

			// commit the transaction
			session.getTransaction().commit();
			return c;
		} finally {
			// close the factory
			System.out.println("Done!");
			factory.close();
		}
	}

	@Override
	public boolean update(int id, String firstName) {
		session = factory.getCurrentSession();
		try {
			// start the transaction
			System.out.println("Begin tranaction");
			session.beginTransaction();
			Customer c = session.get(Customer.class, id);
			if (c != null) {
				c.setFirstName(firstName);
				// commit the transaction
				session.getTransaction().commit();

				return true;
			} else {
				return false;
			}
		} finally

		{
			// close the factory
			System.out.println("Done!");
			factory.close();
		}

	}

	@Override
	public boolean delete(int id) {
		session = factory.getCurrentSession();
		try {
			// start the transaction
			System.out.println("Begin tranaction");
			session.beginTransaction();
			Customer c = session.get(Customer.class, id);
			if (c != null) {
				session.delete(c);
				// commit the transaction
				session.getTransaction().commit();

				return true;
			} else {
				return false;
			}
		} finally

		{
			// close the factory
			System.out.println("Done!");
			factory.close();
		}
	}

}
