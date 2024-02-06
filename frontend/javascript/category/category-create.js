const BASE_URL = "http://localhost:8088/api/category/create"
function submitCategoryCreateForm() {
    document.getElementById("addCategoryForm").addEventListener('submit', function(event) {
        event.preventDefault();

        const formData = new FormData(this);
        const formDataObj = {};
        formData.forEach((value, key) => { formDataObj[key] = value; });

        const jsonData = JSON.stringify(formDataObj);

        fetch(BASE_URL, {
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


submitCategoryCreateForm();