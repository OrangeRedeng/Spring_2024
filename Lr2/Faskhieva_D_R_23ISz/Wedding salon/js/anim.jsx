function Timer(funUser, delayMillisecond) {
	this.interval_millisecond = delayMillisecond; // РРЅС‚РµСЂРІР°Р» РІСЂРµРјРµРЅРё РІ РјРёР»Р»РёСЃРµРєСѓРЅРґР°С…
	this.idFunction = 0;		   				  // РРґРµРЅС‚РёС„РёРєР°С‚РѕСЂ СЃРѕР·РґР°РЅРЅРѕР№ С„СѓРЅРєС†РёСЏ РІС‹Р·С‹РІР°РµРјРѕР№ С‡РµСЂРµР· РѕРїСЂРґ.РїСЂРѕРјРµР¶СѓС‚РѕРє РІСЂРµРјРµРЅРё
	this.userFunction = funUser;				  // РџРѕР»СЊР·РѕРІР°С‚РµР»СЊСЃРєР°СЏ С„СѓРЅРєС†РёСЏ
	// РђРєС‚РёРІР°С†РёСЏ С‚Р°Р№РјРµСЂР°
	this.start = function() {
		this.idFunction = setInterval(this.userFunction, this.interval_millisecond);
	}
	// Р”РµР°РєС‚РёРІР°С†РёСЏ С‚Р°Р№РјРµСЂР°
	this.stop = function() {
		// Р¤СѓРЅРєС†РёСЏ clearInterval РґР»СЏ СЃРЅСЏС‚РёСЏ СЃ РѕР±СЂР°Р±РѕС‚РєРё РІС‹Р·РѕРІР° 
		clearInterval(this.idFunction);
	}
	this.setDelayFunction = function(funUser) {
		this.userFunction = funUser;
	}
	this.setIntervalMillisecond = function(millisecond) {
		this.interval_millisecond = millisecond;
	}
}

var count = 30; // РљРѕР»РёС‡РµСЃС‚РІРѕ РѕР±СЉРµРєС‚РѕРІ РІРёРґРёРјС‹С… РЅР° СЃС†РµРЅРµ

var arrayImages = []; // РњР°СЃСЃРёРІ РѕР±СЉРµРєС‚РѕРІ IMG

var timerAnim1 = new Timer(animation_scene, 40);

var arrayPoints = []; // РњР°СЃСЃРёРІ РєРѕРѕСЂРґРёРЅР°С‚ РґР»СЏ РєР°Р¶РґРѕРіРѕ РіСЂР°С„РёС‡РµСЃРєРѕРіРѕ РѕР±СЉРµРєС‚Р° РЅР° СЃС†РµРЅРµ
var ffa = [];
var ffb = [];

// РљР»Р°СЃСЃ РўРѕС‡РєР°
function Point(x, y) {
	this.x = x;
	this.y = y;
}

// Р¤СѓРЅРєС†РёСЏ СЃРѕР·РґР°РµС‚ РјРЅРѕР¶РµСЃС‚РІРѕ РіСЂР°С„РёС‡РµСЃРєРёС… СЌР»РµРјРµРЅС‚РѕРІ РґР»СЏ СЃС†РµРЅС‹
// СЃРѕС…СЂР°РЅСЏРµС‚ РёС… РІ РјР°СЃСЃРёРІ РґР»СЏ РґР°Р»СЊРЅРµР№С€РµР№ СЂР°Р±РѕС‚С‹
function initScene() { 

	// Р”РѕР±Р°РІРёС‚СЊ РІ С‚РµРі BODY РІСЃРµ СЌР»РµРјРµРЅС‚С‹ 
	for(var j = 0; j < count; j ++) {
		// РЎРѕР·РґР°С‚СЊ РЅРѕРІС‹Р№ РѕСЉРµРєС‚-СЃРµСЂРґС†Рµ
		var newObject = document.createElement("IMG");
		// РЈСЃС‚Р°РЅРѕРІРєР° СЃРѕР±СЃС‚РІРµРЅРЅС‹С… РїР°СЂР°РјРµС‚СЂРѕРІ
		newObject.width = "26"; // РЁРёСЂРёРЅР° Р±РёС‚РѕРІРѕР№ РєР°СЂС‚С‹
		newObject.height= "25"; // Р’С‹СЃРѕС‚Р° Р±РёС‚РѕРІРѕР№ РєР°СЂС‚С‹

		newObject.src   = "http://master-akadem.ru/public_test_comp/scripts/heartsred/images/heartr.png";     // РЎСЃС‹Р»РєР° РЅР° РіСЂР°С„РёС‡РµСЃРєРёР№ С„Р°Р№Р» http://101widgets.com/jswidgets/img/ball_3.png
		newObject.style.zIndex = "100" + j;
		// РЈСЃС‚Р°РЅРѕРІРєР° СЃС‚РёР»РµРІС‹С… СЃРІРѕР№СЃС‚РІ
		newObject.style.position = "absolute"; // РђР±СЃРѕР»СЋС‚РЅРѕРµ РїРѕР·РёС†РёРѕРЅРёСЂРѕРІР°РЅРёРµ 
		// Р“РµРЅРµСЂР°С†РёСЏ РїСЃРµРІРґРѕСЃР»СѓС‡Р°Р№РЅС‹С… С‡РёСЃРµР» РєРѕРѕСЂРґРёРЅР°С‚
		var randX = parseFloat(((Math.random() * (document.documentElement.scrollWidth)).toFixed(0))) - 50; 
		var randY = parseFloat(randomNumber(-document.documentElement.scrollHeight, 0));

		arrayPoints[j] = new Point(3, 3); // РЎРѕС…СЂР°РЅРёС‚СЊ РєРѕРѕСЂРґРёРЅР°С‚С‹ РІ РѕР±СЉРµРєС‚

		arrayPoints[j].x = parseFloat(((Math.random() * (2)).toFixed(5)));

		arrayPoints[j].y = randomNumber(25, 44) / 10;

		var sa = randomNumber(12, 33) / 355;

		ffa[j] = sa == 0 ? randomNumber(12, 100) / 255 : sa;

		ffb[j] = 0;

		newObject.style.left = randX + "px"; // РљРѕРѕСЂРґРёРЅР°С‚Р° РїРѕ РѕСЃРё X
		newObject.style.top  = randY + "px";  // РљРѕРѕСЂРґРёРЅР°С‚Р° РїРѕ РѕСЃРё Y

		arrayImages[j] = newObject; // РЎРѕС…СЂР°РЅРёС‚СЊ РІ РјР°СЃСЃРёРІ
	}
}
function addToBody() {
	// Р”РѕР±Р°РІРёС‚СЊ РІ С‚РµРі BODY РІСЃРµ СЌР»РµРјРµРЅС‚С‹ 
	for(var j = 0; j < arrayImages.length; j ++) {
		document.body.appendChild(arrayImages[j]);
	}
}
var ff=0;


