
<form class="signUp " method="post">

	<div class="panel panel-default">

		<input type="hidden" class="redirectSignUp" value="${redirect}" />

		<div class="panel-heading">
			<h3 class="panel-title">Inscription</h3>
		</div>
		<div class="panel-body">
			<br />

			<div class="form-group">
				<label>Genre * : </label>
				<label class="radio-inline">
					<input type="radio" name="gender" id="genre" class="required" value="H" checked>
					Homme
				</label>
				<label class="radio-inline">
					<input type="radio" name="gender" id="genre" class="required" value="F">
					Femme
				</label>
				<p class="help-block"></p>

			</div>

			<div class="form-group">
				<label class="control-label">Prenom * </label>
				<div class="form-label">
					<input type="text" class="name form-control required checkLength" data-length="255" placeholder="Prenom" />
				</div>
				<p class="help-block"></p>

			</div>

			<div class="form-group">
				<label class="control-label">Nom * </label>
				<div class="form-label">
					<input type="text" class="lastName form-control required checkLength" data-length="255" placeholder="Nom" />
				</div>
				<p class="help-block"></p>

			</div>

			<div class="form-group">
				<label class="control-label">Adresse email * </label>
				<div class="form-label">
					<input type="text" class="emailSignUp form-control required checkLength" data-length="255" placeholder="Adresse email" />
				</div>
				<p class="help-block"></p>

			</div>

			<div class="form-group">
				<label class="control-label">Mot de passe * </label>
				<div class="form-label">
					<input type="password" class="passwordSignUp form-control required checkLength" data-length="255" placeholder="Mot de passe" />
				</div>
				<p class="help-block"></p>

			</div>

			<div class="form-group">
				<label class="control-label">Confirmation mot de passe * </label>
				<div class="form-label">
					<input type="password" class="confirmPassword form-control required" placeholder="Confirmation mot de passe" />
				</div>
				<p class="help-block"></p>

			</div>

			<div class="checkbox">
				<label>
					<input type="checkbox" class="cgv checkbox required">
					J'ai lu et j'accepte les
					<a href="#">conditions g&eacute;n&eacute;rales d'utilisation du Site et du Service Nozama. *</a>
				</label>
				<p class="help-block"></p>
			</div>
			
			<div class="form-group">
				<input type="submit" class="btn btn-default submitSearch input-cart input-100" value="S'incrire" />
			</div>

			* Champs obligatoire

		</div>
	</div>

</form>

<script src="resources/js/authentification/signUp.js"></script>
