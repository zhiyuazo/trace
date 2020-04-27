package StrSimilarity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.SetUtils;

public class CosinSimilarity {
    public static float compute(String a, String b) {
        if (a == null || b == null) {
            return 0F;
        }
        Set<Integer> aChar = a.chars().boxed().collect(Collectors.toSet());
        Set<Integer> bChar = b.chars().boxed().collect(Collectors.toSet());
        // 统计字频
        Map<Integer, Integer> aMap = new HashMap<>();
        Map<Integer, Integer> bMap = new HashMap<>();
        for (Integer a1 : aChar) {
            aMap.put(a1, aMap.getOrDefault(a1, 0) + 1);
        }
        for (Integer b1 : bChar) {
            bMap.put(b1, bMap.getOrDefault(b1, 0) + 1);
        }
        // 向量化
        Set<Integer> union = SetUtils.union(aChar, bChar);
        int[] aVec = new int[union.size()];
        int[] bVec = new int[union.size()];
        List<Integer> collect = new ArrayList<>(union);
        for (int i = 0; i < collect.size(); i++) {
            aVec[i] = aMap.getOrDefault(collect.get(i), 0);
            bVec[i] = bMap.getOrDefault(collect.get(i), 0);
        }
        // 分别计算三个参数
        int p1 = 0;
        for (int i = 0; i < aVec.length; i++) {
            p1 += (aVec[i] * bVec[i]);
        }
        float p2 = 0f;
        for (int i : aVec) {
            p2 += (i * i);
        }
        p2 = (float) Math.sqrt(p2);
        float p3 = 0f;
        for (int i : bVec) {
            p3 += (i * i);
        }
        p3 = (float) Math.sqrt(p3);
        return ((float) p1) / (p2 * p3);
    }
    public static void main(String[] args) {
    	System.out.println(SorensenDiceSimilarity.compute("每天爱你多一点", "每你点"));
	}
}
