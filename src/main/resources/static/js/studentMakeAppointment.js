$("#makeAppointmentButton").on("click", displayMain);

function displayMain() {
    $("#makeAppointmentForm").removeClass("d-none");
    $.ajax({
        url: "/students/appointments/resourceGroups",
        type: "GET",
        success: function(resp) {
            var container = $("#makeAppointment");
            for (var i = 0; i < resp.length; i++) {
                var currentSubContainer = $("<div></div>");
                currentSubContainer.addClass("row");
                var entity = resp[i];
                var groupId = $("<div></div>").text(entity.groupId);
                groupId.addClass("col-1");
                var groupName = $("<div></div>").text(entity.groupName);
                groupName.addClass("col-1");
                var selectButton = $("<button></button>").text("select");
                selectButton.attr("group", entity.groupId);
                selectButton.addClass("col-1");
                selectButton.addClass("selectButton");
                selectButton.on("click", function() {
                    $("#teachingAssistants").empty();
                    $.ajax({
                        data: {
                            groupId: entity.groupId
                        },
                        url: "/students/appointments/teachingAssistantDTOs",
                        type: "GET",
                        success: function(resp) {
                            var container = $("#teachingAssistants");
                            alert(resp);
                        }
                    })
                });
                currentSubContainer.append(groupId);
                currentSubContainer.append(groupName);
                currentSubContainer.append(selectButton);
                container.append(currentSubContainer);
            }

        }
    })
}




$("#viewAppointmentButton").on("click", function () {
   $.ajax({
       url: "/students/appointments/appointments",
       type: "GET",
       success: function(resp) {
           var container = $("#viewAppointments");
           alert(resp);
       }
   })
});