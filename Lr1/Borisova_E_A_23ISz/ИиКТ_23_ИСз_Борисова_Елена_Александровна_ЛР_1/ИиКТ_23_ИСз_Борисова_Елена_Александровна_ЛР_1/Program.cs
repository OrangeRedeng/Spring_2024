using System;

namespace ИиКТ_23_ИСз_Борисова_Елена_Александровна_ЛР_1
{
    class Program
    {
        static void Main(string[] args)
        {
            //Ввод строки
            Console.WriteLine("Введите двоичное число: ");
            string str = Console.ReadLine();
            //Проверка - введено ли двоичное число.
            bool prov = CheckingTheBinaryNumber(str);
            //Если введено двоичное, то переводим в десятичное
            if (prov == true)
            {
                // sum - итоговое десятичное число, n - разряд цифры числа; степень, в которую нужно будет возводить единицы
                int sum = 0, n = 0;
                for (int i = str.Length - 1; i > -1; i--) // i = str.Length-1, тк индекс последнего символа на 1 меньше длины строки; i > -1, тк символ с индексом 0 тоже нужно проверить
                {
                    if (str[i] == '1') //Берем только 1, тк при умножении на 0 будет 0
                    {
                        sum += (int)Math.Pow(2, n);
                    }
                    n++;
                }
                //Выводим результат
                Console.WriteLine(str + " = " + sum);
            }
            //Если введено не двоичное, то выводим сообщение
            else
            {
                Console.WriteLine("Введено не двоичное число!");
            }
        }

        static bool CheckingTheBinaryNumber(string str) //Проверка - введено ли двоичное число
        {
            for (int i = 0; i < str.Length; i++) //str.Lenght - определение длины строки
            {
                if (str[i] != '1')
                {
                    if (str[i] != '0')
                    {
                        //Если символ не равен ни 1, ни 0, то тогда введено не двоичное число
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
