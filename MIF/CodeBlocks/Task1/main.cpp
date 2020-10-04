#include <iostream>
#include <string>
#include <algorithm>
#include <bits/stdc++.h>

using namespace std;

int my_max(string s){
    int max1 = 0;
    for(int i = 0; i < s.length(); i++){
        max1 = max(max1, s[i] - '0');
    }
    return max1;
}

int myf_max(string s){
    int max1 = 0;
    int pos = 0;
    for(int i = 0; i < s.length(); i++){
        if(s[i] - '0' > max1){
            pos = i;
        }
        max1 = max(max1, s[i] - '0');
    }
    return pos;
}

int main()
{
    string s;
    cin>> s;
    int i = stoi(s);
    int j = i / pow(10, s.length()/2);
    int answer = my_max(to_string(j));
    s.substr(myf_max(to_string(j)));
    for(int q = 0; q < s.length()/2 - 1; q++){
        answer *= 10;
        answer += my_max(s);
        s.subs
    }
    return 0;
}
