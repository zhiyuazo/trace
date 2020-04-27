package StrSimilarity;

import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.SetUtils;

public class SorensenDiceSimilarity {
	
    public static float compute(String a, String b) {

        if (a == null && b == null) {
            return 1f;
        }

        if (a == null || b == null) {
            return 0F;
        }

        Set<Integer> aChars = a.chars().boxed().collect(Collectors.toSet());
        Set<Integer> bChars = b.chars().boxed().collect(Collectors.toSet());
        // 求交集数量
        int intersect = SetUtils.intersection(aChars, bChars).size();
        if (intersect == 0) {
            return 0F;
        }
        // 全集，两个集合直接加起来
        int aSize = aChars.size();
        int bSize = bChars.size();
        return (2 * (float) intersect) / ((float) (aSize + bSize));
    }
    
    public static void main(String[] args) {
    	System.out.println(SorensenDiceSimilarity.compute("中国", "中国人"));
    	
	}
}
