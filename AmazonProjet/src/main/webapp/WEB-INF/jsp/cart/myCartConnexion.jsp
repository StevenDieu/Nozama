
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
	<li class="step_current second">
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

<div class="row">
	<div class="col-md-6">
		<jsp:include page="../authentication/contentSignIn.jsp" />
	</div>
	<div class="col-md-6">
		<jsp:include page="../authentication/contentSignUp.jsp" />
	</div>
</div>

<script src="/resources/js/cart/cart.js"></script>

<jsp:include page="../templates/footer.jsp" />