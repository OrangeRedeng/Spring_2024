var canvas = document.getElementById("myCanvas");
var context = canvas.getContext("2d");
document.onload = drawPic(context);
function drawPic(context) {
    var pic = new Image();
    pic.onload = function () {
      context.drawImage(pic, 0, 0, 400, 400);
      context.beginPath();
    context.moveTo(0, 0);
    context.lineTo(400, 0);
    context.lineTo(400, 400);
    context.lineTo(0, 400);
    context.lineTo(0, 0);
    context.strokeStyle = "#631c2a";
    context.stroke();
    };
    pic.src = "media/images/3.jpg";
  }