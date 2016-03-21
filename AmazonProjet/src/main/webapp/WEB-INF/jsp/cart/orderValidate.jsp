<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="../templates/header.jsp" />

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">Confirmation de votre commande</h3>
	</div>
	<div class="panel-body">

		<h1>Votre num�ro de commande est : ${sessionScope.lastOrder.idOrder}</h1>

		<br />
		<p>
			Nous avons le plaisir de vous confirmer la validation de votre commande N� ${sessionScope.lastOrder.idOrder}, pass�e le
			<fmt:formatDate pattern="yyyy-MM-dd" value="${sessionScope.lastOrder.createTime}" />
			�
			<fmt:formatDate pattern="HH:mm:ss" value="${sessionScope.lastOrder.createTime}" />
			sur misys.fr
		</p>
		<br />
		<p>Cette r�f�rence vous servira pour suivre l'�tat de votre commande depuis la rubrique <a href="/mon-compte">Mon compte</a>.</p>
		<br />
		<p>Nous vous remercions de votre visite sur misys.fr.</p>
		<br/><br/>
		<a class="btn btn-primary center100" href="/">Retour page d'accueil</a>
		
	</div>
</div>


<jsp:include page="../templates/footer.jsp" />