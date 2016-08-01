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

$("#courseSelection").change(function() {
	var selection = this.value;
	$.ajax({
        type: "GET",
        url: "TeacherController",
        data: {course: selection},
        dataType: "json",
        //if received a response from the server
        success: function (data, textStatus, jqXHR) {
        	var $row;
        	var mystring;
            //our country code was correct so we have some information to display
        	$(".gradeInfo").html("");
        	$("#updateGrades").hide();
            if (!$.isEmptyObject(data) && !$.isEmptyObject(data.grades)) {
            	var isEdit = data.edit;
            	if (isEdit) {
            		$("#updateGrades").show();
            	}
            	$.each(data.grades, function(idx, val) {
            		mystring = "<tr><td>" + val.lname + "</td><td>" + val.fname + "</td><td>";
            		if (isEdit) {
            			mystring += "<input type=text value='" + val.grade + "' name='" + val.enrollId + "'></td></tr>";
            		}
            		else { 
            			mystring += val.grade + "</td></tr>";
            		}
            		$row = $(mystring);
            		$(".gradeInfo").append($row);
            	});
            }
            //display error message
            else {
                $(".gradeInfo").html("<div><b>There are no students in the class.</b></div>");
            }
        }

    });
}).change();
