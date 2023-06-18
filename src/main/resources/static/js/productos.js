import {requestAuthorization,inicio,deleteCookies,error404} from "./export.js";

inicio();

document.querySelector("#cerrarSesion").addEventListener("click",()=>{
    deleteCookies();
    window.location = "index.html";
});

let traerProductos = async ()=>{

    let authorization =await requestAuthorization();

    if (!authorization) { error404(); return;}

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
            <td><a href="#" onclick='pasarPrecioProducto(${JSON.stringify(producto)})' data-toggle="modal" data-target="#editarProducto"><img src="img/icons8-editar.svg" class="acciones" alt="Editar"></a>
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

    if (!auth) { error404(); return;}

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

const pasarPrecioProducto = (producto)=>{
    document.getElementById("precio").value = producto.precio;
    document.getElementById("actualizar").dataset.producto = `${JSON.stringify(producto)}`;
};

document.getElementById("actualizar").addEventListener("click",async ()=>{
    const precioEditado = document.getElementById("precio").value;
    let producto = JSON.parse(document.getElementById("actualizar").dataset.producto);

    producto.precio = precioEditado;

    let auth = await requestAuthorization();

    if (!auth) { error404(); return;}

    const request = await fetch("products/edit",{
        method:"POST",
        headers:{
            "Accept":"application/json",
            "Content-type":"application/json",
            "Authorization":auth
        },
        body:JSON.stringify(producto)
    })

    const response = await request.text();

    if (response!=="OK") alert(response);
    window.location.reload();
});

document.getElementById("agregarProducto").addEventListener("click",async ()=>{

    let producto = {};

    producto.precio = document.getElementById("precioNuevoProducto").value;
    producto.id = document.getElementById("idNuevoProducto").value;
    producto.descripcion = document.getElementById("descripcioNuevoProducto").value;
    producto.estado = 1;

    const auth = await requestAuthorization();

    if (!auth){ error404(); return;}

    const request = await fetch("products/add",{
        method:"POST",
        headers:{
            "Accept":"application/json",
            "Content-type":"application/json",
            "Authorization":auth
        },
        body:JSON.stringify(producto)
    })

    const response = await request.text();

    if (response!=="OK"){alert(response); return;}
    window.location.reload();
})

window.pasarPrecioProducto = pasarPrecioProducto;

window.eliminarProducto = eliminarProducto;

