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

        let counter = 0;
        let keys;
        let values;

        data.forEach(element => {
            keys = Object.keys(element);
            values = Object.values(element);
            let table_value = "<div style='display: flex'>";

            for (let i = 0; i < keys.length; i++) {
                table_value += "<div style='display: flex; flex-direction: column; margin-right: 10px; margin-left: 10px'>"
                    + keys[i] + ": "
                    + `<input type="text" value='${values[i]}' id='${keys[i]}${counter}' style='width: 160px'/></div>`;
            }

            table_value += `<button class="apps" onclick="deleteApp('${values[0]}')" style="width: 90px; margin: 5px; align-self: flex-end">Delete</button>`
            table_value += `<button class="apps" onclick="updateApp('${values[0]}','${counter}')" style="width: 90px; margin: 5px; align-self: flex-end">Update</button>`
            result.innerHTML += table_value + "<br/></div>";
            counter++;
        });
        let table_value_second = "<div style='display: flex'>";

        for (let i = 0; i < keys.length; i++) {
            table_value_second += "<div style='display: flex; flex-direction: column; margin-right: 10px; margin-left: 10px'>"
                + keys[i] + ": "
                + `<input type="text" id='${keys[i]}${counter}' style='width: 160px'/></div> `;
        }
        table_value_second += `<button class="films" onclick="insertApp('${counter}')" style="width: 90px;  margin: 5px; align-self: flex-end">Insert</button>`;
        result.innerHTML += table_value_second + "<br/></div>";
        counter++;
    });
}

function insertApp(num) {
    console.log("INSERT");

    let insertObject = jsonArray[num - 1];
    let updKeys = Object.keys(insertObject);
    let updValues = Object.values(insertObject);

    for (let i = 0; i < updKeys.length; i++) {
        updValues[i] = document.getElementById(updKeys[i] + num).value;
        console.log(updValues[i]);
        insertObject[updKeys[i]] = updValues[i];
    }
    console.log(insertObject);

    fetch(`/api/admin/apps`,
        {
            method: "POST",
            headers:
                {
                    "Content-Type": 'application/json',
                    "Accept": 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('jwt')}`
                }, body: JSON.stringify(insertObject)
        }).then(res => {
        return res.json();
    }).then((data) => {
        console.log(data);
    });
}

function search() {
    let filmName = document.getElementById('search').value;
    console.log('SEARCH_FILMS');
    fetch(`/api/apps/admin/${appName}`, {
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

        let counter = 0;
        let keys;
        let values;

        data.forEach(element => {
            keys = Object.keys(element);
            values = Object.values(element);
            let table_value = "<div style='display: flex'>";

            for (let i = 0; i < keys.length; i++) {
                table_value += "<div style='display: flex; flex-direction: column; margin-right: 10px; margin-left: 10px'>"
                    + keys[i] + ": "
                    + `<input type="text" value='${values[i]}' id='${keys[i]}${counter}' style='width: 160px'/></div>`;
            }

            table_value += `<button class="apps" onclick="deleteApp('${values[0]}')" style="width: 90px; margin: 5px; align-self: flex-end">Delete</button>`
            table_value += `<button class="apps" onclick="updateApp('${values[0]}','${counter}')" style="width: 90px; margin: 5px; align-self: flex-end">Update</button>`
            result.innerHTML += table_value + "<br/></div>";
            counter++;
        });
    });
}

function updateApp(id, num) {
    console.log("UPDATE");

    console.log(jsonArray[num]);

    let updObject = jsonArray[num];
    let updKeys = Object.keys(updObject);
    let updValues = Object.values(updObject);

    for (let i = 0; i < updKeys.length; i++) {
        updValues[i] = document.getElementById(updKeys[i] + num).value;
        console.log(updValues[i]);
        updObject[updKeys[i]] = updValues[i];
    }
    console.log(updObject);

    fetch(`/api/admin/apps/${id.toString()}`,
        {
            method: "PUT",
            headers:
                {
                    "Content-Type": 'application/json',
                    "Accept": 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('jwt')}`
                }, body: JSON.stringify(updObject)
        }).then(res => {
        return res.json();
    }).then((data) => {
        console.log(data);
    })
}

function deleteApp(id) {
    alert(id);
    console.log("delete_element: " + " name: " + id);

    fetch(`/api/admin/apps/${id.toString()}`, {
        method: "DELETE",
        headers: {
            "Content-Type": 'application/json; charset=utf-8',
            "Accept": 'application/json',
            'Authorization': `Bearer ${localStorage.getItem('jwt')}`
        }

    }).then(res => {
        return res.json();
    }).then((data) => {
        console.log(data);
    })
}