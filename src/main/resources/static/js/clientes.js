import {requestAuthorization,inicio,deleteCookies} from "./export.js";

inicio();

const traerClientes = async()=>{
    let request = await fetch("/user/get/clients", {
        method:"GET"
    });


    let clientes = await request.json();

    let html = "";

    clientes.forEach(cliente => {
    
        html+=` <tr>
        <td>${cliente.id}</td>
        <td>${cliente.nombre}</td>
        <td>${cliente.direccion}</td>
        <td>${cliente.telefono}</td>
        <td><a href=""><img src="img/icons8-editar.svg" class="acciones"></a>
        <a href=""><img src="img/icons8-basura-llena.svg" class="acciones"></a></td>
    </tr>`;
    });
    
    document.querySelector("#dataTable tbody").innerHTML = html;
}

window.addEventListener("DOMContentLoaded", traerClientes);

document.querySelector("#cerrarSesion").addEventListener("click",()=>{
    deleteCookies();
});
