#include <bits/stdc++.h>
using namespace std;

bool CheckValid(char str[]){
	for(int i = 0; str[i] != '\0'; i++){
		if(isalpha(str[i]) == 0) return 0;
		if(str[i] == ' ') return 0;
	}
	return 1;
}

void sorting(char str[][55], int n){
	char temp[55];
	for(int i = 0; i < n; i++){
		for(int j = 0; j < n-i-1; j++){
			if (strcmp(str[j], str[j+1]) > 0) {
                strcpy(temp, str[j]);
                strcpy(str[j], str[j+1]);
                strcpy(str[j+1], temp);
			}
		}
	}
}

int main(){
	char name[205][55];
	int n = 0;
	cout << "Enter student names and input END to finish the input:\n";
	while(1){
		cin.getline(name[n], 55);
		if(strcmp(name[n], "END") == 0){
			break;
		}
		if(CheckValid(name[n]) == 0){
			cout << "Wrong input: please input only upper-case and low-case letters with no space in betwen\n";
			continue;
		}
		n++;
	}
	for(int i = 0; i < n; i++){
		for(int j = 0; j < strlen(name[i]); j++){
			if(name[i][j] >= 97 && name[i][j] <= 122){
				name[i][j] -= 32;
			}
		}
	}
	sorting(name, n);
	for(int i = 0; i < n; i++){
		for(int j = 0; j < strlen(name[i]); j++){
			cout << name[i][j];
		}
		cout << "\n";
	}
} 
