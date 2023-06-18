export const getCookie = (name)=>{
    const cookies = document.cookie.split(";");

    for (let  i= 0; i < cookies.length ; i++) {
      const cookie = cookies[i].split('=');
      const cookieName = decodeURIComponent(cookie[0]);
      if(cookieName.trim()===name){
        const cookieValue = decodeURIComponent(cookie[1]);
        return JSON.parse(cookieValue);
      }
    }
    return null;
}

export const requestAuthorization = async ()=>{

    if (getCookie("user")===null) return;

    let cookie = getCookie("user");

    let requestToken = await fetch("auth/token",{
        method:"POST",
        headers:{
            "Accept":"application/json",
            "Content-type":"application/json",
            "Authorization":cookie.token
        }
    })
    return await requestToken.json();
}

export const inicio = ()=>{
    if (getCookie("user")!==null) document.querySelector("#userDropdown span").innerHTML = getCookie("user").nombre;
};

export const deleteCookies = ()=>{
    document.cookie = `user=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/`;
}

export const error404 = async ()=>{
    const request = await fetch("../404.html");

    const response = await request.text();

    document.querySelector(".container-fluid").innerHTML = response;
}
