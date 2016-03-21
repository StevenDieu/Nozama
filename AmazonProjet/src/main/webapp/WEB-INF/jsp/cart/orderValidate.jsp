<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:include page="../templates/header.jsp" />

<h1>
	VOTRE COMMANDE N° ${sessionScope.lastOrder.idOrder} DU
	<fmt:formatDate pattern="yyyy-MM-dd" value="${sessionScope.lastOrder.createTime}" />
</h1>

Nous avons le plaisir de vous confirmer la validation de votre commande
Nº${sessionScope.lastOrder.idOrder}, passée le
<fmt:formatDate pattern="yyyy-MM-dd" value="${sessionScope.lastOrder.createTime}" />
à
<fmt:formatDate type="time" value="${sessionScope.lastOrder.createTime}" />
sur misys.fr


<a class="btn btn-primary center100" href="/">Retour page d'accueil</a>
<jsp:include page="../templates/footer.jsp" />