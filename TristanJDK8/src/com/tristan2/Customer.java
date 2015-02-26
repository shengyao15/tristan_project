package com.tristan2;

import java.util.Optional;

public class Customer {
	Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public static void main(String[] args) {
		//t1();
		//t2();
		//t3();
		t4();
	}

	private static void t1() {
		String cityName ="NA";
		Customer customer = new Customer();
		cityName = customer.getAddress().getCity().getName();
		System.out.println(cityName);
	}
	
	private static void t2() {
		String cityName ="NA";
		Customer customer = new Customer();
		Address address = customer.getAddress();
		if(address!=null){
			City city = address.getCity();
			if(city != null){
				cityName = city.getName();
			}
		}
		System.out.println(cityName);
	}
	
	private static void t3() {
		String cityName ="NA";
		Customer customer = new Customer();
		Address a = new Address();
		City c = new City();
		c.setName("Shanghai");
		a.setCity(c);
		customer.setAddress(a);
		
		Address address = customer.getAddress();
		if(address!=null){
			City city = address.getCity();
			if(city != null){
				cityName = city.getName();
			}
		}
		System.out.println(cityName);
	}
	
	private static void t4() {
		Customer customer = new Customer();
		Address a = new Address();
		City c = new City();
		c.setName("Shanghai");
		a.setCity(c);
		customer.setAddress(a);
		
		String cityName = Optional.ofNullable(customer.getAddress()).map(Address::getCity).map(City::getName).orElse("NA");
		System.out.println(cityName);
	}
}


class Address{
	City city;

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	
}

class City{
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}