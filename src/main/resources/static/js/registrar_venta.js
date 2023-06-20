let tabla = document.getElementById("informacion");
let fila = 0, columna = 0;
let opcion = false;
let cliente = false;

function agregar() {
    let cliente = document.getElementById("clientes").value;
    let producto = document.getElementById("productos").value;
    let precio = document.getElementById("precio").value;
    let cantidad = document.getElementById("cantidad").value;

    if (verificar(producto)) {
        let x = tabla.rows[fila].cells;
        let x2 = parseInt(x[2].innerHTML)+parseInt(cantidad)
        x[2].innerHTML = x2;
        x[3].innerHTML = parseInt(precio)*x2;
        
        resultado();
        opcion = false;
    }else{
        var n = tabla.rows.length;
        var row = tabla.insertRow(n);
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);

        cell1.innerHTML = cliente;
        cell2.innerHTML = producto;
        cell3.innerHTML = precio;
        cell4.innerHTML = cantidad;
        cell5.innerHTML = precio*cantidad;

        resultado();

        alert("¡producto agregado con éxito!")
    }
}


function verificar(p) {
    for (let i = 0; i < tabla.rows.length; i++) {
        if (tabla.rows[i].cells[0].innerHTML==p) {
            opcion = true;
            fila = i;
            columna = 0;
        }  
    }

    return opcion;
}

function resultado() {
    var suma = 0;
    for (var i = 1; i < tabla.rows.length; i++) {
        suma = suma + parseInt(tabla.rows[i].cells[3].innerHTML);
    }

    document.getElementById("total").value = suma;
}