### Pagination using JSP and Servlets

This is a little explanation of how to build application with pagination and data sorting using only JSP and Servlets. I will describe details about 
pagination engine, the best practice for database connection, what is Data Transfer Object (DAO) and Transfer Object, how to write servlet 
and bind it with JSP. Only one jstl.jar you need for project and it should be placed on the WEB-INF/lib directory.

In this example we will display a list of product’s details like name and price that is bind with appropriate group. Left panel contains 
a list of group, right panel contains a list of product of the selected group. Near the name of each group display a products count. 
Some of group can be empty, other contains a thousand of products. Thats why we should display not more that 10 products on the page. 

It’s necessary to show the current page and navigation elements for moving to the next and previous page. Also we will add sorting for 
products column in ascending and descending order. If the user clicks on the header column(product name or price) the first time that 
column should be sorted in ascending order and if the action is repeated on the same header column then the column should be sorted in descending order.

A little bit about pagination and what method we will choose?
In our case pagination means that large document will be breaking into separate pages for viewing. Basically it’s a way of making a website 
with a lot of data more user-friendly and manageable. 

There are different ways of pagination. 
First way, not very good as for me based on getting all records at once. Later when user needs result, some part of data can be retrieved 
from the cache instead of querying the database to fetch the next set of results. The negative side of this approach is that the data becomes stale. 
And also fetching a lot of data from database requires many resources(CPU and memory). Nobody likes a slow website, faster is always better.
And the second way more interesting approach. We get a range of records every time we need and display it to the user, just limit the result 
of data that we need to display. This approaches solves a lot of problems. And we will build application that way.

* This example uses two tables Group and Product. Here is the link to the [mysql database dump file](https://github.com/dmitrynikol/jsp-serlet-pagination/tree/master/mysql-database-dump).
* [Transfer object classes](https://github.com/dmitrynikol/jsp-serlet-pagination/tree/master/src/com/dmitrynikol/entity) that will encapsulates our business data.

* [Connection factory help](https://github.com/dmitrynikol/jsp-serlet-pagination/blob/master/src/com/dmitrynikol/util/ConnectionFactory.java) us to make connection to the database. And of course it’s a singleton.

* [Data Access Object](https://github.com/dmitrynikol/jsp-serlet-pagination/tree/master/src/com/dmitrynikol/dao) or DAO encapsulates database operations.

* Servlet processes different request parameters like group of products, current page, desc/asc sorting. Also we are storing a lot of attributes in the request scope and then forwarding request to a JSP. 
Here is the code of [Servlet](https://github.com/dmitrynikol/jsp-serlet-pagination/blob/master/src/com/dmitrynikol/serlets/ProductServlet.java).
 
* Only one [JSP page](https://github.com/dmitrynikol/jsp-serlet-pagination/blob/master/WebContent/displayProduct.jsp) that retrieves the attributes from request and display the result. Easier to nowhere! :)

In summary, here is the [project structure](https://github.com/dmitrynikol/jsp-serlet-pagination/blob/master/screenshots/project_hierarchy.png) and [other screenshots](https://github.com/dmitrynikol/jsp-serlet-pagination/tree/master/screenshots).

 
 
