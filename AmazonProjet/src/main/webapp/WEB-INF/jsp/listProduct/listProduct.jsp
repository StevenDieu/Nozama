<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="rowProduct">

	<c:set var="listProducts" value="${products}" />
	<c:forEach var="product" items="${listProducts}" varStatus="counter">


		<a href="/product/${product.type}/${product.nameTagDateReleased}" class="product">
			<div class="titleProduct">
				<c:out value="${product.name}" />
			</div>

			<div class="hashTagList">
				<button data-redirect="${product.urlType}" class="btn-link">#${product.type}</button>
				<c:if test="${not empty product.artiste}">
					<button data-redirect="/artiste/${product.artiste}" class="btn-link">#${product.artiste}</button>
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

							<img data-toggle="tooltip" data-placement="left" title="Format : ${type.support}" src="/resources/img/${type.support}.png" />
							<fmt:formatNumber value="${type.price}" type="currency" />
						</div>
						<div class="buttonAddCart">
							<input type="button" class="addCart btn btn-primary" data-id="${type.id}" data-type="${product.type}" value="Ajouter au panier" />
						</div>
					</div>

				</c:forEach>
			</div>
		</a>


	</c:forEach>
</div>
<div class="clearBoth"></div>