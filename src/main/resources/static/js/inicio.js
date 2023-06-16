import {requestAuthorization,inicio,deleteCookies} from "./export.js";

inicio();

document.querySelector("#cerrarSesion").addEventListener("click",()=>{
    deleteCookies();
});