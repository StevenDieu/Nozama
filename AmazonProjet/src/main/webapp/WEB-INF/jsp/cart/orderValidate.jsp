<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:include page="../templates/header.jsp" />

VOTRE COMMANDE N° ${sessionScope.order.idOrder}
DU 20/03/16
 

Nous avons le plaisir de vous confirmer la validation de votre commande Nº${sessionScope.order.idOrder}, passée le 20/03/16 à 18:01 sur decathlon.fr


<a class="btn btn-primary center100" href="/">Retour page d'accueil</a>
<jsp:include page="../templates/footer.jsp" />