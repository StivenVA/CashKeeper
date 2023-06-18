import {requestAuthorization,inicio,deleteCookies,error404} from "./export.js";

inicio();

const traerClientes = async()=>{

    const auth =await requestAuthorization();

    if (!auth){ await error404(); return;}

    let request = await fetch("/user/get/clients", {
        method:"GET",
        headers:{
            "Accept":"application/json",
            "Content-type":"application/json",
            "Authorization":auth
        }
    });

    let clientes = await request.json();

    let html = "";

    clientes.forEach(cliente => {
    
        html+=` <tr>
        <td>${cliente.id}</td>
        <td>${cliente.nombre}</td>
        <td>${cliente.direccion}</td>
        <td>${cliente.telefono}</td>
        <td>
        <a href="#" data-toggle="modal" data-target="#editarCliente" onclick='editarCliente(${JSON.stringify(cliente)})'>
            <img alt="editar" src="img/icons8-editar.svg" class="acciones"></a>
        <a href="#" onclick="eliminarCliente('${cliente.id}')" data-toggle="modal" data-target="#eliminarCliente">
                <img src="img/icons8-basura-llena.svg" class="acciones" alt="Eliminar">
            </a>
        </td>
    </tr>`;
    });
    
    document.querySelector("#dataTable tbody").innerHTML = html;
}

window.addEventListener("DOMContentLoaded", ()=>{

    traerClientes();
});

document.querySelector("#cerrarSesion").addEventListener("click",()=>{
    deleteCookies();
    window.location = "index.html";
});

const eliminarCliente = (idCliente)=>{
    document.getElementById("eliminar").dataset.eliminarCliente = idCliente;
}

document.getElementById("eliminar").addEventListener("click",async ()=>{

    const idCliente = document.getElementById("eliminar").dataset.eliminarCliente;

    const auth = await requestAuthorization();

    if (!auth) {await error404(); return;}

    await fetch("user/delete/client",{
        method:"DELETE",
        headers:{
            "Accept":"application/json",
            "Content-type":"application/json",
            "Authorization":auth
        },
        body:idCliente
    });

    window.location.reload();
})

const editarCliente = (cliente)=>{
    document.getElementById("nombre").value = cliente.nombre;
    document.getElementById("direccion").value = cliente.direccion;
    document.getElementById("telefono").value = cliente.telefono;
    document.getElementById("editar").dataset.editarCiente = `${JSON.stringify(cliente)}`;
}

document.getElementById("editar").addEventListener("click",async ()=>{
    const auth =await requestAuthorization();

    if (!auth){ await error404(); return;}

    const cliente = JSON.parse(document.getElementById("editar").dataset.editarCiente);
    const nombre = document.getElementById("nombre").value;
    const telefono = document.getElementById("telefono").value;
    const direccion = document.getElementById("direccion").value;

    cliente.nombre = nombre;
    cliente.telefono = telefono;
    cliente.direccion = direccion;

    await fetch("user/edit/client",{
        method:"POST",
        headers:{
            "Accept":"application/json",
            "Content-type":"application/json",
            "Authorization":auth
        },
        body:JSON.stringify(cliente)
    })

    window.location.reload();
})

window.editarCliente = editarCliente;
window.eliminarCliente = eliminarCliente;