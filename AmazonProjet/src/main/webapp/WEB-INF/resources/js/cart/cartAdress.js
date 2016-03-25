var boolProgress = true;
var inputAdress = ["name", "nameLastName", "adressPrincipal",
    "adressPrincipal", "adressSecondaire", "region", "codePostal", "pays",
    "numberPhone", "city"]

function addInputAdress(selectId) {
  if (selectId.val() == "") {
    $(".blockAdressButton").hide();

    inputAdress.forEach(function(input) {
      $("." + input).val("")
    });
    $(".pays").val("FR")

    inputAdress.forEach(function(input) {
      $("." + input).attr("disabled", false);
    });
  } else {
    $(".blockAdressButton").show();

    var idSelect = selectId.find(':selected').val()
    var selectAdress = $(".selectAdress option[value='" + idSelect + "']")

    inputAdress.forEach(function(input) {
      $("." + input).val(selectAdress.attr("data-" + input))
    });

    inputAdress.forEach(function(input) {
      $("." + input).attr("disabled", true);
    });
    $(".update").attr("disabled", false);
  }
}

function deleteAdress() {
  var idSelect = $(".selectAdress").val();
  if (idSelect != "" && boolProgress) {
    boolProgress = false;
    $.ajax({
      type: "post",
      url: "ajaxDeleteDeleteAdress",
      data: "idAdress=" + idSelect,
      success: function(t) {
        $(".selectAdress").val("")
        $(".selectAdress option[value='" + idSelect + "']").remove();
        addInputAdress($(".selectAdress"));

        self.showMessage("sucess", "Adresse supprimer.")

        self.boolProgress = true;
      },
      error: function() {
        self.showMessage("error", "Un problème est survenu.")

        self.boolProgress = true;
      }
    });

  }
}

function udapteAdress() {
  var idSelect = $(".selectAdress").val();
  if (idSelect != "" && boolProgress) {
    var url = "idAdress="
            + idSelect
            + "&name="
            + encodeURIComponent($(".formUpdateAdress .name").val())
            + "&nameLastName="
            + encodeURIComponent($(".formUpdateAdress .nameLastName").val())
            + "&adressPrincipal="
            + encodeURIComponent($(".formUpdateAdress .adressPrincipal").val())
            + "&adressSecondaire="
            + encodeURIComponent($(".formUpdateAdress .adressSecondaire").val())
            + "&region="
            + encodeURIComponent($(".formUpdateAdress .region").val())
            + "&codePostal="
            + encodeURIComponent($(".formUpdateAdress .codePostal").val())
            + "&pays=" + encodeURIComponent($(".formUpdateAdress .pays").val())
            + "&city=" + encodeURIComponent($(".formUpdateAdress .city").val())
            + "&numberPhone="
            + encodeURIComponent($(".formUpdateAdress .numberPhone").val());
    boolProgress = false;
    $.ajax({
      type: "post",
      url: "ajaxUpdateAdress",
      data: url,
      success: function(t) {
        $('#popUpUpdateAdress').modal('hide')

        inputAdress.forEach(function(input) {
          $("." + input).val($(".formUpdateAdress ." + input).val())
        });

        var selectAdress = $(".selectAdress option[value='" + idSelect + "']")

        selectAdress.html($(".formUpdateAdress .name").val());

        inputAdress.forEach(function(input) {
          selectAdress.attr("data-" + input, $(".formUpdateAdress ." + input)
                  .val());
        });

        self.showMessage("succes", "Adresse modifiée avec succés.")

        self.boolProgress = true;
      },
      error: function() {
        $('#popUpUpdateAdress').modal('hide')

        self.showMessage("error", "Un problème est survenu.")

        self.boolProgress = true;
      }
    });
  }
}

$(document).ready(function() {
  addInputAdress($(".selectAdress"));
  $(".selectAdress").on("change", function() {
    addInputAdress($(this));
  });

  $(".suppressionAdress").on("click", function() {
    deleteAdress();
  })

  $(".updateAdress").on("click", function() {
    if (checkForm("formUpdateAdress")) {
      udapteAdress();
    }
    return false;
  })

});