const BASE_URL = "http://localhost:8088/api"
function submitProductCreateForm(endpoint) {
    document.getElementById("productForm").addEventListener('submit', function(event) {
        event.preventDefault();

        const formData = new FormData(this);
        const formDataObj = {};
        formData.forEach((value, key) => { formDataObj[key] = value; });

        const jsonData = JSON.stringify(formDataObj);

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


async function getProductCategory() {

    let html = ""

    await fetch('http://localhost:8088/api/category')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            return response.json();
        })
        .then(categories => {
            categories.forEach(category => {
                let categoryName = category.categoryName.toUpperCase();
                let tag = `<option value="${categoryName}">${categoryName}</option>`
                html += tag;

            });
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });

    console.log(html)

    const productCategory = document.getElementById("productCategory");
    productCategory.innerHTML = html;
}

submitProductCreateForm("/shopping/create")
getProductCategory();