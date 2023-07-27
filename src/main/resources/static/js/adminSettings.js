$("#settingsButton").on("click", displaySettings);

function displaySettings() {
    $("#resetPassword").removeClass("d-none");
    $.ajax({
        url: "/admin/settings",
        type: "GET",
        success: function(resp) {
            var container = $("#settings");
            for (var i = 0; i < resp.length; i++) {
                var currentSubContainer = $("<div></div>");
                currentSubContainer.addClass("row");
                var entity = resp[i];
                var name = $("<div></div>").text(entity.name);
                name.addClass("col-4");
                var value = $("<div></div>").text(entity.value);
                value.addClass("col-1");
                var editButton = $("<button></button>").text("edit");
                editButton.addClass("col-1");
                currentSubContainer.append(name);
                currentSubContainer.append(value);
                currentSubContainer.append(editButton);
                container.append(currentSubContainer);
            }
        }
    })

}
