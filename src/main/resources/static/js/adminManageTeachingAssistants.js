$("#manageTeachingAssistantsButton").on("click", retrieveTeachingAssistants);

function retrieveTeachingAssistants() {
    // var sections = $(".section");
    // sections.empty();
    $.ajax({
        url: "/admin/teachingAssistants/teachingAssistantDTOs",
        type: "GET",
        success: function(resp) {
            var container = $("#manageTeachingAssistants");
            for (var i = 0; i < resp.length; i++) {
                var currentSubContainer = $("<div></div>");
                currentSubContainer.addClass("row");
                var entity = resp[i];
                var userId = $("<div></div>").text(entity.userId);
                userId.addClass("col-1");
                var username = $("<div></div>").text(entity.username);
                username.addClass("col-1");
                var available = $("<div></div>").text(entity.available);
                available.addClass("col-1");
                var adjustable = $("<div></div>").text(entity.adjustable);
                adjustable.addClass("col-1");
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
                currentSubContainer.append(available);
                currentSubContainer.append(adjustable);
                currentSubContainer.append(listDiv);
                currentSubContainer.append(editButton);
                currentSubContainer.append(removeButton);
                container.append(currentSubContainer);
            }
        }
    })
}
