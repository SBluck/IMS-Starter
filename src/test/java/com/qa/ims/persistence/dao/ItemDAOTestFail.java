package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTestFail {
	// resources
	private final ItemDAO itemDAO = new ItemDAO();

	// Keep track of tests
	private static int thisTest = 1;
	private static String startLine = "======================================\n";
	private static String endLine = "==================END OF TEST=====================\n";

	@BeforeClass
	public static void init() {
		System.out.println("START of ItemDAO class exception tests");
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
		final Item createTest = new Item(2L, "Hand Sanitiser 100ml", 8.17);
		assertNull(itemDAO.create(createTest));

	}

	@Test
	public void testReadAll() {
		assertEquals(new ArrayList<>(), itemDAO.readAll());

	}

	@Test
	public void testReadLatest() {
		assertNull(itemDAO.readLatest());

	}

	@Test
	public void testRead() {
		assertNull(itemDAO.readItem(1L));

	}

	@Test
	public void testUpdate() {
		final Item itemUpdate = new Item(1L, "Hand sanitiser 30ml", 4.17);
		assertNull(itemDAO.update(itemUpdate));

	}

	@Test
	public void testDelete() {
		assertEquals(0, itemDAO.delete(1L));

	}

	@After
	public void after() {
		System.out.println(endLine);
	}

	@AfterClass
	public static void afterAll() {
		System.out.println("END of ItemDAO class exception tests");
	}
}
