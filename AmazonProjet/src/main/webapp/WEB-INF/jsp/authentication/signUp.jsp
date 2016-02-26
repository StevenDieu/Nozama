<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../templates/header.jsp"/>

	<form class="signUp col-lg-3 col-lg-offset-4"  method="post">
		<h2>Inscription</h2>
		<span class="errorConnexion"></span><br/>
		<input type="radio" name="gender" id="genre"  value="H"> Homme 
		<input type="radio" name="gender" id="genre" value="F"> Femme <br/>
		<input type="text" class="name form-control" placeholder="Prenom" /><br /> 
		<input type="text" class="lastName form-control" placeholder="Nom" /><br /> 
		<input type="text" class="email form-control" placeholder="Adresse email" /><br />
		<input type="password" class="password form-control" placeholder="Mot de passe" /><br /> 
		<input type="password" class="confirmPassword form-control" placeholder="Confirmation mot de passe" /><br /> 
		<input type="checkbox" class="cgv checkbox">J'ai lu et j'accepte les <a href="#">conditions g&eacute;n&eacute;rales d'utilisation du Site et du Service Kazsucar.</a><br/><br/>
		<input type="submit" class="btn btn-default submitSearch" value="S'incrire" />
	</form>

<jsp:include page="../templates/footer.jsp"/>