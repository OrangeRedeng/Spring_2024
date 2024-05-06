using System;
using System.Linq;

namespace Lab1
{
    class Program
    {
        //Константа для разделения вывода в консоли
        const string separateConsole = "~~~~~~~~~~~~~~~~~~~~~~~~\n";

        static void Main(string[] args)
        {
            //Вывод информации о работе
            Console.WriteLine("Вариант 27. Дана строка. Заменить гласные буквы шифром(a-123 о-999 => Точка -> Т999чк123).\nВыполнил: Скворцов Андрей 23 - ВМз");
            Console.Write(separateConsole);
            //Создание переменных для шифрования и вызов функции для создания шифров
            string[] symArr = new string[] { "а", "е", "ё", "и", "о", "у", "ы", "э", "ю", "я" };
            string[] codeArr = getCodes(symArr);
            Console.Write(separateConsole);
            //Ввод строки для шифрования
            Console.Write("Введите строку: ");
            string str = Console.ReadLine();
            //Вызов функции для шифрования и запись ответа в переменную
            str = changeStr(str, codeArr, symArr);
            Console.Write(separateConsole);
            Console.WriteLine("Итоговая строка: " + str); //Вывод ответа
            Console.ReadKey(); //Выход из программы при нажатии любой клавиши
        }

        //Фунцкия для ввода шифра для каждой гласной буквы
        static string[] getCodes(string[] symArr) {
            string[] codeArr = new string[symArr.Length];
            for (int i = 0; i < symArr.Length; i++)
            {
                Console.Write("Введите для символа " + symArr[i] + " шифр: ");
                codeArr[i] = Console.ReadLine();
            }
            return codeArr;
        }

        //Функция для шифрования строки
        static string changeStr(string str, string[] codeArr, string[] symArr) {
            string returnStr = "";
            for (var i = 0; i < str.Length; i++) {
                if (symArr.Contains(Convert.ToString(str[i]).ToLower()))
                {
                    returnStr += codeArr[Array.IndexOf(symArr, Convert.ToString(str[i]))];
                } else
                {
                    returnStr += str[i];
                }
            }
            return returnStr;
        }
    }
}
