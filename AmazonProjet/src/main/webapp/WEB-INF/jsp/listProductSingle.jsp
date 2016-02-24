<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp" />

<c:set var="listAdherent" value="${products}" />
<c:forEach var="product" items="${listAdherent}" varStatus="counter">
	<c:if test="${counter.count - 1 % 4 == 0}">
		<div class="rowProduct">
	</c:if>

	<a href="#" class="product">
		<div class="titleProduct">
			<c:out value="${product.single.product.name}" />
		</div>
		<div class="imageProduct">
			<img src="resources/img/product/<c:out value="${product.single.product.urlPicture}" />" alt="${product.single.product.name}" />
		</div>
		
		<div class="descriptionProduct">
			<c:if test="${fn:length(product.single.product.description) == 0}">
				Pas de description...
			</c:if>
			<c:out value="${fn:substring(product.single.product.description, 0, 87)}" />
			<c:if test="${fn:length(product.single.product.description) > 87}">
				...
			</c:if>
		</div>
		<div class="priceProduct">
			<fmt:formatNumber value="${product.price}" type="currency"/>
		</div>
		<div class="buttonAddCart">
			<input type="button" class="btn btn-primary" value="Ajouter au panier" />
		</div>
	</a>

	<c:if test="${counter.count % 4 == 0}">
		</div>
	</c:if>

</c:forEach>
<jsp:include page="templates/footer.jsp" />