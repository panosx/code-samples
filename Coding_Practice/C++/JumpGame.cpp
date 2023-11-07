/*
Problem:
You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].

Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:

0 <= j <= nums[i] and
i + j < n
Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [2,3,0,1,4]
Output: 2
 

Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 1000
It's guaranteed that you can reach nums[n - 1].

Solution Author: Panos Xenakis
Problem Sourced from: leetcode.com
*/

class Solution {
public:
    int jump(vector<int>& nums) {
        int num_jumps = 0, n = nums.size(), current_index = 0, range = nums[0];

        if(n==1){
            return 0;
        }

        if(range >= n-1){
            return 1;
        }

        int biggest, biggest_index;

        while(range < n-1){
            biggest = range;
            for(int i = current_index+1; i <= range; i++){
                if(nums[i]+i > biggest){
                    biggest = nums[i]+i;
                    biggest_index = i;
                }
            }
            num_jumps++;
            range = biggest;
            current_index = biggest_index;
            if(range>=n-1){
                num_jumps++;
            }
        }
        
        return num_jumps;
    }
};