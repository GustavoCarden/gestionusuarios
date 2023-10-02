$(document).ready(function() {

});

async function registrarUsuarios(){
      let datos = {};
      datos.nombre = document.getElementById('nombre').value;
      datos.apellido = document.getElementById('apellido').value;
      datos.email = document.getElementById('email').value;
      datos.password = document.getElementById('password').value;

      const request = await fetch('http://localhost:8080/api/usuario', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });

      alert("La cuenta fue creada con exito");
      window.location.href = "login.html";
}