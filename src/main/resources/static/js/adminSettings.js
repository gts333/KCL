$("#settingsButton").on("click", displaySettings);
$("#resetPasswordButton").on("click", resetPassword);
function resetPassword() {
    $.ajax({
        url: "/admin/settings/resetPassword",
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



function displaySettings() {
    $("#resetPasswordHome").removeClass("d-none");
    $.ajax({
        url: "/admin/settings/settings",
        type: "GET",
        success: function (resp) {
            var container = $("#settings");
            for (var i = 0; i < resp.length; i++) {
                var currentSubContainer = $("<div></div>");
                currentSubContainer.addClass("row");
                var entity = resp[i];
                var placeHolder = $("<div></div>");
                placeHolder.addClass("col-1");
                var name = $("<div></div>").text(entity.name);
                name.addClass("col-4");
                var value = $("<div></div>").text(entity.value);
                value.addClass("col-1");
                value.attr("contentEditable", true);
                value.attr("id", "value" + i);
                var placeHolder2 = $("<div></div>");
                placeHolder2.addClass("col-4");
                var editButton = $("<button></button>").text("save");
                editButton.addClass("edit btn btn-outline-dark mx-1 my-1 col-1");
                editButton.attr("id", i + "e");
                editButton.on("click", function () {
                    var number = this.id;
                    number = number.substring(0, number.length - 1);
                    var optionValue = $("#value" + number).text();
                    $.ajax({
                        url: "/admin/settings/updateSettings",
                        type: "GET",
                        data: {
                            option: number,
                            value: optionValue
                        },
                        success: function (resp) {
                            alert(resp);
                        }
                    })
                });
                var hr = $("<hr/>");
                currentSubContainer.append(placeHolder);
                currentSubContainer.append(name);
                currentSubContainer.append(value);
                currentSubContainer.append(placeHolder2);
                currentSubContainer.append(editButton);
                currentSubContainer.append(hr);
                container.append(currentSubContainer);
            }
        }
    })
}
