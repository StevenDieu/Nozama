<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp" />

<ol class="breadcrumb">
	<li>
		<a href="/"><span>Accueil </span></a>
	</li>
	<li>
		<a href="/liste-tous-les-films"><span>Movie</span></a>
	</li>
	<li class="active">
		<span>${products.name} </span>
	</li>
</ol>
<div class="container">
	<div class="row">
		<div class="col-md-5 col-md-offset-0">
			<img class="img-product img-responsive" src="/resources/img/product/<c:out value="${products.urlPicture}" />" alt="${products.name}" />
		</div>
		<div class="col-md-7">
			<h2>
				<c:out value="${products.name}" />
			</h2>
			<div class="artist-product">
				<a href="/artiste/"></a>
				<span>
					Date de sortie
					<fmt:formatDate pattern="dd/MM/yyyy" value="${products.dateReleased}" />
				</span>

			</div>


			<p>
				<c:if test="${fn:length(products.description) == 0}">
					Pas de description...
				</c:if>
				<c:out value="${products.description}" />
			</p>

			<div class="row">

				<c:set var="listType" value="${products.listType}" />

				<c:forEach var="type" items="${listType}">
					<div class="blockProductBottom">

						<div class="col-md-12 price-product">
							<div class="priceProduct">
								<img data-toggle="tooltip" data-placement="left" title="Format : ${type.support}" src="/resources/img/${type.support}.png" />
								<fmt:formatNumber value="${type.price}" type="currency" />
							</div>
							<button class="btn btn-primary" type="button">Ajouter au panier</button>
						</div>
					</div>

				</c:forEach>

			</div>
		</div>
	</div>
	<div class="page-header">
		<h3>Détail du produit</h3>
	</div>
	<p>Temps total :</p>
	<p>Producteur :</p>
</div>

<jsp:include page="templates/footer.jsp" />
