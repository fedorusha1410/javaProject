let id;
var header = 'Bearer ' + localStorage.getItem("jwt")

function errorPage(mes) {
    return `<div><h3>${mes}</h3><h4><a href="/login">Pls, sing in</a></h4></div>`;
}


function getApps() {
    console.log('GET_APPS');
    fetch(`/api/user/apps`, {
        method: 'GET',
        headers:
            {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('jwt')}`
            }
    }).then(res => res.json()).then(res => {
            let str = 'Not Found';
            console.log(res);
            if (res.status == 401)
                $('#div').html(errorPage("You are not authorized"));
            else {
                if (res.status == 403)
                    $('#div').html(errorPage("Not enough access rights"));
                else {
                    if (res.status == 500) {
                        $('#div').html(errorPage("Authorisation Error"));
                    } else {
                        if (!res.error) {
                            let user = res;
                            // id = user.id;
                            // console.log(id);
                            let counter = 0;

                            str = '<table style="border-spacing: 0 10px;\n' +
                                'font-family: \'Open Sans\', sans-serif;\n' +
                                'font-weight: bold;">' +
                                '    <thead>' +
                                '    <tr>' +
                                '        <th>App</th>' +

                                '    </tr>' +
                                '    </thead>' +
                                '    <tbody>';
                            res.forEach(obj => {
                                counter = obj.id;
                                console.log(counter);
                                str += '<tr>' +
                                    '<td>' + obj.name + '</td>' +
                                    '<td>' + '<button class="install__button" style="color: palevioletred; margin: 5px;"  data-name="'
                                    + obj.name +
                                    '">Install</button>' + '</td>' +
                                    '</tr>';
                            });

                            str += '</tbody></table>';
                        }
                    }
                }
            }

            document.addEventListener('click', (e) => {
                if (e.target.classList.contains('install__button')) {
                    const element = e.target;
                    const name = element.dataset.name;
                    //console.log(name);
                    install(name).then(r => console.log(r));
                }
            });

            document.getElementById("apps").innerHTML = str;


        }
    )
    ;
}

async function install(name) {
    console.log('INSTALL_APP');
    console.log(localStorage.getItem("Id"));

    const app_name = name;
    let user_id = localStorage.getItem("Id");
    console.log(user_id);

    await fetch("/api/user/add",
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Authorization': header
            },
            body: JSON.stringify({
                app_name: app_name,
                id: user_id
            })
        }).then(res => res.json()).then(res => {
        if (res.status >= 400 && res.status <= 500) {
            console.log(res.status);
        } else {

            console.log("successful");

        }
    });


}


function getMyApps() {
    console.log('GET_MY_APPS');
    let id = localStorage.getItem("Id");
    console.log(id);
    fetch("/api/user/usersApps/" +id, {
        method: 'GET',
        headers:
            {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Authorization': header
            }
    }).then(res => res.json()).then(res => {
            let str = 'Not Found';
            console.log(res);
            if (res.status == 401)
                document.getElementById("apps").innerHTML+="Error 401";
            else {
                if (res.status == 403)
                    document.getElementById("apps").innerHTML+="error 403";
                else {
                    if (res.status == 500) {
                        document.getElementById("apps").innerHTML+="Auth error";
                    } else {
                        if (!res.error) {

                            // id = user.id;
                            // console.log(id);


                            str = '<table style="border-spacing: 0 10px;\n' +
                                'font-family: \'Open Sans\', sans-serif;\n' +
                                'font-weight: bold;">' +
                                '    <thead>' +
                                '    <tr>' +
                                '        <th>App</th>' +

                                '    </tr>' +
                                '    </thead>' +
                                '    <tbody>';
                            res.forEach(obj => {

                                str += '<tr>' +
                                    '<td>' + obj.name + '</td>' +
                                    '<td>' + '<button class="uninstall__button" style="color: palevioletred; margin: 5px;"  data-name="'
                                    + obj.name +
                                    '">Uninstall</button>' + '</td>' +
                                    '</tr>';
                            });

                            str += '</tbody></table>';
                        }
                    }
                }
            }

            document.addEventListener('click', (e) => {
                if (e.target.classList.contains('uninstall__button')) {
                    const element = e.target;
                    const name = element.dataset.name;
                    //console.log(name);
                    uninstall(name).then(r => console.log(r));
                }
            });

            document.getElementById("apps").innerHTML = str;


        }
    )

}

function  uninstall(name){

}


