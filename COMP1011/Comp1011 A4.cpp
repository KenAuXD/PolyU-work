#include <bits/stdc++.h>
using namespace std;

void mySort(int arr[1005], int n){
	sort(arr, arr+n); 
	//I don't know if the input must be sort in ascending order so I use this function to ensure the input is sort in ascending order
	for(int i = 0;i < n; i++) if(arr[i] % 2 == 1) cout << arr[i] << " "; 
	for(int i = n;i >= 0; i--) if(arr[i] % 2 == 0) cout << arr[i] << " ";
}

int main(){
	int arr[1005];
	int n = 0;
	cout << "Enter a sequence of integer (-999 to finish):";
	while(1){
		cin >> arr[n];
		if(arr[n] == -999){
			break;
		}
		n++;
	}
	mySort(arr, n);
}