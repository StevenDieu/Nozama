
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

<div class="col-md-8 col-md-offset-2">
	<div class=" panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Adresse</h3>
		</div>
		<div class="panel-body">

			<br />
			<c:set var="messageInfos" value="${message}" />
			<c:forEach var="messageInfo" items="${messageInfos}" varStatus="counter">
				<div class="alert alert-info" style="display: block" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>Aie !</strong>
					<c:out value="${messageInfo}" />
				</div>
				<br />
			</c:forEach>

			<form class="formAdresse" action="mon-panier-etape-validation-adress" name="singnIn"
				method="post">

				<jsp:include page="../authentication/contentAdress.jsp" />

				<div class="form-group">
					<input type="submit" class="btn btn-primary input-cart input-100" value="Continuer">
				</div>
			</form>
		</div>

	</div>
</div>
<script src="/resources/js/cart/cart.js"></script>

<jsp:include page="../templates/footer.jsp" />