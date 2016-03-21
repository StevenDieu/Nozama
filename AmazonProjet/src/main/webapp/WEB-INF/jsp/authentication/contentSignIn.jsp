
<form class="singnIn" name="singnIn" method="post">

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Connexion</h3>
		</div>
		<div class="panel-body">

			<br/>

			<input type="hidden" class="redirectSignIn" value="${redirect}" />

			<div class="form-group">
				<label class="control-label">Adresse email * </label>
				<div class="form-label">
					<input type="text" name="email" class="email form-control required checkLength" data-length="255" placeholder="Adresse email" />
				</div>
				<p class="help-block"></p>

			</div>

			<div class="form-group">
				<label class="control-label">Mot de passe * </label>
				<div class="form-label">
					<input type="password" name="password" class="pwd form-control required checkLength" data-length="255" placeholder="Mot de passe" />
				</div>
				<p class="help-block"></p>

			</div>

			<div class="checkbox">
				<label>
					<input type="checkbox" name="rememberMe" class="checkbox">
					Se souvenir de moi
				</label>
			</div>
			<div class="form-group">
				<a href="inscription">Pas encore inscrit ?</a>
			</div>

			<div class="form-group">
				<input type="submit" class="btn btn-default input-cart input-100" placeholder="Se connecter" />
			</div>



			* Champs obligatoire
		</div>
	</div>
</form>

<script src="resources/js/authentification/signIn.js"></script>