package com.tensquare.article.config;
import java.util.HashMap;
import java.util.Map;
public class KK {
    static int[] distances;
    public static void main(String[] args) {
        distances = new int[]{12,6,4,3,1};

        System.out.println(calMinDis(distances.length, 2));
    }

    private static int calMinDis(int n, int k) {
        int state = (1<<n)-1;

        return bt(state, k);
    }
    static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    private static int bt(int state, int k) {
        if(k == 0) {
            return 0;
        }
        if(map.containsKey(state)) {
            return map.get(state);
        }
        int pre = -1, min=Integer.MAX_VALUE;
        for(int i=0; i<32; i++) {
            if(((state >> i) & 1) == 1) {
                if(pre != -1) {
                    int nState = state;
                    nState ^= (1<< pre);
                    nState ^= (1<< i);
                    min = Math.min(distances[pre]-distances[i]+bt(nState, k-1), min);
                }
                pre = i;
            }
        }
        map.put(state, min);
        return min;
    }
}
