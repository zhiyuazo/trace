package lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;
public class LucenePage {
	
	/*
	 *  10001,房屋卫士自流平美缝剂瓷砖地砖专用双组份真瓷胶防水填缝剂镏金色,品质建材,398.00,上海,540785126782
		10002,艾瑞泽手工大号小号调温热熔胶枪玻璃胶枪硅胶条热溶胶棒20W-100W,品质建材,21.80,山东青岛,24727352473
		10003,HIGOLD/悍高 水槽双槽 厨房洗菜盆304不锈钢加厚拉丝手工水槽套餐,品质建材,2198.00,广东佛山,40972207020
		10004,海康威视4路200万网络监控设备套装2 6 8路poe高清家用摄像头套餐,品质建材,2500.00,广东深圳,40057050304
		10005,警视卫4路监控设备套装200万家用一体机高清3000线夜视摄像头套餐,品质建材,13738.00,广东东莞,43720681536
	 */
	public static void main(String[] args) throws IOException, ParseException {
        String fileName = "E:\\Steps\\trace\\src\\main\\java\\lucene\\140k_products.txt";
        List<Product4LucenePage> productNames = file2list(fileName);
        System.out.println("数据池具有：" + productNames.size() + "数据");
        //Below to search with lucene
        
        // 1. 准备中文分词器
        IKAnalyzer analyzer = new IKAnalyzer();
        // 2. 索引
        Directory index = new RAMDirectory();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(index, config);
        for (Product4LucenePage pl : productNames) {
	        Document doc = new Document();
	        doc.add(new TextField("id", String.valueOf(pl.id), Field.Store.YES));
	        doc.add(new TextField("price", String.valueOf(pl.price), Field.Store.YES));
	        doc.add(new TextField("code", String.valueOf(pl.code), Field.Store.YES));
	        doc.add(new TextField("name", String.valueOf(pl.name), Field.Store.YES));
	        doc.add(new TextField("place", String.valueOf(pl.place), Field.Store.YES));
	        doc.add(new TextField("category", String.valueOf(pl.category), Field.Store.YES));
	        writer.addDocument(doc);
        }
        writer.close();
 
        // 3. 查询器
        String keyword = "护眼带光源";
        Query query = new QueryParser("name", analyzer).parse(keyword);
         
        // 4. 搜索
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
//        全部查询
//        int numberPerPage = Integer.MAX_VALUE;
//        System.out.printf("于[%d]-条数据查询key=[%s]",productNames.size(),keyword);
//        ScoreDoc[] hits = searcher.search(query, numberPerPage).scoreDocs;
//        分页查询-1-全部查询-取出感兴趣的段
        int pageNow = 1;
        int pageSize = 10;
        ScoreDoc[] hits = pageSearch1(query, searcher, pageNow, pageSize);
//        分页查询-2
        int pageNow2 = 1;
        int pageSize2 = 10;
        ScoreDoc[] hits2 = pageSearch2(query, searcher, pageSize2, pageSize2);
        
        
        
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
    public  static ScoreDoc[] pageSearch1(Query query, IndexSearcher searcher, int pageNow, int pageSize)
            throws IOException {
         TopDocs topDocs = searcher.search(query, pageNow*pageSize);
         System.out.println("查询到的总条数\t"+topDocs.totalHits);
         ScoreDoc [] alllScores = topDocs.scoreDocs;
 
         List<ScoreDoc> hitScores = new ArrayList<>();
         
         int start = (pageNow -1)*pageSize ;
         int end = pageSize*pageNow;
         for(int i=start;i<end;i++) {
             hitScores.add(alllScores[i]);
         }
         ScoreDoc[] hits = hitScores.toArray(new ScoreDoc[]{});
        return hits;
    }
    
    public  static ScoreDoc[] pageSearch2(Query query, IndexSearcher searcher, int pageNow, int pageSize)
            throws IOException {
         
        int start = (pageNow - 1) * pageSize;
        if(0==start){
            TopDocs topDocs = searcher.search(query, pageNow*pageSize);
            return topDocs.scoreDocs;
        }
        // 查询数据， 结束页面自前的数据都会查询到，但是只取本页的数据
        TopDocs topDocs = searcher.search(query, start);
        //获取到上一页最后一条
         
        ScoreDoc preScore= topDocs.scoreDocs[start-1];
 
        //查询最后一条后的数据的一页数据
        topDocs = searcher.searchAfter(preScore, query, pageSize);
        return topDocs.scoreDocs;
    }
    
    public static List<Product4LucenePage> file2list(String fileName) throws IOException {
        File f = new File(fileName);
        List<String> lines = FileUtils.readLines(f,"UTF-8");
        List<Product4LucenePage> products = new ArrayList<>();
        for (String line : lines) {
        	Product4LucenePage p = line2product(line);
            products.add(p);
        }
        return products;
    }
     
    private static Product4LucenePage line2product(String line) {
    	Product4LucenePage p = new Product4LucenePage();
        String[] fields = line.split(",");
        p.setId(Integer.parseInt(fields[0]));
        p.setName(fields[1]);
        p.setCategory(fields[2]);
        p.setPrice(Float.parseFloat(fields[3]));
        p.setPlace(fields[4]);
        p.setCode(fields[5]);
        return p;
    }
}

class Product4LucenePage {
    int id;
    String name;
    String category;
    float price;
    String place;
    String code;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
 
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", place="
                + place + ", code=" + code + "]";
    }
 
}