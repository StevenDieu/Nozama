
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../templates/header.jsp" />
<ul class="step clearfix" id="order_step">
	<li class="step_done  first">
		<a href="/mon-panier">
			<em>01.</em> Récapitulatif
		</a>
	</li>
	<li class="step_done second">
		<a href="/mon-panier-etape-connexion">
			<em>02.</em> Connexion
		</a>
	</li>
	<li class="step_done third">
		<a href="/mon-panier-etape-adresse">
			<em>03.</em> Adresse
		</a>
	</li>
	<li class="step_done four">
		<a href="/mon-panier-etape-livraison">
			<em>04.</em> Livraison
		</a>
	</li>
	<li class="step_current last">
		<span>
			<em>05.</em> Paiement
		</span>
	</li>
</ul>

<div class="col-md-9">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Méthode de paiement</h3>
		</div>
		<div class="panel-body">
			<div class="form-group radioFormPayment">

				<div class="radio">
					<label>
						<input type="radio" name="choosePayment" value="CB" <c:if test="${sessionScope.payment == 'CB'}">checked</c:if>/>
						Carte bleu VISA
						<img src="/resources/img/cart/visa.png" class="imgPaypment" alt="visa"  />
						
					</label>
				</div>
				<div class="radio">
					<label>
						<input type="radio" name="choosePayment" value="PAYPAL" <c:if test="${sessionScope.payment == 'PAYPAL'}">checked</c:if>/>
						Paypal
						<img src="/resources/img/cart/paypal.png" alt="paypal" class="imgPaypment" />
					</label>
				</div>
				<div class="radio">
					<label>
						<input type="radio" name="choosePayment" value="PREPAYE"/>
						Compte prépayé MISYS
						<img src="/resources/img/cart/prepaye.png" class="imgPaypment" alt="prepaye"><br/>
						Argent sur le compte : <fmt:formatNumber value="${sessionScope.User.comptePrepaye}" minFractionDigits="2" type="number" /> €
					</label>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="col-md-3">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Finaliser commande</h3>
		</div>
		<div class="panel-body">
			<p>
				Produits :
				<span class="floatRight">
					<fmt:formatNumber value="${sessionScope.prixTotalProduct}" minFractionDigits="2" type="number" /> €
				</span>
			</p>
			<p>
				Livraison :
				<span class="floatRight">
					<fmt:formatNumber value="${sessionScope.transport.prix}" minFractionDigits="2" type="number" /> €
				</span>
			</p>
			<p>
				Montant total :
				<span class="floatRight">
					<span class="resultCommandeTotal">
						<fmt:formatNumber value="${sessionScope.totalPrice}" minFractionDigits="2" type="number" /> €
					</span>
				</span>
			</p>
			<br />
			<input type="button" class="btn btn-primary width100 validCart" value="Terminer commande" />
		</div>
	</div>
</div>

<div class="col-md-12">


	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Récapitulatif commande</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="col-md-6">
					<p>
						<strong>Adresse d'expédition</strong>
					</p>
					<p>Nom et Prénom : ${sessionScope.address.nameLastName}</p>
					<p>Adresse principale : ${sessionScope.address.adressPrincipal}</p>
					<c:if test="${sessionScope.address.adressSecondaire != null}">
						<p>Adresse secondaire : ${sessionScope.address.adressSecondaire}</p>
					</c:if>
					<c:if test="${sessionScope.address.region}">
						<p>Région : ${sessionScope.address.region}</p>
					</c:if>
					<p>Code postal : ${sessionScope.address.codePostal}</p>
					<p>Pays : ${sessionScope.address.pays}</p>
					<p>Numéros de téléphone : ${sessionScope.address.numberPhone}</p>
				</div>

				<div class="col-md-6">
					<p>
						<strong>Mode de livraison</strong>
					</p>

					<c:if test="${sessionScope.transport.id != eco}">
						<p>2016 EXPRESS &amp; SAMEDI - 14,00 €</p>
						<p>Date de livraison estimée : 3-4 jours</p>
					</c:if>
					<c:if test="${sessionScope.transport.id == eco}">
						<p>ECONOMIQUE - 10,00 €</p>
						<p>Date de livraison estimée : 7-9 jours</p>
					</c:if>
					
					<c:if test="${sessionScope.transport.commentaire != null}">
						<p class="breakWord">Commentaire transporteur : ${sessionScope.transport.commentaire}</p>
					</c:if>
					

				</div>
			</div>
			<br />
			<div class="row">
				<div class="col-md-12">
					<p>
						<strong>Récapitulatif produits</strong>
					</p>
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
								</tr>
							</thead>
							<tbody class="tbodyCart">
								<c:set var="listProducts" value="${products}" />
								<c:forEach var="product" items="${listProducts}" varStatus="counter">
									<tr class="productCart">
										<td class="center tdLeft">
											<a href="/product/${product.type}/${product.nameTagDateReleased}">
												<img src="/resources/img/product/<c:out value="${product.urlPicture}" />"
													alt="${product.urlPicture}"  class="imageCart">
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
											<img data-toggle="tooltip" alt="${product.support}" data-placement="left" title="Format : ${product.support}"
												src="/resources/img/${product.support}.png" />
										</td>
										<td class="centerVerticale tdLeft tdPrixProduct">
											<span class="blockTdBeforeCart">Prix unitaire: </span>

											<span class="priceProduct${product.id}${product.type}"
												data-price="<c:out value="${product.price}" />">
												<fmt:formatNumber value="${product.price}" minFractionDigits="2" type="number" /> €
											</span>
										</td>
										<td class="centerVerticale center tdLeft tdQuantiteProduct">
											<span class="blockTdBeforeCart">Quantité : </span>

											${product.numberProduct}
										</td>
										<td class="centerVerticale tdPrixTotalProduct clearBothTrCart">
											<span class="blockTdBeforeCart">Prix total : </span>
											<span class="totalProductBlock">
												<span data-price="${product.totalProduct}"
													class="totalProduct${product.id}${product.type} totalProduct">${product.totalProduct}</span>
												€
											</span>
										</td>
									</tr>
								</c:forEach>
								<tr class="trTotal">
									<td colspan="4" class="trCartSpanTotal"></td>
									<td class="totalCart centerVerticale ">Total :</td>
									<td colspan="2" class="tdPrixTotalFinal">
										<span class="priceTotal">${priceTotal}</span>
										€
									</td>
								</tr>

							</tbody>
						</table>

					</div>


				</div>
			</div>

		</div>
	</div>
</div>

<script src="/resources/js/cart/cart.js"></script>

<jsp:include page="../templates/footer.jsp" />