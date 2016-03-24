<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="rowProduct">
	<c:set var="listProducts" value="${products}" />
	<c:forEach var="product" items="${listProducts}" varStatus="counter">


		<div data-href="/product/${product.type}/${product.nameTagDateReleased}" class="product clickProduct">
			<div class="titleProduct">
				<c:out value="${product.name}" />
			</div>

			<div class="hashTagList">
				<a data-redirect="${product.urlType}" class="btn-link">#${product.type}</a>
				<c:if test="${not empty product.artiste}">
					<a href="/artiste/${product.artiste}" class="btn-link">#${product.artiste}</a>
				</c:if>

			</div>

			<div class="imageProduct">
				<img src="/resources/img/product/<c:out value="${product.urlPicture}" />" alt="${product.name}" />
			</div>

			<div class="descriptionProduct">
				<c:out value="${fn:substring(product.description, 0, 87)}" />
				<c:if test="${fn:length(product.description) > 87}">
					...
				</c:if>

			</div>

			<div class="allBlockProductBottom">
				<c:set var="listType" value="${product.listType}" />
				<c:forEach var="type" items="${listType}">
					<div class="blockProductBottom">
						<div class="priceProduct">

							<img data-toggle="tooltip" data-placement="left" title="Format : ${type.support}" src="/resources/img/${type.support}.png" alt="${type.support}"/>
							<fmt:formatNumber value="${type.price}" minFractionDigits="2" type="number" /> €
						</div>
						<div class="buttonAddCart">
							<input type="button" class="addCart btn btn-primary" data-id="${type.id}" data-type="${product.type}" value="Ajouter au panier" />
						</div>
					</div>

				</c:forEach>
			</div>
		</div>


	</c:forEach>
</div>
<div class="clearBoth"></div>