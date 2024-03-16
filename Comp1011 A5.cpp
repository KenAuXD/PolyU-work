#include <bits/stdc++.h>
using namespace std;

int total(int arr[], int n){
	int total = 0;
	for(int i = 0; i < n; i++) total += arr[i];
	return total;
}

int largest(int arr[], int n){
	int max = arr[0];
	for(int i = 1; i < n; i++){
		if(max < arr[i]) max = arr[i];
	}
	return max;
}

int smallest(int arr[], int n){
	int min = arr[0];
	for(int i = 1; i < n; i++){
		if(min > arr[i]) min = arr[i];
	}
	return min;
}

float average(int arr[], int n){
	float sum = total(arr, n);
	return sum/n;
}
int main(){
	int arr[1005];
	cout << "Enter a sequence of integer (-999 to finish):";
	int n = 0;
	while(1){
		cin >> arr[n];
		if(arr[n] == -999) break;
		n++;
	}
	cout << "Largest Number is " << largest(arr, n) << "\n";
    cout << "Smallest Number is " << smallest(arr, n) << "\n";
    cout << "Total is " << total(arr, n) << "\n";
    cout << "Average is " << fixed << setprecision(3) << average(arr, n);
}

