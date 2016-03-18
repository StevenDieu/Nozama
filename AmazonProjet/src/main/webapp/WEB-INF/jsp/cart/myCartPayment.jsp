
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
<div class="col-md-6 col-md-offset-3">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Méthode de paiement</h3>
		</div>
		<div class="panel-body">
			
		</div>
	</div>
</div>

<script src="/resources/js/cart/cart.js"></script>

<jsp:include page="../templates/footer.jsp" />