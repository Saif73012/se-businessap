package com.businessapp.pojos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerTest {
/*
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
*/
	@Before
	public void setUp() throws Exception {
	System.out.println("____________");
	}
/*
	@After
	public void tearDown() throws Exception {
	}
*/
	//@Test
	//public void test() {
	/*	Customer testkunde = new Customer("21","Tzeze");
		Customer testkunde2 = new Customer("21","Tzeze");

testkunde2.setName("");
System.out.println(testkunde2.getName());
		System.out.println(testkunde.getName());
		testkunde.setName("Meyer");
		System.out.println(testkunde.getName());
		System.out.println("_______________________");
		testkunde.addContact(testkunde.getName());
		System.out.println(testkunde.getContacts());
		*/
//	getIdTest();
	//getNameTest();
	
	
	// Assert methoden hinzufügen!!!!
	
	
	
	
	//}
	@Test
	public void getIdTest() 
	{
		
		Customer testkunde = new Customer("21","Meyer");
		System.out.println("Id des Objektes: "+testkunde.getId());
		System.out.println();
		assertEquals("21",testkunde.getId());
		
		Customer testkunde1 = new Customer(null,"");
		System.out.println("Id des Objektes: "+ testkunde1.getId());
		System.out.println();
		assertNotEquals((null),testkunde1.getId());
		
		Customer testkunde2 = new Customer("","");
		System.out.println("Id des Objektes: "+testkunde2.getId());
		System.out.println();
		assertEquals("",testkunde2.getId());
		
		
	}
	@Test
	public void getNameTest() {
		
		Customer testkunde = new Customer("21","Meyer");
		System.out.println("Name des Objektes:");
		System.out.println(testkunde.getName());
		assertEquals("Meyer",testkunde.getName());
		Customer testkunde1 = new Customer(null,null);
		System.out.println("Name des Objektes:");
		System.out.println(testkunde1.getName());
		assertEquals(null,testkunde1.getName());
		Customer testkunde2 = new Customer("","");
		System.out.println("Name des Objektes:");
		System.out.println(testkunde2.getName());
		assertEquals("",testkunde2.getName());
		
		
	}
	@Test
	public void setNameTest() 
	{
		Customer testkunde = new Customer("21","Peter");
		assertEquals("Peter",testkunde.getName());
		testkunde.setName("Meyer");
		assertEquals("Meyer",testkunde.getName());
		testkunde.setName("");  
		assertEquals("",testkunde.getName());
	
		
	}
	
	@Test
	public void addContactTest() 
	{
		Customer testkunde= new Customer("12","Holga");
		System.out.println(Customer.getContacts());
		testkunde.addContact(testkunde.getName());
		System.out.println(Customer.getContacts());
		List<String> test = new ArrayList<String>();
		test.add("Holga");
		test.add(0, "Meyer");
		//	System.out.println(test);
		assertEquals(test, Customer.getContacts());
	
	}
	
	@Test
	
	public void getContactTest() {
		
	Customer testkunde2= new Customer("12","Meyer");
	testkunde2.addContact(testkunde2.getName());
	List<String> test = new ArrayList<String>();
	
	test.add("Meyer");
	test.add("Holga");
	//System.out.println(test);
	
	//System.out.println(Customer.getContacts());
	assertNotEquals(test, Customer.getContacts());

	}
	@Test 
	public void getStatusTest() 
	{
		Customer testkunde = new Customer("1","Bob");
		System.out.println("Der Status des Customer ist: "+testkunde.getStatus().toString());
		assertEquals("ACTIVE",testkunde.getStatus().toString());
	
	}
	@Test 
	public void setStatusTest() 
	{
		Customer testkunde = new Customer("1","Bob");
		System.out.println("Before: "+testkunde.getStatus().toString());
		testkunde.setStatus(Customer.CustomerStatus.SUSPENDED);
		System.out.println("After: "+testkunde.getStatus().toString());
		assertEquals("SUSPENDED",testkunde.getStatus().toString());
		
	}
	@Test 
	public void getNotesTest() 
	{
		Customer testkunde = new Customer("1","Bob");
		System.out.println("Der Status des Customer ist: "+testkunde.getNotes().toString());
		String []a=testkunde.getNotes().toString().split(",");
		String note;
		note=a[1].replaceAll("]", "");
		System.out.println(note);
		assertEquals(" Customer record created.",note);
	
	}
}
