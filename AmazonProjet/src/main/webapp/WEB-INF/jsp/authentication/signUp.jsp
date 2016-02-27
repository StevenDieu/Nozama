<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../templates/header.jsp" />
<form class="signUp col-md-6 col-md-offset-3" method="post">

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Inscription</h3>
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
			<br />

			<div class="form-group">
				<input type="radio" name="gender" id="genre" value="H">
				Homme
				<input type="radio" name="gender" id="genre" value="F">
				Femme
			</div>

			<div class="form-group">
				<input type="text" class="name form-control" placeholder="Prenom" />
			</div>
			<div class="form-group">
				<input type="text" class="lastName form-control" placeholder="Nom" />
			</div>
			<div class="form-group">
				<input type="text" class="email form-control" placeholder="Adresse email" />
			</div>
			<div class="form-group">
				<input type="password" class="password form-control" placeholder="Mot de passe" />
			</div>

			<div class="form-group">
				<input type="password" class="confirmPassword form-control" placeholder="Confirmation mot de passe" />
			</div>

			<div class="checkbox">
				<label> <input type="checkbox" class="cgv checkbox"> J'ai lu et j'accepte les <a href="#">conditions g&eacute;n&eacute;rales d'utilisation du Site et du Service Nozama.</a>
				</label>
			</div>

			<input type="submit" class="btn btn-default submitSearch" value="S'incrire" />
		</div>
	</div>
</form>


<script src="resources/js/authentification/signUp.js"></script>

<jsp:include page="../templates/footer.jsp" />