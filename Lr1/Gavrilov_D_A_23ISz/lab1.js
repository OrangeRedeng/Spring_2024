const str = "Привет Node js";
// Вводим строчное значение
const count = str.indexOf(":");
//находим индекс двоеточия
if (count !== -1) {
    console.log('Количество знаков, предшествующих двоеточию: '+ count);
} else {
    console.log('Двоеточие не найдено в строке.');
}


