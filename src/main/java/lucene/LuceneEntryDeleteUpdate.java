package lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class LuceneEntryDeleteUpdate {
	 
    public static void main(String[] args) throws Exception {
    	// 0 原始数据池
        List<String> productNames = new ArrayList<>();
        productNames.add("A,1,飞利浦led灯泡e27螺口暖白球泡灯家用照明超亮节能灯泡转色温灯泡");
        productNames.add("B,2,飞利浦led灯泡e14螺口蜡烛灯泡3W尖泡拉尾节能灯泡暖黄光源Lamp");
        productNames.add("C,3,雷士照明 LED灯泡 e27大螺口节能灯3W球泡灯 Lamp led节能灯泡");
        productNames.add("D,4,飞利浦 led灯泡 e27螺口家用3w暖白球泡灯节能灯5W灯泡LED单灯7w");
        productNames.add("E,5,飞利浦led小球泡e14螺口4.5w透明款led节能灯泡照明光源lamp单灯");
        productNames.add("F,6,飞利浦蒲公英护眼台灯工作学习阅读节能灯具30508带光源");
        productNames.add("G,7,欧普照明led灯泡蜡烛节能灯泡e14螺口球泡灯超亮照明单灯光源");
        productNames.add("H,8,欧普照明led灯泡节能灯泡超亮光源e14e27螺旋螺口小球泡暖黄家用");
        productNames.add("I,9,聚欧普照明led灯泡节能灯泡e27螺口球泡家用led照明单灯超亮光源");    
        productNames.add("J,10,希尔瓦娜斯");    
        productNames.add("K,11,希尔瓦娜");    
    	
        // 1. 准备中文分词器
        IKAnalyzer analyzer = new IKAnalyzer();
 
        // 2. 索引
        Directory index = new RAMDirectory();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(index, config);
        for (String name : productNames) {
        	String[] items = name.split(",");
	        Document doc = new Document();
	        doc.add(new TextField("code", items[0], Field.Store.YES));
	        doc.add(new TextField("id", items[1], Field.Store.YES));
	        doc.add(new TextField("name", items[2], Field.Store.YES));
	        writer.addDocument(doc);
        }
        //DELETE
        writer.deleteDocuments(new Term("id", "11"));
        writer.commit();
        
        //UPDATE
        Document doc = new Document();
        doc.add(new TextField("id", "12", Field.Store.YES));
        doc.add(new TextField("name", "希尔于阿尔萨斯", Field.Store.YES));
        doc.add(new TextField("code", "L", Field.Store.YES));
        writer.updateDocument(new Term("id", "10"), doc );
        writer.commit();
        writer.close();
 
        // 3. 查询器
        String keyword = "希尔瓦娜";
        Query query = new QueryParser("name", analyzer).parse(keyword);
         
        // 4. 搜索
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        int numberPerPage = Integer.MAX_VALUE;
        System.out.printf("于[%d]-条数据查询key=[%s]",productNames.size(),keyword);
        ScoreDoc[] hits = searcher.search(query, numberPerPage).scoreDocs;
 
        // 5. 显示查询结果
        System.out.println("找到 " + hits.length + " 个命中.");
        System.out.println("序号\t匹配度得分\t结果");
        for (int i = 0; i < hits.length; ++i) {
            ScoreDoc scoreDoc= hits[i];
            int docId = scoreDoc.doc;
            Document d = searcher.doc(docId);
            List<IndexableField> fields = d.getFields();
            System.out.print((i + 1));
            System.out.print("\t" + scoreDoc.score);
            for (IndexableField f : fields) {
                System.out.print("\t" + d.get(f.name()));
            }
            System.out.println();
        }
        // 6. 关闭查询
        reader.close();
    }
}
