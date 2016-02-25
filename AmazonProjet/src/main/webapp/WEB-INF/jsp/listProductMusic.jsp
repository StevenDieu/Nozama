<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp" />

<div class="filtre">
	<form action="/liste-toutes-les-musiques" class="formMusic" method="get">
		<div class="texteFiltre">Filtre :</div>

		<div class="blocFiltre">
			<input type="hidden" id="recordType" name="recordType" value="${recordType}" />
			Format :
			<span data-value="single" class="buttonWithoutBackground <c:if test="${recordType == 'single'}">selectedFormat</c:if>"> Single </span>
			<span data-value="album" class="buttonWithoutBackground <c:if test="${recordType == 'album'}">selectedFormat</c:if>"> Album </span>
		</div>

		<div class="blocFiltre">
			Support :
			<select id="support" name="support">
				<option value="CD" <c:if test="${support == 'CD' }">selected</c:if>>CD</option>
				<option value="VINYLE" <c:if test="${support == 'VINYLE' }">selected</c:if>>Vinyle</option>
				<option value="DOWLOAD" <c:if test="${support == 'DOWLOAD' }">selected</c:if>>Téléchargement</option>
			</select>
		</div>

		<div class="blocFiltre">
			Année :
			<select id="years" name="years">
				<option value="-1">Toutes les années</option>
				<option value="1940" <c:if test="${years == '1940' }">selected</c:if>>Année 40</option>
				<option value="1950" <c:if test="${years == '1950' }">selected</c:if>>Année 50</option>
				<option value="1960" <c:if test="${years == '1960' }">selected</c:if>>Année 60</option>
				<option value="1970" <c:if test="${years == '1970' }">selected</c:if>>Année 70</option>
				<option value="1980" <c:if test="${years == '1980' }">selected</c:if>>Année 80</option>
				<option value="1990" <c:if test="${years == '1990' }">selected</c:if>>Année 90</option>
				<option value="2000" <c:if test="${years == '2000' }">selected</c:if>>Année 2000</option>
			</select>
		</div>

		<div class="blocFiltre">
			Genre de musique :
			<select id="type" name="type">
				<option value="ALL">Toutes les genres de musique</option>
				<option value="VarieteFrancaise" <c:if test="${type == 'VarieteFrancaise' }">selected</c:if>>Variété française</option>
				<option value="PopRockInde" <c:if test="${type == 'PopRockInde' }">selected</c:if>>Pop-rock indé</option>
				<option value="MusiqueClassqie" <c:if test="${type == 'MusiqueClassqie' }">selected</c:if>>Musique classique</option>
				<option value="HarRockMetal" <c:if test="${type == 'HarRockMetal' }">selected</c:if>>Hard rock, métal</option>
				<option value="JassBlues" <c:if test="${type == 'JassBlues' }">selected</c:if>>Jazz, blues</option>
				<option value="RBSoulFunk" <c:if test="${type == 'RBSoulFunk' }">selected</c:if>>R&amp;B, soul, funk</option>
				<option value="MusiqueDuMonde" <c:if test="${type == 'MusiqueDuMonde' }">selected</c:if>>Musiques du monde</option>
				<option value="Rap" <c:if test="${type == 'Rap' }">selected</c:if>>Rap</option>
				<option value="MusiquePourEnfant" <c:if test="${type == 'MusiquePourEnfant' }">selected</c:if>>Musique pour enfants</option>
				<option value="Electro" <c:if test="${type == 'Electro' }">selected</c:if>>Electro</option>
				<option value="Opera" <c:if test="${type == 'Opera' }">selected</c:if>>Opéra</option>
				<option value="BoMusiqueFilm" <c:if test="${type == 'BoMusiqueFilm' }">selected</c:if>>BO, musiques de film</option>
			</select>
		</div>

		<div class="blocFiltre">
			<input type="submit" class="btn btn-primary" value="Filtrer" />
		</div>

		<div class="clearBoth"></div>

	</form>

</div>

<c:set var="listAdherent" value="${products}" />
<c:forEach var="typeSupport" items="${listAdherent}" varStatus="counter">

	<c:if test="${recordType == 'single'}">
		<c:set var="single" value="${typeSupport.single}" />
		<c:set var="product" value="${typeSupport.single.product}" />
	</c:if>
	<c:if test="${recordType == 'album'}">
		<c:set var="single" value="${typeSupport.album}" />
		<c:set var="product" value="${typeSupport.album.product}" />
	</c:if>

	<c:if test="${counter.count - 1 % 4 == 0}">
		<div class="rowProduct">
	</c:if>

	<a href="#" class="product">
		<div class="titleProduct">
			<c:out value="${product.name}" />
		</div>
		<div class="imageProduct">
			<img src="${baseURL}/resources/img/product/<c:out value="${product.urlPicture}" />" alt="${product.name}" />
		</div>

		<div class="descriptionProduct">
			<c:if test="${fn:length(product.description) == 0}">
				Pas de description...
			</c:if>
			<c:out value="${fn:substring(product.description, 0, 87)}" />
			<c:if test="${fn:length(product.description) > 87}">
				...
			</c:if>
		</div>
		<div class="priceProduct">
			<fmt:formatNumber value="${typeSupport.price}" type="currency" />
		</div>
		<div class="buttonAddCart">
			<input type="button" class="btn btn-primary" value="Ajouter au panier" />
		</div>
	</a>

	<c:if test="${counter.count % 4 == 0}">
		</div>
	</c:if>

</c:forEach>

<script src="/resources/js/listeProduct.js"></script>

<jsp:include page="templates/footer.jsp" />