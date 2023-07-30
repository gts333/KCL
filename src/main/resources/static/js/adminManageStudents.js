$("#manageStudentsButton").on("click", retrieveStudents);
$("#addNewStudentButton").on("click", addNewStudent);

function addNewStudent() {
    var i = $(".btn-outline-danger").length;
    var container = $("#manageStudents");
    var currentSubContainer = $("<div></div>");
    currentSubContainer.addClass("row");
    var placeHolder = $("<div></div>");
    placeHolder.addClass("col-1");
    var username = $("<div></div>").text("enter name");
    username.addClass("col-3");
    username.attr("id", "username" + i);
    username.attr("contentEditable", true);
    var priorityString = $("<div></div>").text("enter priority");
    priorityString.addClass("col-2");
    priorityString.attr("contentEditable", true);
    priorityString.attr("id", "priority" + i);
    var listDiv = $("<div></div>");
    listDiv.addClass("col-3");
    listDiv.text("enter groups");
    listDiv.attr("contentEditable", true);
    listDiv.attr("id", "list" + i);
    var editButton = $("<button></button>").text("save");
    editButton.addClass("edit btn btn-outline-dark mx-1 my-1 col-1");
    editButton.attr("id", i + "e");
    editButton.on("click", function () {
        var number = this.id;
        number = number.substring(0, number.length - 1);
        var username = $("#username" + number).text();
        var priorityStatus = $("#priority" + number).text();
        var list = $("#list" + number).text();
        $.ajax({
            url: "/admin/students/addStudentDTO",
            type: "POST",
            data: {
                username: username,
                identityString: "STUDENT",
                priorityStatusString: priorityStatus,
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
            url: "/admin/students/removeStudentDTO",
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
    currentSubContainer.append(priorityString);
    currentSubContainer.append(listDiv);
    currentSubContainer.append(editButton);
    currentSubContainer.append(removeButton);
    currentSubContainer.append(hr);
    container.append(currentSubContainer);

}


function retrieveStudents() {
    $("#manageStudentsHome").removeClass("d-none");
    $.ajax({
        url: "/admin/students/studentDTOs",
        type: "GET",
        success: function (resp) {
            var container = $("#manageStudents");
            for (var i = 0; i < resp.length; i++) {
                var currentSubContainer = $("<div></div>");
                currentSubContainer.addClass("row");
                var entity = resp[i];
                var placeHolder = $("<div></div>");
                placeHolder.addClass("col-1");
                var username = $("<div></div>").text(entity.username);
                username.addClass("col-3");
                username.attr("id", "username" + i);
                var priorityString = $("<div></div>").text(entity.priorityStatusString);
                priorityString.addClass("col-2");
                priorityString.attr("contentEditable", true);
                priorityString.attr("id", "priority" + i);
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
                    var priorityStatus = $("#priority" + number).text();
                    var list = $("#list" + number).text();
                    $.ajax({
                        url: "/admin/students/updateStudentDTO",
                        type: "POST",
                        data: {
                            username: username,
                            identityString: "STUDENT",
                            priorityStatusString: priorityStatus,
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
                        url: "/admin/students/removeStudentDTO",
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
                currentSubContainer.append(priorityString);
                currentSubContainer.append(listDiv);
                currentSubContainer.append(editButton);
                currentSubContainer.append(removeButton);
                currentSubContainer.append(hr);
                container.append(currentSubContainer);
            }

        }
    })
}