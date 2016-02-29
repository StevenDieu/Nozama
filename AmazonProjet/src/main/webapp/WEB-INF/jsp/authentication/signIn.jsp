<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../templates/header.jsp" />
<form class="singnIn col-md-6 col-md-offset-3" name="singnIn" method="post">

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Connexion</h3>
		</div>
		<div class="panel-body">

			<div class="alert alert-danger alert-dismissible hide" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<strong>Erreur !! </strong>
				<p class="errorMessage"></p>
				<p>
			</div>
			</br>

			<input type="hidden" class="redirect" value="${redirect}" />
			
			<div class="form-group">
				<input type="text" name="email" class="email form-control" placeholder="Adresse email" />
			</div>
			
			<div class="form-group">
				<input type="password" name="password" class="pwd form-control" placeholder="Mot de passe" />
			</div>
			
			<div class="checkbox">
				<label> <input type="checkbox" name="rememberMe" class="checkbox"> Se souvenir de moi
				</label>
			</div>
			
			<div class="form-group">
				<input type="submit" class="btn btn-default" placeholder="Se connecter" />
			</div>
			
			<div class="form-group">
				<a href="mot-de-passe-oublie">Mot de passe oubli&eacute;?</a>
			</div>
			
			<div class="form-group">
				<a href="inscription">Pas encore inscrit ?</a>
			</div>
		</div>
	</div>
</form>


<script src="resources/js/authentification/signIn.js"></script>

<jsp:include page="../templates/footer.jsp" />