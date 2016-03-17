
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
	<li class="step_current third">
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

<jsp:include page="../authentication/contentAdress.jsp" />


<script src="/resources/js/cart.js"></script>

<jsp:include page="../templates/footer.jsp" />