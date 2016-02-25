<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp" />

<div class="filtre">
	<form action="liste-toutes-les-musiques" method="get">
		Filtre :
		<br />
		<br />
		<input type="hidden" name="recordType" value="${recordType}" />
		Format :
		<span data-value="single" class="buttonWithoutBackground 
<c:choose>
	<c:when test="${recordType == 'single'}">
		selectedFormat
	</c:when>
</c:choose>"> Single </span>
		<span data-value="album" class="buttonWithoutBackground
<c:choose>
	<c:when test="${recordType == 'album'}">
		selectedFormat
	</c:when>
</c:choose>"> Album </span>

		Support : <select name="support">
			<option value="CD" <c:if test="${support == 'CD' }">selected</c:if>>CD</option>
			<option value="VINYLE" <c:if test="${support == 'VINYLE' }">selected</c:if>>Vinyle</option>
			<option value="DOWLOAD" <c:if test="${support == 'DOWLOAD' }">selected</c:if>>Téléchargement</option>

		</select> Année : <select name="years">
			<option value="-1">Toutes les années</option>
			<option value="1940" <c:if test="${years == '1940' }">selected</c:if>>Année 40</option>
			<option value="1950" <c:if test="${years == '1950' }">selected</c:if>>Année 50</option>
			<option value="1960" <c:if test="${years == '1960' }">selected</c:if>>Année 60</option>
			<option value="1970" <c:if test="${years == '1970' }">selected</c:if>>Année 70</option>
			<option value="1980" <c:if test="${years == '1980' }">selected</c:if>>Année 80</option>
			<option value="1990" <c:if test="${years == '1990' }">selected</c:if>>Année 90</option>
			<option value="2000" <c:if test="${years == '2000' }">selected</c:if>>Année 2000</option>
		</select>


		<input type="submit" class="btn btn-primary" value="Lancer" />

	</form>

</div>

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
			<fmt:formatNumber value="${product.price}" type="currency" />
		</div>
		<div class="buttonAddCart">
			<input type="button" class="btn btn-primary" value="Ajouter au panier" />
		</div>
	</a>

	<c:if test="${counter.count % 4 == 0}">
		</div>
	</c:if>

</c:forEach>

<script src="resources/js/listeProduct.js"></script>

<jsp:include page="templates/footer.jsp" />