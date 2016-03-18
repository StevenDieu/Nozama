var boolProgress = true;

function testNumber(id, type, idtype) {

	var self = this;

	boolProgress = false;

	var number = $(".numberProduct" + idtype).val();

	if (isNaN(number)) {
		$(".numberProduct" + idtype).val(1);
	} else if (number < 0) {
		$(".numberProduct" + idtype).val(0);
	} else if (number > 100) {
		$(".numberProduct" + idtype).val(100);
	} else if (isNotInt(number)) {
		$(".numberProduct" + idtype).val(parseInt(number) + 1);
	}
	number = $(".numberProduct" + idtype).val();
	$.ajax({
	type : "post",
	url : "ajaxChangeNumberProductCart",
	data : "id=" + id + "&number=" + number + "&type=" + type,
	success : function(t) {
		t = JSON.parse(t);
		number = t.number;
		var price = $(".priceProduct" + idtype).data("price");
		var result = (price * number).toFixed(2);
		$(".totalProduct" + idtype).html(result);
		$(".totalProduct" + idtype).data("price", result);

		var totalPrice = 0;
		$(".totalProduct").each(function(index) {
			totalPrice = totalPrice + parseFloat($(this).data("price"));
		});
		$(".priceTotal").html(totalPrice.toFixed(2));
		self.boolProgress = true;
	},
	error : function() {
		self.boolProgress = true;
	}
	});
}

function isNotInt(n) {
	return n % 1 != 0;
}

function deleteProduct(element, id, type) {
	if (boolProgress) {
		boolProgress = false;
		var self = this;
		$.ajax({
		type : "post",
		url : "ajaxDeleteProductCart",
		data : "id=" + id + "&type=" + type,
		success : function(t) {
			element.parent().parent().remove();

			var totalPrice = 0;
			$(".totalProduct").each(function(index) {
				totalPrice = totalPrice + parseFloat($(this).data("price"));
			});
			$(".priceTotal").html(totalPrice.toFixed(2));

			$(".nbCart").html(t);
			self.boolProgress = true;
		},
		error : function() {
			self.boolProgress = true;
		}
		});
	}
}

function deleteCart() {
	if (boolProgress) {
		boolProgress = false;
		var self = this;
		$.ajax({
		type : "post",
		url : "ajaxDeleteAllProductCart",
		success : function() {
			$(".productCart").remove();
			$(".nbCart").html(0);
			var totalPrice = 0;
			$(".priceTotal").html(totalPrice.toFixed(2));
			self.boolProgress = true;
		},
		error : function() {
			self.boolProgress = true;
		}
		});
	}
}


$(document).ready(function() {
	var totalPrice = 0;
	$(".totalProduct").each(function(index) {
		totalPrice = totalPrice + parseFloat($(this).data("price").replace(",", "."));
	});
	$(".priceTotal").html(totalPrice.toFixed(2));

	$(".deleteProduct").on("click", function() {
		if (boolProgress) {
			deleteProduct($(this), $(this).data("id"), $(this).data("type"));
		}
	});

	$(".numberProduct").on("change", function() {
		if (boolProgress) {
			testNumber($(this).data("id"), $(this).data("type"), $(this).data("idtype"));
		}
	});

	$(".plus").on("click", function() {
		if (boolProgress) {
			var idtype = $(this).data("idtype");
			var val = $(".numberProduct" + idtype).val();
			if (isNaN(val)) {
				$(".numberProduct" + idtype).val(1);
			} else {
				$(".numberProduct" + idtype).val(parseInt(val) + 1);
				testNumber($(this).data("id"), $(this).data("type"), $(this).data("idtype"));
			}
		}

	});

	$(".minus").on("click", function() {
		if (boolProgress) {

			var idtype = $(this).data("idtype");
			var val = $(".numberProduct" + idtype).val();
			if (isNaN(val)) {
				$(".numberProduct" + idtype).val(1);
			} else {
				$(".numberProduct" + idtype).val(parseInt(val) - 1);
				testNumber($(this).data("id"), $(this).data("type"), $(this).data("idtype"));
			}
		}
	});

	$(".suppressionPanier").on("click", function() {
		deleteCart();
	});


});