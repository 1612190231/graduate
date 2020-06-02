package com.luck.graduate.controller;

import com.luck.graduate.utils.JWTToken.PassToken;
import com.luck.graduate.utils.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;

@RestController
@RequestMapping("/code")
@CrossOrigin
public class CodingController {


    @PostMapping("/codingTest")
    @ResponseBody
    @PassToken
    public int[] codingTest(@RequestBody int[] nums, @RequestBody int target){
        HashMap<Integer, Integer> mp = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            mp.put(i, nums[i]);
        }
        Arrays.sort(nums);
        /*HashMap<Integer, Integer> sortMp = mp
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue())
            .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,
                                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        */
        int p = 0, q = nums.length-1;
        int ant[] = new int[2];
        while(p<q){
            if(nums[p] + nums[q] == target) {
                ant[0] = p;
                ant[1] = q;
                break;
            }
            if(nums[p] + nums[q] > target) q--;
            if(nums[p] + nums[q] < target) p++;
        }
        return ant;
    }
}
