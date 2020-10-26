package com.zzy.trace.StrSimilarity;

public class hammingSimilarity {
    public static float compute(String a, String b) {
        if (a == null || b == null) {
            return 0f;
        }

        if (a.length() != b.length()) {
            return 0f;
        }

        int disCount = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                disCount++;
            }
        }
        return (float) disCount / (float) a.length();
    }
    public static void main(String[] args) {
    	System.out.println(SorensenDiceSimilarity.compute("中国", "美国"));
    	
	}
}
