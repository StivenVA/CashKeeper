import {requestAuthorization,inicio,deleteCookies,error404} from "./export.js";

inicio();

const traerInventario = async()=>{
    const auth = await requestAuthorization();

    if(!auth){ await error404(); return;}

    let request = await fetch("inventory/get",{
        method:"GET",
        headers:{
            "Accept": "application/json",
            "Content-type": "application/json",
            "Authorization": auth
        }
    });

    let inventarios = await request.json();

    let congelador1="";
    let congelador2="";
    let congelador3="";

    inventarios.forEach(inventario => {

        if (inventario.congelador===1) {
            congelador1 = `<tr>
            <td>${inventario.id_producto}</td>
            <td>${inventario.fecha}</td>
            <td>${inventario.descripcion}</td>
            <td>${inventario.cantidad}</td>
    </tr>`;    
    document.querySelector("#congelador1 tbody").insertAdjacentHTML("beforeend",congelador1);
        }
        
        if (inventario.congelador===2) {
            congelador2 = `<tr>
            <td>${inventario.id_producto}</td>
            <td>${inventario.fecha}</td>
            <td>${inventario.descripcion}</td>
            <td>${inventario.cantidad}</td>
    </tr>`;
    document.querySelector("#congelador2 tbody").insertAdjacentHTML("beforeend",congelador2);
        }

        if (inventario.congelador===3) {
            congelador3 = `<tr>
        <td>${inventario.id_producto}</td>
        <td>${inventario.fecha}</td>
        <td>${inventario.descripcion}</td>
        <td>${inventario.cantidad}</td>
    </tr>`;
    document.querySelector("#congelador3 tbody").insertAdjacentHTML("beforeend",congelador3);
        }

    });
}

window.addEventListener("DOMContentLoaded", ()=>{

    traerInventario();
});

document.querySelector("#cerrarSesion").addEventListener("click",()=>{
    deleteCookies();
    window.location = "index.html";
});

const traerPedidos = async()=>{
    const auth = await requestAuthorization();

    if(!auth){ await error404(); return;}

    let request = await fetch("order/get", {
        method: "GET",
        headers:{
            "Accept": "application/json",
            "Content-type": "application/json",
            "Authorization": auth
        }
    });

    let pedidos = await request.json();

    let card="";
    let i = 1;


    pedidos.forEach(pedido => {
        card =`<div class="tarjeta">
        <div class="card-left-column background-left-column">
            <div class="tarjeta2">
                <li class="check-list-item"><i class="fa fa-id-card"></i>${pedido.idProveedor}</li>
                <li class="check-list-item"><i class="fa fa-user"></i>${pedido.nombreProveedor}</li>
                <li class="check-list-item"><i class="fa fa-calendar"></i>${pedido.fecha}</li>
                <li class="check-list-item"><i class="fa fa-clock" aria-hidden="true"></i>${pedido.hora}</li>
            </div>
        </div>

        <div class="card-right-column">
            <div class="tabla" id="proveedor${i}">
                <div class="card shadow mb-4">
                    <div class="card-body-pedido">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="congelador3" width="100%" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>Producto</th>
                                        <th>cantidad</th>
                                        <th>precio compra</th>
                                    </tr>
                                </thead>
                        
                                <tbody>
                                
                                </tbody>
                            </table>

                            <div id="total${i}" class="price-value" data-currency="$ USD" data-currency-simple="USD">
                                    <div id="priceDiscountCent">Total: &nbsp;</div>
                                    <p class="price-number">&nbsp; $<span class="price-integer"></span></p>
                                </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>`;

    document.querySelector(`.tarjeta-contenedor`).insertAdjacentHTML("beforeend",card);

   
    let html="";

    
    pedido.productos.forEach(producto=>{
        html=`<tr>
    <td>${producto.descripcion}</td>
    <td>${producto.cantidad}</td>
    <td>${producto.precio}</td>
    </tr>`

    document.querySelector(`#proveedor${i} tbody`).insertAdjacentHTML("beforeend", html);
    });
    document.querySelector(`#total${i} .price-integer`).insertAdjacentHTML("beforeend",pedido.total);
    i++;
    });


}

window.addEventListener("DOMContentLoaded", traerPedidos);

const traerTotalInventario = async() =>{
    const auth = await requestAuthorization();

    if(!auth){ await error404(); return;}

    console.log(auth);

    let request = await fetch("inventory/total",{
        method:"GET",
        headers:{
            "Accept": "application/json",
            "Content-type": "application/json",
            "Authorization": auth
        }
    });

    let response = await request.json();
    let html="";

    if (response.error!==null) {
        alert(response.error);return;
    }

    response.totalInventory.forEach(totalInventario => {
        
        html=`<tr>
        <td>${totalInventario.fecha}</td>
        <td>${totalInventario.descripcion}</td>
        <td>${totalInventario.cantidad}</td>
    </tr>`;

    document.querySelector("#dataTable tbody").insertAdjacentHTML("beforeend",html);
        
    });
}

window.addEventListener("DOMContentLoaded", ()=>{
    traerTotalInventario();
});