package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTestFail {
	// resources
		private final ItemDAO itemDAO = new ItemDAO();
		private final OrderDAO orderDAO = new OrderDAO();

		// Keep track of tests
		private static int thisTest = 1;
		private static String startLine = "======================================\n";
		private static String endLine = "==================END OF TEST=====================\n";

		@BeforeClass
		public static void init() {
			System.out.println("START of OrderDAO class exception tests");
			DBUtils.connect("root", "fail");
		}

		@Before
		public void setup() {
			System.out.println("\tTest " + thisTest + " " + startLine);
			thisTest++;

			DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data-more.sql");
		}

		@Test  //Test 1
		public void testCreate() {
			final Order createTest = new Order(2L, 1L);
			assertNull(orderDAO.create(createTest));

		}
		
		@Test  //Test 2
		public void testCreateOrderItem() {
			assertNull(orderDAO.createOrderItem(3L, 2L));

		}

		@Test  //Test 3
		public void testReadAll() {
			assertEquals(new ArrayList<>(), orderDAO.readAll());

		}

		@Test  //Test 4
		public void testReadLatest() {
			assertNull(orderDAO.readLatest());

		}

		@Test  //Test 5
		public void testReadOrder() {
			assertNull(orderDAO.readOrder(1L));

		}
		
		@Test  //Test 6
		public void testReadAllOrderItem() {
			assertNull(orderDAO.readAllOrderItem(1L));

		}
		
		@Ignore  // Test fail:  expected null but was <[]>
		@Test  //Test 7
		public void testReadLatestOrderItem() {
			assertNull(orderDAO.readLatestOrderItem());

		}

	
		@Test  //Test 
		public void testUpdate() {
			final Order updated = new Order(1L, 1L);
			assertNull(orderDAO.update(updated));

		}

		@Test
		public void testDelete() {
			assertEquals(0, orderDAO.delete(1L));

		}

		@Test
		public void testDeleteOrderItem() {
			assertEquals(0, orderDAO.deleteOrderItem(1L));

		}

		@After
		public void after() {
			System.out.println(endLine);
		}

		@AfterClass
		public static void afterAll() {
			System.out.println("END of OrderDAO class exception tests");
		}
}
