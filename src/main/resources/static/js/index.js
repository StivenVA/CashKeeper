             $(function() {
               $("form[name='login']").validate({
                 rules: {
                   
                   email: {
                     required: true,
                     email: true
                   },
                   password: {
                     required: true,
                     
                   }
                 },
                  messages: {
                   email: "Please enter a valid email address",
                  
                   password: {
                     required: "Please enter password",
                    
                   }
                   
                 },
                 submitHandler: function(form) {
                   form.submit();
                 }
               });
             });
             

    let login = async ()=>{
        let datos={}

        datos.email = document.getElementById("email").value;
        datos.password = document.getElementById("password").value;

        let request = await fetch("auth/log",{
            method:"POST",
            headers:{
                "Accept":"application/json",
                "Content-type":"application/json",
            },
            body:JSON.stringify(datos)
        })

        let response = await request.json();

        if (response.error) { alert(response.error); return; }
        let cookie = {};

        cookie.nombre = response.user.nombre;
        cookie.id = response.user.id;
        cookie.rol = response.user.rol;
        cookie.token = response.token;

        document.cookie = `user=${encodeURIComponent(JSON.stringify(cookie))};path=/`;

        window.location = 'inicio.html';
    }



    document.getElementById("iniciarSesion").addEventListener("click",(e)=>{
        e.preventDefault();
        login();
    })