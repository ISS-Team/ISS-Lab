/**
 * Created by Afterwind on 5/31/2017.
 */
$(document).ready(function () {
    $(function () {
        $(".datepicker").datetimepicker({timepicker: false, format: 'Y-m-d'});
        $(".datetimepicker").datetimepicker({timepicker: true, format: "c"});
    });
    //se incarca automat la deschiderea paginii

    $("#About").click(function () {
        show("#generalInformations");

        //apel populare
        $.ajax({
            url: "/conferences/getall", //schimbat
            success: function (data) {
                $("#tableGeneralInformations > tbody").empty();
                var tr;
                for (var i = 0; i < data.length; i++) {
                    tr = $('<tr/>');
                    tr.append("<td class='id'>" + data[i].id + "</td>");
                    tr.append("<td>" + data[i].title + "</td>");
                    tr.append("<td>" + data[i].theme + "</td>");
                    tr.append("<td>" + data[i].deadlineAbstractInfo + "</td>");
                    tr.append("<td>" + data[i].deadlineFullPaper + "</td>");
                    tr.append("<td>" + data[i].deadlineReview + "</td>");
                    tr.append("<td>" + data[i].startTime + "</td>");
                    tr.append("<td>" + data[i].endTime + "</td>");
                    tr.click(function (ev) {
                        showConferenceInformation(getParent($(ev.target), "tr"));
                    });
                    $("#tableGeneralInformations > tbody").append(tr);
                }
            },
            error: function (res) {
                alert("Eroare " + res);
            }
        });

    });

    $("#Login").click(function () {
        show("#formLogin");
    });

    $("#Register").click(function () {
        show("#formRegister");
    });

    $("#UploadConference").click(function () {
        show("#formConference");
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
                show("#formLogin");
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
                    $(".hidden-button").removeClass("hidden-button");
                    show();
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
        var deadlineAbstractInfo = $("#DeadlineAbstractInfo").val();
        var deadlineFullPaper = $("#DeadlineFullPaper").val();
        var deadlineReview = $("#DeadlineReview").val();
        var firstDay = $("#FirstDay").val();
        var lastDay = $("#LastDay").val();

        var forUploadConference = {
            "reviewersPerPaper": revPerPaper,
            "title": title,
            "theme": theme,
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

function show(id) {
    $(".hidden-box").removeClass("shown");
    if (id !== undefined) {
        $(id).addClass("shown");
    }
}

function showConferenceInformation(row) {
    var info = $("#conferenceInformation").find("#conferenceInfo");
    var conferenceId = row.find(".id").html();
    $.ajax({
        url: "/conferences/" + conferenceId,
        type: "GET",
        success: function (res) {
            info.empty();
            info.append($("<h2>" + res.title + "</h2>"));
            info.append($("<h4>Theme: " + res.theme + "</h4>"));
            info.append($("<span>" + res.startTime + " - " + res.endTime + "</span>"));
            var biddingButton = $("<input type='button' value='Bidding'>");
            biddingButton.click(function() {
                $("#confpapers").find("tbody").empty();
                show("#confpapers");
                $.ajax({
                    url: "/conferences/" + conferenceId + "/papers/getall",
                    type: "GET",
                    success: function(res) {
                        for (var i = 0; i < res.length; i++) {
                            var paper = res[i];
                            var tr = $("<tr></tr>");
                            tr.append($("<td>" + paper.id + "</td>"));
                            tr.append($("<td>" + paper.title + "</td>"));
                            var td = $("<td></td>");
                            var radioReject = $("<label class='radio-inline'><input type='radio' value='REJECTED' name='optradio" + paper.id + "'>Reject</label>");
                            var radioNeutral = $("<label class='radio-inline'><input type='radio' value='NEUTRAL' name='optradio" + paper.id + "'>Neutral</label>");
                            var radioAccept = $("<label class='radio-inline'><input type='radio' value='ACCEPTED' name='optradio" + paper.id + "'>Accept</label>");
                            td.append(radioReject);
                            td.append(radioNeutral);
                            td.append(radioAccept);
                            tr.append(td);
                            $("#confpapers").find("tbody").append(tr);
                        }
                    }
                });
                $("#confpapers").find("input[type='button']").click(function() {
                    $.each($("#confpapers").find("tbody tr"), function(i, val) {
                        var paperId = $(this).find("td:first-child").html();
                        var status = $(this).find("input:checked").val();
                        $.ajax({
                            url: "/conferences/" + conferenceId + "/papers/bid/" + paperId,
                            type: "POST",
                            contentType: "application/json",
                            dataType: "json",
                            data: JSON.stringify({ status: status })
                        });
                    });

                });
            });
            info.append(biddingButton);
        }
    });
    $.ajax({
        url: "/conferences/" + conferenceId + "/sessions/getall",
        type: "GET",

        success: function (data) {
            var tr;
            $("#sessionInfo").find("tbody").empty();
            for (var i = 0; i < data.length; i++) {
                tr = $('<tr/>');
                tr.append("<td>" + data[i].id + "</td>");
                tr.append("<td>" + data[i].title + "</td>");
                tr.append("<td>" + data[i].startTime + "</td>");
                tr.append("<td>" + data[i].duration + "</td>");
                tr.append("<td>" + data[i].paper.title + "</td>");
                tr.append("<td>" + data[i].paper.topics.join(',') + "</td>");
                tr.append("<td>" + data[i].paper.keywords.join(',') + "</td>");
                $("#sessionInfo").find("tbody").append(tr);
            }
        }
    });
    show("#conferenceInformation");
}

function getParent(node, selector) {
    while (!node.is("body")) {
        if (node.is(selector)) {
            return node;
        } else {
            node = node.parent();
        }
    }
    return null;
}