const traerClientes = async()=>{
    let request = await fetch("/user/get/clients", {
        method:"GET"
    });


    let clientes = await request.json();

    let html = "";

    clientes.forEach(cliente => {
    
        html+=` <tr>
        <td>${cliente.id}</td>
        <td>${cliente.nombre}</td>
        <td>${cliente.direccion}</td>
        <td>${cliente.telefono}</td>
        <td>Acciones</td>
    </tr>`;
    });
    
    document.querySelector("#dataTable tbody").innerHTML = html;
}

window.addEventListener("DOMContentLoaded", traerClientes);
