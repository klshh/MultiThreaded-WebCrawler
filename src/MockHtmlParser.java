import java.util.Arrays;
import java.util.List;

public class MockHtmlParser implements HtmlParserStrategy {
    @Override
    public List<String> getUrls(String startUrl) {

        System.out.println(Thread.currentThread().getName() + " fetching url from " + startUrl);
        switch(startUrl){
            case LinkUtils.TOPICS_URL:
               return Arrays.asList("http://news.yahoo.com/news/topics/sports",
                        "http://news.yahoo.com/news/topics/technology",
                        "http://news.yahoo.com/news",
                        "http://news.yahoo.com/news/topics/");
            case LinkUtils.NEWS_URL:
                return Arrays.asList("http://news.yahoo.com/news/topics/",
                        "http://news.yahoo.com/news/politics");
            case LinkUtils.SPORTS_URL:
                return Arrays.asList("http://news.yahoo.com/news/topics/sports/football",
                        "http://news.yahoo.com/news/topics/sports/basketball");
            default:
                return List.of();
        }
    }
}
