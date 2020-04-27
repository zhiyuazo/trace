package StrSimilarity;

import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.SetUtils;

public class JaccardSimilarity {
	
    public static float compute(String a, String b) {

        // 都为空相似度为 1
        if (a == null && b == null) {
            return 1f;
        }
        // 其中一个为空相似度为0
        if (a == null || b == null) {
            return 0f;
        }
        //a集合 
        Set<Integer> aChar = a.chars().boxed().collect(Collectors.toSet());
        //b集合 
        Set<Integer> bChar = b.chars().boxed().collect(Collectors.toSet());
        // 交集数量
        int intersection = SetUtils.intersection(aChar, bChar).size();
        //无交集相似度为0
        if (intersection == 0) {
        	return 0;
        }
        // 并集数量
        int union = SetUtils.union(aChar, bChar).size();
        return ((float) intersection) / (float)union;
    }
    
    public static void main(String[] args) {
    	System.out.println(JaccardSimilarity.compute("中国", "中国人"));
    	
	}
}
