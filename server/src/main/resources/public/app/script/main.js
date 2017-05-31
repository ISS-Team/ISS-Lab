/**
 * Created by Afterwind on 5/31/2017.
 */
$(document).ready(function () {
    console.log("ready");
    $("#Login").click(function () {
        $("#generalInformations").hide();
        $("#formRegister").css("display", "none");
        $("#formLogin").css("display", "block");

    });
    $("#Register").click(function () {

        $("#generalInformations").hide();
        $("#formLogin").css("display", "none");
        $("#formRegister").css("display", "block");


    });
    $("#About").click(function () {
        $("#formLogin").css("display", "none");
        $("#formRegister").css("display", "none");
        $("#generalInformations").show();
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
                alert("Success");
                console.log(res);
            },

            error: function (res) {
                alert("Eroare");
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
