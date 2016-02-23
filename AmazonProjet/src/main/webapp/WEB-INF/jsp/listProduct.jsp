<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="templates/header.jsp" />


Liste product Music
<c:set var="listAdherent" value="${products}" />
<c:forEach var="product" items="${listAdherent}">
<div class="">
	<c:out value="${product.name}" /><br/><br/>
	<img src="resources/img/product/<c:out value="${product.urlPicture}" />" alt="${product.name}" />
	<c:out value="${product.description}" />
</div>



<br/><br/><br/><br/><br/><br/><br/><br/><br/>
</c:forEach>
<jsp:include page="templates/footer.jsp" />