<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form class="signUp " method="post">

	<div class="panel panel-primary">

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
					<input type="text" class="name form-control required checkLength" data-length="255"
						placeholder="Prenom" />
				</div>
				<p class="help-block"></p>

			</div>

			<div class="form-group">
				<label class="control-label">Nom * </label>
				<div class="form-label">
					<input type="text" class="lastName form-control required checkLength" data-length="255"
						placeholder="Nom" />
				</div>
				<p class="help-block"></p>

			</div>

			<div class="form-group">
				<label class="control-label">Adresse email * </label>
				<div class="form-label">
					<input type="text" class="emailSignUp form-control required checkLength" data-length="255"
						placeholder="Adresse email" />
				</div>
				<p class="help-block"></p>

			</div>

			<div class="form-group">
				<label class="control-label">Mot de passe * </label>
				<div class="form-label">
					<input type="password" class="passwordSignUp form-control required checkLength"
						data-length="255" placeholder="Mot de passe" />
				</div>
				<p class="help-block"></p>

			</div>

			<div class="form-group">
				<label class="control-label">Confirmation mot de passe * </label>
				<div class="form-label">
					<input type="password" class="confirmPassword form-control required"
						placeholder="Confirmation mot de passe" />
				</div>
				<p class="help-block"></p>

			</div>

			<div class="checkbox">
				<label>
					<input type="checkbox" class="cgv checkbox required">
					J'ai lu et j'accepte les
					<a href="#" class="colorBlueA" data-toggle="modal" data-target="#cgv">conditions
						g&eacute;n&eacute;rales d'utilisation du Site et du Service misys. *</a>
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
<div class="modal fade" id="cgv" tabindex="-1" role="dialog" aria-labelledby="cgv">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Conditions générales</h4>
			</div>
			<div class="modal-body body-article">
				<div id="corps">
					<div id="main" class="galerie">
						<!-- Debut titre + bouton imprimer... -->
						<div id="topContent">
							<div id="contentWithContext">
								<div id="contentIdeesConseil">
									<div class="articles">
										<div class="article">

											<h2>1. Champ d'application</h2>
											<div class="paragraphe">
												<p>
													Les conditions générales de vente décrites ci-dessous (ci-après les <b>"Conditions
														Générales de Vente"</b>) ont pour objet de régir l'ensemble des relations contractuelles
													entre la SA MiSys France au capital de 100.000.000eur dont le siège est situé : Rue
													Chanzy - 59260 LEZENNES - RCS LILLE 384 560 942 (ci-après <b> "Nous" </b>) et les
													clients, âgés de plus de 18 ans et bénéficiant d'une pleine capacité juridique
													(ci-après <b> "Vous" </b>) passant commande par le biais du site misys.fr (ci-après le
													<b> "Site" </b>).
												</p>
												<p>Les Conditions Générales de Vente s'appliquent sans restrictions ni réserves à
													l'ensemble des ventes de produits figurant sur notre catalogue publié sur le Site, à
													l'exclusion des achats effectués directement auprès de nos établissements physiques
													MiSys.</p>
												<p>La passation de commande par le biais du Site implique votre acceptation sans
													réserve des Conditions Générales de Vente. A cet effet, Vous confirmez accepter
													l'ensemble des Conditions Générales de Vente lorsque Vous cochez la case "J'accepte?
													lors de la passation de votre commande. Nous nous réservons la possibilité de modifier
													à tout moment les présentes Conditions Générales de Vente. Nous Vous invitons ainsi à
													faire une lecture attentive des Conditions Générales de Vente à chaque nouvelle
													commande effectuée. Les Conditions Générales de Vente applicables seront celles en
													vigueur au jour de la passation de la commande.</p>
												<p>Les Conditions Générales de Vente sont accessibles à tout moment sur le Site et
													prévalent sur toute autre version ou tout autre document contradictoire.</p>
												<p>Pour toute information notamment relative au suivi de votre commande, Vous êtes
													invités à nous contacter :</p>
												<ul class="listLink02">
													<li>Par téléphone au numéro suivant : 03.59.57.46.04 (numéro non surtaxé)</li>
												</ul>
												<p></p>
											</div>
										</div>
									</div>
									<div class="articles">
										<div class="article">

											<h2>2. Votre commande</h2>
											<div class="paragraphe">
												La commande des produits que Vous sélectionnez (ci-après les <b> "Produits" </b> ) sera
												réalisée selon le processus décrit ci-dessous.
											</div>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Votre identification:</h3>
												Vous accédez au contenu de votre panier, comprenant l'ensemble des Produits. Vous devez
												alors procéder à votre identification. A ce titre, Vous vous engagez à Nous fournir des
												informations exactes et fiables, Nous permettant ainsi d'exécuter l'ensemble de nos
												obligations contractuelles. Notre responsabilité ne saurait être engagée dans
												l'hypothèse où les informations que Vous avez fournies s'avéreraient fausses ou
												incomplètes. Dans ce contexte ou en cas de défaut de paiement ou de toute autre
												irrégularité sur votre compte, Nous nous réservons le droit d'annuler votre commande
												et/ou de supprimer votre compte utilisateur. Votre identification peut être réalisée
												directement après la sélection des Produits ou par le biais de votre compte utilisateur
												(ci-après le <b> "Compte" </b> ). L'ouverture d'un Compte comprend la saisie de deux
												identifiants : une adresse de courrier électronique et le choix d'un mot de passe. Vos
												identifiants vous sont personnels et devront être tenus confidentiels.
												<br>
												Vous pouvez accéder aux données personnelles que Vous nous avez communiquées sur simple
												demande, conformément aux dispositions prévues à l'article Données Personnelles des
												Conditions Générales de Vente.
											</div>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Passation de votre commande:</h3>
												Une fois les produits sélectionnés et vos données d'identification renseignées, Vous
												procédez ensuite à la sélection de votre mode de paiement.
												<br>
												Un récapitulatif de votre commande Vous est alors présenté, lequel reprendra notamment
												les informations relatives aux Produits, aux prix ou encore aux modalités de livraison.
												Un numéro de commande Vous sera également attribué.
											</div>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Vos avantages fidélité en ligne</h3>
												Vous pouvez retrouver, simuler et activer vos remises (bienvenue 5% et passion 10%) sur
												notre site misys.fr directement lors de la mise au panier des produits de votre
												commande. Notre site misys.fr étant ouvert 24h/24h et 7j/7, une journée de remise se
												termine à 23h59.
											</div>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Confirmation définitive de votre commande:</h3>
												Une fois la commande validée, deux situations sont à distinguer : En cas de règlement
												par carte bancaire : la validation définitive de votre commande interviendra lorsque que
												l'autorisation bancaire de débit Nous aura été donnée, soit dans un délai maximal de dix
												(10) jours. Une confirmation définitive vous sera alors adressée, reprenant l'ensemble
												des éléments relatifs à votre commande. En cas de règlement par chèque bancaire ou
												postal : une confirmation de votre commande vous sera adressée après la validation de
												votre commande. La validation définitive de cette dernière interviendra cependant à
												compter de la réception et de la vérification du règlement. A défaut de réception et de
												validation du règlement dans un délai de dix (10) jours, Nous considérons
												automatiquement la commande interrompue.
												<br>
												Seule la validation définitive de la commande Nous engage contractuellement vis-à-vis de
												Vous.
												<br>
												Votre facture vous sera adressée par mail après réception de votre colis.
											</div>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Disponibilité des produits:</h3>
												Les articles Vous sont proposés dans la limite des stocks disponibles. Ainsi, en cas
												d'indisponibilité du Produit commandé, Nous nous engageons à Vous informer dans les plus
												brefs délais et procéderons au remboursement du Produit (frais de port inclus) dans un
												délai maximal de 30 jours. Dans ce cas, Nous procéderons à l'annulation et au
												remboursement de votre commande. En fonction de nos stocks et dans la limite du
												possible, un article équivalent peut vous être proposé.
											</div>
										</div>
									</div>
									<div class="articles">
										<div class="article">

											<h2>3. Prix des produits</h2>
											<div class="paragraphe">
												Les prix indiqués sur le Site sont indiqués "Toute Taxe Comprise" (TTC), et tiennent
												compte du coût d'élimination des déchets d'équipements électriques et électroniques
												(l'éco-contribution).
												<br>
												Sauf indication contraire, les prix indiqués ne comprennent pas les frais de port.
												<br>
												Nous Vous proposons d'accéder à des informations sur des produits non commercialisés sur
												le Site, mais accessibles en magasin. Le prix des produits non vendus en ligne sont
												communiqués à titre purement indicatif. Ils peuvent ainsi varier des prix réellement
												pratiqués en magasins, et les prix peuvent varier d'un magasin à l'autre.
												<br>
											</div>
										</div>
									</div>
									<div class="articles">
										<div class="article">

											<h2>4. Paiement de votre commande</h2>
											<div class="paragraphe">Vous pouvez régler vos achats par :</div>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Carte bancaire :</h3>
												Cartes acceptées : CB, VISA, MASTERCARD ou AMERICAN EXPRESS par la saisie de vos données
												bancaires depuis le Site. Toutes les données bancaires saisies font l'objet d'un
												traitement sécurisé et sont immédiatement cryptées. Ces informations sont uniquement
												accessibles à notre partenaire bancaire, afin qu'il puisse se mettre en relation avec
												votre banque. A aucun moment, Nous, ou tout autre tiers, ne pouvons accéder à vos
												données bancaires. Vos règlements sont transmis à notre partenaire bancaire le jour de
												la commande.
												<br>
												<p>Lors de la validation de votre commande, nous prenons l'empreinte de votre carte
													bancaire. Elle n'est débitée qu'à l'expedition de votre commande ou au plus tard 6
													jours après la validation de votre commande.</p>
												<br>
												<p>
													<b> Attention, le paiement par e-carte bleue n'est pas accepté sur notre site. </b>
												</p>
											</div>
											<div class="paragraphe">
												<h3 class="titreParagraphe">3 fois carte bancaire avec Facily Pay :</h3>
												<p>Notre partenaire Oney Banque Accord vous propose une solution de financement
													dénommée Facily Pay, qui permet de payer vos achats de 300€ à 2000€ en trois fois avec
													votre carte bancaire.</p>
												<br>

												<p>Cette offre est réservée aux particuliers (personnes physiques majeures) résidant
													en France Métropolitaine et titulaires d’une carte bancaire Visa et MasterCard
													possédant une date de validité supérieure à la durée du financement choisie.</p>
												<br>
												<p>Les cartes à autorisation systématique de type Electron ou Maestro, les e-cards
													et les cartes Indigo ne sont pas acceptées.</p>
												<br>

												<p>Pour le paiement en 3 fois : le coût du financement est 1,5% du montant total de
													la commande.</p>
												<br>

												<p>Exemple pour un achat de 250€, apport de 87.08€, puis 2 mensualités de 83.33€,
													coût du financement de 3.74€ au TAEG fixede 19.94%.</p>
												<br>

												<p>Après avoir terminé votre commande, il vous suffit de cliquer sur le « bouton
													paiement en 3 fois par carte bancaire ». Vous êtes alors redirigé vers la page internet
													Facily Pay de notre partenaire affichant le récapitulatif détaillé de votre commande et
													la demande de financement personnalisée, que vous devez ensuite valider.</p>
												<br>
												Vous saisissez vos informations personnelles, prenez connaissance ces conditions
												générales de paiement en plusieurs fois auquel vous souhaitez souscrire et notifiez
												votre acceptation électronique par la case à cocher correspondante. Vous reconnaissez
												que le « double clic » associé à la case à cocher sur la prise de connaissance des
												conditions générales valent consentement à contracter et constituent une acceptation
												irrévocable et sans réserve des conditions générales du produit. Facily Pay est une
												solution de financement d’Oney, marque de Banque Accord. Offre sous réserve sous réserve
												d’acceptation par Banque Accord – SA au capital de 28 981 280€ - 40 avenue de Flandre
												59170 Croix – RCS Lille Métropole 546 380 197 – n°Orias 07 023 261 www.orias.fr -
												Correspondance : CS 60006 – 59895 Lille cedex 9 – www.banque-accord.fr
											</div>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Chèque postal ou bancaire :</h3>
												<p>Votre chèque, dûment complété, signé et libellé à l'ordre de SA MiSys France
													devra nous être adressé à l'adresse suivante dans un délai de 10 jours a compter de la
													passation de votre commande :</p>
												<p>
													MiSys COMMERCE ELECTRONIQUE
													<br>
													Rue Chanzy – Lezennes
													<br>
													59712 LILLE CEDEX 9
												</p>

												<br>
												Pour tout professionnel, toute facture non réglée à l'échéance donnera lieu à une
												pénalité forfaitaire pour frais de recouvrement d'un montant de 40euros qui se cumulera
												de plein droit avec celui des pénalités de retard.
											</div>
											<div class="paragraphe">
												<h3 class="titreParagraphe">En cas de retard de paiement</h3>
												<p>Conformément à la réglementation en vigueur, toute somme non réglée à l'échéance
													par tout professionnel donnera lieu à une pénalité forfaitaire pour frais de
													recouvrement d'un montant de quarante (40) euros qui se cumulera de plein droit avec
													celui des pénalités de retard indiqué sur votre facture.</p>
											</div>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Carte cadeau, carte maison et carte accord</h3>
												<p>Carte cadeau</p>
												<p>
													<br>
												</p>
												<p>Vous pouvez régler tout ou partie de vos achats par Carte Cadeau MiSys. Lors de
													la validation de votre commande, votre carte cadeau est débitée du montant de votre
													choix défini à l’étape paiement.</p>
												<br>
												<br>

												<p>Carte Maison et Carte Accord Comptant</p>
												<p>
													<br>
												</p>
												<p>Vous pouvez régler immédiatement vos achats avec votre Carte Maison (Option de
													financement) ou votre Carte Accord. Lors de la validation de votre commande, nous
													prenons l'empreinte de votre Carte Maison. Elle n'est débitée qu'à l’expédition de
													votre commande ou au plus tard 6 jours après la validation de votre commande.</p>
												<p></p>
											</div>
										</div>
									</div>
									<div class="articles">
										<div class="article">

											<h2>5. Sécurité de vos moyens de paiement</h2>
											<div class="paragraphe">
												<p>Afin de garantir un maximum de sécurité lors des commandes, tous les paiements
													par cartes bancaires sont sécurisés par le système SIPS proposé par ATOS. Ce système
													repose sur des techniques éprouvées de cryptographie assurant ainsi la confidentialité
													et l'intégrité des échanges. Ainsi, lors de la saisie de vos coordonnées bancaires
													(numéro de carte, date de validité, et code .......), ces dernières sont
													automatiquement chiffrées et adressées en mode sécurisé à un serveur (serveur SIPS),
													sans passer par Nous.</p>
												<p>Parallèlement, Nous adressons au même serveur, toujours en mode sécurisé, des
													données propres à votre commande (numéro de commerçant, numéro de transaction, montant
													de la commande). Le système SIPS effectue un contrôle de la carte et une demande
													d'autorisation auprès de votre banque en utilisant un réseau bancaire privé. Le système
													SIPS renvoie la confirmation du paiement et Vous renvoie vers le Site. Le système SIPS
													se charge ensuite du paiement auprès de notre partenaire bancaire.</p>
												<p>
													Pour plus d'informations sur le système SIPS, Vous pouvez consulter le site internet
													d'ATOS
													<a href="http://www.atos-origin.com" target="_blank" class="arialnoir">www.sips.atos-origin.com</a>
													<br>
													Vous pouvez vous assurer de la sécurisation du paiement, en vérifiant dans la barre
													d'adresse du navigateur que l'adresse commence bien par "https". Par ailleurs, un des
													pictogrammes suivant :
													<img
														src="http://s1.lmcdn.fr/7.2/imagesV3/navigationPermanente/pictos-secur._r020656f10f16_.gif"
														width="76" height="18">
													selon le type et la version de votre navigateur doit apparaître.
												</p>
											</div>
										</div>
									</div>
									<div class="articles">
										<div class="article">

											<h2>6. Livraison de votre commande</h2>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Informations générales</h3>
												<p>Nous entendons par livraison l'ensemble des moyens mis en oeuvre pour la
													livraison des Produits commandés par le biais du Site. Les spécificités liées aux
													différents modes de livraison sont détaillées ci-après.</p>
												<p>
													Les livraisons ne peuvent s'effectuer qu'en France Métropolitaine. Seul les colis dont
													la livraison peut s'effectuer par colis postal (poids inférieur à trente kilogrammes
													(30 kg) et/ou dimensions totales inférieures à un mètre cinquante (1m50) pourront être
													livrées en Corse et dans les autres îles de la Métropole.
													<br>
													La livraison interviendra à l'adresse que Vous avez indiquée au moment de la passation
													de votre commande.
												</p>
												<p>La livraison interviendra dans les délais indiqués sur la confirmation de
													commande que Nous Vous aurons adressée. Les livraisons ne pourront être réalisées les
													week-ends et les jours fériés. Les délais de livraison courent à partir du lendemain du
													règlement effectué par carte bancaire, sous réserve d'acceptation de ce dernier, ou à
													compter de la réception du chèque bancaire ou postal.</p>
												<p>Nous nous engageons à mettre en oeuvre l'ensemble des moyens nécessaires au
													respect des dates de livraison indiquées. Aussi, dans l'hypothèse où Nous serions
													informés d'un retard de livraison, Nous nous engageons à Vous en avertir dans les plus
													brefs délais. Vous déciderez alors du maintien ou de l'annulation totale ou partielle
													de votre commande.</p>
												<p>En cas de dépassement du délai de livraison excédant sept (7) jours, Vous
													disposez de la possibilité d'annuler votre commande, par téléphone au 03.59.57.46.04
													(numéro non surtaxé). Votre commande Vous sera remboursée dans un délai maximum de
													trente (30) jours suivant la réception du courrier électronique. Si la livraison est
													intervenue avant que votre demande d'annulation ait été prise en compte par nos
													services, nous mettrons à votre disposition un bordereau de retour et Vous indiquerons
													l'adresse de retour des Produits livrés.</p>
											</div>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Livraison par colis postal ou par point relais</h3>
												<p>Pour les Produits dont le poids n'excède pas trente kilogrammes(30 kg) et/ou dont
													les dimensions n'excédent pas un mètre cinquante (1m50), la livraison pourra être
													effectuée par le biais de colis postal, livré directement à votre domicile ou par le
													biais de Mondial Relay, directement le point relais de votre choix.</p>
												Pour le retrait en point Relais, une pièce d’identité en vigueur sera exigée ainsi que,
												le cas échant, la carte ayant servi au paiement. A défaut, les produits commandés ne
												pourront Vous être remis.
												<p>Concernant la livraison à domicile : en cas d'absence le jour de la livraison, un
													avis de passage sera déposé dans votre boîte aux lettres, Vous invitant à venir retirer
													le Produit auprès de votre bureau de Poste.</p>
												<p>Concernant la livraison en Point relais : La commande sera conservée dans le
													point relais pendant 15 jours. Passé ce délai, la commande nous sera retournée.</p>
											</div>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Livraison par transporteur</h3>
												<p>Pour les Produits dont le poids excède trente (30) kilogrammes et/ou dont les
													dimensions excédent un mètre cinquante (1m50),la livraison sera effectuée par le biais
													d'un transporteur que nous aurons désigné.</p>
												<p>
													La livraison s'entend du dépôt du Produit devant votre domicile ou au pied de votre
													immeuble.<b> Aucune opération de manutention (notamment livraison à l'étage, ...)
														et de pose ne sera effectuée par le transporteur. Vous serez donc en charge de la
														récupération, l'entreposage et la pose des Produits à votre domicile.</b>
												</p>
												<p>Le transporteur Vous contactera directement afin de convenir d'une date de
													livraison. Il est à préciser que cette livraison se fera du lundi au vendredi entre 8
													heures et 18 heures et qu'aucune heure précise ne pourra être communiquée, le
													rendez-vous étant fixé à la journée.</p>
												<p>Seul le transporteur est habilité à décider du lieu le plus propice de
													déchargement. Il est précisé que la livraison sera effectuée en limite de propriété.
													Aucun déchargement en hauteur ne pourra être exécuté (sur une dalle par exemple).</p>
												<p>
													Dans l'hypothèse où la configuration réelle du lieu de livraison empêcherait
													physiquement le déchargement de la marchandise, le transporteur se réserve le droit
													d'annuler la livraison. Les frais de livraison resteront dans ce cas à votre charge. Il
													Vous appartiendra de Vous rapprocher de Nous par téléphone au <b> 03.59.57.46.04
														(numéro non surtaxé)</b> afin de fixer les nouvelles modalités et coûts d'acheminement de
													votre commande.
												</p>
												<p>
													En cas d'absence au moment de la livraison, un avis de passage sera déposé dans votre
													boîte aux lettres. Vous serez alors invité à Vous rapprocher directement du
													transporteur ou de Nous-mêmes, par téléphone au numéro <b> 03.59.57.46.04 (numéro
														non surtaxé). </b> Les frais de cette seconde livraison seront à notre charge.
												</p>
												<br>
												<p>Néanmoins, sur certains typologies de produits, tels que listés ci-après, vous
													disposez de la possibilité de bénéficier d’opérations accessoires à la simple livraison
													du produit, à savoir une livraison à deux livreurs ou la livraison avec engin de
													manutention mécanique.</p>
												<br>

												<p>
													<br>
													Les produits des familles suivantes peuvent être concernés par une livraison à 2
													livreurs :
												</p>
												<ul class="listLink02">
													<li>Coffre-fort et coffret à monnaie</li>
													<li>Plan de travail de cuisine</li>
													<li>Porte de placard standard</li>
													<li>Crédence de cuisine (inox, verre, ...)</li>
													<li>Store banne, store balcon et fenêtre</li>
													<li>Tonnelle, pergola et toiture de terrasse</li>
												</ul>
												<p></p>
												<br>
												<p>Les produits des familles suivantes peuvent être concernés par une livraison avec
													un engin de manutention mécanique :</p>
												<ul class="listLink02">
													<li>Tondeuse et autoportée</li>
													<li>Abri de jardin</li>
												</ul>
												<p></p>
											</div>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Livraison Gros Volumes (LDD)</h3>
												<p>Nous proposons sur le Site des produits qualifiés de « gros volume » (tels que
													des Produits du type abris de jardin, carports, portes de garage, portail, etc.).</p>
												<p>La livraison des Produits « gros volume » est réalisée par un transporteur que
													nous aurons désigné qui effectuera le dépôt du Produit devant votre domicile ou au pied
													de votre immeuble.</p>

												<br>
												<br>
												<p>Au moment de la passation de sa commande de livraison, vous indiquerez à MiSys
													les différents passages à risques que seraient susceptibles de rencontrer le chauffeur
													au moment du déchargement (existence de trottoirs, puisards, pelouses, terrains non
													stabilisés, ….). Il est de votre responsabilité de prévoir un accès facile pour un
													camion de 19 à 38 tonnes pour le déchargement des produits commandés. Une zone dégagée
													de déchargement du produit est impérative au plus près de son lieu de dépose. En
													fonction des éléments que vous nous aurez transmis et de la configuration du lieu de
													livraison, et tout particulièrement selon l’accessibilité des lieux, la livraison
													pourra être réalisée soit en limite soit au sein même de votre propriété. Si le
													transporteur constate l’impossibilité de livraison, celui-ci conviendra avec vous d’un
													nouveau point de livraison au plus proche accessible de l’adresse de livraison indiquée
													sur le bon de commande. Tout frais supplémentaire pourra dès lors vous être facturé. A
													chaque fois qu’il estime que le dépôt du produit peut se faire dans votre propriété (à
													l’abri du vol), il s’exécutera. Seuls certains produits des univers rangement, cuisine,
													parquet et fenêtres pourront être livrés et déposés à l’étage. Pour tous les autres
													produits, la livraison s’arrêtera dès qu’ils seront à l’abri du vol. En tout état de
													cause, afin de garantir votre sécurité et celle de votre commande, seul le chauffeur
													est habilité à décider du lieu le plus propice de déchargement et des opérations de
													manutention à exécuter.</p>
												<br>
												<br>


												<p>
													En votre absence au moment de la livraison, un avis de passage sera déposé dans votre
													boîte aux lettres. Vous serez alors invité à Vous rapprocher directement du
													transporteur ou de Nous-mêmes, par téléphone au numéro <b> 03.59.57.46.04 (numéro
														non surtaxé). </b> Les frais de cette seconde livraison seront à notre charge.
													<br>
													Il Vous appartiendra de Vous rapprocher de Nous par téléphone au <b> 03.59.57.46.04
														(numéro non surtaxé)</b> afin de fixer les nouvelles modalités et coûts d'acheminement de
													votre commande.
												</p>
											</div>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Livraison des produits végétaux</h3>
												<p>Les végétaux commandés par le biais du Site ne seront expédiées que sous réserve
													de température favorable (absence de fortes chaleurs ou de gel, ...). Dans un souci de
													préservation des végétaux, Nous nous réservons la possibilité d'effectuer un envoi
													séparé, et ce sans que vous ne supportiez aucun frais.</p>
												<p>
													Les variétés de végétaux indisponibles pourront être remplacées par d'autres variétés
													de mêmes coloris et de prix équivalents.
													<br>
													Lors de la livraison des végétaux, certains d'entre eux peuvent présenter un aspect
													défectueux. Malgré cette apparence, les végétaux sont en bon état et il conviendra de
													supprimer les parties abimées avant leur plantation.
												</p>
												<p>La reproduction des variétés protégées ou des appellations commerciales marquées
													du signe ® est interdite sauf autorisation préalable. L'authenticité de la variété est
													garantie par l'étiquette d'origine fixée aux végétaux. Toute illustration
													photographique ou autre, destinée à la publicité de ces variétés est interdite sauf
													autorisation préalable. Ces conditions figurent sur toute offre de vente de végétaux
													des variétés précitées.</p>
											</div>
										</div>
									</div>
									<div class="articles">
										<div class="article">

											<h2>7. Conformité de votre commande</h2>
											<div class="paragraphe">
												<p>
													Lors de la réception de votre commande et avant le départ du transporteur, Vous devez
													impérativement procéder à l'ouverture de cette dernière et à sa vérification, tant en
													ce qui concerne la conformité et la quantité des produits. Vous signerez à cet effet un
													bon de livraison ou de transport. Vous pourrez émettre les réserves nécessaires,
													circonstanciées, lisibles, les plus précises possible en cas d'anomalies constatées
													(casses ; avaries ; non-conformité : erreur de Produit, couleur et/ ou dimensions
													différentes ; manquants, etc.). Dans ce cas, Vous devrez refuser la livraison du colis.
													Vous nous confirmerez vos réserves soit par l'intermédiaire du formulaire situé dans la
													rubrique « Contactez-nous », soit par téléphone au numéro suivant : <b>
														03.59.57.46.04 (numéro non surtaxé) </b>, en précisant bien le numéro de la commande
													concerné dans un delai de 3 jours, non compris les jours feries, suivant la livraison.
												</p>
												<p>En cas de non-conformité avérée ou si MiSys accepte à titre commercial de
													reprendre votre produit, la nouvelle expédition des produits concernés se fera dans la
													limite de la disponibilité des stocks. A défaut, nous procéderons à l'annulation du
													produit non disponible et à son remboursement.</p>
												<br>
												<p>Attention : A défaut de réserves ou cas de réserves insuffisantes, nous nous
													réservons le droit de refuser la reprise, l'échange ou le remboursement de vos
													produits. Dés lors que vous aurez signé le bordereau de livraison, sans réserve
													précise, les responsabilités du transporteur et de www.misys.fr seront dégagées au
													titre des dommages éventuellement occasionnés au cours des opérations de livraison. Il
													est rappelé que la mention « sous réserve de déballage » est dépourvue de toute portée
													légale et ne saurait dégager le client de son devoir de contrôle de l'état de la
													marchandise livrée. Si le livreur ne vous laisse pas le temps de contrôler l'état de la
													marchandise (qualité et quantité), pour quelque raison que ce soit, il faut
													impérativement le préciser sur le bordereau de transport et faire contresigner le
													chauffeur. A défaut de réserves précises sur le bordereau de livraison, la marchandise
													sera considérée comme livrée conforme.</p>
												<br>
												<p>En cas de commande d'un échantillon Parquet, nous nous engageons à vous livrer un
													produit en conformité à votre commande. Toutefois, nous vous informons au préalable
													qu'il est possible, en raison du caractère naturel et vivant du bois, que l'aspect
													final du produit soit nuancé indépendamment du fait de MiSys. Les commandes
													d'échantillons sont limitées à 3 échantillons maximum. Dans le cas d'une commande
													supérieure à 3 échantillons d'une même référence, nous serons dans l'obligation
													d'annuler cette dernière.</p>
												<br>
												<p>Avant la première utilisation ou mise en marche du Produit, Nous Vous
													recommandons expressément de procéder à une lecture attentive des notices et documents
													joints aux Produits. Le cas échéant, Nous Vous rappelons l'importance du port des
													équipements de protection individuel lors de l'utilisation des Produits. Nous ne serons
													pas tenus pour responsable des dommages découlant d'une utilisation des produits non
													conforme aux prescriptions prévues dans les notices et documents joints aux produits.</p>

												<p>Nous mettons à votre disposition le service ASSISTANCE TELEPHONIQUE de MiSys (
													par N° Azur 0810 634 634 pour le prix d'un appel local, 7 jours/7 de 8h à 19h, ou par
													e-mail à l'assistance technique, rubrique « contactez-nous ». Ce service est en mesure
													de vous apporter tous les renseignements utiles à une mise en service et une
													utilisation sans danger des produits achetés.</p>
											</div>
										</div>
									</div>
									<div class="articles">
										<div class="article">

											<h2>8. Le retrait des produits en magasin</h2>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Champ d'application</h3>
												<p>Les présentes conditions particulières de vente régissent la faculté de commander
													en ligne sur www.misys.fr des produits à retirer dans un magasin MiSys offrant ce
													service et disposant de ces produits, en stock. (Hors amenagement exterieur et
													materiaux)</p>

											</div>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Commandes</h3>
												<p>Pour les commandes ‘Retrait en magasin', aucun frais de traitement et de port ne
													Vous sera facturé.</p>
												<p>Pour passer commande, Vous devez sélectionnez « Retrait en magasin » lors de la
													consultation des produits souhaités. Une fois la commande validée, Vous recevrez un
													message de confirmation vous indiquant une date prévisionnelle de mise à disposition de
													votre commande dans le magasin sélectionné.</p>
											</div>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Disponibilité</h3>
												Les quantités disponibles indiquées lors de votre commande sont basées sur le relevé du
												stock magasin. Ces quantités sont mises à jour quotidiennement. Des écarts peuvent
												exceptionnellement exister. Ainsi, en cas d'indisponibilité totale ou partielle de Votre
												commande, vous serez contacté par le magasin. En cas d’indisponibilité totale, Misys se
												réserve le droit d’annuler la commande.
											</div>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Retrait</h3>
												Les produits commandés sur le site peuvent être retirés uniquement dans le magasin
												sélectionné lors de la commande. Au cours du retrait, le numéro de commande ainsi qu'une
												pièce d'identité pourra être demandé. Lors du passage de votre commande, vous
												sélectionnerez le jour et l’heure de votre retrait. Votre commande sera conservée
												pendant 48h après le rendez-vous. Passé ce délai, elle sera annulée. Dans ce cas, votre
												commande vous sera intégralement remboursée.
											</div>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Paiement</h3>
												Vous pourrez payer votre commande 100% en ligne ou en magasin lors du retrait aux
												conditions évoquées à l’article Paiement.
											</div>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Exercice du droit de rétractation / Reprise marchandise</h3>
												Pour les produits retirés en magasin, les retours (effectués soit dans le cadre de votre
												droit de rétractation soit dans la cadre de notre politique de reprise) pourront être
												réalisés dans n'importe lequel de nos magasins.
											</div>
										</div>
									</div>
									<div class="articles">
										<div class="article">

											<h2>9. Droit de vous rétracter</h2>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Modalités d'exercice du droit de rétractation.</h3>
												<p>Conformément à la réglementation en vigueur, pour tout achat de Produit effectué
													sur le Site, Vous disposez d'un délai de rétractation de quatorze (14) jours.
													Néanmoins, MiSys vous permet d'exercer ce droit de rétractation pendant un délai
													supplémentaire à savoir six (6) mois ou un (1) an si vous avez une carte maison en
													cours de validité, à compter de la réception de la marchandise Vous permettant
													d'annuler totalement ou partiellement vos commandes.</p>
												<p>
													Afin de faciliter la gestion des retours-produits et de Nous assurer du transport des
													Produits dans les meilleures conditions, les Produits devront être retournés dans leur
													emballage d'origine, accompagnés de l'ensemble des accessoires et notices, être en bon
													état, et accompagnés de leur facture. Les produits "sur mesure" ne peuvent faire
													l'objet de l'exercice du droit à la rétractation.
													<br>
													Si Vous souhaitez exercer votre droit de rétractation, Vous êtes invité à Nous
													contacter, soit par mail à l'adresse suivante
													<a class="arialnoir" href="mailto:contact.site.internet@misys.fr">
														<b> contact.site.internet@misys.fr</b>
													</a>
													ou soit par téléphone au numéro suivant : <b> 03.59.57.46.04 (numéro non surtaxé) </b>
													afin de Nous avertir de votre volonté de retourner votre commande totalement ou
													partiellement. Nous mettrons à votre disposition un bordereau de retour et Vous
													indiquerons l'adresse de retour des Produits.
												</p>
											</div>
											<div class="paragraphe">
												<p>Conformément aux dispositions légales, Nous nous engageons à Vous rembourser
													l'ensemble des sommes versées (frais de port inclus), et ce dans le délai maximal de
													quatorze (14) jours à compter de la récupération des produits.</p>
												<p>En cas de règlement initial par carte bancaire, Vous serez re-crédité de la somme
													directement sur votre carte bancaire.</p>
												<p>En cas de règlement initial par chèque postal ou bancaire, Nous nous
													rapprocherons de Vous afin d'obtenir communication d'un relevé d'identité bancaire. Le
													virement sera alors effectué directement sur votre compte bancaire.</p>
											</div>
										</div>
									</div>
									<div class="articles">
										<div class="article">

											<h2>10. Remboursement de deux fois la différence</h2>
											<div class="paragraphe">
												<p>Si dans les 48 heures suivant la passation de commande sur le site misys.fr, vous
													trouvez sur un site internet français, un produit neuf strictement identique (même
													marque, modèle, référence) et avec des conditions de service équivalentes (livraison,
													garantie, et.) à un prix moins élevé, nous nous engageons à vous rembourser deux fois
													la différence existant entre le produit et le produit concurrent. Les frais de
													transports ne sont pas pris en compte dans le cadre du remboursement.</p>
												<p>Le prix s'entend en euros, toutes taxes comprises (TTC), hors offres
													promotionnelles, prix exclusifs web et offres de déstockage. De plus, la date de
													disponibilité du produit du site concurrent ne doit pas être supérieure à 10 jours de
													celle que nous proposons.</p>
												<p>Cette possibilité ne concerne pas les produits d'occasion, ou les produits
													commercialisés par le biais de site d'enchères, ou d'annonces de particuliers, ni
													d'offres extraites d'un comparateur de prix.</p>
												<p>
													Vous devrez alors nous adresser par mail à
													<a class="arialnoir" href="mailto:contact.site.internet@misys.fr">
														<b> contact.site.internet@misys.fr</b>
													</a>
													, dans les 48 heures suivant l'achat, votre demande de remboursement ainsi que l'offre
													concurrente.
												</p>
												<p>Après vérifications du respect des conditions exposées ci-dessus, Nous
													procéderons au versement de deux fois la différence existant entre le Produit et les
													produits concurrents dans un délai de trente (30) jours à compter de votre demande,
													soit par biais de l'envoi d'un chèque bancaire ou postal, soit en créditant votre carte
													bancaire.</p>
											</div>
										</div>
									</div>
									<div class="articles">
										<div class="article">

											<h2>11.Garanties - SAV</h2>
											<div class="paragraphe">
												<div style="border: 1px solid #000; border-bottom-style: none">Les garanties
													légales</div>
												<div style="border: 1px solid; border-top-style: none">
													<br>
													<b>Garantie de conformité</b>
													<br>
													Nous sommes tenus des défauts de conformité du Produit au Contrat, dans les conditions
													des articles L.211-4 et suivants du Code de la consommation. Sous réserve de la
													présentation d’une preuve d’achat, Vous disposez d’un délai de deux (2) ans à compter
													de la délivrance du Produit pour faire valoir la garantie légale de conformité. Durant
													les six (6) premiers mois suivant la délivrance du Produit, Vous êtes dispensé de
													rapporter la preuve de l’existence du défaut de conformité, celle-ci pesant sur Nous.
													Ce délai est porté à vingt-quatre (24) mois à compter du 18 mars 2016. En cas de défaut
													de conformité, Vous choisissez entre la réparation et le remplacement du Produit.
													Toutefois, Nous pouvons ne pas procéder selon Votre choix si ce cela entraîne un coût
													manifestement disproportionné au regard de l'autre modalité proposée, compte tenu de la
													valeur du bien ou de l'importance du défaut. Si toutefois aucune des solutions
													envisagées ne peuvent être mises en œuvre dans le mois suivant la réclamation, Vous
													avez la possibilité de demander une diminution du prix ou la résolution du contrat. La
													résolution du contrat peut toutefois ne pas être acceptée si le défaut de conformité
													est mineur.
													<br>
													<br>
													<b>Garantie des vices cachés</b>
													<br>
													Nous sommes tenus des vices cachés du Produit dans les conditions prévues aux articles
													1641 et suivants du Code civil. Sous réserve de la présentation d’une preuve d’achat,
													Vous pouvez faire valoir la garantie des vices cachés dans un délai de deux (2) ans à
													compter de la découverte du vice. Pour bénéficier de la garantie des vices cachés, Vous
													devez apporter la preuve que le vice était non apparent, existait lors de l’achat et
													rend le Produit impropre à l’usage auquel vous le destiniez, ou diminue très fortement
													cet usage. Si Vous apportez une telle preuve, Vous pourrez choisir entre la résolution
													de la vente ou une réduction du prix de vente, conformément à l’article 1644 du Code
													civil.
													<br>
													<br>
													<b>La garantie légale de conformité et la garantie des vices cachés s’appliquent
														indépendamment de la garantie commerciale éventuellement consentie.</b>
												</div>
											</div>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Les garanties commerciales éventuellement consenties</h3>
												<br>
												Les garanties commerciales relatives aux Produits, et leurs conditions d'application,
												sont fixées par le fournisseur du Produit. Elles sont facultatives et ne se substituent
												pas aux garanties légales. Les garanties commerciales sont donc susceptibles d'être
												différentes (durée, étendue, etc.) en fonction des Produits, des fournisseurs et des
												marques. Vous trouverez le détail de ces garanties sur la fiche de présentation du
												Produit, jointe au Produit, et précisé sur la notice d'utilisation ou tout autre
												document accompagnant le Produit. Le délai de garantie commence à courir à compter de la
												délivrance des Produits. De manière générale, Nous vous indiquons que ne sont pas
												couverts par la garantie commerciale offerte par les fournisseurs :
												<ul>
													<li>Les dysfonctionnements liés à une mauvaise utilisation et/ou à un défaut
														d'entretien du produit.</li>
													<li>Les dysfonctionnements liés à l'usure normale des Produits et le remplacement
														des accessoires, pièces d'usure et consommables.</li>
													<li>Les dégradations liées aux divers chocs occasionnés aux produits.</li>
													<li>Les opérations d'entretien courant (vidanges, réglages, affûtage, etc.)
														n'entrent pas dans le cadre de la garantie commerciale offerte par les fournisseurs.</li>
												</ul>
											</div>
											<div class="paragraphe">
												<h3 class="titreParagraphe">Le service après vente</h3>
												<br>
												Un problème dans l'utilisation ou l'installation de votre produit ? Notre Assistance
												téléphonique est ouverte 7jours/7 de 8h à 19h pour vous apporter tous les conseils
												nécessaires à la mise en œuvre de vos produits au 0810 634 634.
												<br>
												Votre produit est défectueux, abimé ? Nous assurons un service après-vente de nos
												produits. Vous êtes invité à Nous contacter, par téléphone, au numéro suivant :
												03.59.57.46.04 (numéro non surtaxé). Nous Vous dresserons alors un premier diagnostic
												(application ou non de la garantie contractuelle, panne, etc.).
												<br>
												Par la suite, Le service après-vente peut être effectué directement dans l'ensemble des
												magasins Misys de votre choix. Il peut se faire également par le biais d'un retour du
												Produit au sein de nos services. Cette seconde option nécessitera un délai plus
												conséquent pour l'acheminement et le réacheminement du produit.
												<br>
												Dans le cadre d'un retour d'un Produit, et au terme du diagnostic que Nous aurons
												réalisé, si le problème lié au Produit est couvert par la garantie contractuelle du
												fournisseur, un bon de retour Vous sera adressé, Vous permettant de Nous adresser le
												Produit, sans frais. Les réparations seront alors effectuées et le Produit Vous sera
												retourné, sans que Vous ne supportiez aucun frais supplémentaire.
												<br>
												Toutefois, si la garantie n'est pas applicable, un devis de réparation Vous sera
												adressé. Si Vous acceptez le devis adressé, Vous devrez Nous faire part de Votre
												acceptation. Le retour du Produit sera dans ce cas à vos seuls frais.
												<br>
											</div>
										</div>
									</div>
									<div class="articles">
										<div class="article">

											<h2>12. Responsabilités</h2>
											<div class="paragraphe">
												<p>Notre responsabilité, ne peut pas être engagée lorsque Vous ne respectez pas, en
													toute ou partie, les Conditions Générales de Vente, ou encore en cas de fait
													imprévisible d'un tiers ou en cas de force majeure.</p>
												Nous pouvons en ce cas nous exonérer de toute ou partie de notre responsabilité en
												apportant la preuve de l'inexécution ou la mauvaise exécution des présentes Conditions
												Générales de Vente, soit lorsque celle-ci Vous est imputables soit, lorsqu'elle est le
												fait imprévisible et insurmontable d'un tiers au contrat ou alors consécutive à un cas
												de force majeure reconnu comme tel par la jurisprudence.
											</div>
										</div>
									</div>
									<div class="articles">
										<div class="article">

											<h2>13. Réserve de propriété</h2>
											<div class="paragraphe">
												Les marchandises livrées restent notre propriété jusqu'au complet paiement de leur prix.
												Le défaut de paiement pourra entraîner la revendication des marchandises.
												<br>
												Le transfert des risques de perte ou de détérioration est néanmoins opéré entre vos les
												mains à compter de la livraison des produits.
												<br>
											</div>
										</div>
									</div>
									<div class="articles">
										<div class="article">

											<h2>14. Données personnelles</h2>
											<div class="paragraphe">
												La passation de votre commande via le Site implique que Nous collections vos données
												personnelles. Nous Vous invitons donc à consulter la rubrique
												<a href="/v3/p/donnees-personnelles-l1308220759" target="blank" class="arialnoir11bold">
													"Politique de données personnelles" </a>
												que Nous mettons en ligne sur notre Site.
											</div>
										</div>
									</div>
									<div class="articles">
										<div class="article">

											<h2>15. Loi applicable et tribunal compétent</h2>
											<div class="paragraphe">
												Les Conditions Générales de Vente sont soumises pour l'ensemble de leur stipulation à la
												loi française. Tous litiges relatifs à la relation commerciale existant entre Vous et
												Nous sont soumis à la compétence exclusive des juridictions françaises.
												<br>
												En cas de litige, une solution amiable sera au préalable recherchée. La recherche d'une
												solution amiable n'interrompt pas la garantie contractuelle mais interrompt les délais
												d'action dont Vous bénéficiez.
											</div>
										</div>
									</div>
								</div>
								<!-- Fin contenu centrale -->
							</div>

							<div id="context" class="seeAlso">
								<h3>Voir aussi :</h3>
								<ul class="liensContext">
									<li>
										<a href="/v3/p/donnees-personnelles-l1308220759">Politique de données personnelles</a>
									</li>
								</ul>
							</div>
						</div>
						<!-- Fin contenu avec bloc de context -->
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="resources/js/authentification/signUp.js"></script>
