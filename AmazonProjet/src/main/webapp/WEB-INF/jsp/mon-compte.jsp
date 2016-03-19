
<%@ taglib prefix="form"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"
	uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp" />


<div class="panel-group" id="accordion"
	role="tablist" aria-multiselectable="true">
	<div class="panel panel-default">
		<div class="panel-heading" role="tab"
			id="headingOne">
			<h4 class="panel-title">
				<a role="button" data-toggle="collapse"
					data-parent="#accordion" href="#collapseOne"
					aria-expanded="true"
					aria-controls="collapseOne"> Mon compte </a>
			</h4>
		</div>
		<div id="collapseOne"
			class="panel-collapse collapse in"
			role="tabpanel" aria-labelledby="headingOne">
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
	<div class="panel panel-default">
		<div class="panel-heading" role="tab"
			id="headingTwo">
			<h4 class="panel-title">
				<a class="collapsed" role="button"
					data-toggle="collapse"
					data-parent="#accordion" href="#collapseTwo"
					aria-expanded="false"
					aria-controls="collapseTwo"> Mes
					commandes </a>
			</h4>
		</div>
		<div id="collapseTwo"
			class="panel-collapse collapse" role="tabpanel"
			aria-labelledby="headingTwo">
			<div class="panel-body">Toutes mes
				commandes</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading" role="tab"
			id="headingThree">
			<h4 class="panel-title">
				<a class="collapsed" role="button"
					data-toggle="collapse"
					data-parent="#accordion"
					href="#collapseThree" aria-expanded="false"
					aria-controls="collapseThree"> Mes
					adresses </a>
			</h4>
		</div>
		<div id="collapseThree"
			class="panel-collapse collapse" role="tabpanel"
			aria-labelledby="headingThree">
			<div class="panel-body">
				<jsp:include page="authentication/contentAdress.jsp" />
			</div>
		</div>
	</div>
</div>

<jsp:include page="templates/footer.jsp" />