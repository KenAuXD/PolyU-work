#include <bits/stdc++.h>
using namespace std;


int main(){
	system("Color 0A");
	int order, branch;
	char letter;
	int k;
	cout << "order(1) or reverse order (2):";
	cin >> order;
	cout << "please input the branch size: ";
	cin >> branch; 
	if(order == 1){
		letter = 'A';
		k = 1;
	}
	if(order == 2){
		letter = 'Z';
		k = -1;
	}
	// Branch
	for(int i = 0; i < branch; i++){
		for(int j = 0; j < branch-i-1; j++){
			cout << " ";
		}
		for(int j = 0; j < 2*i+1; j++){
			cout << letter;
			letter += k;
			if(order == 1 && letter > 'Z') letter = 'A'; 
			if(order == 2 && letter < 'A') letter = 'Z';
		}
		cout << "\n";
	}
	//Trunk
	for(int i = 0; i < branch/2; i++){
		for(int j = 0; j < branch-2; j++){
			cout << " ";
		}
		cout << letter << " " << letter << "\n";
		letter += k;
		if(order == 1 && letter > 'Z') letter = 'A'; 
		if(order == 2 && letter < 'A') letter = 'Z'; 
	}
}
