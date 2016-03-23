<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form class="formAdresse" action="mon-panier-etape-validation-adress" name="singnIn" method="post">
	<div class="row">
		<div class="col-sm-8">
			<c:set var="listeAdress" value="${adresss}" />
			<div class="form-group">
				<select class="form-control selectAdress" name="idAdress">
					<option value="">Cr�er une nouvelle adresse</option>
					<c:forEach var="adress" items="${listeAdress}" varStatus="counter">
						<option value="${adress.idAdress}" data-name="<c:out value="${adress.name}"/>"
							data-nameLastName="<c:out value="${adress.nameLastName}"/>"
							data-adressPrincipal="<c:out value="${adress.adressPrincipal}"/>"
							data-adressSecondaire="<c:out value="${adress.adressSecondaire}"/>"
							data-region="<c:out value="${adress.region}"/>"
							data-codePostal="<c:out value="${adress.codePostal}"/>"
							data-pays="<c:out value="${adress.pays}"/>"
							data-numberPhone="<c:out value="${adress.numberPhone}"/>"
							<c:if test="${sessionScope.address != null && sessionScope.address.idAdress == adress.idAdress}">
									selected
								</c:if>>${adress.name}</option>
					</c:forEach>

				</select>
			</div>
		</div>
		<div class="hide blockAdressButton col-sm-1 blockUpdateAdress">
			<div class=" form-group">
				<button type="button" class="btn btn-primary input-100" data-toggle="modal"
					data-target="#popUpUpdateAdress">Modifier</button>
			</div>
		</div>
		<div class="hide blockAdressButton col-sm-2 blockDeleteAdress">
			<div class=" form-group">
				<button type="button" class="btn btn-danger input-100" data-toggle="modal"
					data-target="#popUpDeleteAdress">Supprimer</button>
			</div>
		</div>
	</div>


	<input type="hidden" class="redirectSignIn" value="${redirect}" />


	<div class="form-group">
		<label>Nom de votre adresse * </label>
		<div class="form-label">
			<input type="text" class="name form-control required checkLength" data-length="255" name="name"
				placeholder="Nom de votre adresse, ex : Maison, Parent, Copine ...">
		</div>
		<p class="help-block"></p>
	</div>

	<div class="form-group">
		<label>Nom et Pr�nom * </label>
		<div class="form-label">
			<input type="text" class="nameLastName form-control required checkLength" data-length="255"
				name="nameLastName" placeholder="Nom et Pr�nom">
		</div>
		<p class="help-block"></p>
	</div>

	<div class="form-group">
		<label>Adresse principale * </label>
		<div class="form-label">
			<input type="text" class="adressPrincipal form-control required checkLength" data-length="1024"
				name="adressPrincipal" placeholder="Adresse principale">
		</div>
		<p class="help-block"></p>
	</div>

	<div class="form-group">
		<label>Adresse secondaire</label>
		<div class="form-label">
			<input type="text" class="adressSecondaire form-control checkLength" data-length="1024"
				name="adressSecondaire" placeholder="Adresse secondaire">
		</div>
		<p class="help-block"></p>
	</div>

	<div class="form-group">
		<label>R�gion</label>
		<div class="form-label">
			<input type="text" class="region form-control checkLength" data-length="255" name="region"
				placeholder="R�gion">
		</div>
		<p class="help-block"></p>
	</div>

	<div class="form-group">
		<label>Code postal * </label>
		<div class="form-label">
			<input type="text" class="codePostal form-control required checkLengthMandatory checkInt"
				data-length="5" name="codePostal" placeholder="Code postal">
		</div>
		<p class="help-block"></p>
	</div>

	<div class="form-group">
		<label>Pays * </label>
		<select name="pays" class="pays form-control required">
			<option value="">--</option>
			<option value="ZA">Afrique du Sud</option>
			<option value="AL">Albanie</option>
			<option value="DZ">Alg�rie</option>
			<option value="DE">Allemagne</option>
			<option value="AD">Andorre</option>
			<option value="AO">Angola</option>
			<option value="AI">Anguilla</option>
			<option value="AQ">Antarctique</option>
			<option value="AG">Antigua-et-Barbuda</option>
			<option value="AN">Antilles n�erlandaises</option>
			<option value="SA">Arabie Saoudite</option>
			<option value="AR">Argentine</option>
			<option value="AM">Arm�nie</option>
			<option value="AW">Aruba</option>
			<option value="AU">Australie</option>
			<option value="AT">Autriche</option>
			<option value="AZ">Azerba�djan</option>
			<option value="BS">Bahamas</option>
			<option value="BH">Bahre�n</option>
			<option value="BD">Bangladesh</option>
			<option value="BB">Barbade</option>
			<option value="BE">Belgique</option>
			<option value="BZ">B�lize</option>
			<option value="BJ">B�nin</option>
			<option value="BM">Bermudes</option>
			<option value="BT">Bhoutan</option>
			<option value="BY">Bi�lorussie</option>
			<option value="BO">Bolivie</option>
			<option value="BQ">Bonaire, Saint Eustatius et Saba</option>
			<option value="BA">Bosnie-Herz�govine</option>
			<option value="BW">Botswana</option>
			<option value="BR">Br�sil</option>
			<option value="BN">Brun�i Darussalam</option>
			<option value="BG">Bulgarie</option>
			<option value="BF">Burkina Faso</option>
			<option value="BI">Burundi</option>
			<option value="KH">Cambodge</option>
			<option value="CM">Cameroun</option>
			<option value="CA">Canada</option>
			<option value="CV">Cap Vert</option>
			<option value="CL">Chili</option>
			<option value="CN">Chine</option>
			<option value="CY">Chypre</option>
			<option value="CO">Colombie</option>
			<option value="KM">Comores</option>
			<option value="CG">Congo</option>
			<option value="CD">Congo, R�publique d�mocratique du</option>
			<option value="KR">Cor�e, R�publique de</option>
			<option value="CR">Costa Rica</option>
			<option value="CI">C�te d'ivoire</option>
			<option value="HR">Croatie</option>
			<option value="CW">Cura�ao</option>
			<option value="DK">Danemark</option>
			<option value="DJ">Djibouti</option>
			<option value="DM">Dominique</option>
			<option value="EG">�gypte</option>
			<option value="SV">El Salvador</option>
			<option value="AE">�mirats arabes unis</option>
			<option value="EC">�quateur</option>
			<option value="ER">�rythr�e</option>
			<option value="ES">Espagne</option>
			<option value="EE">Estonie</option>
			<option value="US">�tats-Unis</option>
			<option value="ET">Ethiopie</option>
			<option value="RU">F�d�ration de Russie</option>
			<option value="FJ">Fidji</option>
			<option value="FI">Finlande</option>
			<option value="FR" selected>France</option>
			<option value="GA">Gabon</option>
			<option value="GM">Gambie</option>
			<option value="GE">G�orgie</option>
			<option value="GS">G�orgie du Sud et les �les Sandwich du Sud</option>
			<option value="GH">Ghana</option>
			<option value="GI">Gibraltar</option>
			<option value="GR">Gr�ce</option>
			<option value="GD">Grenade</option>
			<option value="GL">Groenland</option>
			<option value="GP">Guadeloupe</option>
			<option value="GU">Guam</option>
			<option value="GT">Guatemala</option>
			<option value="GN">Guin�e</option>
			<option value="GQ">Guin�e �quatoriale</option>
			<option value="GW">Guin�e-Bissau</option>
			<option value="GY">Guyane</option>
			<option value="GF">Guyane fran�aise</option>
			<option value="HT">Ha�ti</option>
			<option value="HN">Honduras</option>
			<option value="HK">Hong Kong</option>
			<option value="HU">Hongrie</option>
			<option value="BV">�le Bouvet</option>
			<option value="CX">�le Christmas</option>
			<option value="HM">�le Heard et �les McDonald</option>
			<option value="NF">�le Norfolk</option>
			<option value="KY">�les Ca�mans</option>
			<option value="CC">�les Cocos (Keeling)</option>
			<option value="CK">�les Cook</option>
			<option value="FK">�les Falkland (Malouines)</option>
			<option value="FO">�les F�ro�</option>
			<option value="MP">�les Mariannes du Nord</option>
			<option value="MH">�les Marshall</option>
			<option value="UM">�les mineures �loign�es des �tats-Unis</option>
			<option value="SB">�les Salomon</option>
			<option value="TC">�les Turques-et-Ca�ques</option>
			<option value="VI">�les Vierges am�ricaines</option>
			<option value="VG">�les Vierges britanniques</option>
			<option value="IN">Inde</option>
			<option value="ID">Indon�sie</option>
			<option value="IE">Irlande</option>
			<option value="IS">Islande</option>
			<option value="IL">Isra�l</option>
			<option value="IT">Italie</option>
			<option value="JM">Jama�que</option>
			<option value="JP">Japon</option>
			<option value="JO">Jordanie</option>
			<option value="KZ">Kazakhstan</option>
			<option value="KE">Kenya</option>
			<option value="KG">Kirghizistan</option>
			<option value="KI">Kiribati</option>
			<option value="XK">Kosovo</option>
			<option value="KW">Kowe�t</option>
			<option value="LS">Lesotho</option>
			<option value="LV">Lettonie</option>
			<option value="LB">Liban</option>
			<option value="LR">Liberia</option>
			<option value="LY">Libye</option>
			<option value="LI">Liechtenstein</option>
			<option value="LT">Lituanie</option>
			<option value="LU">Luxembourg</option>
			<option value="MO">Macao</option>
			<option value="MK">Mac�doine, Ex-R�publique yougoslave de</option>
			<option value="MG">Madagascar</option>
			<option value="MY">Malaisie</option>
			<option value="MW">Malawi</option>
			<option value="MV">Maldives</option>
			<option value="ML">Mali</option>
			<option value="MT">Malte</option>
			<option value="MA">Maroc</option>
			<option value="MQ">Martinique</option>
			<option value="MU">Maurice</option>
			<option value="MR">Mauritanie</option>
			<option value="YT">Mayotte</option>
			<option value="MX">Mexique</option>
			<option value="FM">Micron�sie, �tats f�d�r�s de</option>
			<option value="MC">Monaco</option>
			<option value="MN">Mongolie</option>
			<option value="ME">Montenegro</option>
			<option value="MS">Montserrat</option>
			<option value="MZ">Mozambique</option>
			<option value="MM">Myanmar</option>
			<option value="NA">Namibie</option>
			<option value="NR">Nauru</option>
			<option value="NP">N�pal</option>
			<option value="NI">Nicaragua</option>
			<option value="NE">Niger</option>
			<option value="NG">Nigeria</option>
			<option value="NU">Niu�</option>
			<option value="NO">Norv�ge</option>
			<option value="NC">Nouvelle-Cal�donie</option>
			<option value="NZ">Nouvelle-Z�lande</option>
			<option value="OM">Oman</option>
			<option value="UG">Ouganda</option>
			<option value="UZ">Ouzb�kistan</option>
			<option value="PK">Pakistan</option>
			<option value="PW">Palaos</option>
			<option value="PA">Panama</option>
			<option value="PG">Papouasie-Nouvelle-Guin�e</option>
			<option value="PY">Paraguay</option>
			<option value="NL">Pays-Bas</option>
			<option value="PE">P�rou</option>
			<option value="PH">Philippines</option>
			<option value="PN">Pitcairn</option>
			<option value="PL">Pologne</option>
			<option value="PF">Polyn�sie fran�aise</option>
			<option value="PR">Porto Rico</option>
			<option value="PT">Portugal</option>
			<option value="QA">Qatar</option>
			<option value="CF">R�publique centrafricaine</option>
			<option value="MD">R�publique de Moldavie</option>
			<option value="LA">R�publique d�mocratique populaire lao</option>
			<option value="DO">R�publique dominicaine</option>
			<option value="CZ">R�publique Tch�que</option>
			<option value="RE">R�union</option>
			<option value="RO">Roumanie</option>
			<option value="GB">Royaume-Uni</option>
			<option value="RW">Rwanda</option>
			<option value="EH">Sahara occidental</option>
			<option value="BL">Saint-Barth�lemy</option>
			<option value="KN">Saint-Kitts-et-Nevis</option>
			<option value="SM">Saint-Marin</option>
			<option value="MF">Saint-Martin (France)</option>
			<option value="SX">Saint-Martin (Pays Bas)</option>
			<option value="PM">Saint-Pierre-et-Miquelon</option>
			<option value="VA">Saint-Si�ge</option>
			<option value="VC">Saint-Vincent-et-les Grenadines</option>
			<option value="SH">Sainte-H�l�ne, Ascension et Tristan da Cunha</option>
			<option value="LC">Sainte-Lucie</option>
			<option value="WS">Samoa</option>
			<option value="AS">Samoa am�ricaines</option>
			<option value="ST">Sao-Tom�-et-Principe</option>
			<option value="SN">S�n�gal</option>
			<option value="RS">Serbie</option>
			<option value="SC">Seychelles</option>
			<option value="SL">Sierra Leone</option>
			<option value="SG">Singapour</option>
			<option value="SK">Slovaquie</option>
			<option value="SI">Slov�nie</option>
			<option value="SO">Somalie</option>
			<option value="LK">Sri Lanka</option>
			<option value="SE">Su�de</option>
			<option value="CH">Suisse</option>
			<option value="SR">Surinam</option>
			<option value="SJ">Svalbard et Jan Mayen</option>
			<option value="SZ">Swaziland</option>
			<option value="TJ">Tadjikistan</option>
			<option value="TW">Ta�wan</option>
			<option value="TZ">Tanzanie, R�publique unie de</option>
			<option value="TD">Tchad</option>
			<option value="TF">Terres australes et antarctiques fran�aises</option>
			<option value="IO">Territoire britannique de l'oc�an Indien</option>
			<option value="TH">Tha�lande</option>
			<option value="TL">Timor-leste</option>
			<option value="TG">Togo</option>
			<option value="TK">Tokelau</option>
			<option value="TO">Tonga</option>
			<option value="TT">Trinit�-et-Tobago</option>
			<option value="TN">Tunisie</option>
			<option value="TM">Turkm�nistan</option>
			<option value="TR">Turquie</option>
			<option value="TV">Tuvalu</option>
			<option value="UA">Ukraine</option>
			<option value="UY">Uruguay</option>
			<option value="VU">Vanuatu</option>
			<option value="VE">Venezuela</option>
			<option value="VN">Vietnam</option>
			<option value="WF">Wallis-et-Futuna</option>
			<option value="YE">Y�men</option>
			<option value="ZM">Zambie</option>
			<option value="ZW">Zimbabwe</option>
		</select>
		<p class="help-block"></p>
	</div>

	<div class="form-group">
		<label>Num�ros de t�l�phone * </label>
		<div class="form-label">
			<input type="text" class="numberPhone form-control required checkLengthMandatory checkInt"
				data-length="10" name="numberPhone" placeholder="Num�ros de t�l�phone">
		</div>
		<p class="help-block"></p>
	</div>

	<div class="form-group">
		<input type="submit" class="btn btn-primary input-cart input-100" value="Continuer">
	</div>

	* Champs obligatoire
