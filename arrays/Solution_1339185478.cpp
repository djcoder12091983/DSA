bool comp1(int,int);
class Solution {
public:
    string largestNumber(vector<int>& nums) {
        sort(nums.begin(),nums.end(),comp1);
        if(nums[0]==0){
            return "0";
        }
        string ans = "";
        for(int i=0;i<nums.size();i++){
            string temp = to_string(nums[i]);
            ans += temp;
        }
        return ans;
    }
};
bool comp1(int x,int y){
    string first = to_string(x);
    string second = to_string(y);
    string xy = first + second;
    string yx = second + first;
    if(xy>yx){
        return true;
    }
    else{
        return false;
    }
}