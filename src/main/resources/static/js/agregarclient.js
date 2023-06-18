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