</form>


<div class="modal fade" id="popUpDeleteAdress" tabindex="-1" role="dialog"
	aria-labelledby="popUpDeletePanier">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Suppression adresse</h4>
			</div>
			<div class="modal-body">Voulez-vous r�ellement supprimer cette adresse ?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger suppressionAdress" data-dismiss="modal">Oui</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal">Non</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="popUpUpdateAdress" tabindex="-1" role="dialog"
	aria-labelledby="popUpUpdateAdress">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Modification adresse</h4>
			</div>
			<form class="formUpdateAdress">

				<div class="modal-body">
					<div class="form-group">
						<label>Nom de votre adresse * </label>
						<div class="form-label">
							<input type="text" class="name update form-control required checkLength" data-length="255"
								name="name" placeholder="Nom de votre adresse, ex : Maison, Parent, Copine ...">
						</div>
						<p class="help-block"></p>
					</div>

					<div class="form-group">
						<label>Nom et Pr�nom * </label>
						<div class="form-label">
							<input type="text" class="nameLastName update form-control required checkLength"
								data-length="255" name="nameLastName" placeholder="Nom et Pr�nom">
						</div>
						<p class="help-block"></p>
					</div>

					<div class="form-group">
						<label>Adresse principale * </label>
						<div class="form-label">
							<input type="text" class="adressPrincipal update form-control required checkLength"
								data-length="1024" name="adressPrincipal" placeholder="Adresse principale">
						</div>
						<p class="help-block"></p>
					</div>

					<div class="form-group">
						<label>Adresse secondaire</label>
						<div class="form-label">
							<input type="text" class="adressSecondaire update form-control checkLength"
								data-length="1024" name="adressSecondaire" placeholder="Adresse secondaire">
						</div>
						<p class="help-block"></p>
					</div>

					<div class="form-group">
						<label>R�gion</label>
						<div class="form-label">
							<input type="text" class="region update form-control checkLength" data-length="255"
								name="region" placeholder="R�gion">
						</div>
						<p class="help-block"></p>
					</div>

					<div class="form-group">
						<label>Code postal * </label>
						<div class="form-label">
							<input type="text"
								class="codePostal update form-control required checkLengthMandatory checkInt"
								data-length="5" name="codePostal" placeholder="Code postal">
						</div>
						<p class="help-block"></p>
					</div>

					<div class="form-group">
						<label>Pays * </label>
						<select name="pays" class="pays update form-control required">
							<option value="">--</option>
							<option value="ZA">Afrique du Sud</option>
							<option value="AL">Albanie</option>
							<option value="DZ">Alg�rie</option>
							<option value="DE">Allemagne</option>
							<option value="AD">Andorre</option>
							<option value="AO">Angola</option>
							<option value="AI">Anguilla</option>
							<option value="AQ">Antarctique</option>
							<option value="AG">Antigua-et-Barbuda</option>
							<option value="AN">Antilles n�erlandaises</option>
							<option value="SA">Arabie Saoudite</option>
							<option value="AR">Argentine</option>
							<option value="AM">Arm�nie</option>
							<option value="AW">Aruba</option>
							<option value="AU">Australie</option>
							<option value="AT">Autriche</option>
							<option value="AZ">Azerba�djan</option>
							<option value="BS">Bahamas</option>
							<option value="BH">Bahre�n</option>
							<option value="BD">Bangladesh</option>
							<option value="BB">Barbade</option>
							<option value="BE">Belgique</option>
							<option value="BZ">B�lize</option>
							<option value="BJ">B�nin</option>
							<option value="BM">Bermudes</option>
							<option value="BT">Bhoutan</option>
							<option value="BY">Bi�lorussie</option>
							<option value="BO">Bolivie</option>
							<option value="BQ">Bonaire, Saint Eustatius et Saba</option>
							<option value="BA">Bosnie-Herz�govine</option>
							<option value="BW">Botswana</option>
							<option value="BR">Br�sil</option>
							<option value="BN">Brun�i Darussalam</option>
							<option value="BG">Bulgarie</option>
							<option value="BF">Burkina Faso</option>
							<option value="BI">Burundi</option>
							<option value="KH">Cambodge</option>
							<option value="CM">Cameroun</option>
							<option value="CA">Canada</option>
							<option value="CV">Cap Vert</option>
							<option value="CL">Chili</option>
							<option value="CN">Chine</option>
							<option value="CY">Chypre</option>
							<option value="CO">Colombie</option>
							<option value="KM">Comores</option>
							<option value="CG">Congo</option>
							<option value="CD">Congo, R�publique d�mocratique du</option>
							<option value="KR">Cor�e, R�publique de</option>
							<option value="CR">Costa Rica</option>
							<option value="CI">C�te d'ivoire</option>
							<option value="HR">Croatie</option>
							<option value="CW">Cura�ao</option>
							<option value="DK">Danemark</option>
							<option value="DJ">Djibouti</option>
							<option value="DM">Dominique</option>
							<option value="EG">�gypte</option>
							<option value="SV">El Salvador</option>
							<option value="AE">�mirats arabes unis</option>
							<option value="EC">�quateur</option>
							<option value="ER">�rythr�e</option>
							<option value="ES">Espagne</option>
							<option value="EE">Estonie</option>
							<option value="US">�tats-Unis</option>
							<option value="ET">Ethiopie</option>
							<option value="RU">F�d�ration de Russie</option>
							<option value="FJ">Fidji</option>
							<option value="FI">Finlande</option>
							<option value="FR" selected>France</option>
							<option value="GA">Gabon</option>
							<option value="GM">Gambie</option>
							<option value="GE">G�orgie</option>
							<option value="GS">G�orgie du Sud et les �les Sandwich du Sud</option>
							<option value="GH">Ghana</option>
							<option value="GI">Gibraltar</option>
							<option value="GR">Gr�ce</option>
							<option value="GD">Grenade</option>
							<option value="GL">Groenland</option>
							<option value="GP">Guadeloupe</option>
							<option value="GU">Guam</option>
							<option value="GT">Guatemala</option>
							<option value="GN">Guin�e</option>
							<option value="GQ">Guin�e �quatoriale</option>
							<option value="GW">Guin�e-Bissau</option>
							<option value="GY">Guyane</option>
							<option value="GF">Guyane fran�aise</option>
							<option value="HT">Ha�ti</option>
							<option value="HN">Honduras</option>
							<option value="HK">Hong Kong</option>
							<option value="HU">Hongrie</option>
							<option value="BV">�le Bouvet</option>
							<option value="CX">�le Christmas</option>
							<option value="HM">�le Heard et �les McDonald</option>
							<option value="NF">�le Norfolk</option>
							<option value="KY">�les Ca�mans</option>
							<option value="CC">�les Cocos (Keeling)</option>
							<option value="CK">�les Cook</option>
							<option value="FK">�les Falkland (Malouines)</option>
							<option value="FO">�les F�ro�</option>
							<option value="MP">�les Mariannes du Nord</option>
							<option value="MH">�les Marshall</option>
							<option value="UM">�les mineures �loign�es des �tats-Unis</option>
							<option value="SB">�les Salomon</option>
							<option value="TC">�les Turques-et-Ca�ques</option>
							<option value="VI">�les Vierges am�ricaines</option>
							<option value="VG">�les Vierges britanniques</option>
							<option value="IN">Inde</option>
							<option value="ID">Indon�sie</option>
							<option value="IE">Irlande</option>
							<option value="IS">Islande</option>
							<option value="IL">Isra�l</option>
							<option value="IT">Italie</option>
							<option value="JM">Jama�que</option>
							<option value="JP">Japon</option>
							<option value="JO">Jordanie</option>
							<option value="KZ">Kazakhstan</option>
							<option value="KE">Kenya</option>
							<option value="KG">Kirghizistan</option>
							<option value="KI">Kiribati</option>
							<option value="XK">Kosovo</option>
							<option value="KW">Kowe�t</option>
							<option value="LS">Lesotho</option>
							<option value="LV">Lettonie</option>
							<option value="LB">Liban</option>
							<option value="LR">Liberia</option>
							<option value="LY">Libye</option>
							<option value="LI">Liechtenstein</option>
							<option value="LT">Lituanie</option>
							<option value="LU">Luxembourg</option>
							<option value="MO">Macao</option>
							<option value="MK">Mac�doine, Ex-R�publique yougoslave de</option>
							<option value="MG">Madagascar</option>
							<option value="MY">Malaisie</option>
							<option value="MW">Malawi</option>
							<option value="MV">Maldives</option>
							<option value="ML">Mali</option>
							<option value="MT">Malte</option>
							<option value="MA">Maroc</option>
							<option value="MQ">Martinique</option>
							<option value="MU">Maurice</option>
							<option value="MR">Mauritanie</option>
							<option value="YT">Mayotte</option>
							<option value="MX">Mexique</option>
							<option value="FM">Micron�sie, �tats f�d�r�s de</option>
							<option value="MC">Monaco</option>
							<option value="MN">Mongolie</option>
							<option value="ME">Montenegro</option>
							<option value="MS">Montserrat</option>
							<option value="MZ">Mozambique</option>
							<option value="MM">Myanmar</option>
							<option value="NA">Namibie</option>
							<option value="NR">Nauru</option>
							<option value="NP">N�pal</option>
							<option value="NI">Nicaragua</option>
							<option value="NE">Niger</option>
							<option value="NG">Nigeria</option>
							<option value="NU">Niu�</option>
							<option value="NO">Norv�ge</option>
							<option value="NC">Nouvelle-Cal�donie</option>
							<option value="NZ">Nouvelle-Z�lande</option>
							<option value="OM">Oman</option>
							<option value="UG">Ouganda</option>
							<option value="UZ">Ouzb�kistan</option>
							<option value="PK">Pakistan</option>
							<option value="PW">Palaos</option>
							<option value="PA">Panama</option>
							<option value="PG">Papouasie-Nouvelle-Guin�e</option>
							<option value="PY">Paraguay</option>
							<option value="NL">Pays-Bas</option>
							<option value="PE">P�rou</option>
							<option value="PH">Philippines</option>
							<option value="PN">Pitcairn</option>
							<option value="PL">Pologne</option>
							<option value="PF">Polyn�sie fran�aise</option>
							<option value="PR">Porto Rico</option>
							<option value="PT">Portugal</option>
							<option value="QA">Qatar</option>
							<option value="CF">R�publique centrafricaine</option>
							<option value="MD">R�publique de Moldavie</option>
							<option value="LA">R�publique d�mocratique populaire lao</option>
							<option value="DO">R�publique dominicaine</option>
							<option value="CZ">R�publique Tch�que</option>
							<option value="RE">R�union</option>
							<option value="RO">Roumanie</option>
							<option value="GB">Royaume-Uni</option>
							<option value="RW">Rwanda</option>
							<option value="EH">Sahara occidental</option>
							<option value="BL">Saint-Barth�lemy</option>
							<option value="KN">Saint-Kitts-et-Nevis</option>
							<option value="SM">Saint-Marin</option>
							<option value="MF">Saint-Martin (France)</option>
							<option value="SX">Saint-Martin (Pays Bas)</option>
							<option value="PM">Saint-Pierre-et-Miquelon</option>
							<option value="VA">Saint-Si�ge</option>
							<option value="VC">Saint-Vincent-et-les Grenadines</option>
							<option value="SH">Sainte-H�l�ne, Ascension et Tristan da Cunha</option>
							<option value="LC">Sainte-Lucie</option>
							<option value="WS">Samoa</option>
							<option value="AS">Samoa am�ricaines</option>
							<option value="ST">Sao-Tom�-et-Principe</option>
							<option value="SN">S�n�gal</option>
							<option value="RS">Serbie</option>
							<option value="SC">Seychelles</option>
							<option value="SL">Sierra Leone</option>
							<option value="SG">Singapour</option>
							<option value="SK">Slovaquie</option>
							<option value="SI">Slov�nie</option>
							<option value="SO">Somalie</option>
							<option value="LK">Sri Lanka</option>
							<option value="SE">Su�de</option>
							<option value="CH">Suisse</option>
							<option value="SR">Surinam</option>
							<option value="SJ">Svalbard et Jan Mayen</option>
							<option value="SZ">Swaziland</option>
							<option value="TJ">Tadjikistan</option>
							<option value="TW">Ta�wan</option>
							<option value="TZ">Tanzanie, R�publique unie de</option>
							<option value="TD">Tchad</option>
							<option value="TF">Terres australes et antarctiques fran�aises</option>
							<option value="IO">Territoire britannique de l'oc�an Indien</option>
							<option value="TH">Tha�lande</option>
							<option value="TL">Timor-leste</option>
							<option value="TG">Togo</option>
							<option value="TK">Tokelau</option>
							<option value="TO">Tonga</option>
							<option value="TT">Trinit�-et-Tobago</option>
							<option value="TN">Tunisie</option>
							<option value="TM">Turkm�nistan</option>
							<option value="TR">Turquie</option>
							<option value="TV">Tuvalu</option>
							<option value="UA">Ukraine</option>
							<option value="UY">Uruguay</option>
							<option value="VU">Vanuatu</option>
							<option value="VE">Venezuela</option>
							<option value="VN">Vietnam</option>
							<option value="WF">Wallis-et-Futuna</option>
							<option value="YE">Y�men</option>
							<option value="ZM">Zambie</option>
							<option value="ZW">Zimbabwe</option>
						</select>
						<p class="help-block"></p>
					</div>

					<div class="form-group">
						<label>Num�ros de t�l�phone * </label>
						<div class="form-label">
							<input type="text"
								class="numberPhone update form-control required checkLengthMandatory checkInt"
								data-length="10" name="numberPhone" placeholder="Num�ros de t�l�phone">
						</div>
						<p class="help-block"></p>
					</div>

					* Champs obligatoire
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-success updateAdress" value="Modifier" />
					<button type="button" class="btn btn-danger" data-dismiss="modal">Annuler</button>
				</div>
			</form>
		</div>
	</div>
</div>


