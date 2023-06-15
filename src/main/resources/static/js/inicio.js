let listElements = document.querySelectorAll('.list_button--click');

listElements.forEach(listElement => {
    listElement.addEventListener('click', ()=>{
        
        listElement.classList.toggle('arrow');

        let height = 0;
        let menu = listElement.nextElementSibling;

        console.log(menu.scrollHeight)

        if(menu.clientHeight == 0){
            height=menu.scrollHeight;
        }

        menu.style.height = height+"px";
    })    
});

document.getElementById("cerrar_sesion").addEventListener("click",()=>{
    document.cookie = `user=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/`;
    document.cookie = `token=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/`;
});