package model;

import java.util.List;

import entity.Customer;

public class StartCustomer {

	public static void main(String[] args) {
		Customer c = new Customer("karim", "kotb", "IST");
		CrudImp crudImp = new CrudImp();
//		List<Customer> c1 = crudImp.select();
//
//		for (Customer customer : c1) {
//
//			System.out.println(customer);
//		}
//		c = crudImp.select(1);
//		System.out.println(c);
//		boolean res = crudImp.update(1, "salah");
//		System.out.println(res);
//		Customer c1 = crudImp.insert(c);
//		System.out.println(c1);
		boolean res1 = crudImp.delete(2);
		System.out.println(res1);

	}

}
