var actif = false;

function redirigeUrlMovie() {
	var url = $(".formFilm").attr("action");
	url = url + "/" + $("#support").val();
	url = url + "/" + $("#years").val();
	url = url + "/" + $("#type").val();
	document.location.href = url;
}

function redirigeUrlMusic() {
	var url = $(".formMusic").attr("action");
	url = url + "/" + $("#support").val();
	url = url + "/" + $("#recordType").val();
	url = url + "/" + $("#years").val();
	url = url + "/" + $("#type").val();

	document.location.href = url;
}

$(document).ready(function() {
	$(window).load(function() {
		actif = true;

	});

	$(".buttonWithoutBackground").on("click", function() {
		$(".selectedFormat").removeClass("selectedFormat");
		$(this).addClass("selectedFormat");
		$("input[name='recordType']").val($(this).data("value"))
	});

	$(".formFilm").on("submit", function() {
		redirigeUrlMovie();
		return false;
	});

	$(".formMusic").on("submit", function() {
		redirigeUrlMusic();
		return false;
	});

	if (varNumberPage > 0) {
		$('.paginationFilm').twbsPagination({
		totalPages : varNumberPage,
		visiblePages : 7,
		startPage : varStartPage,
		onPageClick : function(event, page) {
			if (actif) {
				redirigeUrlMovie();
			}
		}
		});
		$('.paginationMusic').twbsPagination({
		totalPages : varNumberPage,
		visiblePages : 7,
		startPage : varStartPage,
		onPageClick : function(event, page) {
			if (actif) {
				redirigeUrlMusic();
			}
		}
		});
	}
});