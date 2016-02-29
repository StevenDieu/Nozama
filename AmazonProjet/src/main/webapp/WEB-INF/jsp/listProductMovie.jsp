<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp" />
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">Filtre :</h3>
	</div>
	<div class="panel-body">

		<form action="/liste-tous-les-films" class="formFilm" method="get">

			<div class="blocFiltre">
				Support : <select id="support" name="support">AllSupport
					<option value="AllSupport" <c:if test="${support == 'AllSupport' }">selected</c:if>>Tous les supports</option>
					<option value="DVD" <c:if test="${support == 'DVD' }">selected</c:if>>DVD</option>
					<option value="BLUERAY" <c:if test="${support == 'BLUERAY' }">selected</c:if>>Blu-ray</option>
					<option value="DOWNLOAD" <c:if test="${support == 'DOWNLOAD' }">selected</c:if>>Téléchargement</option>
				</select>
			</div>

			<div class="blocFiltre">
				Année : <select id="years" name="years">
					<option value="AllYears">Toutes les années</option>
					<option value="2010" <c:if test="${years == '2010' }">selected</c:if>>Année 2010</option>
					<option value="2000" <c:if test="${years == '2000' }">selected</c:if>>Année 2000</option>
					<option value="1990" <c:if test="${years == '1990' }">selected</c:if>>Année 90</option>
					<option value="1980" <c:if test="${years == '1980' }">selected</c:if>>Année 80</option>
					<option value="1970" <c:if test="${years == '1970' }">selected</c:if>>Année 70</option>
					<option value="1960" <c:if test="${years == '1960' }">selected</c:if>>Année 60</option>
					<option value="1950" <c:if test="${years == '1950' }">selected</c:if>>Année 50</option>
					<option value="1940" <c:if test="${years == '1940' }">selected</c:if>>Année 40</option>
					<option value="1939" <c:if test="${years == '1939' }">selected</c:if>>Antérieur</option>
				</select>
			</div>


			<div class="blocFiltre">
				Genre de film : <select id="type" name="type">
					<option value="ALL">Tous les genres de films</option>
					<option value="Action" <c:if test="${type == 'Action' }">selected</c:if>>Action</option>
					<option value="Animation" <c:if test="${type == 'Animation' }">selected</c:if>>Animation</option>
					<option value="ArtsMartiaux" <c:if test="${type == 'ArtsMartiaux' }">selected</c:if>>Arts Martiaux</option>
					<option value="Aventure" <c:if test="${type == '"Aventure"' }">selected</c:if>>Aventure</option>
					<option value="Biopic" <c:if test="${type == 'Biopic' }">selected</c:if>>Biopic</option>
					<option value="ComedieDramatique" <c:if test="${type == 'ComedieDramatique' }">selected</c:if>>Comédie dramatique</option>
					<option value="ComedieMusicale" <c:if test="${type == 'ComedieMusicale' }">selected</c:if>>Comédie musicale</option>
					<option value="Comedie" <c:if test="${type == 'Comedie' }">selected</c:if>>Comédie</option>
					<option value="Divers" <c:if test="${type == 'Divers' }">selected</c:if>>Divers</option>
					<option value="Documentaire" <c:if test="${type == 'Documentaire' }">selected</c:if>>Documentaire</option>
					<option value="Drame" <c:if test="${type == 'Drame' }">selected</c:if>>Drame</option>
					<option value="EpouvanteHorreur" <c:if test="${type == 'EpouvanteHorreur' }">selected</c:if>>Epouvante-horreur</option>
					<option value="Espionnage" <c:if test="${type == 'Espionnage' }">selected</c:if>>Espionnage</option>
					<option value="Famille" <c:if test="${type == 'Famille' }">selected</c:if>>Famille</option>
					<option value="Fantastique" <c:if test="${type == 'Fantastique' }">selected</c:if>>Fantastique</option>
					<option value="Guerre" <c:if test="${type == 'Guerre' }">selected</c:if>>Guerre</option>
					<option value="Historique" <c:if test="${type == 'Historique' }">selected</c:if>>Historique</option>
					<option value="Musical" <c:if test="${type == 'Musical' }">selected</c:if>>Musical</option>
					<option value="Peplum" <c:if test="${type == 'Peplum' }">selected</c:if>>Péplum</option>
					<option value="Policier" <c:if test="${type == 'Policier' }">selected</c:if>>Policier</option>
					<option value="Romance" <c:if test="${type == 'Romance' }">selected</c:if>>Romance</option>
					<option value="ScienceFiction" <c:if test="${type == 'ScienceFiction' }">selected</c:if>>Science fiction</option>
					<option value="Thriller" <c:if test="${type == 'Thriller' }">selected</c:if>>Thriller</option>
					<option value="Western" <c:if test="${type == 'Western' }">selected</c:if>>Western</option>
				</select>
			</div>

			<div class="blocFiltre">
				<input type="submit" class="btn btn-primary" value="Filtrer" />
			</div>
		</form>
	</div>
</div>

<div class="clearBoth"></div>

<div class="rowProduct">

	<c:set var="listProducts" value="${products}" />
	<c:forEach var="product" items="${listProducts}" varStatus="counter">

		<a href="#" class="product">
			<div class="titleProduct">
				<c:out value="${product.name}" />
			</div>
			<div class="imageProduct">
				<img src="/resources/img/product/<c:out value="${product.urlPicture}" />" alt="${product.name}" />
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

			<div class="allBlockProductBottom">
				<c:set var="listType" value="${product.listType}" />
				<c:forEach var="type" items="${listType}">
					<div class="blockProductBottom">

						<div class="priceProduct">
							<img src="/resources/img/${type.support}.png" />
							<fmt:formatNumber value="${type.price}" type="currency" />
						</div>
						<div class="buttonAddCart">
							<input type="button" class="btn btn-primary" value="Ajouter au panier" />
						</div>
					</div>

				</c:forEach>
			</div>
		</a>


	</c:forEach>

</div>
<div class="paginationFilm center100"></div>
<script>
	var varNumberPage = ${numberPage};
	var varStartPage = ${startPage};
</script>

<script src="/resources/js/jquery.twbsPagination.min.js"></script>
<script src="/resources/js/listeProduct.js"></script>


<jsp:include page="templates/footer.jsp" />