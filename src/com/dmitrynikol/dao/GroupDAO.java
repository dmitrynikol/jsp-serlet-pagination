package com.dmitrynikol.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dmitrynikol.entity.Group;
import com.dmitrynikol.util.ConnectionFactory;

/**
 * DAO for the Group entity.
 * 
 * @author Dmitry Nikolaenko
 *
 */
public class GroupDAO {
    private Connection connection;
    private Statement stmt;
    
	public GroupDAO() {}
    
	/**
	 * Method return all group in alphabetical order.
	 * Entity of the group contains a number of the products and an unique ID.
	 * 
	 * @return list of groups
	 */
    public List<Group> viewAllGroup() {
    	String query = "SELECT group_name, p_group.group_id, COUNT(product_id) as product_group_count " +
    				   "FROM product RIGHT JOIN p_group ON product.group_id=p_group.group_id " +
    				   "GROUP BY group_name";
    	List<Group> list = new ArrayList<Group>();
		Group group;
		try {
			connection = ConnectionFactory.getInstance().getConnection();
			stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				group = new Group();

				group.setGroupName(rs.getString("group_name"));
				group.setGroupId(rs.getInt("group_id"));
				group.setProductCountOfGroup(rs.getInt("product_group_count"));

				list.add(group);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}