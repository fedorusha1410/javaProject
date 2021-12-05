async function Register() {
    let login = document.getElementById("login").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;

    fetch("api/registration", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify({
            login: login,
            password: password,
            email: email
        })
    }).then(res => res.json()).then(res => {
        let data = res;
        if (!data.error) {
            document.location.href = "/login";
        } else {
            document.getElementById("error").innerHTML = data.error;
        }
    });
}