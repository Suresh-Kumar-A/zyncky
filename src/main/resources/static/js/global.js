function tooglePassword() {
    var toogleOption = document.getElementById("showPassword");
    if (toogleOption.checked) {
        document.getElementById("password").type = "text"
    } else {
        document.getElementById("password").type = "password"
    }
}

function toogleMenu() {
    document.getElementById("sidebar").classList.toggle("active");
}