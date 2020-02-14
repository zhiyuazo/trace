package lucene;

import java.io.IOException;

import org.apache.lucene.analysis.TokenStream;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class IKAnalyzerTest {

	public static void main(String[] args) throws IOException {
		
        IKAnalyzer analyzer = new IKAnalyzer();
        TokenStream ts= analyzer.tokenStream("name", "希尔瓦娜斯-战士-PLA103");
        ts.reset();
        while(ts.incrementToken()){
            System.out.println(ts.reflectAsString(false));
        }
		
	}
}
