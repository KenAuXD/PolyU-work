#include <bits/stdc++.h>
using namespace std;


int main(){
	system("Color 0A");
	int n;
	double num1, num2;
	cout << "MENU\n";
	cout << "\t1. Divide, a/b\n";
	cout << "\t2. Multiply, a*b\n";
	cout << "\t3. Power, a^b\n";
	cout << "\t4. Square root, sqrt(a)\n";
	cout << "Enter your choice:";
	cin >> n;
	switch (n){
	case 1:
		cout << "Enter two number:";
		cin >> num1 >> num2;
		printf("%.f/%.f=%.3f", num1, num2, num1 / num2);
		break;
	case 2:
		cout << "Enter two number:";
		cin >> num1 >> num2;
		printf("%.f*%.f=%.3f", num1, num2, num1 * num2);
		break;
	case 3:
		cout << "Enter two number:";
		cin >> num1 >> num2;
		printf("%.f^%.f=%.3f", num1, num2, pow(num1, num2));
		break;
	case 4:
		cout << "Enter a number:";
		cin >> num1;
		printf("sqrt(%.f)=%.3f", num1, sqrt(num1));
		break;
	}
}

