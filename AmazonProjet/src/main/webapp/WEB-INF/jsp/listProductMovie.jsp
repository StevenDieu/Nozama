<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp" />
<div class="filtre">
	<form action="/liste-tous-les-films" class="formFilm" method="get">
		Filtre :
		<br />
		<br />

		Support :
		<select id="support" name="support">
			<option value="DVD" <c:if test="${support == 'CD' }">selected</c:if>>DVD</option>
			<option value="BLUERAY" <c:if test="${support == 'BLUERAY' }">selected</c:if>>Blu-ray</option>
			<option value="DOWNLOAD" <c:if test="${support == 'DOWNLOAD' }">selected</c:if>>Téléchargement</option>
		</select>

		Genre de film :
		<select id="type" name="type">
			<option value="ALL">Toutes les genres de films</option>
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
			<img src="/resources/img/product/<c:out value="${product.movie.product.urlPicture}" />" alt="${product.movie.product.name}" />
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

<script src="/resources/js/listeProduct.js"></script>


<jsp:include page="templates/footer.jsp" />