function randomNumber (m,n)
{
  m = parseInt(m);
  n = parseInt(n);
  return Math.floor( Math.random() * (n - m + 1) ) + m;
}

function animation_scene() { 

	for(var j = 0; j < arrayImages.length; j ++) {

		// РџРµСЂРµСЃС‡РёС‚Р°С‚СЊ РєРѕРѕСЂРґРёРЅР°С‚С‹
		arrayImages[j].style.top  = (parseFloat(arrayImages[j].style.top) + arrayPoints[j].y) + "px";
		// РћС‚РѕР±СЂР°Р·РёС‚СЊ РѕР±СЉРµРєС‚С‹
		arrayImages[j].style.left = (parseFloat(arrayImages[j].style.left)) + (0.323 + 1.7 * Math.sin(66.5 * 12 + ffb[j])) + "px";
		// РџСЂРѕРІРµСЂРєР° РЅР° СЃС‚РѕР»РєРЅРѕРІРµРЅРёРµ СЃ РіСЂР°РЅРёС†Р°РјРё РІРёРґРёРјРѕСЃС‚Рё
		// Р•СЃР»Рё РїСЂРѕРёР·РѕС€Р»Рѕ СЃС‚РѕР»РєРЅРѕРІРµРЅРёРµ РўРѕ СЃРєСЂС‹РІР°РµС‚СЃСЏ РѕР±СЉРµРєС‚ Рё РµРјСѓ РїРµСЂРµРЅР°Р·РЅР°С‡Р°СЋС‚СЃСЏ РєРѕРѕСЂРґРёРЅР°С‚С‹ РѕС‚РѕР±СЂР°Р¶РµРЅРёСЏ
		// Рё РїРѕСЃР»Рµ РѕРЅ РЅР°С‡РёРЅР°РµС‚СЃСЏ РїР°РґР°С‚СЊ СЃРІРµСЂС…Сѓ РєР°Рє РЅРѕРІС‹Р№ РѕР±СЉРµРєС‚
		var yClientFieldTop    = window.pageYOffset || document.documentElement.scrollTop; // РїР°СЂР°РјРµС‚СЂ РїСЂРѕРєСЂСѓС‚РєРё РґРѕ РІРёРґРёРјРѕР№ РѕР±Р»Р°СЃС‚Рё
		var yClientFieldBottom = document.documentElement.clientHeight + (window.pageYOffset || document.documentElement.scrollTop) - 50; // РїР°СЂР°РјРµС‚СЂ РІС‹СЃРѕС‚Р° РєР»РёРµРЅС‚СЃРєРѕР№ РѕР±Р»Р°СЃС‚Рё

		if((arrayImages[j].offsetTop + arrayImages[j].offsetHeight) >= (document.documentElement.scrollHeight) ||
			(arrayImages[j].offsetLeft + arrayImages[j].offsetWidth) >= document.documentElement.scrollWidth - 50) {
			// РЎРєСЂС‹С‚СЊ РѕР±СЉРµРєС‚
			arrayImages[j].style.visibility = "hidden";
			// Р“РµРЅРµСЂР°С†РёСЏ РїСЃРµРІРґРѕСЃР»СѓС‡Р°Р№РЅС‹С… С‡РёСЃРµР» РєРѕРѕСЂРґРёРЅР°С‚
			var randX = Math.sin(parseFloat(((Math.random() * (document.documentElement.clientWidth - 1)).toFixed(0))) - 50) * 1000; 
			var randY = parseFloat(randomNumber(-document.documentElement.scrollHeight, 0));
			arrayImages[j].style.left = randX + "px";  // РљРѕРѕСЂРґРёРЅР°С‚Р° РїРѕ РѕСЃРё X
			arrayImages[j].style.top  = randY + "px";  // РљРѕРѕСЂРґРёРЅР°С‚Р° РїРѕ РѕСЃРё Y

			arrayImages[j].src = "http://master-akadem.ru/public_test_comp/scripts/heartsred/images/heartr.png";
			arrayImages[j].style.visibility = "visible";
		}
	}
	ff += 0.1131;
	for(var j = 0; j < ffb.length; j++) {
		ffb[j] += ffa[j];
	}
}

initScene();
// Р”РѕР±Р°РІРёС‚СЊ РЅР° СЃС†РµРЅСѓ
addToBody();
// РЎС‚Р°СЂС‚ С‚Р°Р№РјРµСЂР°
timerAnim1.start();