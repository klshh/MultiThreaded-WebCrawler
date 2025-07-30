import java.util.List;

public class Main {
    public static void main(String[] args) {
        WebCrawler webCrawler = new WebCrawler();
        HtmlParserStrategy htmlParser = new MockHtmlParser();

        List<String> result = webCrawler.crawl("http://news.yahoo.com/news/topics/", htmlParser);

        System.out.println("Crawler has parsed these urls");
        for (String url : result) {
            System.out.println(url);
        }
    }
}