
const BASE_URL = "http://localhost:8088/api/auth/";

function login(){
    document.getElementById('register').style.left = "450px";
    document.getElementById('login').style.left = "50px";
    document.getElementById('btn').style.left = "0";
    document.getElementById("login").style.display = "contents";
    document.getElementById("register").style.display = "none";
    submitForm("login", "login")
}

function register(){
    document.getElementById('register').style.left = "50px";
    document.getElementById('login').style.left = "-400px";
    document.getElementById('btn').style.left = "110px";
    document.getElementById("register").style.display = "contents";
    document.getElementById("login").style.display = "none";
    submitForm("register", "register")

}



function submitForm(formId, endpoint) {
        document.getElementById(formId).addEventListener('submit', function(event) {
        event.preventDefault();

        // Gather form data
        const formData = new FormData(this);
        const formDataObj = {};
        formData.forEach((value, key) => { formDataObj[key] = value; });

        // Convert form data to JSON
        const jsonData = JSON.stringify(formDataObj);

        // Send JSON data to the server
        fetch(BASE_URL + endpoint, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: jsonData,
        })
            .then(response => response.json())
            .then(data => console.log('Success:', data))
            .catch((error) => console.error('Error:', error));
        });
}



// Trigger default tab
login();
