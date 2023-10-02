$(document).ready(function() {
  cargarUsuarios();
  $('#usuarios').DataTable();
  actualizarEmailUsuario();
});

function actualizarEmailUsuario(){
    document.getElementById("txtEmail").innerHTML = localStorage.email;
}

async function cargarUsuarios(){
      const request = await fetch('http://localhost:8080/api/usuario', {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization': localStorage.token
        },
        //body: JSON.stringify({a: 1, b: 'Textual content'})
      });

      const usuarios = await request.json();
       let data = '';


       for(let usuario of usuarios){
            let botonEliminar = '<a href="#" class="btn btn-danger btn-circle btn-sm" onclick="eliminarUsuario('+usuario.id+')">'+
                                      '<i class="fas fa-trash"></i>'+
                                   '</a>';

            let telefonoTexto = usuario.telefono == null ? '-': usuario.telefono;

            var template = ' <tr>' +
                           '<td>'+usuario.id+'</td>'+
                           '<td>'+usuario.nombre + ' ' + usuario.apellido+'</td>'+
                           '<td>'+usuario.email+'</td>'+
                           '<td>'+ telefonoTexto +'</td>'+
                           '<td>'+
                                botonEliminar +
                           '</td>'+
                       '</tr>';
            data += template;
       }

      document.querySelector('#usuarios tbody').innerHTML = data;
}

async function eliminarUsuario(id){

    if(confirm('¿Desea eliminar el usuario?')){
        const request = await fetch('http://localhost:8080/api/usuario/'+id, {
            method: 'DELETE',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json',
              'Authorization': localStorage.token
            },
        });
        location.reload();
    }else{
        return;
    }
}