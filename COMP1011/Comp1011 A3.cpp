#include <bits/stdc++.h>
using namespace std;

int main(){
	int arr[1005];
	cout << "Enter a sequence of integer (-999 to finish):";
	int n = 0;
	while(1){
		cin >> arr[n];
		if(arr[n] == -999) break;
		n++;
	}
	int sum = 0;
	for(int i = 0;i < n; i++){
		if(i % 2 == 0){
			sum += arr[i];
		}
		else{
			sum -= arr[i];
		}
	}
	cout << sum;
}
