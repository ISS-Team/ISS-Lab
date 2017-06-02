/**
 * Created by Afterwind on 5/31/2017.
 */
$(document).ready(function () {
    $(function () {
        $(".datepicker").datetimepicker({timepicker: false, format: 'Y-m-d'});
        $(".datetimepicker").datetimepicker({timepicker: true, format: "c"});
    });
    console.log("ready");
    //show sessions
    $("tr").click(function () {
        var id_conference = $(this).find("*:first-child").html();
        //$("#generalInformations").hide();
        $.ajax({
            url: "/conferences/" + id_conference + "/sessions/getall",
            type: "GET",

            success: function (data) {
                alert("Success");
                console.log(data);
                //$each(res.id,res.title,res.theme,res.date,res.deadLineAbstractInfo,res.deadLineFullPaper,res.deadLineReview,res.startTime,res.endTime){
                var tr;
                for (var i = 0; i < data.length; i++) {
                    tr = $('<tr/>');
                    tr.append("<td style='display:none'>" + data[i].id + "</td>");
                    tr.append("<td>" + data[i].title + "</td>");
                    tr.append("<td>" + data[i].startTime + "</td>");
                    tr.append("<td>" + data[i].duration + "</td>");
                    //	tr.append("<td>"+ <button type="button">Participa</button> +"</td>");
                    //paper
                    $.ajax({
                        url: "/conferences/" + data[i].id + "/sessions/getall",   //schimbat
                        type: "GET",
                        success: function (dataPaper) {
                            alert("Success");
                            console.log(dataPaper);
                            for (var i = 0; i < dataPaper.length; i++) {
                                tr.append("<td>" + dataPaper[i].title + "</td>");
                                tr.append("<td>" + dataPaper[i].theme + "</td>");
                                tr.append("<td>" + dataPaper[i].date + "</td>");
                            }
                        },
                        error: function (res1) {
                            alert("Eroare " + res1);
                        }
                    });

                    $("#sessionInfo").append(tr);

                }
            },
            error: function (res) {
                alert("Eroare " + res);
            }
        });
    });

    //se incarca automat la deschiderea paginii

    $("#About").click(function () {
        $("#formLogin").css("display", "none");
        $("#formRegister").css("display", "none");
        $("#generalInformations").show();

        //apel populare
        $.ajax({
            url: "/conferences/getall", //schimbat
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
                alert("Eroare " + res);
            }
        });

    });

    $("#Login").click(function () {
        $("#generalInformations").hide();
        $("#formRegister").hide();
        $("#tableGeneralInformations").hide();
        $("#formLogin").show();
    });
    $("#Register").click(function () {
        $("#generalInformations").hide();
        $("#formLogin").hide();
        $("#tableGeneralInformations").hide();
        $("#formRegister").show();
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
                //trebuie modificat in functie de permisie
                if (res.permissionLevel === 0 || res.permissionLevel === 1) {
                    $("#navBar").append(" <li><a id='UploadConference' onclick='uploadinfo()' href='#'>Upload Conference</a></li>");
                    $("#Login").css('display', 'none');
                }
                //console.log(res);
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
    $("#btnSubmitUploadConference").click(function () {
        var title = $("#Title").val();
        var theme = $("#Theme").val();
        var revPerPaper = $("#RevPerPaper").val();
        var date = $("#Date").val();
        var deadlineAbstractInfo = $("#DeadlineAbstractInfo").val();
        var deadlineFullPaper = $("#DeadlineFullPaper").val();
        var deadlineReview = $("#DeadlineReview").val();
        var firstDay = $("#FirstDay").val();
        var lastDay = $("#LastDay").val();

        var forUploadConference = {
            "reviewersPerPaper": revPerPaper,
            "title": title,
            "theme": theme,
            "date": date,
            "deadlineAbstractInfo": deadlineAbstractInfo,
            "deadlineFullPaper": deadlineFullPaper,
            "deadlineReview": deadlineReview,
            "startTime": firstDay,
            "endTime": lastDay
        };

        $.ajax({
            url: "/conferences/create",
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(forUploadConference),
            success: function (res) {
                alert("Success!")
            },

            error: function (res) {
                alert("Eroare la incarcare");
            }
        });
    });
});

function uploadinfo() {
    $("#generalInformations").hide();
    $("#formRegister").hide();
    $("#formLogin").hide();
    $("#tableGeneralInformations").hide();
    $("#formConference").show();
}
