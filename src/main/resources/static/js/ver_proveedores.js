import {requestAuthorization,inicio,deleteCookies,error404} from "./export.js";

inicio();

const traerProveedores = async()=>{
    const auth = await requestAuthorization();

    if (!auth){ await error404(); return;}

    let request = await fetch("supplier/get", {
        method:"GET", 
        headers:{
            "Accept":"application/json",
            "Content-type":"application/json",
            "Authorization":auth
        }
    });

    let proveedores = await request.json();

    let html="";

    proveedores.forEach(proveedor => {
        html=`<tr>
        <td>${proveedor.id_proveedor}</td>
        <td>${proveedor.nombre}</td>
        <td>${proveedor.email}</td>
        <td>${proveedor.telefono}</td>
        <td><a href="#" data-toggle="modal" data-target="#editarCliente">
        <img alt="editar" src="img/icons8-editar.svg" class="acciones"></a>
    <a href="#"  data-toggle="modal" data-target="#eliminarCliente">
            <img src="img/icons8-basura-llena.svg" class="acciones" alt="Eliminar">
        </a></td>
    </tr>`;

    document.querySelector("#dataTable tbody").insertAdjacentHTML("beforeend",html);
    });
}

window.addEventListener("DOMContentLoaded", ()=>{
    traerProveedores();
})