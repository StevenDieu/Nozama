<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head lang="fr">
<meta charset="UTF-8" />
<title>Nozama</title>
<meta name="description" content="Site d'association" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="robots" content="index, follow" />
<meta name="googlebot" content="index, follow" />
<meta name="google" content="notranslate" />
<meta name="viewport" content="width=device-width" />
<link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="/resources/css/jquery.smartmenus.bootstrap.css">
<link type="text/css" rel="stylesheet" href="/resources/css/site.css">
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/site.js"></script>

</head>
<body>

	<!-- Navbar -->
	<div class="navbar navbar-default navbar-static-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/">
					<img src="/resources/img/Misys_logo.png" alt="logo" class="logo" />
				</a>
			</div>
			<div class="navbar-collapse collapse">

				<!-- Left nav -->
				<ul class="nav navbar-nav">
					<li>
						<a href="/liste-tous-les-produits">
							Tous les produits
							<span class="caret"></span>
						</a>

						<ul class="dropdown-menu">
							<li class="menu-lateral">
								<div class="blockMusique">
									<h3 class="text-center titleMenu">
										<a href="/liste-toutes-les-musiques">Musique</a>
									</h3>
									<div class="blockSsMenu">
										<ol class="list-unstyled ssMenu">
											<li>
												<a href="/liste-toutes-les-musiques/VarieteFrancaise"> Variété française </a>
											</li>
											<li>
												<a href="/liste-toutes-les-musiques/PopRockInde"> Pop-rock indé </a>
											</li>
											<li>
												<a href="/liste-toutes-les-musiques/MusiqueClassique"> Musique classique </a>
											</li>
											<li>
												<a href="/liste-toutes-les-musiques/HarRockMetal"> Hard rock, métal </a>
											</li>
											<li>
												<a href="/liste-toutes-les-musiques/JassBlues"> Jazz, blues </a>
											</li>
											<li>
												<a href="/liste-toutes-les-musiques/RBSoulFunk"> R&amp;B, soul, funk </a>
											</li>
										</ol>
										<ol class="list-unstyled ssMenu">

											<li>
												<a href="/liste-toutes-les-musiques/MusiqueDuMonde"> Musiques du monde </a>
											</li>
											<li>
												<a href="/liste-toutes-les-musiques/Rap"> Rap </a>
											</li>
											<li>
												<a href="/liste-toutes-les-musiques/MusiquePourEnfant"> Musique pour enfants </a>
											</li>
											<li>
												<a href="/liste-toutes-les-musiques/Electro"> Electro </a>
											</li>
											<li>
												<a href="/liste-toutes-les-musiques/Opera"> Opéra </a>
											</li>
											<li>
												<a href="/liste-toutes-les-musiques/BoMusiqueFilm"> BO, musiques de film </a>
											</li>
										</ol>
									</div>
								</div>
								<div class="clearBoth"></div>
								<div class="legend"></div>
								<div class="blockFilm">
									<h3 class="text-center titleMenu">
										<a href="/liste-tous-les-films">Film </a>
									</h3>
									<div class="blockSsMenu">

										<ol class="list-unstyled ssMenu">
											<li>
												<a href="/liste-tous-les-films/Action">Action</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/Animation">Animation</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/ArtsMartiaux">Arts Martiaux</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/Aventure">Aventure</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/Biopic">Biopic</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/ComedieDramatique">Comédie dramatique</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/ComedieMusicale">Comédie musicale</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/Comedie">Comédie</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/Divers">Divers</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/Documentaire">Documentaire</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/Drame">Drame</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/EpouvanteHorreur">Epouvante-horreur</a>
											</li>
										</ol>
										<ol class="list-unstyled ssMenu">
											<li>
												<a href="/liste-tous-les-films/Espionnage">Espionnage</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/Famille">Famille</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/Fantastique">Fantastique</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/Guerre">Guerre</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/Historique">Historique</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/Musical">Musical</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/Peplum">Péplum</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/Policier">Policier</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/Romance">Romance</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/ScienceFiction">Science fiction</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/Thriller">Thriller</a>
											</li>
											<li>
												<a href="/liste-tous-les-films/Western">Western</a>
											</li>
										</ol>
									</div>
								</div>
							</li>
						</ul>
					</li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${sessionScope.User != null}">
							<li>
								<a href="/mon-compte">Mon compte</a>
							</li>
							<li>
								<a href="/se-deconnecter">Se deconnecter</a>
							</li>
						</c:when>
						<c:when test="${sessionScope.User == null}">
							<li>
								<a href="/connexion">Connexion</a>
							</li>
							<li>
								<a href="/inscription">Inscription</a>
							</li>
						</c:when>
					</c:choose>
					<li>

						<a href="/mon-panier">
							<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
							<span class="badge nbCart">
								<c:choose>
									<c:when test="${sessionScope.nbCart != null}">
										<c:out value="${sessionScope.nbCart}" />
									</c:when>
									<c:when test="${sessionScope.nbCart == null}">
										0
									</c:when>
								</c:choose>
							</span>

						</a>
					</li>
				</ul>


			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<div class="container">

		<div id="alert" class="block-alert-fixe"></div>