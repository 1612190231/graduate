package com.luck.graduate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

@SpringBootTest
public class CodingTest {
    @Test
    public int numPairsDivisibleBy60(int[] time) {
        int count = 0;
        int mqs[] =new int[60];
        Arrays.fill(mqs,0);
        for(Integer item:time){
            mqs[item % 60] ++;
        }
        int u=21;
        int j=1;
        Map<Integer, Vector<Integer>> mp = new HashMap<>();
        mp.get(u).elementAt(j);
        for (int i = 0; i<60; i++){
            count = count + mqs[i] * mqs[59-i];
        }
        return count;
    }
}
