var boolProgress = true;
var numberAlert = 0;
function showMessage(type, message) {

	if ($(".alert").length < 3) {
		var html = '<div class="blockAlert' + numberAlert + '"><div class="alert alert' + numberAlert + ' alert-dismissible fade alert-fixed" role="alert"><span class="message"></span></div><br/></div>';
		$("#alert").append(html);
		$(".alert" + numberAlert).css({
			display : "inline-block"
		});
		$(".alert" + numberAlert).removeClass("alert-info alert-success");
		if (type === "error") {
			$(".alert" + numberAlert).addClass("alert-info in");
			$(".message").html("<strong>Oups !</strong> " + message);
		} else {
			$(".alert" + numberAlert).addClass("alert-success in");
			$(".message").html("<strong>Succ&#232;s !</strong> " + message);
		}
		setTimeout('$(".blockAlert" + ' + numberAlert + ').remove()', 2000);
		numberAlert++;
	}

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