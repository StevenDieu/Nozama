<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp" />
<div class="filtre">
	<form action="liste-tous-les-films" method="get">
		Filtre :
		<br />
		<br />

		Support : <select name="support">
			<option value="DVD" <c:if test="${support == 'CD' }">selected</c:if>>DVD</option>
			<option value="BLUERAY" <c:if test="${support == 'BLUERAY' }">selected</c:if>>Blu-ray</option>
			<option value="DOWNLOAD" <c:if test="${support == 'DOWLOAD' }">selected</c:if>>Téléchargement</option>

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
			<c:out value="${product.movie.product.name}" />
		</div>
		<div class="imageProduct">
			<img src="resources/img/product/<c:out value="${product.movie.product.urlPicture}" />" alt="${product.movie.product.name}" />
		</div>

		<div class="descriptionProduct">
			<c:if test="${fn:length(product.movie.product.description) == 0}">
				Pas de description...
			</c:if>
			<c:out value="${fn:substring(product.movie.product.description, 0, 87)}" />
			<c:if test="${fn:length(product.movie.product.description) > 87}">
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
<jsp:include page="templates/footer.jsp" />