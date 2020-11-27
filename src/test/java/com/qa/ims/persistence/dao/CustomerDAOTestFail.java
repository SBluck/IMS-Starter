package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DBUtils;

public class CustomerDAOTestFail {

	// resources
	private final CustomerDAO custDAO = new CustomerDAO();
		

	// Keep track of tests
	private static int thisTest = 1;
	private static String startLine = "======================================\n";
	private static String endLine = "==================END OF TEST=====================\n";

	@BeforeClass
	public static void init() {
		System.out.println("START of CustomerDAO class exception tests");
		DBUtils.connect("root", "fail");
	}


	@Before
	public void setup() {
		System.out.println("\tTest " + thisTest + " " + startLine);
		thisTest++;
		
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final Customer createTest = new Customer(2L, "john", "brown");
		assertNull(custDAO.create(createTest));
		
	}
	@Test
	public void testReadAll() {
		assertEquals(new ArrayList<>(), custDAO.readAll());
		
	}
	
	@Test
	public void testReadLatest() {
		assertNull(custDAO.readLatest());
		
	}
	
	@Test
	public void testRead() {
		assertNull(custDAO.readCustomer(1L));
		
	}
	
	@Test
	public void testUpdate() {
		final Customer custUpdate = new Customer(1L, "george", "harrison");
		assertNull(custDAO.update(custUpdate));
		
	}
	
	@Test
	public void testDelete() {
		assertEquals(0, custDAO.delete(1L));
		
	}
	
	@After
	public void after() {
		System.out.println(endLine);
	}

	@AfterClass
	public static void afterAll() {
		System.out.println("END of CustomerDAO class exception tests");
	}
}
