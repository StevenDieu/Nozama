var boolProgress = true;
var inputAdress = ["name", "nameLastName", "adressPrincipal",
    "adressPrincipal", "adressSecondaire", "region", "codePostal", "pays",
    "numberPhone"]

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
    $(".btn-continue").show();
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
    $(".btn-continue").hide();

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

function ajoutAdresse() {
  if (boolProgress) {
    var url = "name=" + encodeURIComponent($(".formAdresse .name").val())
            + "&nameLastName="
            + encodeURIComponent($(".formAdresse .nameLastName").val())
            + "&adressPrincipal="
            + encodeURIComponent($(".formAdresse .adressPrincipal").val())
            + "&adressSecondaire="
            + encodeURIComponent($(".formAdresse .adressSecondaire").val())
            + "&region=" + encodeURIComponent($(".formAdresse .region").val())
            + "&codePostal="
            + encodeURIComponent($(".formAdresse .codePostal").val())
            + "&pays=" + encodeURIComponent($(".formAdresse .pays").val())
            + "&numberPhone="
            + encodeURIComponent($(".formAdresse .numberPhone").val());
    boolProgress = false;
    $.ajax({
      type: "post",
      url: "ajaxAjoutAdresse",
      data: url,
      success: function(t) {
        t = JSON.parse(t);
        if (t.statut == "success") {

          $('.selectAdress').append($('<option>', {
            value: t.id,
            text: $(".formAdresse .name").val()
          }));

          var selectAdress = $(".selectAdress option[value='" + t.id + "']")
          selectAdress.html($(".formAdresse .name").val());
          inputAdress.forEach(function(input) {
            selectAdress.attr("data-" + input, $(".formAdresse ." + input)
                    .val());
          });
          $(".selectAdress option[value='" + t.id + "']").attr('selected', 'selected');

          self.showMessage("succes", "Adresse ajoutée avec succés.")
        } else {
          self.showMessage("error", t.message)
        }

        self.boolProgress = true;
      },
      error: function() {
        self.showMessage("error", "Un problème est survenu.")
        self.boolProgress = true;
      }
    });
  }
}

$(document).ready(function() {
  $(".btn-continue").val("Ajouter");

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

  $(".formAdresse").on("submit", function() {
    if (checkForm("formAdresse")) {
      ajoutAdresse();
    }
    return false;
  })

});