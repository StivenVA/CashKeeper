import {error404,requestAuthorization,deleteCookies,inicio} from "./export.js";

inicio();

const productos = [];
let total = 0;

document.getElementById("cerrarSesion").addEventListener("click",()=>{
    deleteCookies();
    window.location = "index.html";
});

const mostrarClientes = async ()=>{

    const auth = await requestAuthorization();

    if (!auth){ await error404(); return;}

    const request = await fetch("user/get/clients",{
        headers:{
            "Accept":"application/json",
            "Content-type":"application/json",
            "Authorization":auth
        }
    });

    const clientes = await request.json();

    const select=document.getElementById("clientes");

    clientes.forEach(cliente=>{
        const opcion = document.createElement("option");

        opcion.value = cliente.id;
        opcion.text = cliente.nombre;
        select.appendChild(opcion);
    })
}

document.addEventListener("DOMContentLoaded",()=>{
    mostrarClientes();
    mostrarProductos();
});

const mostrarProductos = async ()=>{

    const auth = await requestAuthorization();

    if(!auth) { await error404(); return; }

    const request = await fetch("products/get",{
        headers:{
            "Accept":"application/json",
            "Content-type":"application/json",
            "Authorization":auth
        }
    });

    const productos = await request.json();
    const select = document.getElementById("productos");

    productos.forEach(producto=>{
        const opcion = document.createElement("option");

        opcion.value = producto.id;
        opcion.text = producto.descripcion;
        select.appendChild(opcion);
    });
}

const actualizarTabla = ()=>{
    const cantidad = document.getElementById("cantidad");
    const clientes = document.getElementById("clientes");
    const idProducto = document.getElementById("productos");
    const precio = document.getElementById("precio");
    const metodoPago = document.getElementById("pago");


    const producto ={};
    producto.cantidad = cantidad.value;
    producto.id_producto = idProducto.value;
    producto.precio = precio.value;

    productos.push(producto);

    let html = `
                        <tr>
                             <th>${clientes.options[clientes.selectedIndex].text}</th>
                            <th>${idProducto.options[idProducto.selectedIndex].text}</th>
                            <th>${metodoPago.options[metodoPago.selectedIndex].text}</th>
                            <th>${cantidad.value}</th>
                            <th>${precio.value}</th>
                            <th>${cantidad.value*precio.value}</th>
                        </tr>`;
    total += precio.value*cantidad.value;
    document.querySelector("#informacion tbody").insertAdjacentHTML("beforeend",html);

    document.getElementById("total").innerText = total;

    cantidad.value ="";
    idProducto.selectedIndex = 0;
    precio.value ="";
    metodoPago.disabled = true;
    clientes.disabled = true;
};

document.getElementById("button").addEventListener("click",(e)=>{
    e.preventDefault();
    actualizarTabla();
});

const realizarVenta=async ()=>{

    const auth = await requestAuthorization();

    if (!auth){ await error404(); return;}

    const cliente = document.getElementById("clientes");
    const metodoPago = document.getElementById("pago");
    const fecha = new Date();
    let horas = fecha.getHours();
    let minutos = fecha.getMinutes();
    let segundos = fecha.getSeconds();

// Asegurar que los valores de horas, minutos y segundos tengan dos dígitos
    horas = horas < 10 ? `0${horas}` : horas;
    minutos = minutos < 10 ? `0${minutos}` : minutos;
    segundos = segundos < 10 ? `0${segundos}` : segundos;

    let horaFormato24 = `${horas}:${minutos}:${segundos}`;


    const factura = {
        id_cliente:cliente.value,
        fecha:fecha.toISOString().slice(0, 8)+fecha.getDate(),
        hora:horaFormato24,
        total,
        productos,
        metodo:metodoPago.value
    }

    await fetch("sales/add",{
        method:"POST",
        headers:{
            "Accept":"application/json",
            "Content-type":"application/json",
            "Authorization":auth
        },
        body:JSON.stringify(factura)
    })
    alert("venta registrada");
    window.location.reload();
}

document.getElementById("registrar").addEventListener("click",realizarVenta);

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