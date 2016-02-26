<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../templates/header.jsp"/>

	<form class="singnIn col-lg-3 col-lg-offset-4" name="singnIn" method="post">
		<h2>Connexion</h2>
		<span class="errorConnexion"></span><br/>
		<input type="hidden" class="redirect" value="{{redirect}}" />
		<input type="text" name="email" class="email form-control" placeholder="Adresse email" /><br/>
		<input type="password" name="password" class="pwd form-control" placeholder="Mot de passe" /><br/>
		<input type="checkbox" name="rememberMe" class="checkbox">Se souvenir de moi<br/>
		<input type="submit" class="btn btn-default" placeholder="Se connecter" /><br/>
		<a href="mot-de-passe-oublie">Mot de passe oubli&eacute;?</a><br/>
		<a href="inscription">Pas encore inscrit ?</a>
	</form>

<jsp:include page="../templates/footer.jsp"/>