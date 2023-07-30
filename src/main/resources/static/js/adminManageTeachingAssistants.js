$("#manageTeachingAssistantsButton").on("click", retrieveTeachingAssistants);
$("#addNewTeachingAssistantButton").on("click", addNewTeachingAssistant);

function addNewTeachingAssistant() {
    var i = $(".btn-outline-danger").length;
    var container = $("#manageTeachingAssistants");
    var currentSubContainer = $("<div></div>");
    currentSubContainer.addClass("row");
    var placeHolder = $("<div></div>");
    placeHolder.addClass("col-1");
    var username = $("<div></div>").text("enter username");
    username.attr("contentEditable", true);
    username.addClass("col-3");
    username.attr("id", "username" + i);
    var available = $("<div></div>").text("true");
    available.addClass("col-1");
    available.attr("contentEditable", true);
    available.attr("id", "available" + i);
    var adjustable = $("<div></div>").text("true");
    adjustable.addClass("col-1");
    adjustable.attr("contentEditable", true);
    adjustable.attr("id", "adjustable" + i);
    var listDiv = $("<div></div>");
    listDiv.text("enter groups");
    listDiv.addClass("col-3");
    listDiv.attr("contentEditable", true);
    listDiv.attr("id", "list" + i);
    var editButton = $("<button></button>").text("save");
    editButton.addClass("edit btn btn-outline-dark mx-1 my-1 col-1");
    editButton.attr("id", i + "e");
    editButton.on("click", function () {
        var number = this.id;
        number = number.substring(0, number.length - 1);
        var username = $("#username" + number).text();
        var available = $("#available" + number).text();
        var adjustable = $("#adjustable" + number).text();
        var list = $("#list" + number).text();
        $.ajax({
            url: "/admin/teachingAssistants/addTeachingAssistantDTO",
            type: "POST",
            data: {
                username: username,
                identityString: "TEACHING_ASSISTANT",
                available: available,
                adjustable: adjustable,
                resourceGroupNames: list.split(",")
            },
            success: function (resp) {
                alert(resp);
            }
        })
    });
    var removeButton = $("<button></button>").text("remove");
    removeButton.attr("id", i + "r");
    removeButton.addClass("remove btn btn-outline-danger mx-1 my-1 col-1");
    removeButton.on("click", function () {
        var number = this.id;
        number = number.substring(0, number.length - 1);
        var username = $("#username" + number).text();
        $.ajax({
            url: "/admin/teachingAssistants/removeTeachingAssistantDTO",
            type: "GET",
            data: {
                username: username
            },
            success: function (resp) {
                alert(resp);
            }
        })
    });
    var hr = $("<hr/>");
    currentSubContainer.append(placeHolder);
    currentSubContainer.append(username);
    currentSubContainer.append(available);
    currentSubContainer.append(adjustable);
    currentSubContainer.append(listDiv);
    currentSubContainer.append(editButton);
    currentSubContainer.append(removeButton);
    currentSubContainer.append(hr);
    container.append(currentSubContainer);

}



function retrieveTeachingAssistants() {
    $("#manageTeachingAssistantsHome").removeClass("d-none");
    $.ajax({
        url: "/admin/teachingAssistants/teachingAssistantDTOs",
        type: "GET",
        success: function(resp) {
            var container = $("#manageTeachingAssistants");
            for (var i = 0; i < resp.length; i++) {
                var currentSubContainer = $("<div></div>");
                currentSubContainer.addClass("row");
                var entity = resp[i];
                var placeHolder = $("<div></div>");
                placeHolder.addClass("col-1");
                var username = $("<div></div>").text(entity.username);
                username.addClass("col-3");
                username.attr("id", "username" + i);
                var available = $("<div></div>").text(entity.available);
                available.addClass("col-1");
                available.attr("contentEditable", true);
                available.attr("id", "available" + i);
                var adjustable = $("<div></div>").text(entity.adjustable);
                adjustable.addClass("col-1");
                adjustable.attr("contentEditable", true);
                adjustable.attr("id", "adjustable" + i);
                var listDiv = $("<div></div>");
                var list = entity.resourceGroupNames;
                var groups = "";
                for (var j = 0; j < list.length; j++) {
                    groups += list[j] + ",";
                }
                listDiv.addClass("col-3");
                listDiv.text(groups);
                listDiv.attr("contentEditable", true);
                listDiv.attr("id", "list" + i);
                var editButton = $("<button></button>").text("save");
                editButton.addClass("edit btn btn-outline-dark mx-1 my-1 col-1");
                editButton.attr("id", i + "e");
                editButton.on("click", function () {
                    var number = this.id;
                    number = number.substring(0, number.length - 1);
                    var username = $("#username" + number).text();
                    var available = $("#available" + number).text();
                    var adjustable = $("#adjustable" + number).text();
                    var list = $("#list" + number).text();
                    $.ajax({
                        url: "/admin/teachingAssistants/updateTeachingAssistantDTO",
                        type: "POST",
                        data: {
                            username: username,
                            identityString: "TEACHING_ASSISTANT",
                            available: available,
                            adjustable: adjustable,
                            resourceGroupNames: list.split(",")
                        },
                        success: function (resp) {
                            alert(resp);
                        }
                    })
                });
                var removeButton = $("<button></button>").text("remove");
                removeButton.attr("id", i + "r");
                removeButton.addClass("remove btn btn-outline-danger mx-1 my-1 col-1");
                removeButton.on("click", function () {
                    var number = this.id;
                    number = number.substring(0, number.length - 1);
                    var username = $("#username" + number).text();
                    $.ajax({
                        url: "/admin/teachingAssistants/removeTeachingAssistantDTO",
                        type: "GET",
                        data: {
                            username: username
                        },
                        success: function (resp) {
                            alert(resp);
                        }
                    })
                });
                var hr = $("<hr/>");
                currentSubContainer.append(placeHolder);
                currentSubContainer.append(username);
                currentSubContainer.append(available);
                currentSubContainer.append(adjustable);
                currentSubContainer.append(listDiv);
                currentSubContainer.append(editButton);
                currentSubContainer.append(removeButton);
                currentSubContainer.append(hr);
                container.append(currentSubContainer);
            }
        }
    })
}
