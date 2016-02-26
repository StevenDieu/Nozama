var boolProgress = true;

function signUp() {
	var gender = $('input[name=gender]:checked').val()
	$(".alert").hide();
	if (boolProgress) {
		if (gender !== undefined && $(".lastName").val() !== "" && $(".email").val() !== "" && $(".password").val() !== "") {
			if ($(".password").val() === $(".confirmPassword").val()) {
				if (isAdressMail($(".email").val())) {
					if ($(".password").val().length >= 6 && $(".password").val().length <= 54) {
						if ($(".cgv:checked").length !== 0) {
							boolProgress = false;
							var self = this;
							$.ajax({
							type : "post",
							url : "ajaxInscription",
							data : "name=" + $(".name").val() + "&lastName=" + $(".lastName").val() + "&email=" + $(".email").val() + "&password=" + $(".password").val() + "&gender=" + gender,
							success : function(t) {
								t = JSON.parse(t);
								if (t.statut == "ok") {
									window.location.replace(t.redirect);
								} else if (t.statut == "nok") {
									$(".alert").show();
									$(".errorMessage").html(t.message);
								}
								self.boolProgress = true;
							}
							});
						} else {
							$(".alert").show();
							$(".errorMessage").html("Les conditions d'utilisation doivent \352tre accept\351s.");
						}
					} else {
						$(".alert").show();
						$(".errorMessage").html("Le mot de passe doit être compris entre 6 et 54 charat\350res.");
					}
				} else {
					$(".alert").show();
					$(".errorMessage").html("L'adresse mail n'est pas valide.");
				}
			} else {
				$(".alert").show();
				$(".errorMessage").html("Les mots de passe ne sont pas identiques.");
			}
		} else {
			$(".alert").show();
			$(".errorMessage").html("Aucun champ ne doit \352tre vide.");
		}

	}

	return false;
}

function isAdressMail(email) {
	var reg = new RegExp('^[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*@[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*[\.]{1}[a-z]{2,6}$', 'i');
	if (reg.test(email)) {
		return true;
	} else {
		return false;
	}
}

$(document).ready(function() {
	$('.signUp').on('submit', function(e) {
		e.preventDefault();
		if (boolProgress) {
			signUp();
		}
	});
});