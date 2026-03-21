class Solution {
public:
    string largestTimeFromDigits(vector<int>& A) {
        int choose[][4] = {
            {0, 1, 2, 3},
            {0, 2, 1, 3},
            {0, 3, 1, 2},
            {1, 2, 0, 3},
            {1, 3, 0, 2},
            {2, 3, 0, 1}
        };
        
        int hours = -1, minutes = -1;
        for(int i = 0; i < 6; i++) {
            int max_minutes = -1;
            int t_minute = 10 * A[choose[i][2]] + A[choose[i][3]];
            if(t_minute < 60 && t_minute > max_minutes) {
                max_minutes = t_minute;
            }
            t_minute = 10 * A[choose[i][3]] + A[choose[i][2]];
            if(t_minute < 60 && t_minute > max_minutes) {
                max_minutes = t_minute;
            }
            int t_hour = 10 * A[choose[i][0]] + A[choose[i][1]];
            if(t_hour < 24 && t_hour > hours && max_minutes != -1) {
                hours = t_hour;
                minutes = max_minutes;
            }
            t_hour = 10 * A[choose[i][1]] + A[choose[i][0]];
            if(t_hour < 24 && t_hour > hours && max_minutes != -1) {
                hours = t_hour;
                minutes = max_minutes;
            }
        }
        
        //cout << hours << " " << minutes << "\n";
        
        if(hours == -1 || minutes == -1) {
            // not possible
            return "";
        }
        string answer;
        if(hours < 10) {
            answer.append("0" + to_string(hours));
        } else {
            answer.append(to_string(hours));
        }
        answer.append(":");
        if(minutes < 10) {
            answer.append("0" + to_string(minutes));
        } else {
            answer.append(to_string(minutes));
        }
        return answer;
    }
};