$("#settingsButton").on("click", displaySettings);
$("#resetPasswordButton").on("click", resetPassword);

function displaySettings() {
    $("#resetPasswordHome").removeClass("d-none");
}


function resetPassword() {
    $.ajax({
        url: "/student/settings/resetPassword",
        type: "POST",
        data: {
            originalPassword: $("#fname").val(),
            updatedPassword: $("#lname").val()
        },
        success: function(resp) {
            alert(resp);
            if (resp === "true") {
                window.location = "/loginPage.html"
            }
        }
    })
}

