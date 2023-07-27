
$("#manageResourceGroupsButton").on("click", retrieveResourceGroups);


function retrieveResourceGroups() {
    // var sections = $(".section");
    // sections.empty();
    $.ajax({
        url: "/admin/resourceGroups",
        type: "GET",
        success: function(resp) {
            var container = $("#manageRequests");
            for (var i = 0; i < resp.length; i++) {
                var currentSubContainer = $("<div></div>");
                currentSubContainer.addClass("row");
                var entity = resp[i];
                var groupId = $("<div></div>").text(entity.groupId);
                groupId.addClass("col-1");
                var groupName = $("<div></div>").text(entity.groupName);
                groupName.addClass("col-1");
                currentSubContainer.append(groupId);
                currentSubContainer.append(groupName);
                container.append(currentSubContainer);
            }
        }
    })
}