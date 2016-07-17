$("#year, #term").change(function () {
    var year = $("#year").val();
    var term = $("#term").val();
    var dataString = $("#courseForm").serialize();
    $.ajax({
        type: "POST",
        url: "RegistrationController_servlet",
        data: dataString,
        dataType: "json",
        //if received a response from the server
        success: function (data, textStatus, jqXHR) {
            //our country code was correct so we have some information to display
            if (data.success) {
                $("#coursedropdown").html("");
                var selectStr = "<select name='course'>";
                $.each( data.courses, function( key, value ) {
                    selectStr += "<option value='" + value.offering_id + "'>" + value.course_id + " - " + value.course_name + "</option>";
                });
                selectStr += "</select>";
                $("#coursedropdown").append(selectStr);
            }
            //display error message
            else {
                $("#coursedropdown").html("<div><b>There is no available courses.</b></div>");
            }
        }

    });
}).change();
