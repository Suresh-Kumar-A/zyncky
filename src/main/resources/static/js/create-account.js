
function goToStep1(){
    document.getElementById("step1-div").classList.remove("d-none");
    document.getElementById("step2-div").classList.add("d-none");
}

function goToStep2(){
    if(validateStep1Form()){
        document.getElementById("step1-div").classList.add("d-none");
        document.getElementById("step2-div").classList.remove("d-none");
    }
    
}

function validateStep1Form() {
    var userName = document.getElementById("username");
    var password = document.getElementById("password");
    var confirmPassword = document.getElementById("confirmPassword");


    userName.classList.remove("is-valid", "is-invalid");
    password.classList.remove("is-valid", "is-invalid");
    confirmPassword.classList.remove("is-valid", "is-invalid");



    if (validateUsername(userName.value))
        userName.classList.add("is-valid")
    else {
        userName.classList.add("is-invalid")
        return false;
    }

    if (validatePassword(password.value))
        password.classList.add("is-valid")
    else {
        password.classList.add("is-invalid")
        return false;
    }

    if (confirmPassword.value !== undefined && confirmPassword.value !=='' 
        && confirmPassword.value === password.value)
        confirmPassword.classList.add("is-valid")
    else {
        confirmPassword.classList.add("is-invalid")
        return false;
    }

    return true;
}

function validateStep2Form() {
    // var userName = document.getElementById("userName");
    // var password = document.getElementById("password");

    // userName.classList.remove("is-valid", "is-invalid");
    // password.classList.remove("is-valid", "is-invalid");


    // if (validateUsername(userName.value))
    //     userName.classList.add("is-valid")
    // else {
    //     userName.classList.add("is-invalid")
    //     return false;
    // }

    // if (validatePassword(password.value))
    //     password.classList.add("is-valid")
    // else {
    //     password.classList.add("is-invalid")
    //     return false;
    // }

    return true;
}

function showRegisterButton() {
    document.getElementById("btn-create-account").disabled = false
}
function disableRegisterButton() {
    document.getElementById("btn-create-account").disabled = true
}

function createAccount(){
    disableRegisterButton()
    if(validateStep1Form() && validateStep2Form()){
        var username = document.getElementById("username");
        var password = document.getElementById("password");
        var emailAddress = document.getElementById("emailAddress");
        var displayName = document.getElementById("displayName");
        var phoneNumber = document.getElementById("phoneNumber");

    
        // Continue with Account Creation
        var user = {};
        user.username = username.value;
        user.email = emailAddress.value;
        user.password = password.value;
    
        const postRequestOptions = {
            method: 'POST',
            headers: new Headers({ 'content-type': 'application/json' }),
            body: JSON.stringify(user)
        };
        fetch(apiServerUrl + "/user/create-account", postRequestOptions)
            .then(response => response.json())
            .then((data) => {
                console.log(data);
                showTopSuccessNotificationWithMessageAndAutoReload("Account Created Successfully")
            })
            .catch(() => {
                showTopErrorNotificationWithMessage("Unable to create account. Try again")
            })
    }else{
        showTopErrorNotificationWithMessage("Form Validation failed. Try again")

    }
    
}
