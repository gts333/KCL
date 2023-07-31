$("#manageTimesButton").on("click", retrieveTimes);
$("#addNewTimeButton").on("click", addNewTime);

function addNewTime() {
    var container = $("#manageTimes");
    var i = $(".btn-outline-danger").length;
    var currentSubContainer = $("<div></div>");
    currentSubContainer.addClass("row");
    var placeHolder = $("<div></div>");
    placeHolder.addClass("col-1 d-none");
    placeHolder.attr("id", "placeHolder" + i);
    var time = $("<div></div>").text("enter time");
    time.addClass("col-3");
    time.attr("contentEditable", true);
    time.attr("id", "time" + i);
    var available = $("<div></div>").text("enter availability");
    available.addClass("col-1");
    available.attr("contentEditable", true);
    available.attr("id", "available" + i);
    var placeHolder2 = $("<div></div>");
    placeHolder2.addClass("col-4");
    var editButton = $("<button></button>").text("save");
    editButton.addClass("edit btn btn-outline-dark mx-1 my-1 col-1");
    editButton.attr("id", i + "e");
    editButton.on("click", function () {
        var number = this.id;
        number = number.substring(0, number.length - 1);
        var time = $("#time" + number).text();
        time = toOriginal(time, false);
        var available = $("#available" + number).text();
        $.ajax({
            url: "/teachingAssistant/times/addTime",
            type: "POST",
            data: {
                time: time,
                available: available
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
        var timeId = $("#placeHolder" + number).text();
        $.ajax({
            url: "/teachingAssistant/times/removeTime",
            type: "GET",
            data: {
                timeId: -1
            },
            success: function (resp) {
                alert(resp);
            }
        })
    });
    var hr = $("<hr/>");
    currentSubContainer.append(placeHolder);
    currentSubContainer.append(time);
    currentSubContainer.append(available);
    currentSubContainer.append(placeHolder2);
    currentSubContainer.append(editButton);
    currentSubContainer.append(removeButton);
    currentSubContainer.append(hr);
    container.append(currentSubContainer);

}

function retrieveTimes() {
    $("#manageTimesHome").removeClass("d-none");
    $.ajax({
        url: "/teachingAssistant/times/times",
        type: "GET",
        success: function (resp) {
            var container = $("#manageTimes");
            for (var i = 0; i < resp.length; i++) {
                var currentSubContainer = $("<div></div>");
                currentSubContainer.addClass("row");
                var entity = resp[i];
                var placeHolder = $("<div></div>");
                placeHolder.addClass("col-1 d-none");
                placeHolder.text(entity.timeId);
                placeHolder.attr("id", "placeHolder" + i);
                var time = $("<div></div>").text(toReadable(entity.time, false));
                time.addClass("col-3");
                time.attr("contentEditable", true);
                time.attr("id", "time" + i);
                var available = $("<div></div>").text(entity.available);
                available.addClass("col-1");
                available.attr("contentEditable", true);
                available.attr("id", "available" + i);
                var placeHolder2 = $("<div></div>");
                placeHolder2.addClass("col-4");
                var editButton = $("<button></button>").text("save");
                editButton.addClass("edit btn btn-outline-dark mx-1 my-1 col-1");
                editButton.attr("id", i + "e");
                editButton.on("click", function () {
                    var number = this.id;
                    number = number.substring(0, number.length - 1);
                    var timeId = $("#placeHolder" + number).text();
                    var time = $("#time" + number).text();
                    time = toOriginal(time, false);
                    var available = $("#available" + number).text();
                    $.ajax({
                        url: "/teachingAssistant/times/updateTime",
                        type: "POST",
                        data: {
                            timeId: timeId,
                            time: time,
                            available: available
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
                    var timeId = $("#placeHolder" + number).text();
                    $.ajax({
                        url: "/teachingAssistant/times/removeTime",
                        type: "GET",
                        data: {
                            timeId: timeId
                        },
                        success: function (resp) {
                            alert(resp);
                        }
                    })
                });
                var hr = $("<hr/>");
                currentSubContainer.append(placeHolder);
                currentSubContainer.append(time);
                currentSubContainer.append(available);
                currentSubContainer.append(placeHolder2);
                currentSubContainer.append(editButton);
                currentSubContainer.append(removeButton);
                currentSubContainer.append(hr);
                container.append(currentSubContainer);
            }
        }
    })
}
