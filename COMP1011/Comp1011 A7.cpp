#include <bits/stdc++.h>
using namespace std;

void rotate(char *charArray, int *sizeOfArray){
	for(int i = 0; i < *sizeOfArray; i++){
		for(int j = 0; j < *sizeOfArray-1; j++){
			int temp = charArray[j];
			charArray[j] = charArray[j+1];
			charArray[j+1] = temp;
		}
		cout << charArray << "\n";
	}
}


int main(){
	char str[105];
	cin >> str;
	int size = strlen(str);
	rotate(str, &size);
}
