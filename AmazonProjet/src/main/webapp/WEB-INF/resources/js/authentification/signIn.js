var boolProgress = true;

function signIn() {
  if (boolProgress) {
    boolProgress = false;

    var self = this;
    $.ajax({
      type: "post",
      url: "ajaxConnexion",
      data: "email=" + encodeURIComponent($(".email").val()) + "&pwd="
              + encodeURIComponent($(".pwd").val()),
      success: function(t) {
        t = JSON.parse(t);
        if (t.statut == "ok") {
          if ($(".redirectSignIn").val() != "") {
            document.location.href = $(".redirectSignIn").val();
            return;
          }
          document.location.href = t.redirect;

        } else {
          self.showMessage(t.statut, t.message)
        }

        self.boolProgress = true;
      },
      error: function() {
        self.boolProgress = true;
      }
    });
  }
  return false;
}

$(document).ready(function() {
  $('.singnIn').on('submit', function(e) {
    e.preventDefault();
    if (boolProgress) {
      if (submit) {
        signIn();
      }
    }
  });
});