$( function() {
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
                        selectStr += "<option value='" + value.offeringId + "'>" + value.courseId + " - " + value.courseName + "</option>";
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
    
    var dialog = $( "#dialog-confirm" ).dialog({
      autoOpen: false,
      resizable: false,
      height: "auto",
      width: 400,
      modal: true,
      buttons: {
        "Drop": function() {
          $( "#courseForm" ).submit();
          $( this ).dialog( "close" );
        },
        Cancel: function() {
          $( this ).dialog( "close" );
        }
      }
    });
    
    $("#dropbutton").on( "click", function() {
        dialog.dialog( "open" );
    });
     
    $( "#menu" ).menu({
      items: "> :not(.ui-widget-header)"
    });
    
    $("#studenthome").on( "click", function() {
        $("#requesttype").val("StudentHome");
        $("#welcomeForm").submit();
    });
    $("#registerCourse").on( "click", function() {
        $("#requesttype").val("CoursesRegister");
        $("#welcomeForm").submit();
    });
    $("#dropcourse").on( "click", function() {
        $("#requesttype").val("CoursesDrop");
        $("#welcomeForm").submit();
    });
    $("#viewgrades").on( "click", function() {
        $("#requesttype").val("ViewGrade");
        $("#welcomeForm").submit();
    });
    $("#logout").on( "click", function() {
        $("#requesttype").val("Logout");
        $("#welcomeForm").submit();
    });
  });
