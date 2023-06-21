import {requestAuthorization,inicio,deleteCookies,error404} from "./export.js";

inicio();

document.querySelector("#cerrarSesion").addEventListener("click",()=>{
    deleteCookies();
    window.location = "index.html";
});

document.getElementById("agregar").addEventListener("click",(e)=>{
    e.preventDefault();

    agregarCliente();
});

window.addEventListener("DOMContentLoaded",async ()=>{
    const auth =await requestAuthorization();
    if (!auth) error404();
})

const agregarCliente = async()=>{

    let cliente ={   };

    cliente.id = document.getElementById("idCliente").value;
    cliente.nombre = document.getElementById("nombre").value;
    cliente.direccion = document.getElementById("direccion").value;
    cliente.telefono = document.getElementById("telefono").value;
    cliente.rol = 2;

    let responseToken =await requestAuthorization();

    if (!responseToken){error404(); return;}

    let request = await fetch("user/add/client",{
        method: "POST",
        headers: {
            "Accept":"application/json",
            "Content-type": "application/json",
            "Authorization": responseToken
        },
        body: JSON.stringify(cliente)
    });
    let response = await request.text();
    alert(response);

}

function validateInput() {
    const input = document.getElementById("nombre");
    const inputValue = input.value;
    const lettersRegex = /^[A-Za-z]+$/;

    if (!lettersRegex.test(inputValue)) {
        input.value = inputValue.replace(/[^A-Za-z]+/g, '');
    }
}


var input = document.getElementById("nombre");

input.addEventListener("input", function(event) {
    var inputValue = event.target.value;

    var cleanValue = inputValue.replace(/[^a-zA-Z]/g, "");

    event.target.value = cleanValue;
});

document.getElementById("nombre").addEventListener("input",validateInput);

function validateNumber(){
    const input = document.getElementById("idCliente");
    const inputValue = input.value;
    const numberRegex = /^[0-9]+$/;

    if (!numberRegex.test(inputValue)) {
        input.value = inputValue.replace(/[^0-9]+/g, '');
    }
}

document.getElementById("idCliente").addEventListener("input",validateNumber);

function validateTelefono(){
    const input = document.getElementById("telefono");
    const inputValue = input.value;
    const numberRegex = /^[0-9]+$/;

    if (!numberRegex.test(inputValue)) {
        input.value = inputValue.replace(/[^0-9]+/g, '');
    }
}

document.getElementById("telefono").addEventListener("input",validateTelefono);