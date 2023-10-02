$(document).ready(function() {

});

async function iniciarSesion(){
      let datos = {};
      datos.email = document.getElementById('email').value;
      datos.password = document.getElementById('password').value;

      const request = await fetch('http://localhost:8080/api/login', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });

      const response = await request.text();
       if(response != "Fail"){
        localStorage.token = response;
        localStorage.email = datos.email;
        window.location.href= "usuarios.html";
       }else{
        alert("Contraseña incorrecta");
       }
}