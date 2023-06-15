import { getCookie } from "./export.js"


const agregarCliente = async()=>{

    let cliente ={   };

    cliente.id = document.getElementById("idCliente").value;
    cliente.nombre = document.getElementById("nombre").value;
    cliente.direccion = document.getElementById("direccion").value;
    cliente.telefono = document.getElementById("telefono").value;
    cliente.rol = 2;

    let cookie = getCookie("user");

    let request = await fetch("user/add/client",{
        method: "POST",
        headers: {
            "Accept":"application/json",
            "Content-type": "application/json",
            "Authorization": cookie.token
        },
        body: JSON.stringify(cliente)
    });
    let response = await request.json();
    alert(response);


}

document.getElementById("agregar").addEventListener("click",(e)=>{
    e.preventDefault();

    agregarCliente();
});

document.getElementById("cerrarSesion").addEventListener("click",()=>{
    document.cookie = `user=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/`;
});