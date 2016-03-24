<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp" />

<div id="carousel-example-generic" class="carousel slide replaceCarousel" data-ride="carousel">
	<!-- Indicators -->
	<ol class="carousel-indicators">
		<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
		<li data-target="#carousel-example-generic" data-slide-to="1"></li>
		<li data-target="#carousel-example-generic" data-slide-to="2"></li>
	</ol>

	<!-- Wrapper for slides -->
	<div class="carousel-inner" role="listbox">
		<div class="item active">
			<a href="/liste-toutes-les-musiques/MusiqueClassique">
				<img src="/resources/img/home/music_classique.jpg" alt="music classique">
			</a>
		</div>

		<div class="item">
			<a href="/liste-toutes-les-musiques/Vinyle/single/AllYears/ALL">
				<img src="/resources/img/home/music_vinyle.jpg" alt="music vinyle">
			</a>
		</div>

		<div class="item">
			<a href="#">
				<img src="/resources/img/home/zootopie.jpg" alt="music vinyle">
			</a>
		</div>


	</div>

	<!-- Controls -->
	<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
		<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
		<span class="sr-only">Previous</span>
	</a>
	<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
		<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
		<span class="sr-only">Next</span>
	</a>
</div>
<c:if test="${not empty movies}">

	<h1>Films du moment</h1>
	<div class="rowProduct">
		<c:set var="listProducts" value="${movies}" />
		<c:forEach var="product" items="${listProducts}" varStatus="counter">


			<div data-href="/product/${product.type}/${product.nameTagDateReleased}"
				class="product clickProduct">
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

								<img data-toggle="tooltip" data-placement="left" title="Format : ${type.support}"
									src="/resources/img/${type.support}.png" alt="${type.support}" />
								<fmt:formatNumber value="${type.price}" minFractionDigits="2" type="number" />
								€
							</div>
							<div class="buttonAddCart">
								<input type="button" class="addCart btn btn-primary" data-id="${type.id}"
									data-type="${product.type}" value="Ajouter au panier" />
							</div>
						</div>

					</c:forEach>
				</div>
			</div>


		</c:forEach>
	</div>
	<div class="clearBoth"></div>
</c:if>
<c:if test="${not empty singles}">

	<h1>Singles du moment</h1>
	<div class="rowProduct">
		<c:set var="listProducts" value="${singles}" />
		<c:forEach var="product" items="${listProducts}" varStatus="counter">


			<div data-href="/product/${product.type}/${product.nameTagDateReleased}"
				class="product clickProduct">
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

								<img data-toggle="tooltip" data-placement="left" title="Format : ${type.support}"
									src="/resources/img/${type.support}.png" alt="${type.support}" />
								<fmt:formatNumber value="${type.price}" minFractionDigits="2" type="number" />
								€
							</div>
							<div class="buttonAddCart">
								<input type="button" class="addCart btn btn-primary" data-id="${type.id}"
									data-type="${product.type}" value="Ajouter au panier" />
							</div>
						</div>

					</c:forEach>
				</div>
			</div>


		</c:forEach>
	</div>
	<div class="clearBoth"></div>
</c:if>
<c:if test="${not empty albums}">

	<h1>Albums du moment</h1>
	<div class="rowProduct">
		<c:set var="listProducts" value="${albums}" />
		<c:forEach var="product" items="${listProducts}" varStatus="counter">


			<div data-href="/product/${product.type}/${product.nameTagDateReleased}"
				class="product clickProduct">
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

								<img data-toggle="tooltip" data-placement="left" title="Format : ${type.support}"
									src="/resources/img/${type.support}.png" alt="${type.support}" />
								<fmt:formatNumber value="${type.price}" minFractionDigits="2" type="number" />
								€
							</div>
							<div class="buttonAddCart">
								<input type="button" class="addCart btn btn-primary" data-id="${type.id}"
									data-type="${product.type}" value="Ajouter au panier" />
							</div>
						</div>

					</c:forEach>
				</div>
			</div>


		</c:forEach>
	</div>
	<div class="clearBoth"></div>
</c:if>
<jsp:include page="templates/footer.jsp" />