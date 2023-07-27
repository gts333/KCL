$("#manageStudentsButton").on("click", retrieveStudents);

function retrieveStudents() {
    $.ajax({
        url: "/admin/students/studentDTOs",
        type: "GET",
        success: function(resp) {
            var container = $("#manageStudents");
            for (var i = 0; i < resp.length; i++) {
                var currentSubContainer = $("<div></div>");
                currentSubContainer.addClass("row");
                var entity = resp[i];
                var userId = $("<div></div>").text(entity.userId);
                userId.addClass("col-1");
                var username = $("<div></div>").text(entity.username);
                username.addClass("col-1");
                var priorityString = $("<div></div>").text(entity.priorityStatusString);
                priorityString.addClass("col-1");
                var listDiv = $("<div></div>");
                var list = entity.resourceGroupIds;
                var groups = "";
                for (var j = 0; j < list.length; j++) {
                    groups += list[j] + ", ";
                }
                listDiv.addClass("col-1");
                listDiv.text(groups);
                var editButton = $("<button></button>").text("edit");
                editButton.addClass("col-1");
                var removeButton = $("<button></button>").text("remove");
                removeButton.addClass("col-1");
                currentSubContainer.append(userId);
                currentSubContainer.append(username);
                currentSubContainer.append(priorityString);
                currentSubContainer.append(listDiv);
                currentSubContainer.append(editButton);
                currentSubContainer.append(removeButton);
                container.append(currentSubContainer);
            }
        }
    })
}