$("#requestButton").on("click", showRequestPage);
$("#makeRequestButton").on("click", submitRequest);
function showRequestPage() {
    $("#makeRequestHome").removeClass("d-none");
}
function submitRequest() {
    $.ajax({
        url: "/student/requests/addRequest",
        type: "POST",
        data: {
            groupName: $("#input1").val(),
            timeIntervals: $("#input2").val(),
            appointmentType:$("#input3").val(),
            title: $("#input4").val(),
            content: $("#input5").val()
        },
        success: function (resp) {
            alert(resp);
        }
    })
}