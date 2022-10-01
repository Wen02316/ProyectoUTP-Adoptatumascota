var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });

    getUsuario().then(function () {

        $("#mi-perfil-btn").attr("href", "profile.html?username=" + username);

        getMascotas(false, "ASC");

        $("#ordenar-genero").click(ordenarMascotas);
    });
});

async function getUsuario() {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioPedir",
        data: $.param({
            username: username
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                user = parsedResult;
            } else {
                console.log("Error recuperando los datos del usuario");
            }
        }
    });
}

function getMascotas(ordenar, orden) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletMascotaListar",
        data: $.param({
            ordenar: ordenar,
            orden: orden
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                mostrarMascotas(parsedResult);
            } else {
                console.log("Error recuperando los datos de las mascotas");
            }
        }
    });
}
function mostrarMascotas(mascotas) {

    let contenido = "";

    $.each(mascotas, function (index, mascota) {

        mascota = JSON.parse(mascota);
        
            contenido += '<tr><th scope="row">' + mascota.id + '</th>' +
                    '<td>' + mascota.nombremascota + '</td>' +
                    '<td>' + mascota.genero + '</td>' +
                    '<td>' + mascota.ciudadmascota + '</td>' +
                    '<td>' + mascota.raza + '</td>' +
                    '<td>' + mascota.edad + '</td>' +
                    '<td><button " class="btn btn-success"> Ver </button><button " class="btn btn-success">Adoptar</button></td></tr>'
    });
    $("#mascotas-tbody").html(contenido);

}   

function ordenarMascotas() {

    if ($("#icono-ordenar").hasClass("fa-sort")) {
        getMascotas(true, "ASC");
        $("#icono-ordenar").removeClass("fa-sort");
        $("#icono-ordenar").addClass("fa-sort-down");
    } else if ($("#icono-ordenar").hasClass("fa-sort-down")) {
        getMascotas(true, "DESC");
        $("#icono-ordenar").removeClass("fa-sort-down");
        $("#icono-ordenar").addClass("fa-sort-up");
    } else if ($("#icono-ordenar").hasClass("fa-sort-up")) {
        getMascotas(false, "ASC");
        $("#icono-ordenar").removeClass("fa-sort-up");
        $("#icono-ordenar").addClass("fa-sort");
    }
}