function validateSignInFormData() {
    var username = document.getElementById("username");
    var password = document.getElementById("password");

    username.classList.remove("is-valid", "is-invalid");
    password.classList.remove("is-valid", "is-invalid");


    if (validateUsername(username.value))
        username.classList.add("is-valid")
    else {
        username.classList.add("is-invalid")
        return false;
    }

    if (validatePassword(password.value))
        password.classList.add("is-valid")
    else {
        password.classList.add("is-invalid")
        return false;
    }

    return true;
}