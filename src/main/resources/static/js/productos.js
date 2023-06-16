import {requestAuthorization,inicio,deleteCookies} from "./export.js";

inicio();

document.querySelector("#cerrarSesion").addEventListener("click",()=>{
    deleteCookies();
});

let traerProductos = async ()=>{

    let authorization =await requestAuthorization();

    let request = await fetch('products/get',{
        method:"GET",
        headers:{
            "Accept":"application/json",
            "Content-type":"application/json",
            "Authorization":authorization
        }
    });

    let html='';

    let productos = await request.json();

    productos.forEach(producto => {
        if (producto.estado===1) {
            html += ` <tr>
            <td>${producto.id}</td>
            <td>${producto.descripcion}</td>
            <td>${producto.precio}</td>
            <td><a href="#" data-toggle="modal" data-target="#editarProducto"><img src="img/icons8-editar.svg" class="acciones" alt="Editar"></a>
            <a href="#" onclick="eliminarProducto(${producto.id})" data-toggle="modal" data-target="#eliminarProducto"><img src="img/icons8-basura-llena.svg" class="acciones" alt="Eliminar"></a></td>
            </tr>`;
        }
    });

    document.querySelector("#dataTable tbody").innerHTML = html;
}

window.addEventListener("DOMContentLoaded", traerProductos);


const eliminarProducto = (idProducto) =>{
    document.getElementById("eliminar").value = idProducto;
}

document.getElementById("eliminar").addEventListener("click",async ()=>{

    const auth = await requestAuthorization();

    let idProducto = document.getElementById("eliminar").value;

    const request = await fetch("products/delete",{
        method:"DELETE",
        headers:{
            "Accept":"application/json",
            "Content-type":"application/json",
            "Authorization":auth
        },
        body:idProducto
    })

    const response =await request.json();

    if (!response){
        alert("Inicie sesion nuevamente"); return;
    }

    alert("Producto eliminado exitosamente");
    window.location.reload();
});



window.eliminarProducto = eliminarProducto;

