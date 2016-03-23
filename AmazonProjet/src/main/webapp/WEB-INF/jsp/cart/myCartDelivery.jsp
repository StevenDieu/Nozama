
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
	<li class="step_current four">
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


<div class="col-md-6 col-md-offset-3">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">Méthode de livraison</h3>
		</div>
		<div class="panel-body">
			<form action="mon-panier-etape-validation-transport" method="post">
				<div class="form-group">
					<label class="radio-inline">
						<c:choose>
							<c:when test="${sessionScope.transport == null}">
								<input type="radio" name="chooseTransport" value="eco" checked />
							</c:when>
							<c:when test="${sessionScope.transport != null}">
								<c:if test="${sessionScope.transport.id == 'eco'}">
									<input type="radio" name="chooseTransport" value="eco" checked />
								</c:if>
								<c:if test="${sessionScope.transport.id != 'eco'}">
									<input type="radio" name="chooseTransport" value="eco" />
								</c:if>
							</c:when>
						</c:choose>

						ECONOMIQUE - 10,00 €<br/>
						Date de livraison estimée : 7-9 jours
					</label>
				</div>
				<div class="form-group">

					<label class="radio-inline">
						<c:choose>
							<c:when test="${sessionScope.transport == null}">
								<input type="radio" name="chooseTransport" value="exp" />
							</c:when>
							<c:when test="${sessionScope.transport != null}">
								<c:if test="${sessionScope.transport.id == 'eco'}">
									<input type="radio" name="chooseTransport" value="exp" />
								</c:if>
								<c:if test="${sessionScope.transport.id != 'eco'}">
									<input type="radio" name="chooseTransport" value="exp" checked />
								</c:if>
							</c:when>
						</c:choose>
						2016 EXPRESS &amp; SAMEDI - 14,00 €<br/>
						Date de livraison estimée : 3-4 jours
					</label>
				</div>
				<div class="form-group">
					<c:if test="${sessionScope.transport != null}">
					<c:if test="${sessionScope.transport.commentaire != null}">
						<div class="form-label">
							<textarea class="form-control checkLength" data-length="255" rows="3" name="commentaire" placeholder="Ajouter un commentaire pour le transporteur (max : 255 charatères)">${sessionScope.transport.commentaire}</textarea>
						</div>
						<p class="help-block"></p>
					</c:if>
					<c:if test="${sessionScope.transport.commentaire == null}">
						<div class="form-label">
							<textarea class="form-control checkLength" data-length="255" rows="3" name="commentaire" placeholder="Ajouter un commentaire pour le transporteur (max : 255 charatères)"></textarea>
						</div>
						<p class="help-block"></p>
					</c:if>
						
					</c:if>
					<c:if test="${sessionScope.transport == null}">
						<div class="form-label">
							<textarea class="form-control checkLength" data-length="255" rows="3" name="commentaire" placeholder="Ajouter un commentaire pour le transporteur (max : 255 charatères)"></textarea>
						</div>
						<p class="help-block"></p>
					</c:if>

				</div>
				<input type="submit" class="btn btn-primary input-cart input-100" value="Continuer">
			</form>
		</div>
	</div>
</div>

<script src="/resources/js/cart/cart.js"></script>

<jsp:include page="../templates/footer.jsp" />