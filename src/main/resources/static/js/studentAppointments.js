$("#manageAppointmentsButton").on("click", retrieveAppointments);

function retrieveAppointments() {
    $("#manageAppointmentsHome").removeClass("d-none");
    $.ajax({
        url: "/student/appointments/appointments",
        type: "GET",
        success: function (resp) {
            var container = $("#manageAppointments");
            for (var i = 0; i < resp.length; i++) {
                var currentSubContainer = $("<div></div>");
                currentSubContainer.addClass("row");
                var entity = resp[i];
                var placeHolder = $("<div></div>");
                placeHolder.addClass("d-none");
                placeHolder.attr("id", "username" + i);
                placeHolder.text(entity.appointmentId);
                var username = $("<div></div>").text(entity.teachingAssistantUsername);
                username.addClass("col-1");
                var groupName = $("<div></div>").text(entity.groupName);
                groupName.addClass("col-1");
                var title = $("<div></div>").text(entity.title);
                title.addClass("col-1");
                var content = $("<div></div>").text(entity.content);
                content.addClass("col-2");
                var appointmentType = $("<div></div>").text(entity.appointmentType);
                appointmentType.addClass("col-1");
                var startTime = $("<div></div>").text(toReadable(entity.startTime, false));
                startTime.addClass("col-1");
                var endTime = $("<div></div>").text(toReadable(entity.endTime, true));
                endTime.addClass("col-1");
                var creationTime = $("<div></div>").text(entity.creationTime);
                creationTime.addClass("col-2");
                var removeButton = $("<button></button>").text("remove");
                removeButton.attr("id", i + "r");
                removeButton.addClass("remove btn btn-outline-danger mx-1 my-1 col-1");
                removeButton.on("click", function () {
                    var number = this.id;
                    number = number.substring(0, number.length - 1);
                    var appointmentId = $("#username" + number).text();
                    $.ajax({
                        url: "/student/appointments/removeAppointment",
                        type: "GET",
                        data: {
                            appointmentId: appointmentId
                        },
                        success: function (resp) {
                            alert(resp);
                        }
                    })
                });
                var hr = $("<hr/>");
                currentSubContainer.append(placeHolder);
                currentSubContainer.append(username);
                currentSubContainer.append(groupName);
                currentSubContainer.append(title);
                currentSubContainer.append(content);
                currentSubContainer.append(appointmentType);
                currentSubContainer.append(startTime);
                currentSubContainer.append(endTime);
                currentSubContainer.append(creationTime);
                currentSubContainer.append(removeButton);
                currentSubContainer.append(hr);
                container.append(currentSubContainer);
            }

        }
    })
}
