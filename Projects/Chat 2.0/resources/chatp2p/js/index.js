toggleFab();

//define chat color
if (typeof (Storage) !== "undefined") {
	if (localStorage.getItem('fab-color') === null) {
		localStorage.setItem("fab-color", "blue");
	}
	$('.fabs').addClass(localStorage.getItem("fab-color"));
} else {
	$('.fabs').addClass("blue");
}

//Fab click
$('#prime').click(function () {
	toggleFab();
});

//Speak admin msg
function botSpeak(text) {
	if ('speechSynthesis' in window) {
		var msg = new SpeechSynthesisUtterance(text);
		window.speechSynthesis.speak(msg);
	}
}

//Toggle chat and links
function toggleFab() {
	$('.prime').toggleClass('zmdi-plus');
	$('.prime').toggleClass('zmdi-close');
	$('.prime').toggleClass('is-active');
	$('#prime').toggleClass('is-float');
	$('.chat').toggleClass('is-visible');
	$('.fab').toggleClass('is-visible');

}

//User msg
function userSend(text) {
	debugger;
	var img = '<i class="zmdi zmdi-account"></i>';

	var sppliter = text.search("http");
	var sppliter2 = text.search("https");
	var sppliter3 = text.search("www.");

	if (sppliter !== -1 || sppliter2 !== -1 || sppliter3 !== -1 ) {
		text = "<A HREF='https://" + text + "' TARGET='_new'>" + text + "</A>";
	}

	switch (text) {
	case "(y)":
		text = "<i class='em em---1'></i>";
		break;
	case "(Y)":
		text = "<i class='em em---1'></i>";
		break;
	case "Google":
		text = "<A HREF='http://www.google.com' TARGET='_new'>Visita Google</A>";
		break;
	}

	var nombreUser = '<span style="float:left;color:white;font-size:13px;font-weight:bold">Daniel</span> <br/>';
	$('#chat_converse').append('<div class="chat_msg_item chat_msg_item_user"><div class="chat_avatar">' + img + '</div>' + nombreUser + text + '</div>');
	$('#chatSend').val('');
	if ($('.chat_converse').height() >= 500) {
		$('.chat_converse').addClass('is-max');
	}
	$('.chat_converse').scrollTop($('.chat_converse')[0].scrollHeight);
}

//Admin msg
function adminSend(text) {
	var nombreUser = '<span style="float:left;color:black;font-size:13px;font-weight:bold">Administrador</span> <br/>';
	$('#chat_converse').append('<div class="chat_msg_item chat_msg_item_admin"><div class="chat_avatar"><i class="zmdi zmdi-accounts"></i></div>' + nombreUser + text + '</div>');
	if ($('.chat_converse').height() >= 256) {
		$('.chat_converse').addClass('is-max');
	}
	$('.chat_converse').scrollTop($('.chat_converse')[0].scrollHeight);
}

//Send input using enter and send key
$('#chatSend').bind("enterChat", function (e) {
	userSend($('#chatSend').val());
});
$('#fab_send').bind("enterChat", function (e) {
	userSend($('#chatSend').val());
});
$('#chatSend').keypress(function (event) {
	if (event.keyCode === 13) {
		event.preventDefault();
		if (jQuery.trim($('#chatSend').val()) !== '') {
			$(this).trigger("enterChat");
		}
	}
});

$('#fab_send').click(function (e) {
	if (jQuery.trim($('#chatSend').val()) !== '') {
		$(this).trigger("enterChat");
	}
});

//Listen user voice
$('#fab_listen').click(function () {
	var recognition = new webkitSpeechRecognition();
	recognition.onresult = function (event) {
		userSend(event.results[0][0].transcript);
	}
	recognition.start();
});

// Color options
$(".chat_color").click(function (e) {
	$('.fabs').removeClass(localStorage.getItem("fab-color"));
	$('.fabs').addClass($(this).attr('color'));
	localStorage.setItem("fab-color", $(this).attr('color'));
});

$(".em").click(function (e) {
	var clase = $(this).attr('class');
	userSend("<i class='" + clase + "'></i>");


});

$('.chat_option').click(function (e) {
	$(this).toggleClass('is-dropped');
});



// Ripple effect
var target, ink, d, x, y;
$(".fab").click(function (e) {
	target = $(this);
	//create .ink element if it doesn't exist
	if (target.find(".ink").length == 0)
		target.prepend("<span class='ink'></span>");

	ink = target.find(".ink");
	//incase of quick double clicks stop the previous animation
	ink.removeClass("animate");

	//set size of .ink
	if (!ink.height() && !ink.width()) {
		//use parent's width or height whichever is larger for the diameter to make a circle which can cover the entire element.
		d = Math.max(target.outerWidth(), target.outerHeight());
		ink.css({
			height: d,
			width: d
		});
	}

	//logic = click coordinates relative to page - parent's position relative to page - half of self height/width to make it controllable from the center;
	x = e.pageX - target.offset().left - ink.width() / 2;
	y = e.pageY - target.offset().top - ink.height() / 2;

	//set the position and add class .animate
	ink.css({
		top: y + 'px',
		left: x + 'px'
	}).addClass("animate");
});

//Cookies handler
function createCookie(name, value, days) {
	var expires;

	if (days) {
		var date = new Date();
		date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
		expires = "; expires=" + date.toGMTString();
	} else {
		expires = "";
	}
	document.cookie = encodeURIComponent(name) + "=" + encodeURIComponent(value) + expires + "; path=/";
}

function readCookie(name) {
	var nameEQ = encodeURIComponent(name) + "=";
	var ca = document.cookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) === ' ') c = c.substring(1, c.length);
		if (c.indexOf(nameEQ) === 0) return decodeURIComponent(c.substring(nameEQ.length, c.length));
	}
	return null;
}

function eraseCookie(name) {
	createCookie(name, "", -1);
}


function hideChat(hide) {
	if (hide) {
		$('.chat_converse').css('display', 'none');
		$('.fab_field').css('display', 'none');
	} else {
		$('#chat_head').html(readCookie('fab_chat_username'));
		// Help
		$('.chat_login').css('display', 'none');
		$('.chat_converse').css('display', 'block');
		$('.fab_field').css('display', 'inline-block');
	}
}



//------------------------Element Draggable