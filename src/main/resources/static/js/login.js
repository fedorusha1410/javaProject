
async function Login() {
    let username = document.getElementById("login").value;
    let password = document.getElementById("password").value;

    let response = await fetch("/api/auth/login",
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        });

    let data = await response.json();

    if (response.status === 200) {
        localStorage.setItem("jwt", data.token);
        localStorage.setItem("Id", data.id);
        console.log(localStorage.getItem("jwt"));
        console.log(localStorage.getItem("Id"));




        if (data.role=="ROLE_ADMIN") {
            document.location.href = "/admin";
        } else {
            document.location.href = "/user";
        }
    } else {
        document.querySelector("#result").style.color = "#e74c3c";
        document.querySelector("#result").innerHTML = data.error;
    }
}