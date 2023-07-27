$("#manageRequestsButton").on("click", retrieveRequests);
function retrieveRequests() {
    // var sections = $(".section");
    // sections.empty();
    $.ajax({
        url: "/admin/requests",
        type: "GET",
        success: function(resp) {
            var container = $("#manageRequests");
            for (var i = 0; i < resp.length; i++) {
                var currentSubContainer = $("<div></div>");
                currentSubContainer.addClass("row");
                var entity = resp[i];
                var requestId = $("<div></div>").text(entity.requestId);
                requestId.addClass("col-1");
                var studentId = $("<div></div>").text(entity.studentId);
                studentId.addClass("col-1");
                var groupId = $("<div></div>").text(entity.groupId);
                groupId.addClass("col-1");
                var timeRequired = $("<div></div>").text(entity.timeIntervals * 10 + "minutes");
                timeRequired.addClass("col-1");
                var title = $("<div></div>").text(entity.title);
                title.addClass("col-1");
                var appointmentType = $("<div></div>").text(entity.appointmentType);
                appointmentType.addClass("col-1");
                var creationTime = $("<div></div>").text(entity.creationTime);
                creationTime.addClass("col-2");
                currentSubContainer.append(requestId);
                currentSubContainer.append(studentId);
                currentSubContainer.append(groupId);
                currentSubContainer.append(timeRequired);
                currentSubContainer.append(title);
                currentSubContainer.append(appointmentType);
                currentSubContainer.append(creationTime);
                container.append(currentSubContainer);
            }
        }
    })
}