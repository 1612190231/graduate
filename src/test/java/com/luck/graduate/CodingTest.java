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
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean mqs[] =new boolean[numCourses];
        Arrays.fill(mqs,false);
        Map<Integer, Vector<Integer>> mp = new HashMap<>();
        for(int[] item : prerequisites){
            Vector<Integer> vec = mp.get(item[1]);
            vec.add(item[0]);
            mp.put(item[1], vec);
        }
        for(int i=0; i<numCourses; i++){
            if(mqs[i]) continue;
            Vector<Integer> vec = mp.get(i);
            for(int j=0; j<vec.size(); j++){
                int v = vec.get(j);
                if(mqs[v]) return false;
                else mqs[v] = true;
            }
        }
        for(boolean mq : mqs){
            if(mq==false) return false;
        }
        return true;
    }
}
