#include<iostream>
using namespace std;



int main()
{
    setlocale(LC_ALL, "ru");

    int n;
    cout << "¬ведите любое целое число = ";
    cin >> n;

    while (n != 0)
    {
        if (n >= 1000) 
        { 
            n = n - 1000; cout << "M";
        }
        else 
        {
            if (n >= 900)
            {
                n = n - 900;   cout << "MC";
            }
            else if (n >= 500) { n = n - 500; cout << "D"; }
            else if (n >= 400) { n = n - 400; cout << "CD"; }
            else if (n >= 100) { n = n - 100; cout << "C"; }
            else if (n >= 90) { n = n - 90; cout << "XC"; }
            else if (n >= 50) { n = n - 50; cout << "L"; }
            else if (n >= 10) { n = n - 10; cout << "X"; }
            else if (n >= 9) { n = n - 9; cout << "IX"; }
            else if (n >= 5) { n = n - 5; cout << "V"; }
            else if (n >= 4) { n = n - 4; cout << "IV"; }
            else if (n >= 1) { n = n - 1; cout << "I"; }
        }
    }
}