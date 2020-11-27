package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {
	// resources
	private final ItemDAO itemDAO = new ItemDAO();

	// Keep track of tests
	private static int thisTest = 1;
	private static String startLine = "======================================\n";
	private static String endLine = "==================END OF TEST=====================\n";
	
	@BeforeClass
	public static void init() {
		System.out.println("START of ItemDAO class standard tests");
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
		final Item createTest = new Item(2L, "Hand Sanitiser 100ml", 8.17);
		assertEquals(createTest, itemDAO.create(createTest));
	}

	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "Hand sanitiser 50ml", 4.25));
		assertEquals(expected, itemDAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Item(1L, "Hand sanitiser 50ml", 4.25), itemDAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Item(ID, "Hand sanitiser 50ml", 4.25), itemDAO.readItem(ID));
	}

	@Test
	public void testUpdate() {
		final Item itemUpdate = new Item(1L, "Hand sanitiser 30ml", 4.17);
		assertEquals(itemUpdate, itemDAO.update(itemUpdate));

	}

	@Test
	public void testDelete() {
		assertEquals(1, itemDAO.delete(1));
	}
	
	@After
	public void after() {
		System.out.println(endLine);
	}
	
	@AfterClass
	public static void afterAll() {
		System.out.println("END of ItemDAO class standard tests");
	}
}
