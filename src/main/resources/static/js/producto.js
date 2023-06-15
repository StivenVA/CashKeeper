const traerClientes = async()=>{
    let request = await fetch("products/get", {
        method:"GET"
    });


    let productos = await request.json();

    let html = "";

    productos.forEach(producto => {
    
        html+=` <tr>
        <td>${producto.id}</td>
        <td>${producto.descripcion}</td>
        <td>${producto.precio}</td>
        <td><a href="#"><img src="img/icons8-editar.svg" class="acciones"></a>
        <a href="#"><img src="img/icons8-basura-llena.svg" class="acciones"></a></td>
    </tr>`;
    });
    
    document.querySelector("#dataTable tbody").innerHTML = html;
}

window.addEventListener("DOMContentLoaded", traerClientes);