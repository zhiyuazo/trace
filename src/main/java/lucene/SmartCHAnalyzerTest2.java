package lucene;

import java.io.IOException;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class SmartCHAnalyzerTest2 {

	public static void main(String[] args) throws IOException {
		
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        TokenStream ts= analyzer.tokenStream("name", "里根号-军用目标-PLA103");
        ts.reset();
        while(ts.incrementToken()){
            System.out.println(ts.reflectAsString(false));
        }
		
	}
}
