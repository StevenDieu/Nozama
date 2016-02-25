$(document).ready(function() {

	$(".buttonWithoutBackground").on("click", function() {
		$(".selectedFormat").removeClass("selectedFormat");
		$(this).addClass("selectedFormat");
		$("input[name='recordType']").val($(this).data("value"))
	})

});