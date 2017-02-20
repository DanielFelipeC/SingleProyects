function hexa() {

    var tile = $('.tile'),
            speed = 500;

    setInterval(function () {

        tile.attr('class', 'tile');

        //Get Random position from hexagons
        var tileRm = Math.floor(Math.random() * tile.length);
        tile.eq(tileRm).attr('class', 'tile active');

        //console
        console.log(tile.eq(tileRm));
    }, speed);
}
hexa();