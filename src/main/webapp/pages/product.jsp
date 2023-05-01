<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> <!-- adding core tag library -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %> <!-- adding formatting tag library -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql" %> <!-- adding sql tag library -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %> <!-- adding core tag library -->
<sql:setDataSource var="dbConnection" driver ="com.mysql.jdbc.Driver" url ="jdbc:mysql://localhost:3306/empyrean" user="root" password = ""/>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/product.css" rel = "stylesheet">
    <title>Document</title>

    <!-- import css for social media icons -->  

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>
    
    <nav class = navbar style="margin-bottom: 50px;">
        <div>
            <img src = "${pageContext.request.contextPath}/images/LOGO.png" alt = "logo.png" class = "logo" onclick = "refresh()">
        </div>

        <div class="menu">
            <a href = "${pageContext.request.contextPath}/pages/home.jsp">HOME</a>

            <a href = "#" style="color: red;">PRODUCTS</a>

            <a href = "about.jsp">ABOUT</a>

            <a href = "contact.jsp">CONTACT</a>

            <a href = "profile.jsp">PROFILE</a>
            
            <a href = "cart.jsp" class="cart"><i class="fa fa-shopping-cart"></i></a>


        </div>
    </nav>
    <div class="sidepanel" id="mySidepanel">
        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
        <a href="#">About</a>
        <a href="#">Services</a>
        <a href="#">Clients</a>
        <a href="#">Contact</a>
    </div>
    <div class="filter-search-container">
        <form action="${pageContext.request.contextPath}/searchServlet" class="searchServlet" method="get">
            <input type="text" placeholder="search" class="search" name="searchText">
            <button type="submit"  class="search_button"><i class="fa fa-search"></i></button>
        </form>

        <div class="sort_by_filter" >
                <button class="sort_by" onclick="myFunction()">Sort By</button>
                <div id="sort_by" class="sort_by-content">
                    <form action="filter" class="">
                        <button type="submit" value="Latest" style="margin-top: 10px;" name="submit">Latest</button>
                        <button type="submit" value="Desc" style="margin-top: 10px;" name="submit">Price (Desc)</button>
                        <button type="submit" value="Asc" style="margin-top: 10px;" name="submit">Price (Asc)</button>
                    </form>
                </div>
            |
            <button class="filter" onclick="openNav()">Filter</button>

        </div>
    </div>
    
   	<sql:query var="products" dataSource="${dbConnection}">
		SELECT product_name,image_link FROM products; 
	</sql:query>
	

	
    <div class="product">
		<c:forEach var="products" items="${products.rows}">
		 <figure>
            <img src="http://localhost:8082/images/products/${products.image_link}" alt="${products.product_name}">
            <figcaption>${products.product_name}<button class="addToCart" value="blueShadow.jpg"><i class='fa fa-cart-plus'></i></button></figcaption>
        </figure>

			
		</c:forEach>    
        <figure>
            <img src="${pageContext.request.contextPath}/images/blueShadow.jpg" alt="">
            <figcaption>Product<button class="addToCart" value="blueShadow.jpg"><i class='fa fa-cart-plus'></i></button></figcaption>
        </figure>
        <figure>
            <img src="${pageContext.request.contextPath}/images/sunshineHat.jpg" alt="">
            <figcaption>Product<button class="addToCart" value="blueShadow.jpg"><i class='fa fa-cart-plus'></i></button></figcaption>
        </figure>
        <figure>
            <img src="${pageContext.request.contextPath}/images/blueShadow.jpg" alt="">
            <figcaption>Product<button class="addToCart" value="blueShadow.jpg"><i class='fa fa-cart-plus'></i></button></figcaption>
        </figure>
        <figure>
            <img src="${pageContext.request.contextPath}/images/sunshineHat.jpg" alt="">
            <figcaption>Product<button class="addToCart" value="blueShadow.jpg"><i class='fa fa-cart-plus'></i></button></figcaption>
        </figure>

        <figure>
            <img src="${pageContext.request.contextPath}/images/leafPaint.jpg" alt="">
            <figcaption>Product<button class="addToCart" value="blueShadow.jpg"><i class='fa fa-cart-plus'></i></button></figcaption>
        </figure>
        <figure>
            <img src="${pageContext.request.contextPath}/images/pinkBag.jpg" alt="">
            <figcaption>Product<button class="addToCart" value="blueShadow.jpg"><i class='fa fa-cart-plus'></i></button></figcaption>
        </figure>

    </div>
</body>

    
<script>
    /* When the user clicks on the button, 
    toggle between hiding and showing the dropdown content */
    function myFunction() {
        document.getElementById("sort_by").classList.toggle("show");
    }
    
    // Close the dropdown if the user clicks outside of it
    window.onclick = function(event) {
        if (!event.target.matches('.sort_by')) {
        var dropdowns = document.getElementsByClassName("sort_by-content");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
            openDropdown.classList.remove('show');
            }
        }
        }
    }
    function openNav() {
        document.getElementById("mySidepanel").style.display = "block";
    }

    function closeNav() {
        document.getElementById("mySidepanel").style.display = "none";
    }

    </script>
    
</html>