export let getCookie = (name)=>{
    const cookies = document.cookie.split(";");

    for (let  i= 0; i < cookies.length ; i++) {
      const cookie = cookies[i].split('=');
      const cookieName = decodeURIComponent(cookie[0]);
      if(cookieName.trim()===name){
        const cookieValue = decodeURIComponent(cookie[1]);

        return JSON.parse(cookieValue);
      }
    }
    return null;
  }