
$(function() {

    $("form[name='registration']").validate({
        rules: {
            firstname: "required",
            lastname: "required",
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 5
            },
            identity:{
                required:true,
                number:true
            }
        },

        messages: {
            firstname: "Por favor ingresa tu primer nombre",
            lastname: "Por favor ingresa tu apellido",
            password: {
                required: "Por favor proporciona una contraseña",
                minlength: "La longitud de la contraseña debe de ser al menos 5 caracteres"
            },
            email: "Por favor ingresa una cuenta de correo electrónico",
            identity:"Por favor ingrese su numero de identificacion"
        }
    });
});

let signup = async ()=>{

    let datos = {}

    datos.id = document.getElementById("Identidad").value;
    datos.nombre = document.getElementById("Nombres").value +' '+document.getElementById("Apellidos").value;
    datos.email = document.getElementById("Email").value;
    datos.password = document.getElementById("Password").value
    datos.rol = '2';

    let request = await fetch("user/signup",{
        method:"POST",
        headers:{
            "Accept":"application/json",
            "Content-type":"application/json"
        },
        body:JSON.stringify(datos)
    });

    let response = await request.text();

    //Cambia este alert por un modal o algo mas llamativo
    alert(response);
    window.location = "index.html";
    console.log(response);
}

document.getElementById("crear").addEventListener("click",(e)=>{
    signup();
    e.preventDefault();
});

