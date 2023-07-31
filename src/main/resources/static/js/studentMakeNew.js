$("#makeNewButton").on("click", showArea);
$("#confirmGroupSelectionButton").on("click", showTimes);
$("#addNewAppointmentButton").on("click", addNewAppointment);

function showArea() {
    $("#makeNewHome").removeClass("d-none");
    $("#dropdownBar").empty();
    $.ajax({
        url: "/student/add/resourceGroupNames",
        type: "GET",
        success: function (resp) {
            var dropdown = $("#dropdownBar");
            for (var i = 0; i < resp.length; i++) {
                var entity = resp[i];
                var option = $("<option></option>").text(entity);
                dropdown.append(option);
            }
        }
    })
}

function showTimes() {
    $("#makeNew").empty();
    var groupNameSelected = $("#dropdownBar :selected").text();
    $.ajax({
        url: "/student/add/teachingAssistantAvailableTimes",
        type: "GET",
        data: {
            groupName: groupNameSelected
        },
        success: function (resp) {
            var container = $("#makeNew");
            for (var i = 0; i < resp.length; i++) {
                var currentSubContainer = $("<div></div>");
                currentSubContainer.addClass("row");
                var entity = resp[i];
                var placeHolder = $("<div></div>");
                placeHolder.addClass("col-1");
                var username = $("<div></div>").text(entity.username);
                username.addClass("col-3");
                var startTime = $("<div></div>").text(toReadable(entity.time, false));
                startTime.addClass("col-1");
                var duration = $("<div></div>").text("10 min");
                duration.addClass("col-1");
                var placeHolder2 = $("<div></div>");
                placeHolder2.addClass("col-5");
                var checkBox = $("<input/>");
                checkBox.addClass("form-check-input");
                checkBox.attr("type", "checkbox");
                checkBox.attr("value", entity.timeId);
                var hr = $("<hr/>");
                currentSubContainer.append(placeHolder);
                currentSubContainer.append(username);
                currentSubContainer.append(startTime);
                currentSubContainer.append(duration);
                currentSubContainer.append(placeHolder2);
                currentSubContainer.append(checkBox);
                currentSubContainer.append(hr);
                container.append(currentSubContainer);
            }
        }
    })
}

function addNewAppointment() {
    const checked = document.querySelectorAll('input[type="checkbox"]:checked');
    var selected = Array.from(checked).map(x => x.value);
    $.ajax({
        url: "/student/add/addAppointment",
        method: "POST",
        data: {
            list: selected.toString(),
            title: $("#appointmentTitleInput").val(),
            type: $("#appointmentTypeInput").val(),
            description: $("#appointmentContentInput").val(),
            groupName: $("#dropdownBar :selected").text(),
        },
        success: function (resp) {
            alert(resp);
        }
    })
}