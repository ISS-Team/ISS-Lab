/**
 * Created by Afterwind on 5/31/2017.
 */
$(document).ready(function () {
    console.log("ready");

    $("tr").click(function() {

        <!-- var table, rows, switching, i, x, y, shouldswitch,vector1={},vector2={}; -->
        <!-- table=$("#tablegeneralinformations")[0];  -->
        <!-- rows=$("tr"); -->
        <!-- for (i = 1; i < (rows.length)-1; i++) { -->
        <!-- x=rows[i].getelementsbytagname("td")[1].innerhtml; -->
        <!-- alert(x); -->
        <!-- } -->
        var id_conference = $(this).find("*:first-child").html();
        $.ajax({
            url: "/conferences/" + id_conference + "/sessions/getall",
            type: "GET",
            //contentType: "application/json",
            // dataType: "json",
            // data: JSON.stringify(id_conference),
            success: function (data) {
                alert("Success");
                console.log(data);
                //$each(res.id,res.title,res.theme,res.date,res.deadLineAbstractInfo,res.deadLineFullPaper,res.deadLineReview,res.startTime,res.endTime){
                var tr;
                for (var i = 0; i < data.length; i++) {
                    tr = $('<tr/>');
                    //tr.append("<td style='display:none'>" + data[i].id+ "</td>");
                    tr.append("<td>" + data[i].title + "</td>");
                    tr.append("<td>" + data[i].startTime + "</td>");
                    tr.append("<td>" + data[i].duration + "</td>");
                    $("#sessionInfo").append(tr);
                }
            },
            error: function (res) {
                alert("Eroare");
            }


        });
    });
    $("#Login").click(function () {
        $("#generalInformations").hide();
        $("#formRegister").hide();
        $("#formLogin").show();
    });

    $("#Register").click(function () {
        $("#generalInformations").hide();
        $("#formLogin").hide();
        $("#formRegister").show();
    });

    $("#About").click(function () {
        $("#formLogin").hide();
        $("#formRegister").hide();
        $("#generalInformations").show();

        $.ajax({

            url: "/users/register",
            type: "GET",
            //   contentType: "application/json",
            //  dataType: "json",
            // data: JSON.stringify(forRegister),
            success: function (data) {
                alert("Success");
                console.log(data);
                //$each(res.id,res.title,res.theme,res.date,res.deadLineAbstractInfo,res.deadLineFullPaper,res.deadLineReview,res.startTime,res.endTime){
                var tr;
                for (var i = 0; i < data.length; i++) {
                    tr = $('<tr/>');
                    tr.append("<td style='display:none' id='idConference'>" + data[i].id + "</td>");
                    tr.append("<td>" + data[i].title + "</td>");
                    tr.append("<td>" + data[i].theme + "</td>");
                    tr.append("<td>" + data[i].date + "</td>");
                    tr.append("<td>" + data[i].deadLineAbstractInfo + "</td>");
                    tr.append("<td>" + data[i].deadLineFullPaper + "</td>");
                    tr.append("<td>" + data[i].deadLineReview + "</td>");
                    tr.append("<td>" + data[i].startTime + "</td>");
                    tr.append("<td>" + data[i].endTime + "</td>");
                    $("#tableGeneralInformations").append(tr);
                }
            },
            error: function (res) {
                alert("Eroare");
            }
        });
    });

    $("#btnSubmitRegister").click(function () {
        var firstName = $("#firstNameR").val();
        var lastName = $("#lastNameR").val();
        var email = $("#emailR").val();
        var username = $("#usernameR").val();
//    var password=SHA256($("#passwordR").val());
        var password = $("#passwordR").val();
        var position = $("#selectPosition").val();

        var forRegister = {
            "firstName": firstName,
            "lastName": lastName,
            "email": email,
            "username": username,
            "password": password,
            "position": position
        };

        $.ajax({
            url: "/users/register",
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(forRegister),
            success: function (res) {
                $("#generalInformations").hide();
                $("#formRegister").hide();
                $("#formLogin").show();
            },

            error: function (res) {
                alert("Eroare la inregistrare");
            }
        });
//    var xhr = new XMLHttpRequest();

//       xhr.open("post", "myurl", true);
//	    xhr.send(JSON.stringify(forRegister));
//		alert("Datele au fost trimise");

    });
    $("#btnSubmitLogin").click(function () {

        var username = $("#username").val();
//    var password=SHA256($("#password").val());
        var password = $("#password").val();
        var forLogin = {
            "username": username,
            "password": password
        };

        $.ajax({
            url: "/users/login",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(forLogin),
            dataType: "json",

            success: function (res) {
                alert("Success");
                console.log(res);
            },

            error: function (res) {
                alert("Eroare");
            }
        });
//    var xhr = new XMLHttpRequest();
//       xhr.open("post", "/users/login", true);

//	    xhr.send(JSON.stringify(forLogin));
//		alert("Datele au fost trimise");
    });
});
