<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet"
	       href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link href="<c:url value="/resources/homedesign.css "/>" rel="stylesheet">
	<script
	       src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script
	       src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
	       src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<title>Home</title>
</head>

<body>
	<div class="topnav">
		<c:choose>
		    <c:when test="${user.username == ''}">
		    </c:when>    
		    <c:otherwise>
		    	<h2>${ user.username }</h2>
		    </c:otherwise>
	    </c:choose>
			<a href="userShop">Home</a>
			<a href="products">Products</a>
			<a href="basket">Basket</a>
			<a href="logout">Logout</a>
	</div>

