<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../templates/header.jsp" />

<ol class="breadcrumb">
	<li><a href="/"> <span>Accueil </span>
	</a></li>
	<li><a href="/liste-toutes-les-musiques"> <span>Musique</span>
	</a></li>
	<li><a
		href="/liste-toutes-les-musiques/AllSupport/${products.type}/AllYears/ALL">
			<span class="first-letter">${products.type}</span>
	</a></li>
	<li class="active"><span>${products.name} </span></li>
</ol>
<div class="container">
	<div class="row">
		<div class="col-md-5 col-md-offset-0">
			<img class="img-product img-responsive"
				src="/resources/img/product/<c:out value="${products.urlPicture}" />"
				alt="${products.name}" />
		</div>
		<div class="col-md-7">
			<h2>
				<c:out value="${products.name}" />
			</h2>
			<div class="artist-product">
				<a href="">${products.artisteName}</a> <span> - Date de
					sortie <fmt:formatDate pattern="dd/MM/yyyy"
						value="${products.dateReleased}" />
				</span>

			</div>


			<p>
				<c:out escapeXml="false" value="${products.description}" />
			</p>
			<div class="row">
				<c:set var="listType" value="${products.listType}" />

				<c:forEach var="type" items="${listType}">
					<div class="blockProductBottom">

						<div class="col-md-12 price-product">
							<div class="priceProduct">
								<img data-toggle="tooltip" data-placement="left"
									alt="${type.support}" title="Format : ${type.support}"
									src="/resources/img/${type.support}.png" />
								<fmt:formatNumber value="${type.price}" minFractionDigits="2"
									type="number" />
								€
							</div>
							<button class="addCart btn btn-primary" data-id="${type.id}"
								data-type="${products.type}">Ajouter au panier</button>
						</div>
					</div>

				</c:forEach>

			</div>
		</div>
	</div>
	<div class="page-header">
		<h3>Détail du produit</h3>
	</div>
	<a target="_blank" title="Twitter"
		href="https://twitter.com/share?url=${url}&text=${products.name}&via=nozama"
		rel="nofollow"
		onclick="javascript:window.open(this.href, '', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=400,width=700');return false;">
		<img
		src="http://korben.info/wp-content/themes/korben2013/hab/twitter_icon.png"
		alt="Twitter" />
	</a> <a target="_blank" title="Facebook"
		href="https://www.facebook.com/sharer.php?u=${url}.&t=${products.name}"
		rel="nofollow"
		onclick="javascript:window.open(this.href, '', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=500,width=700');return false;">
		<img
		src="http://korben.info/wp-content/themes/korben2013/hab/facebook_icon.png"
		alt="Facebook" />
	</a> <a target="_blank" title="Google +"
		href="https://plus.google.com/share?url=${url}.&hl=fr" rel="nofollow"
		onclick="javascript:window.open(this.href, '', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=450,width=650');return false;">
		<img
		src="http://korben.info/wp-content/themes/korben2013/hab/gplus_icon.png"
		alt="Google Plus" />
	</a> <br /> <br />
	<c:if test="${not empty products.genre}">
		<p>
			Genre :

			<c:set var="genres" value="${products.genre}" />
			<c:forEach var="genre" items="${genres}" varStatus="counter">
				<a
					href="/liste-toutes-les-musiques/AllSupport/AllType/AllYears/${genre}"
					class="linkBold"> <c:out value="${genre}" />
				</a>
				<c:if test="${counter.count < fn:length(genres)}">
				,
				</c:if>
			</c:forEach>

		</p>
	</c:if>
	<c:if test="${not empty products.albumName}">
		<p>
			Album : <a
				href="/product/album/${products.albumName.nameTagDateReleased}"
				class="linkBold"> <c:out value="${products.albumName.name}" />
			</a>
		</p>
	</c:if>
	<c:if test="${not empty products.totalTime}">
		<p>
			Temps total :
			<fmt:formatDate type="time" value="${products.totalTime}" />
		</p>
	</c:if>
	<c:if test="${not empty products.label}">
		<p>
			Label :
			<c:out value="${products.label}" />
		</p>
	</c:if>

	<c:if test="${not empty products.allSingle}">
		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Nom Single</th>
						<th>Temps</th>
						<th>Date de sortie</th>
					</tr>
				</thead>
				<tbody>

					<c:set var="singles" value="${products.allSingle}" />
					<c:forEach var="single" items="${singles}" varStatus="counter">

						<tr>
							<td><a href="/product/single/${single.nameTagDateReleased}">
									<strong>${single.name}</strong>
							</a></td>
							<td>${single.totalTime}</td>
							<td><fmt:formatDate pattern="dd/MM/yyyy"
									value="${single.dateReleased}" /></td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:if>
</div>

<jsp:include page="../templates/footer.jsp" />
