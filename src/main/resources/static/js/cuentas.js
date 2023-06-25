import {requestAuthorization,inicio,deleteCookies,error404} from "./export.js";

inicio();

document.getElementById("cerrarSesion").addEventListener("click", ()=>{
    deleteCookies();
    window.location= "index.html";
})

const traerVentas = async() =>{
    const auth =await requestAuthorization();

    let html="";

    let totalCantidades = 0;
    let totalPagos = 0;
    let totalContado =0;
    let totalCredito = 0;
    let gananciaDia = 0;
    let totalPedidos = 0;

    if (!auth){ await error404(); return;}

    let request = await fetch("sales/get",{
        method:"GET",
        headers:{
            "Authorization":auth,
            "Accept":"application/json",
            "Content-type":"application/json"
        }
    });

    let pedidosRequest = await fetch("order/get",{
        headers:{
            "Authorization":auth,
            "Accept":"application/json",
            "Content-type":"application/json"
        }
    })

    let pedidos = await pedidosRequest.json();

    pedidos.forEach(pedido=>{
        totalPedidos+=pedido.total;
    })


    let ventas = await request.json();


    ventas.forEach(venta =>{
        let totalCantidadCliente = 0;

        venta.productos.forEach(producto =>{
            totalCantidadCliente += producto.cantidad;
        });

        html=`<tr>
        <td>${venta.nombreCliente}</td>
        <td>${totalCantidadCliente}</td>
        <td>${venta.total}</td>
        <td>${venta.metodo}</td>
        </tr>`;
        if (venta.metodo==="contado"){
            totalContado+=venta.total;
        }
        if (venta.metodo==="crédito"){
            totalCredito += venta.total;
        }

        totalCantidades += totalCantidadCliente;

        totalPagos += venta.total;
        document.querySelector("#dataTable tbody").insertAdjacentHTML("beforeend",html);
    });

    document.getElementById("fecha").insertAdjacentHTML("beforeend",ventas[0].fecha);
    html=`<tr>
        <td>Total</td>
        <td>${totalCantidades}</td>
        <td>${totalPagos}</td>
        </tr>`;
    gananciaDia= totalPagos-totalPedidos;
    document.getElementById("gananciaProducto").innerText = Math.ceil(gananciaDia/totalCantidades);
    document.getElementById("pedidos").innerText = totalPedidos;
    document.getElementById("gananciaDia").innerText = gananciaDia;
    document.querySelector("#dataTable tbody").insertAdjacentHTML("beforeend",html);
    document.getElementById("contado").innerText = totalContado;
    document.getElementById("credito").innerText = totalCredito;
}

window.addEventListener("DOMContentLoaded", traerVentas);

