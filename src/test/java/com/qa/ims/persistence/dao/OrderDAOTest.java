package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {
	// resources
	private final OrderDAO orderDAO = new OrderDAO();

	// Keep track of tests
	private static int thisTest = 1;
	private static String startLine = "======================================\n";
	private static String endLine = "==================END OF TEST=====================\n";
	
	@BeforeClass
	public static void init() {
		System.out.println("START of OrderDAO class standard tests");
		DBUtils.connect("root", "root");
	}

	@Before
	public void setup() {
		System.out.println("\tTest " + thisTest + " " + startLine);
		thisTest++;
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Order createTest = new Order(2L, 1L);
		assertEquals(createTest, orderDAO.create(createTest));
	}

	@Test
	public void testCreateOrderItem() {
//	mockito testing stub
	}

	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1L, 1L));
		assertEquals(expected, orderDAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(new Order(1L, 1L), orderDAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Order(ID, 1L), orderDAO.readOrder(ID));
	}

	@Test
	public void testLatestOrderItem() {
//	mockito testing stub
	}	
	
	@Test
	public void testUpdate() {
		final Order updated = new Order(2L, 1L);
		assertNull(orderDAO.update(updated));
	}

	@Test
	public void testDeleteOrderItem() {
		assertEquals(1, orderDAO.deleteOrderItem(1));
	}

	@Test
	public void testDelete() {
		assertEquals(1, orderDAO.delete(1));
	}
	
	@After
	public void after() {
		System.out.println(endLine);
	}
	
	@AfterClass
	public static void afterAll() {
		System.out.println("END of OrderDAO class standard tests");
	}
}
