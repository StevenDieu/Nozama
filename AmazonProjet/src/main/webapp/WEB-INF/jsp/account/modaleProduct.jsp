<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="table-responsive">
	<table class="table table-bordered">
		<thead>
			<tr class="trCartTitle">
				<th class="center centerVerticale">
					<strong>Produit</strong>
				</th>
				<th class="centerVerticale">Nom</th>
				<th class="centerVerticale">Type</th>
				<th class="center centerVerticale">Support</th>
				<th class="centerVerticale">Prix unitaire</th>
				<th class="center centerVerticale">Quantité</th>
			</tr>
		</thead>
		<tbody class="tbodyCart">
			<c:set var="listProducts" value="${products}" />
			<c:forEach var="product" items="${listProducts}" varStatus="counter">
				<tr class="productCart">
					<td class="center tdLeft">
						<a target="_blank"
							href="/product/${product.article.product.type}/${product.article.product.nameTagDateReleased}">
							<img src="/resources/img/product/<c:out value="${product.article.product.urlPicture}" />"
								alt="${product.article.product.urlPicture}" class="imageCart">
						</a>
					</td>
					<td class="centerVerticale tdRight tdNameProduct">
						<span class="blockTdBeforeCart">Nom : </span>
						<a target="_blank"
							href="/product/${product.article.product.type}/${product.article.product.nameTagDateReleased}">
							${product.article.product.name} </a>
					</td>
					<td class="centerVerticale tdRight tdTypeProduct">
						<span class="blockTdBeforeCart">Type : </span>
						${product.article.product.type}
					</td>
					<td class="centerVerticale tdRight tdTypeSupport">
						<span class="blockTdBeforeCart">Support : </span>
						${product.article.nameSupport}
					</td>
					<td class="centerVerticale tdRight tdTypePrix">
						<span class="blockTdBeforeCart">Prix unitaire : </span>
						${product.article.price}
					</td>
					<td class="centerVerticale tdPrixTotalProduct clearBothTrCart">
						<span class="blockTdBeforeCart">Quantité : </span>
						${product.quantity}
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>