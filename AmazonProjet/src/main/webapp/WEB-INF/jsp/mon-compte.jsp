
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp" />


<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
	<div class="panel panel-primary">
		<div class="panel-heading" role="tab" id="headingOne">
			<h4 class="panel-title">
				<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
					aria-expanded="true" aria-controls="collapseOne"> Mon compte </a>
			</h4>
		</div>
		<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel"
			aria-labelledby="headingOne">
			<div class="panel-body">
				<c:set var="user" value="${user}" />
				<p>
					<c:out value="${user.name}" />
					<c:out value="${user.lastname}" />
				</p>
				<p>
					<c:out value="${user.emailAdress}" />
				</p>
			</div>
		</div>
	</div>
	<div class="panel panel-primary">
		<div class="panel-heading" role="tab" id="headingTwo">
			<h4 class="panel-title">
				<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
					href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo"> Mes commandes </a>
			</h4>
		</div>
		<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
			<div class="panel-body">
				<c:set var="listeCommandes" value="${commandes}" />
				<div class="form-group">
					<c:if test="${commandes != null}">
						<div class="panel-group" id="accordionCommande" role="tablist" aria-multiselectable="true">
	  						<div class="panel panel-default">
								<c:forEach var="commande" items="${listeCommandes}" varStatus="counter">
									<div class="panel-heading" role="tab">
								      <h4 class="panel-title">
								        <a role="button" data-toggle="collapse" data-parent="#accordionCommande" href="#${commande.idOrder}" aria-expanded="true" aria-controls="collapseOne">
								          Commande n°<c:out value="${commande.idOrder}"/>
								        </a>
								      </h4>
								    </div>
								    <div id="${commande.idOrder}"  class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
								      	<div class="panel-body">
									        <p>Total produit : <c:out value="${commande.totalProduct}"/></p>
											<p>Frais de livraison : <c:out value="${commande.totalDelivery}"/></p>
											<p>Coût total : <c:out value="${commande.totalOrder}"/></p>
											<p>Mode de payement : <c:out value="${commande.modePayment}"/></p>
										</div>
										<div class="panel-body">
									        <p>Adresse : <c:out value="${commande.adress.adressPrincipal}"/></p>
											<p><c:out value="${commande.adress.adressSecondaire}"/></p>
											<p>Code postal : <c:out value="${commande.adress.codePostal}"/></p>
											<%--<p>Ville : <c:out value="${commande.adress.city}"/></p> --%>
									    </div>
									    <button type="button" class="btn btn-primary btn-lg btn-voitPrd input-100" data-id="${commande.idOrder}" data-toggle="modal" data-target="#myModal">
										  Voir mes produits
										</button>
								    </div>
								</c:forEach>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<div class="panel panel-primary">
		<div class="panel-heading" role="tab" id="headingThree">
			<h4 class="panel-title">
				<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
					href="#collapseThree" aria-expanded="false" aria-controls="collapseThree"> Mes adresses </a>
			</h4>
		</div>
		<div id="collapseThree" class="panel-collapse collapse" role="tabpanel"
			aria-labelledby="headingThree">
			<div class="panel-body">

					<jsp:include page="authentication/contentAdress.jsp" />
					
			</div>
		</div>
	</div>
	<div class="panel panel-primary">
		<div class="panel-heading" role="tab">
			<h4 class="panel-title">
				<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
					href="#myComptePrepaye" aria-expanded="false" aria-controls="myComptePrepaye">Mon compte
					prepayé </a>
			</h4>
		</div>
		<div id="myComptePrepaye" class="panel-collapse collapse" role="tabpanel"
			aria-labelledby="headingThree">
			<div class="panel-body">
				<h3>Recharger votre compte</h3>
				<p>Pour faciliter vos achats réguliers, un compte prépayé vous est automatiquement ouvert
					dès votre inscription avec un solde de 0€. Le compte prépayé fonctionne de la même façon qu’un
					porte-monnaie virtuel et vous permet de gagner du temps lors de vos transactions. Créditez
					votre compte prépayé à l’aide de votre carte bancaire ou votre compte Paypal et débitez le
					directement lors de vos achats.</p>
				<br />

				<p>
					Solde actuel:
					<span class="argent">
						<fmt:formatNumber minFractionDigits="2" type="number"
							value="${sessionScope.User.comptePrepaye}" />
					</span>
					€
				</p>

				<p>Montant minimum à créditer: 5,00 €</p>

				<p>
					Indiquez la somme à créditer sur votre compte prépayé en euros :
					<input type="text" class="form-control input-compte-prepaye">
					<input type="button" class="btn btn-primary input-100 btn-compte-prepaye" value="Valider">
				</p>


			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Mes produits</h4>
      </div>
      <div class="modal-body body-article">
        
      </div>
    </div>
  </div>
</div>

<script src="/resources/js/account/accountAdress.js"></script>

<jsp:include page="templates/footer.jsp" />