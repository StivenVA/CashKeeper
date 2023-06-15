import { getCookie } from "./export.js"


const agregarCliente = async()=>{

    let cliente ={   };

    cliente.id = document.getElementById("id").value;
    cliente.nombre = document.getElementById("nombre").value;
    cliente.direccion = document.getElementById("direccion").value;
    cliente.telefono = document.getElementById("telefono").value;

    let token = getCookie("token");

    let request = await fetch("auth/token",{
        method: "POST",
        headers: {
            "Accept":"application/json",
            "Content-type": "application/json",
            "Authorization": JSON.stringify(token)
        }
    });

    let response = request.json();
    
    let addcliente = await fetch("user/add/clients",{
        method: "POST",
        headers: {
            "Accept":"application/json",
            "Content-type": "application/json",
            "Authorization": response
        },
        body: JSON.stringify(cliente)

    });

}

document.getElementById("agregar").addEventListener("click",(e)=>{
    e.preventDefault();

    agregarCliente();
});