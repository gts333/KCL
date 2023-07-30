$("#manageResourceGroupsButton").on("click", retrieveResourceGroups);
$("#addNewResourceGroupButton").on("click", addResourceGroup);

function addResourceGroup() {
    var i = $(".btn-outline-danger").length;
    var container = $("#manageResourceGroups");
    var currentSubContainer = $("<div></div>");
    currentSubContainer.addClass("row");
    var placeHolder = $("<div></div>");
    placeHolder.addClass("col-2");
    var groupName = $("<div></div>").text("enter group name");
    groupName.addClass("col-7");
    groupName.attr("id", "groupName" + i);
    groupName.attr("contentEditable", true);
    var hr = $("<hr/>");
    var editButton = $("<button></button>").text("save");
    editButton.addClass("edit btn btn-outline-dark mx-1 my-1 col-1");
    editButton.attr("id", i + "e");
    editButton.on("click", function () {
        var number = this.id;
        number = number.substring(0, number.length - 1);
        var gname = $("#groupName" + number).text();
        $.ajax({
            url: "/admin/resourceGroups/addResourceGroup",
            type: "POST",
            data: {
                groupName: gname
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
        var gname = $("#groupName" + number).text();
        $.ajax({
            url: "/admin/resourceGroups/removeResourceGroup",
            type: "GET",
            data: {
                groupName: gname
            },
            success: function (resp) {
                alert(resp);
            }
        })
    });
    currentSubContainer.append(placeHolder);
    currentSubContainer.append(groupName);
    currentSubContainer.append(editButton);
    currentSubContainer.append(removeButton);
    currentSubContainer.append(hr);
    container.append(currentSubContainer);
}


function retrieveResourceGroups() {
    $("#manageResourceGroupsHome").removeClass("d-none");
    $.ajax({
        url: "/admin/resourceGroups/resourceGroups",
        type: "GET",
        success: function (resp) {
            var container = $("#manageResourceGroups");
            for (var i = 0; i < resp.length; i++) {
                var currentSubContainer = $("<div></div>");
                currentSubContainer.addClass("row");
                var entity = resp[i];
                var placeHolder = $("<div></div>");
                placeHolder.addClass("col-2");
                var groupName = $("<div></div>").text(entity.groupName);
                groupName.addClass("col-8");
                groupName.attr("id", "groupName" + i);
                var hr = $("<hr/>");
                var removeButton = $("<button></button>").text("remove");
                removeButton.attr("id", i + "r");
                removeButton.addClass("remove btn btn-outline-danger mx-1 my-1 col-1");
                removeButton.on("click", function () {
                    var number = this.id;
                    number = number.substring(0, number.length - 1);
                    var gname = $("#groupName" + number).text();
                    $.ajax({
                        url: "/admin/resourceGroups/removeResourceGroup",
                        type: "GET",
                        data: {
                            groupName: gname
                        },
                        success: function (resp) {
                            alert(resp);
                        }
                    })
                });
                currentSubContainer.append(placeHolder);
                currentSubContainer.append(groupName);
                currentSubContainer.append(removeButton);
                currentSubContainer.append(hr);
                container.append(currentSubContainer);
            }
        }
    })
}