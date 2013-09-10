package com.dmitrynikol.entity;

/**
 * A typical Group entity.
 * 
 * @author Dmitry Nikolaenko
 *
 */
public class Group {
	private int groupId;
	private String groupName;
	private int productCountOfGroup;
	
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getProductCountOfGroup() {
		return productCountOfGroup;
	}
	public void setProductCountOfGroup(int productCountOfGroup) {
		this.productCountOfGroup = productCountOfGroup;
	}
}
