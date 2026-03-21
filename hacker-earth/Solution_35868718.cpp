#include<stdio.h>
 
long long int max(long long int a, long long int b) {
    if(a > b) {
        return a;
    } else {
        return b;
    }
}
 
int main() {
    
    int t, i;
    long long int scores[4], d_scores[4], f_times[4], c_times[4];
    long long int f_score, f_time, c_score, c_time, t_score;
    
    scanf("%d", &t);
    while(--t >= 0) {
        
        // read inputs
        for(i = 0; i < 4; i++) {
            scanf("%ld", scores + i);
        }
        for(i = 0; i < 4; i++) {
            scanf("%ld", d_scores + i);
        }
 
        // get time to solve lal questions for flash
        // along with reading input
        f_time = 0;
        for(i = 0; i < 4; i++) {
            scanf("%ld", f_times + i);
            if(f_times[i] > f_time) {
                f_time = f_times[i];
            }
        }
        // get time to solve lal questions for cisco
        // along with reading input
        c_time = 0;
        for(i = 0; i < 4; i++) {
            scanf("%ld", c_times + i);
            if(c_times[i] > c_time) {
                c_time = c_times[i];
            }
        }
        
        // flash score
        f_score = 0;
        for(i = 0; i < 4; i++) {
            t_score = max(scores[i] - d_scores[i] * f_times[i], scores[i] / 2);
            f_score += t_score;
        }
        
        // cisco score
        c_score = 0;
        for(i = 0; i < 4; i++) {
            t_score = max(scores[i] - d_scores[i] * c_times[i], scores[i] / 2);
            c_score += t_score;
        }
        
        // winner
        if(f_score < c_score) {
            // cisco is winner
            printf("Cisco\n");
        } else if(f_score > c_score) {
            // flash is winner
            printf("Flash\n");
        } else {
            // tie break
            if(f_time < c_time) {
                // flash is winner
                printf("Flash\n");
            } else if(f_time > c_time) {
                // cisco is winner
                printf("Cisco\n");
            } else {
                // tie
                printf("Tie\n");
            }
        }
    }
    
    return 0;
}