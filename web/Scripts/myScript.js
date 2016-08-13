$(function () {

    // change function for #year and #term in Register the course
    $("#year, #term").change(function () {
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
                    $("#offeringdropdown").html("");
                    var selectStr = "<select name='course'>";
                    $.each(data.courses, function (key, value) {
                        selectStr += "<option value='" + value.offeringId + "'>" + value.courseId + " - " + value.courseName + "</option>";
                    });
                    selectStr += "</select>";
                    $("#offeringdropdown").append(selectStr);
                }
                //display error message
                else {
                    $("#offeringdropdown").html("<div><b>There is no available courses.</b></div>");
                }
            }

        });
    }).change();

    //change event for term, year, location, faculty in course override
    $("#termoverride, #yearoverride, #locationoverride, #facultyoverride").change(function () {
        var year = $("#yearoverride").val();
        var term = $("#termoverride").val();
        var location = $("#locationoverride").val();
        var faculty = $("#faculty").val();
        var dataString = "year=" + year + "&term=" + term + "&location=" + location + "&facultyid=" + faculty + "&requesttype=OfferingList";
        $.ajax({
            type: "POST",
            url: "staffController",
            data: dataString,
            dataType: "json",
            //if received a response from the server
            success: function (data, textStatus, jqXHR) {
                //our country code was correct so we have some information to display
                if (data.success) {
                    $("#courseoverride").html("");
                    var selectStr = "";
                    $.each(data.courses, function (key, value) {
                        selectStr += "<option value='" + value.offeringId + "'>" + value.courseId + " - " + value.courseName + "</option>";
                    });
                    selectStr += "";
                    $("#courseoverride").append(selectStr);
                    $("#courseoverride").change();
                }
            }
        });
    }).change();

    $("#courseoverride").change(function () {
        var offeringId = $(this).val();
        var dataString = "requesttype=CourseCapacity&offeringId=" + offeringId;
        $.ajax({
            type: "POST",
            url: "staffController",
            data: dataString,
            dataType: "json",
            //if received a response from the server
            success: function (data, textStatus, jqXHR) {
                //our country code was correct so we have some information to display
                if (data.success) {
                    $("#courseCapacity").val(data.coursecapacity);
                }
                //display error message
                else {
                    $("#courseCapacity").val("");
                }
            }

        });
    }).change();

    $("#coursesdropdown").load(function () {
        var dataString = "requesttype=CoursesList";
        $.ajax({
            type: "POST",
            url: "staffController",
            data: dataString,
            dataType: "json",
            //if received a response from the server
            success: function (data, textStatus, jqXHR) {
                //our country code was correct so we have some information to display
                if (data.success) {
                    $("#coursesdropdown").html("");
                    var selectStr = "<select name='course'>";
                    $.each(data.courses, function (key, value) {
                        selectStr += "<option value='" + value.courseId + "'>" + value.courseId + " - " + value.courseName + "</option>";
                    });
                    selectStr += "</select>";
                    $("#coursesdropdown").append(selectStr);
                }
                //display error message
                else {
                    $("#coursesdropdown").html("<div><b>There is no available courses.</b></div>");
                }
            }
        });
    }).load();

    $("#facultiesdropdown").load(function () {
        var dataString = "requesttype=FacultiesList";
        $.ajax({
            type: "POST",
            url: "staffController",
            data: dataString,
            dataType: "json",
            //if received a response from the server
            success: function (data, textStatus, jqXHR) {
                //our country code was correct so we have some information to display
                if (data.success) {
                    $("#facultiesdropdown").html("");
                    var selectStr = "<select name='faculty' id='faculty'>";
                    $.each(data.faculties, function (key, value) {
                        selectStr += "<option value='" + value.userid + "'>" + value.first_name + " " + value.last_name + "</option>";
                    });
                    selectStr += "</select>";
                    $("#facultiesdropdown").append(selectStr);
                }
                //display error message
                else {
                    $("#facultiesdropdown").html("<div><b>There is no available faculties.</b></div>");
                }
                $("#termoverride, #yearoverride, #locationoverride, #facultyoverride").change();
            }

        });
    }).load();

    var dropform;
    var dialog = $("#dialog-confirm").dialog({
        autoOpen: false,
        resizable: false,
        height: "auto",
        width: 400,
        modal: true,
        buttons: {
            "Drop": function () {
                dropform.submit();
                $(this).dialog("close");
            },
            Cancel: function () {
                $(this).dialog("close");
            }
        }
    });

    $('[name="dropbutton"]').on("click", function () {
        dropform = $(this).closest("form");
        dialog.dialog("open");
    });

    $("#menu").menu({
        items: "> :not(.ui-widget-header)"
    });
    $(function () {
        $("#tabs").tabs();
    });

    //left navigation for student
    $("#studenthome").on("click", function () {
        $("#requesttype").val("StudentHome");
        $("#welcomeForm").submit();
    });
    $("#registerCourse").on("click", function () {
        $("#requesttype").val("CoursesRegister");
        $("#welcomeForm").submit();
    });
    $("#dropcourse").on("click", function () {
        $("#requesttype").val("CoursesDrop");
        $("#welcomeForm").submit();
    });
    $("#viewstudentgrades").on("click", function () {
        $("#requesttype").val("ViewGrade");
        $("#welcomeForm").submit();
    });
    $("#logout").on("click", function () {
        $("#requesttype").val("Logout");
        $("#welcomeForm").submit();
    });

    //left navigation for staff
    $("#staffhome").on("click", function () {
        $("#requesttype").val("StaffHome");
        $("#welcomeForm").submit();
    });
    $("#createcourse").on("click", function () {
        $("#requesttype").val("CreateCourse");
        $("#welcomeForm").submit();
    });
    $("#overrodecourse").on("click", function () {
        $("#requesttype").val("OverrideCourses");
        $("#welcomeForm").submit();
    });

    //left navigation for faculty
    $("#facultyhome").on("click", function () {
        $("#requesttype").val("FacultyHome");
        $("#welcomeForm").submit();
    });
    $("#viewgrades").on("click", function () {
        $("#requesttype").val("Grade");
        $("#welcomeForm").submit();
    });
    $("#editgrades").on("click", function () {
        $("#requesttype").val("EditGrade");
        $("#welcomeForm").submit();
    });

    $("#courseSelection").change(function () {
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
                    $.each(data.grades, function (idx, val) {
                        mystring = "<tr><td>" + val.lname + "</td><td>" + val.fname + "</td><td>";
                        if (isEdit) {
                            mystring += "<input type=text value='" + val.grade + "' name='" + val.enrollId + "'></td></tr>";
                        } else {
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
});



