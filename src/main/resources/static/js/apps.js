let jsonArray;
let templateObject;

function getApps() {
    console.log('GET_APPS');
    fetch(`/api/admin/apps`, {
        method: 'GET',
        headers:
            {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('jwt')}`
            }
    }).then(res => {
        return res.json();
    }).then(data => {
        console.log(data);
        jsonArray = data;
        templateObject = data[0];
        console.log(templateObject);
        result.innerHTML = "";
        let str = 'Not Found';
        str = '<table style="border-spacing: 0 10px;\n' +
            'font-family: \'Open Sans\', sans-serif;\n' +
            'font-weight: bold;">' +
            '    <thead>' +
            '    <tr>' +
            '        <th>App</th>' +

            '    </tr>' +
            '    </thead>' +
            '    <tbody>';
        data.forEach(obj => {


            str += '<tr>' +
                '<td>' + obj.name + '</td>' +
                '<td>' + obj.desc + '</td>' +
                '<td>' + obj.id + '</td>' +
                '<td>' + '<button class="delete__button" style="color: palevioletred; margin: 5px;"  data-name="'
                + obj.id +
                '">Delete App</button>' + '</td>' +
                '</tr>';
        });

        str += '</tbody></table>';

        document.getElementById("apps").innerHTML = str;
    });

    document.addEventListener('click', (e) => {
        if (e.target.classList.contains('delete__button')) {
            const element = e.target;
            //const name = element.dataset.name;
            const id = element.dataset.name;
            console.log(id);

            deleteApp(id);
        }
    });

}


function insertApp(num) {
    console.log("INSERT");

    let name = document.getElementById("name").value;
    let desc = document.getElementById("desc").value;
    if(name==null || desc==null){
        alert("Enter values!");
    }



    fetch(`/api/admin/apps`,
        {
            method: "POST",
            headers:
                {
                    "Content-Type": 'application/json',
                    "Accept": 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('jwt')}`
                }, body:JSON.stringify({
                name: name,
                desc: desc
            })
        }).then(res => res.json()).then(res => {
        if (res.status >= 400 && res.status <= 500) {
            console.log(res.status);
        } else {

            console.log("successful");

        }
    })
}



async function getCharacter(){
    fetch("/api/admin/apps", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization' : `Bearer ${localStorage.getItem('jwt')}`
        }
    }).then(res => res.json()).then(res => {
        if(res.status == 500){
            $('#div').html(errorPage("Authorisation Error"));
        }else {
            var list = document.getElementById('appss');
            let apps = res;
            apps.forEach(obj => {
                console.log(obj.name);
                var option = document.createElement('option');
                option.value = obj.name;
                list.appendChild(option);
            });
        }
    });
}

function updateApp(id, num) {
    console.log("UPDATE");


    let name = document.getElementById("appSearch").value;
    let desc = document.getElementById("newDesc").value;






    fetch(`/api/admin/apps/update`,
        {
            method: "PUT",
            headers:
                {
                    "Content-Type": 'application/json',
                    "Accept": 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('jwt')}`
                },body:JSON.stringify({
                name: name,
                desc: desc
            })
        }).then(res => {
        return res.json();
    }).then((data) => {
        console.log(data);
    })
}

function deleteApp(id) {
    //alert(id);
    console.log("delete_element: " + " id: " + id);

    fetch("/api/admin/apps/" + id, {
        method: "DELETE",
        headers: {
            "Content-Type": 'application/json; charset=utf-8',
            "Accept": 'application/json',
            'Authorization': `Bearer ${localStorage.getItem('jwt')}`
        }

    }).then(res => res.json()).then(res => {
        if (res.status >= 400 && res.status <= 500) {
            console.log(res.status);
        } else {

            console.log("successful");

        }
    })
}