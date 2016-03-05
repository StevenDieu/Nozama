var boolProgress = true;

function signIn() {
	if (boolProgress) {
		if ($(".email").val() !== "" && $(".pwd").val() !== "") {
			boolProgress = false;

			var self = this;
			$.ajax({
			type : "post",
			url : "ajaxConnexion",
			data : "email=" + $(".email").val() + "&pwd=" + $(".pwd").val(),
			success : function(t) {
				t = JSON.parse(t);
				if (t.statut == "ok") {
					if ($(".redirect").val() != "") {
						window.location.replace($(".redirect").val());
					}
					window.location.replace(t.redirect);

				} else {
					self.showMessage(t.statut, t.message)
				}

				self.boolProgress = true;
			}
			});
		} else {
			showMessage("error", "Aucun champ ne doit \352tre vide.");
		}
	}
	return false;
}

$(document).ready(function() {
	$('.singnIn').on('submit', function(e) {
		e.preventDefault();
		if (boolProgress) {
			signIn();
		}
	});
});