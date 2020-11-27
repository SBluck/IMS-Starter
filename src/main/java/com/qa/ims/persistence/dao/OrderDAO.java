package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {
	public static final Logger LOGGER = LogManager.getLogger();
	private ItemDAO itemDAO = new ItemDAO();
	private Item item = new Item();
	
	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long customer_id = resultSet.getLong("customer_id");
		List<Item> itemList = readAllOrderItem(id);
		return new Order(id, customer_id, itemList);
	}
	

	/**
	 * Reads all orders from the database
	 * 
	 * @return A list of orders
	 */
	
	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
			List<Order> orderList = new ArrayList<>();
			while (resultSet.next()) {
				orderList.add(modelFromResultSet(resultSet));
			}
			return orderList;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates an order in the database
	 * 
	 * @param order - takes in an order object. id will be ignored
	 */
	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO orders(customer_id) VALUES ('" + order.getCustomer_id() + "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Creates an order item in the database
	 * 
	 * @param order_item - takes in an order id and an item id
	 * 
	 */
	public List<Item> createOrderItem(Long orderId, Long itemId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO order_items(order_id, item_id) VALUES ('" + orderId 
				+ "','" + itemId + "')");
			return readLatestOrderItem();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Reads an order from the database
	 * 
	 * @param id - takes in an (order) id
	 */
	public Order readOrder(Long id) {
		System.out.println("ReadOrder" + id);
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders WHERE id = " + id);) {
			System.out.println(resultSet);
			resultSet.next();
			System.out.println("readOrder: " + id + "\t" + resultSet.next() + modelFromResultSet(resultSet));
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Reads all order items for a specific order from the database
	 * 
	 * @param id - takes in an (order) id
	 */
	public List<Item> readAllOrderItem(Long orderId) {

		String query = "SELECT oi.id, i.name, i.value "
						+ "FROM order_items oi "
						+ "JOIN items i on oi.item_id = i.id "
						+ "WHERE oi.order_id = " + orderId + ";";
		
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query); ) {
			List<Item> orderItemList = new ArrayList<>();
			while (resultSet.next()) {
				item =  itemDAO.modelFromResultSet(resultSet);
				orderItemList.add(item);
			}
			return orderItemList;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	/**
	 * Reads latest order item from the database
	 * 
	 * @param no parameters expected
	 */
	public List<Item> readLatestOrderItem() {
		String query = "SELECT oi.id, i.name, i.value "
				+ "FROM order_items oi "
				+ "JOIN items i on oi.item_id = i.id "
				+ "ORDER BY id DESC LIMIT 1;";
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query); ) {
			List<Item> orderItemList = new ArrayList<>();
			resultSet.next();
			item = itemDAO.modelFromResultSet(resultSet);
			orderItemList.add(item);
			return orderItemList;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	/**
	 * Updates an order in the database - currently not used as update options are to add or
	 * delete an item from an order - could be used if ability to change 
	 * customer on an order (or other future enhancements) make this a requirement. 
	 * 
	 * @param item - takes in an order object, the id field will be used to
	 *                 update that order in the database    
	 */
	
	@Override
	public Order update(Order order) {
		return null;
	}
	
	/**
	 * Deletes an order_item from the database
	 * 
	 * @param id - id of the order_item
	 */
	
	public int deleteOrderItem(long id) {

		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("DELETE FROM order_items WHERE id = " + id); 
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	/**
	 * Deletes an order from the database
	 * 
	 * @param id - id of the order
	 */
	@Override
	public int delete(long id) {
		
		// =============================================================================
		// NOTE: to delete an order an order items for the order must be deleted first
		//    to avoid failing a foreign key constraint
		// =============================================================================

		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("DELETE FROM order_items WHERE order_id = " + id); 
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}

		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("DELETE FROM orders WHERE id = " + id);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
}
