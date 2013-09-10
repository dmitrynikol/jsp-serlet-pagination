### Pagination using JSP and Servlets

###
    This article explains how to build application with pagination and data sorting using only JSP and Servlets. I will describe details about 
pagination engine, the best practice for database connection, what is Data Transfer Object (DAO) and Transfer Object, how to write servlet 
and bind it with JSP. Only one jstl.jar you need for project and it should be placed on the WEB-INF/lib directory.
###
    In this example we will display a list of product’s details like name and price that is bind with appropriate group. Left panel contains 
a list of group, right panel contains a list of product of the selected group. Near the name of each group display a products count. 
Some of group can be empty, other contains a thousand of products. Thats why we should display not more that 10 products on the page. 
###
    It’s necessary to show the current page and navigation elements for moving to the next and previous page. Also we will add sorting for 
products column in ascending and descending order. If the user clicks on the header column(product name or price) the first time that 
column should be sorted in ascending order and if the action is repeated on the same header column then the column should be sorted in descending order.
###
    A little bit about pagination and what method we will choose?
In our case pagination means that large document will be breaking into separate pages for viewing. Basically it’s a way of making a website 
with a lot of data more user-friendly and manageable. 
###
    There are different ways of pagination. 
First way, not very good as for me based on getting all records at once. Later when user needs result, some part of data can be retrieved 
from the cache instead of querying the database to fetch the next set of results. The negative side of this approach is that the data becomes stale. 
And also fetching a lot of data from database requires many resources(CPU and memory). Nobody likes a slow website, faster is always better.
And the second way more interesting approach. We get a range of records every time we need and display it to the user, just limit the result 
of data that we need to display. This approaches solves a lot of problems. And we will build application that way.
