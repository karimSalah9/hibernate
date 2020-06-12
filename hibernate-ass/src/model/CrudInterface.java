package model;

import java.util.List;

import entity.Customer;

public interface CrudInterface {

	public Customer insert(Customer customer);

	public Customer select(int id);

	public List<Customer> select();

	public boolean update(int id , String firstName);

	public boolean delete(int id);

}
