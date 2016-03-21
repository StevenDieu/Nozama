
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../templates/header.jsp" />
<ul class="step clearfix" id="order_step">
	<li class="step_current  first">
		<span>
			<em>01.</em> Récapitulatif
		</span>
	</li>
	<li class="step_todo second">
		<span>
			<em>02.</em> Connexion
		</span>
	</li>
	<li class="step_todo third">
		<span>
			<em>03.</em> Adresse
		</span>
	</li>
	<li class="step_todo four">
		<span>
			<em>04.</em> Livraison
		</span>
	</li>
	<li id="step_end" class="step_todo last">
		<span>
			<em>05.</em> Paiement
		</span>
	</li>
</ul>

<div class="panel panel-default">
	<div class="panel-heading">Mon Panier</div>
	<div class="panel-body">
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
						<th class="centerVerticale">
							<strong>Total</strong>
						</th>
						<th class="center">
							<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#popUpDeletePanier">Tout supprimer</button>
						</th>
					</tr>
				</thead>
				<tbody class="tbodyCart">
					<c:set var="listProducts" value="${products}" />
					<c:forEach var="product" items="${listProducts}" varStatus="counter">
						<tr class="productCart">
							<td class="center tdLeft">
								<a href="/product/${product.type}/${product.nameTagDateReleased}">
									<img src="/resources/img/product/<c:out value="${product.urlPicture}" />" alt="${product.urlPicture}" class="imageCart">
								</a>
							</td>
							<td class="centerVerticale tdRight tdNameProduct">
								<span class="blockTdBeforeCart">Nom : </span>
								<a href="/product/${product.type}/${product.nameTagDateReleased}"> ${product.name} </a>
							</td>
							<td class="centerVerticale tdRight tdTypeProduct">
								<span class="blockTdBeforeCart">Type : </span>
								<a href="${product.urlType}">${product.type}</a>
							</td>
							<td class="centerVerticale center tdRight tdFormatProduct">
								<img data-toggle="tooltip" alt="${product.support}" data-placement="left" title="Format : ${product.support}" src="/resources/img/${product.support}.png" />
							</td>
							<td class="centerVerticale tdLeft tdPrixProduct">
								<span class="blockTdBeforeCart">Prix unitaire: </span>

								<span class="priceProduct${product.id}${product.type}" data-price="<c:out value="${product.price}" />">
									<fmt:formatNumber value="${product.price}" minFractionDigits="2" type="number" /> €
								</span>
							</td>
							<td class="centerVerticale center tdLeft tdQuantiteProduct">
								<span class="blockTdBeforeCart">Quantité : </span>

								<div class="input-number-cart">
									<input type="text" class="form-control numberProduct numberProduct${product.id}${product.type}" data-type="${product.type}" data-id="${product.id}" data-idtype="${product.id}${product.type}" value="${product.numberProduct}" />
								</div>
								<div class="">
									<button class="btn btn-default minus" data-type="${product.type}" data-id="${product.id}" data-idtype="${product.id}${product.type}" type="button">-</button>
									<button class="btn btn-default plus" data-type="${product.type}" data-id="${product.id}" data-idtype="${product.id}${product.type}" type="button">+</button>
								</div>
							</td>
							<td class="centerVerticale tdRight tdPrixTotalProduct">
								<span class="blockTdBeforeCart">Prix total : </span>
								<span class="totalProductBlock">
								<span data-price="${product.totalProduct}" class="totalProduct${product.id}${product.type} totalProduct">${product.totalProduct}</span>
								€
								</span>
							</td>
							<td class="centerVerticale center tdDeleteProduct">
								<span class="blockTdBeforeCart">Supprimer </span>

								<button class="btn btn-danger center deleteProduct" data-type="${product.type}" data-id="${product.id}" type="button">x</button>
							</td>
						</tr>
					</c:forEach>
					<tr class="trTotal">
						<td colspan="5" class="trCartSpanTotal"></td>
						<td class="totalCart centerVerticale ">Total :</td>
						<td colspan="2" class="tdPrixTotalFinal">
							<span class="priceTotal">${priceTotal}</span>
							€
						</td>
					</tr>

				</tbody>
			</table>

		</div>
		<a href="/mon-panier-validation-panier" class="btn btn-primary input-cart input-100">Valider mon panier</a>
	</div>
</div>
<div class="modal fade" id="popUpDeletePanier" tabindex="-1" role="dialog" aria-labelledby="popUpDeletePanier">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Suppression du panier</h4>
			</div>
			<div class="modal-body">Voulez-vous réellement supprimer le panier ?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger suppressionPanier" data-dismiss="modal">Oui</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal">Non</button>
			</div>
		</div>
	</div>
</div>
<script src="/resources/js/cart/cart.js"></script>

<jsp:include page="../templates/footer.jsp" />