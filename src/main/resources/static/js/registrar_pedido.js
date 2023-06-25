import {error404,requestAuthorization,deleteCookies,inicio} from "./export.js";

inicio();

const productos = [];
let total = 0;

document.getElementById("cerrarSesion").addEventListener("click",()=>{
    deleteCookies();
    window.location = "index.html";
});

const mostrarProveedores = async ()=>{

    const auth = await requestAuthorization();

    if (!auth){ error404(); return;}

    const request = await fetch("supplier/get",{
        headers:{
            "Accept":"application/json",
            "Content-type":"application/json",
            "Authorization":auth
        }
    });

    const proveedores = await request.json();

    const select=document.getElementById("proveedor");

    proveedores.forEach(proveedor=>{
        const opcion = document.createElement("option");

        opcion.value = proveedor.id_proveedor;
        opcion.text = proveedor.nombre;
        select.appendChild(opcion);
    })
}

document.addEventListener("DOMContentLoaded",()=>{
   mostrarProveedores();
   mostrarProductos();
});

const mostrarProductos = async ()=>{

    const auth = await requestAuthorization();

    if(!auth) { error404(); return; }

    const request = await fetch("products/get",{
        headers:{
            "Accept":"application/json",
            "Content-type":"application/json",
            "Authorization":auth
        }
    });

    const productos = await request.json();
    const select = document.getElementById("productosSelect");

    productos.forEach(producto=>{
        const opcion = document.createElement("option");

        opcion.value = producto.id;
        opcion.text = producto.descripcion;
        select.appendChild(opcion);
    });
}

const actualizarTabla = ()=>{
    const cantidad = document.getElementById("cantidad");
    const proveedor = document.getElementById("proveedor");
    const idProducto = document.getElementById("productosSelect");
    const precio = document.getElementById("precio");


    const producto ={};
    producto.cantidad = cantidad.value;
    producto.id_producto = idProducto.value;
    producto.precio = precio.value;

    productos.push(producto);

    let html = `<tr>
                            <td>${proveedor.options[proveedor.selectedIndex].text}</td>
                            <td>${idProducto.options[idProducto.selectedIndex].text}</td>
                            <td>${cantidad.value}</td>
                            <td>${precio.value}</td>
                            <td>${precio.value*cantidad.value}</td>
                       </tr>`;
    total += precio.value*cantidad.value;
    document.querySelector("#informacion tbody").insertAdjacentHTML("beforeend",html);

    document.getElementById("total").innerText = total;

    cantidad.value ="";
    idProducto.selectedIndex = 0;
    precio.value ="";
    proveedor.disabled = true;
};

document.getElementById("button").addEventListener("click",(e)=>{
    e.preventDefault();
    actualizarTabla();
});

const realizarPedido=async ()=>{

    const auth = await requestAuthorization();

    if (!auth){ error404(); return;}

    const proveedor = document.getElementById("proveedor");
    const fecha = new Date();
    let horas = fecha.getHours();
    let minutos = fecha.getMinutes();
    let segundos = fecha.getSeconds();

// Asegurar que los valores de horas, minutos y segundos tengan dos dígitos
    horas = horas < 10 ? `0${horas}` : horas;
    minutos = minutos < 10 ? `0${minutos}` : minutos;
    segundos = segundos < 10 ? `0${segundos}` : segundos;

    let horaFormato24 = `${horas}:${minutos}:${segundos}`;


    const pedido = {
        idProveedor:proveedor.value,
        fecha:fecha.toISOString().slice(0, 10),
        hora:horaFormato24,
        total,
        productos
    }

    const request=await fetch("order/add",{
        method:"POST",
        headers:{
            "Accept":"application/json",
            "Content-type":"application/json",
            "Authorization":auth
        },
        body:JSON.stringify(pedido)
    })
    window.location.reload();
}

document.getElementById("registrar").addEventListener("click",realizarPedido);

document.getElementById("cantidad").addEventListener("input",(e)=>{
    let value = e.target.value;

    if (value <= 0) {
        e.target.value = "";
    }
});

document.getElementById("precio").addEventListener("input",(e)=>{
    let value = e.target.value;

    if (value <= 0) {
        e.target.value = "";
    }
});