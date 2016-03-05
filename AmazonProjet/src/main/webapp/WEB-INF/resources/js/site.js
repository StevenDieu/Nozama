var boolProgress = true;
var timeOut;

function showMessage(type, message) {
	$(".alert").css({display: "inline-block"});
	clearTimeout(timeOut); 
	$(".alert").removeClass("alert-danger alert-success");
	if (type === "error") {
		$(".alert").addClass("alert-danger in");
		$(".message").html("<strong>Erreur !</strong> " + message);
	} else {
		$(".alert").addClass("alert-success in");
		$(".message").html("<strong>Succès !</strong> " + message);
	}
	timeOut = setTimeout('$(".alert").removeClass("in")', 3000); 
	timeOut = setTimeout('$(".alert").hide()', 3500); 

}

function addInCart(id, typeData) {
	if (boolProgress) {
		boolProgress = false;
		if (id !== "" && typeData !== "") {
			$.ajax({
			type : "post",
			url : "/ajaxAddCart",
			data : "id=" + id + "&typeData=" + typeData,
			success : function(t) {
				t = JSON.parse(t);
				if (t.statut == "succes") {
					$(".nbCart").html(t.nbCart);
				} 
				showMessage(t.statut, t.message)

				self.boolProgress = true;
			}
			});
		}
	}
}

$(document).ready(function() {
	$('[data-toggle="tooltip"]').tooltip()

	$(".addCart").on("click", function(e) {
		e.preventDefault();
		addInCart($(this).data("id"), $(this).data("type"));
	});
});