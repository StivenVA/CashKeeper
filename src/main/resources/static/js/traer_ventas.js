import {requestAuthorization,inicio,deleteCookies,error404} from "./export.js";

inicio();

const traerVentas = async()=>{
    const auth = await requestAuthorization();

    if(!auth){ await error404(); return;}

    let request = await fetch("sales/get", {
        method: "GET",
        headers:{
            "Accept": "application/json",
            "Content-type": "application/json",
            "Authorization": auth
        }
    });

    let ventas = await request.json();

    let card="";
    let i = 1;

    ventas.forEach(venta => {
        card =`<div class="tarjeta">
        <div class="card-left-column background-left-column">
            <div class="tarjeta2">
                <li class="check-list-item"><i class="fa fa-id-card"></i>${venta.id_factura}</li>
                <li class="check-list-item"><i class="fa fa-user"></i>${venta.nombreCliente}</li>
                <li class="check-list-item"><i class="fa fa-calendar"></i>${venta.fecha}</li>
                <li class="check-list-item"><i class="fa fa-clock" aria-hidden="true"></i>${venta.hora}</li>
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
                                        <th>precio venta</th>
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

    
    venta.products.forEach(producto=>{
        html=`<tr>
        <td>${producto.descripcion}</td>
        <td>${producto.cantidad}</td>
        <td>${producto.precio}</td>
    </tr>`

    document.querySelector(`#proveedor${i} tbody`).insertAdjacentHTML("beforeend", html);
    });

    document.querySelector(`#total${i} .price-integer`).insertAdjacentHTML("beforeend",venta.total);
    i++;
    });
   

}

window.addEventListener("DOMContentLoaded", traerVentas);