$("#manageRequestsButton").on("click", retrieveRequests);

function retrieveRequests() {
    $("#manageRequestsHome").removeClass("d-none");
    $.ajax({
        url: "/admin/requests/requests",
        type: "GET",
        success: function(resp) {
            var container = $("#manageRequests");
            for (var i = 0; i < resp.length; i++) {
                var currentSubContainer = $("<div></div>");
                currentSubContainer.addClass("row");
                var entity = resp[i];
                var requestId = $("<div></div>");
                requestId.addClass("col-1");
                var studentUsername = $("<div></div>").text(entity.studentUsername);
                studentUsername.addClass("col-3");
                var groupName = $("<div></div>").text(entity.groupName);
                groupName.addClass("col-1");
                var timeRequired = $("<div></div>").text(entity.timeIntervals * 10 + "minutes");
                timeRequired.addClass("col-1");
                var title = $("<div></div>").text(entity.title);
                title.addClass("col-2");
                var appointmentType = $("<div></div>").text(entity.appointmentType);
                appointmentType.addClass("col-1");
                var creationTime = $("<div></div>").text(entity.creationTime);
                creationTime.addClass("col-3");
                var hr = $("<hr/>");
                currentSubContainer.append(requestId);
                currentSubContainer.append(studentUsername);
                currentSubContainer.append(groupName);
                currentSubContainer.append(timeRequired);
                currentSubContainer.append(title);
                currentSubContainer.append(appointmentType);
                currentSubContainer.append(creationTime);
                currentSubContainer.append(hr);
                container.append(currentSubContainer);
            }
        }
    })
}