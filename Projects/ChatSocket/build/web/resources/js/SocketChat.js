(function (window, document, JSON) {
    'use strict';

    var url = "ws://" + window.location.host + '/ChatSocket/chat',
            ws = new WebSocket(url),
            mensajes = document.getElementById('mesage-s'),
            boton = document.getElementById('enviar'),
            nombre = document.getElementById('usuario'),
            message = document.getElementById('mensaje'),
            messageContent = document.getElementById('mensaje').value;

    ws.onopen = onOpen;
    ws.onclose = onClose;
    ws.onmessage = onMessage;
    boton.addEventListener('click', enviar);

    $('#ContenedorChat').bind('keydown', function (e) {
        if (e.keyCode === 13) {
            enviar();
        }
    });


    function onOpen() {
        console.log('Conectado...');
    }

    function onClose() {
        console.log('Desconectado...');
    }

    function enviar() {
        var msg = {
            nombre: nombre.value,
            mensaje: message.value
        };
        ws.send(JSON.stringify(msg));
    }

//    function validar(e) {
//        tecla = (document.all) ? e.keyCode : e.which;
//        if (tecla == 13)
//            onMessage();
//    }

    function onMessage(evt) {

        function codificarEntidad(str) {
            var array = [];
            for (var i = str.length - 1; i >= 0; i--) {
                array.unshift(['&#', str[i].charCodeAt(), ';'].join(''));
            }
            return array.join('');
        }

        var fecha = new Date();

        var horas;
        var minutos;
        var dia = fecha.getDay();
        var mes = fecha.getMonth();

        if (dia < 10) {
            dia = '0' + dia;
        }
        if (mes < 10) {
            mes = '0' + mes;
        }


        var anio = dia + '/' + mes + '/' + fecha.getFullYear();

        if (fecha.getMinutes() < 10) {
            minutos = '0' + fecha.getMinutes();
        } else {
            minutos = fecha.getMinutes();
        }
        if (fecha.getHours() < 10) {
            horas = '0' + fecha.getHours();
        } else {
            horas = fecha.getHours();
        }

        var obj = JSON.parse(evt.data),
//                msg = 'Usuario: ' + obj.nombre + ' dice: ' + codificarEntidad(obj.mensaje) + ' Enviado: '+ anio+ ' - ' + horas + ':' + minutos;
                msg = codificarEntidad(obj.mensaje);

        var msgRecibido = 'float:left;padding:0;padding-left:10px;background:#fff;display:inline-block;padding:13px;width:274px;border-radius:2px;box-shadow: 0 1px 1px rgba(0,0,0,.04);position:relative;border-right:6px solid transparent;border-left:6px solid #fff;border-top: 6px solid transparent;border-bottom:6px solid transparent;';
        var msgEnviado = 'float:right;padding:0;padding-right:10px;background:#fff;display:inline-block;padding:13px;width:274px;border-radius:2px;box-shadow: 0 1px 1px rgba(0,0,0,.04);position:relative;border-right:6px solid transparent;border-left:6px solid #fff;border-top: 6px solid transparent;border-bottom:6px solid transparent;';
        // document.write('<div id=\'o\' style=' + msgRecibido + '>' + msg + '/>');
        var ds = document.getElementById("user").value;
        mensajes.innerHTML += '<div style=' + msgEnviado + '>' + ds + ' dice: ' + msg + '</div>';

        $("#mensaje").each(function () {
            $($(this)).val('');
        });



    }

})(window, document, JSON);
