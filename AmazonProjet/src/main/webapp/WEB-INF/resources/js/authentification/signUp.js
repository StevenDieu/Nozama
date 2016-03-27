var boolProgress = true;

function signUp() {
	var gender = $('input[name=gender]:checked').val()

	if (boolProgress) {
		if ($(".passwordSignUp").val() === $(".confirmPassword").val()) {
			if (isAdressMail($(".emailSignUp").val())) {
				if ($(".passwordSignUp").val().length >= 6) {
					boolProgress = false;
					var self = this;
					$.ajax({
					type : "post",
					url : "ajaxInscription",
					data : "name=" + encodeURIComponent($(".name").val()) + "&lastName=" + encodeURIComponent($(".lastName").val()) + "&email=" + encodeURIComponent($(".emailSignUp").val()) + "&password=" + encodeURIComponent($(".passwordSignUp").val()) + "&gender=" + gender,
					success : function(t) {
						t = JSON.parse(t);
						if (t.statut == "success") {
							if ($(".redirectSignUp").val() != "") {
								document.location.href = $(".redirectSignUp").val();
								return;
							}
							document.location.href = t.redirect;
						} else {
							self.showMessage(t.statut, t.message)
						}
						self.boolProgress = true;
					},
					error : function(){
						self.boolProgress = true;
					}
					});

				} else {
					changeBlockFormAndAddMessage($(".passwordSignUp"), "Votre mot de passe doit contenir au minimun 6 caratères.");
				}
			} else {
				changeBlockFormAndAddMessage($(".emailSignUp"), "L'adresse mail n'est pas valide.");
			}
		} else {
			changeBlockFormAndAddMessage($(".confirmPassword"), "Les mots de passe ne sont pas identiques.");
			changeBlockFormAndAddMessage($(".passwordSignUp"), "Les mots de passe ne sont pas identiques.")
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
			if (submit) {
				signUp();
			}
		}
	});
});