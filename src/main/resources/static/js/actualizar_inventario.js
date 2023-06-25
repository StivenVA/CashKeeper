import {error404, requestAuthorization, inicio, deleteCookies} from "./export.js";

inicio();

const select = document.getElementById("producto");

document.querySelector("#cerrarSesion").addEventListener("click",()=>{
    deleteCookies();
    window.location = "index.html";
});

const selectProductos =async ()=>{
    const auth = await requestAuthorization();

    if (!auth){ error404(); return; }

    const request = await fetch("products/get",{
        headers:{
            "Accept":"application/json",
            "Content-type":"application/json",
            "Authorization":auth
        }
    });

    const productos = await request.json();

    productos.forEach(producto=>{
       const opcion = document.createElement("option");
       opcion.value = producto.id;
       opcion.text = producto.descripcion;
       select.appendChild(opcion);
    });
}

document.addEventListener("DOMContentLoaded",selectProductos);

document.getElementById("cantidad").addEventListener("input",(e)=>{
    let value = e.target.value;

    if (value <= 0) {
        e.target.value = "";
    }
});

let actualizarInventario =async ()=>{

    const auth = await requestAuthorization();

    if (!auth){ error404(); return; }

    const cantidad = document.getElementById("cantidad").value;
    const producto = document.getElementById("producto").value;
    const congelador = document.getElementById("congelador").value;
    const currentDate = new Date();

    const inventario = {
        fecha:currentDate.toISOString().slice(0, 8)+currentDate.getDate(),
        id_producto:producto,
        descripcion:null,
        congelador:congelador,
        cantidad:cantidad
    };

    console.log(inventario);
    const request = await fetch("inventory/update",{
        method:"POST",
        headers:{
            "Accept":"application/json",
            "Content-type":"application/json",
            "Authorization":auth
        },
        body:JSON.stringify(inventario)
    });

    alert("Inventario actualizado");
    window.location.reload();
};

document.getElementById("actualizar").addEventListener("click",(e)=>{
    e.preventDefault();
    actualizarInventario();
});