package com.dmitrynikol.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dmitrynikol.entity.Product;
import com.dmitrynikol.util.ConnectionFactory;
import com.dmitrynikol.util.Sorting;
import com.dmitrynikol.util.SortingType;
 
/**
 * DAO for the Product entity.
 * 
 * @author Dmitry Nikolaenko
 *
 */
public class ProductDAO {
    private Connection connection;
    private Statement statement;
    private int productCount;
 
    public ProductDAO() { }
 
    /**
     * Query used to return the products of group.
     * The method allows to get sorted list of products by 
     * name or price in one direction or opposite.
     * 
     * @param group the group of concrete products
     * @param offset value allow to retrieve just a portion of the rows
     * @param recordsOnPage the amount of data per request
     * @param sorting the way of the data sort
     * @param sortingType possible sorting way, for now it's sort by name or price
     * @return list of products
     */
    public List<Product> viewAllProducts(int group, int offset, int recordsOnPage, 
    		Sorting sorting, SortingType sortingType) {
    	StringBuilder queryBuilder = new StringBuilder();
    	queryBuilder.append("SELECT SQL_CALC_FOUND_ROWS * FROM product ");
    	if (group > 0) {
    		queryBuilder.append("where group_id=").append(group);
    	}
    	if (!Sorting.DEFAULT.equals(sorting)) {
    		queryBuilder.append(" ORDER BY ").append("product_").
    			append(sortingType.getValue()).append(" ").append(sorting.getType());
    	} 
    	queryBuilder.append(" limit ").append(offset).append(", ").append(recordsOnPage);
    	
		List<Product> list = new ArrayList<Product>();
		Product product = null;
		try {
			connection = ConnectionFactory.getInstance().getConnection();
			statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(queryBuilder.toString());
			while (rs.next()) {
				product = new Product();
				product.setProductName(rs.getString("product_name"));
				product.setProductPrice(rs.getInt("product_price"));

				list.add(product);
			}
			rs.close();

			rs = statement.executeQuery("SELECT FOUND_ROWS()");
			if (rs.next()) {
				this.productCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * Return the total count of products for a certain group.
	 */
	public int getProductCount() {
		return productCount;
	}
}