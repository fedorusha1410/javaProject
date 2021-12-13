async function Register() {
    let username = document.getElementById("login").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;


    console.log(username);
    console.log(email);
    console.log(password);
    let response = await fetch("/api/registration",
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify({
                username: username,
                email: email,
                password: password
            })
        });

    if (response.status === 200) {
        // document.querySelector("#result").innerHTML = "check your gmail";
        console.log("successful");
        window.location.replace("http://localhost:8081/login");
    } else {
        let data = await response.json();
        document.querySelector("#result").innerHTML = "please input invalid data";
        // ClearInput();
        console.log(data.error);
    }
}