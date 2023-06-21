import {requestAuthorization,inicio,deleteCookies,error404} from "./export.js";

inicio();

const traerVentas = async() =>{
    const auth =await requestAuthorization();

    if (!auth){ await error404(); return;}

    let request = await fetch("sales/get",{
        method:"GET",
        headers:{
            "Authorization":auth,
            "Accept":"application/json",
            "Content-type":"application/json"
        }
    });


    let ventas = await request.json();

    let html="";

    let totalCantidades = 0;
    let totalPagos = 0;

    ventas.forEach(venta =>{
        let totalCantidadCliente = 0;

        venta.products.forEach(producto =>{
            totalCantidadCliente += producto.cantidad;
        });

        html=`<tr>
        <td>${venta.nombreCliente}</td>
        <td>${totalCantidadCliente}</td>
        <td>${venta.total}</td>
        <td>Método de pago</td>
        </tr>`;

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

    document.querySelector("#dataTable tbody").insertAdjacentHTML("beforeend",html);
}

window.addEventListener("DOMContentLoaded", traerVentas);

