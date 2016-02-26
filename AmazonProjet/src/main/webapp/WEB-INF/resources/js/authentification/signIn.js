var boolProgress = true;

function signIn() {
	$(".alert").hide();
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

				} else if (t.statut == "nok") {
					$(".alert").show();
					$(".errorMessage").html(t.message);
				}
				self.boolProgress = true;
			}
			});
		} else {
			$(".alert").show();
			$(".errorMessage").html("Aucun champ ne doit \352tre vide.");
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