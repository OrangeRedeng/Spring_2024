#include <iostream>
#include <string>
#include <cstring>
#include <stdio.h>
#include <stdlib.h>
using namespace std;

static bool isvowels(char vowels[], char symbol)
{
    bool result = false;
    for (int j = 0; j < sizeof(vowels); j++)
    {
        if (symbol == vowels[j])// проверка на наличие гласных букв
            result = true;
    }
    return result;
}
int main()
{
    setlocale(LC_ALL, "ru");
    string str;
    int i = 0; // счетчик символов в строке
    int k = 0; // счетчик гласных букв
    bool notIsAlpha = false;
    const int N = 12;
    char vowels[N] = { 'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 'U', 'u', 'Y', 'y' };// массив из латинских гласных букв

    std:cout << "Введите текст: " << endl;
    getline(cin, str);


    while (str[i] && !notIsAlpha)  // пока не конец строки 
    {
        if (isalpha(str[i]))// если текущий символ строки латинская буква
        {
            if (isvowels(vowels, str[i]))// вызов функции, определяющей гласные буквы
            {
                k++;//счетчик количества гласных
            }

        } 
        else 
        {
            notIsAlpha = true;
        }
        i++; // инкремент счётчика

    } // конец while
     
    if (notIsAlpha)
    {
        std::cout << "Текст должен содержать только латинские буквы \n";
    }
    else if (k)
    {
        cout << "Количество гласных букв: " << k << endl;
    }
    else
    {
        cout << "Гласных букв не найдено!" << endl;
    }


    return 0;
}

