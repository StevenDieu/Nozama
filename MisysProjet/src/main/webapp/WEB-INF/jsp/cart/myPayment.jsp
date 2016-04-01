<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:include page="../templates/header.jsp" />

<c:set var="redirectPage" value="psp/${sessionScope.payment}.jsp" />

<jsp:include page="${redirectPage}" />

<a class="btn btn-warning input-100" href="/mon-panier-etape-paiement">Retour</a>
<a class="btn btn-success input-cart input-100" href="/finalisation-commande">Finaliser la commande</a>
<jsp:include page="../templates/footer.jsp" />