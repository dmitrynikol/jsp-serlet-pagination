<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Group and products</title>
<style type="text/css">
  #div0{display:table-cell;width:50%;}
  #div1{float:left;text-align:center;display:table-cell;background-color:#9C8DEB}
  #div2{float:left;text-align:center;display:table-cell;background-color:#41EC88}
  #div3{width:361px; overflow:auto;background-color:yellow;}
</style>
</head>
<body>
	<div id="div0">
		<div id="div1">
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<th>GROUP_NAME</th>
				</tr>
				<c:forEach var="group" items="${groupList}">
					<tr>
						<td><a href="product.do?page=1&group=${group.groupId}">
								${group.groupName} (${group.productCountOfGroup})
						</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div id="div2">
		    <table border="1" cellpadding="4" cellspacing="0">
		        <tr>
		        	<th><a href="product.do?page=1&group=${activeGroupId}&${sortedNameColumn}=name">
		            	PRODUCT_NAME
		            </a></th>
		            <th><a href="product.do?page=1&group=${activeGroupId}&${sortedPriceColumn}=price">
		            	PRODUCT_PRICE
		            </a></th>
		        </tr>
		 
		        <c:forEach var="product" items="${productList}">
		            <tr>
		                <td>${product.productName}</td>
		                <td>${product.productPrice}</td>
		            </tr>
		        </c:forEach>
		    </table>
	    </div>
    </div>
 
 	<div>	
 		<c:if test="${numberOfPages > 1}">
	 		<div style="float: left;">
		 	    <!--For displaying previous link except for the 1st page -->
			    <c:if test="${currentPage != 1}">
			        <td><a href="product.do?page=${currentPage - 1}&group=${activeGroupId}
							&${activeSortingWay}=${activeSortingType}">Previous</a></td>
			    </c:if>
		    </div>
	 	
	 		<div id="div3" style="float: left;">	
			    <%--For displaying Page numbers.
			    The when condition does not display a link for the current page--%>
			    <table border="1" cellpadding="3" cellspacing="0">
			        <tr>
			            <c:forEach begin="1" end="${numberOfPages}" var="i">
			                <c:choose>
			                    <c:when test="${currentPage eq i}">
			                        <td>${i}</td>
			                    </c:when>
			                    <c:otherwise>
			                        <td><a href="product.do?page=${i}&group=${activeGroupId}
											&${activeSortingWay}=${activeSortingType}">${i}</a></td>
			                    </c:otherwise>
			                </c:choose>
			            </c:forEach>
			        </tr>
			    </table>
		    </div>
	    	
	    	<div style="float: left;">
			    <%--For displaying Next link except for the last page --%>
			    <c:if test="${currentPage lt numberOfPages}">
			        <td><a href="product.do?page=${currentPage + 1}&group=${activeGroupId}
							&${activeSortingWay}=${activeSortingType}">Next</a></td>
			    </c:if>
		    </div>
	    </c:if>
    </div>
</body>
</html>