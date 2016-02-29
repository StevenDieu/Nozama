var actif = false;

function redirigeUrlMovie() {
	var url = $(".formFilm").attr("action");
	url = url + "/" + $("#support").val();
	url = url + "/" + $("#years").val();
	url = url + "/" + $("#type").val();
	return url;
}

function redirigeUrlMusic() {
	var url = $(".formMusic").attr("action");
	url = url + "/" + $("#support").val();
	url = url + "/" + $("#recordType").val();
	url = url + "/" + $("#years").val();
	url = url + "/" + $("#type").val();

	return url;
}

function redirigeUrlAll() {
	var url = $(".formAll").attr("action");
	url = url + "/" + $("#years").val();

	return url;
}

$(document).ready(function() {
	$(window).load(function() {
		actif = true;

	});

	$(".buttonWithoutBackground").on("click", function() {
		var checkSelectedFormat = $(this).attr('class').search("selectedFormat")
		if(checkSelectedFormat == -1){
			$(this).addClass("selectedFormat");
		}else{
			if($(".selectedFormat").size() != 1){
				$(this).removeClass("selectedFormat");
			}
		}

		if($(".selectedFormat").size() >= 2){
			$("input[name='recordType']").val("AllType")
		}else{
			$("input[name='recordType']").val($(".selectedFormat").data("value"))
		}
	});

	$(".formFilm").on("submit", function() {
		document.location.href = redirigeUrlMovie();
		return false;
	});

	$(".formMusic").on("submit", function() {
		document.location.href = redirigeUrlMusic();
		return false;
	});
	
	$(".formAll").on("submit", function() {
		document.location.href = redirigeUrlAll();
		return false;
	});

	if (varNumberPage > 1) {
		$('.paginationFilm').twbsPagination({
		totalPages : varNumberPage,
		visiblePages : 7,
		startPage : varStartPage,
		onPageClick : function(event, page) {
			if (actif) {
				var url = redirigeUrlMovie();
				url = url + "/" + page;
				document.location.href = url;

			}
		}
		});
		$('.paginationMusic').twbsPagination({
		totalPages : varNumberPage,
		visiblePages : 7,
		startPage : varStartPage,
		onPageClick : function(event, page) {
			if (actif) {
				var url = redirigeUrlMusic();
				url = url + "/" + page;
				document.location.href = url;
			}
		}
		});

		$('.paginationAll').twbsPagination({
		totalPages : varNumberPage,
		visiblePages : 7,
		startPage : varStartPage,
		onPageClick : function(event, page) {
			if (actif) {
				var url = redirigeUrlAll();
				url = url + "/" + page;
				document.location.href = url;
			}
		}
		});
	}
});