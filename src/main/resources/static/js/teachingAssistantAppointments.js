$("#manageAppointmentsButton").on("click", retrieveAppointments);

function retrieveAppointments() {
    $("#manageAppointmentsHome").removeClass("d-none");
    $.ajax({
        url: "/teachingAssistant/appointments/appointments",
        type: "GET",
        success: function (resp) {
            var container = $("#manageAppointments");
            for (var i = 0; i < resp.length; i++) {
                var currentSubContainer = $("<div></div>");
                currentSubContainer.addClass("row");
                var entity = resp[i];
                var username = $("<div></div>").text(entity.studentUsername);
                username.addClass("col-1");
                var groupName = $("<div></div>").text(entity.groupName);
                groupName.addClass("col-1");
                var title = $("<div></div>").text(entity.title);
                title.addClass("col-1");
                var content = $("<div></div>").text(entity.content);
                content.addClass("col-3");
                var appointmentType = $("<div></div>").text(entity.appointmentType);
                appointmentType.addClass("col-1");
                var startTime = $("<div></div>").text(toReadable(entity.startTime, false));
                startTime.addClass("col-1");
                var endTime = $("<div></div>").text(toReadable(entity.endTime, true));
                endTime.addClass("col-1");
                var creationTime = $("<div></div>").text(entity.creationTime);
                creationTime.addClass("col-3");
                var hr = $("<hr/>");
                currentSubContainer.append(username);
                currentSubContainer.append(groupName);
                currentSubContainer.append(title);
                currentSubContainer.append(content);
                currentSubContainer.append(appointmentType);
                currentSubContainer.append(startTime);
                currentSubContainer.append(endTime);
                currentSubContainer.append(creationTime);
                currentSubContainer.append(hr);
                container.append(currentSubContainer);
            }

        }
    })
}
