import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class WebCrawler {
    private String hostName;
    private HtmlParserStrategy htmlParserStrategy;
    private ConcurrentHashMap<String,Boolean> visitedUrls = new ConcurrentHashMap<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    private AtomicInteger urlsToBeParsed = new AtomicInteger(0);

    class Task implements Runnable{
        private String url;

        public Task(String url) {
            this.url = url;
        }


        @Override
        public void run() {

            List<String> extractedUrls = htmlParserStrategy.getUrls(url);

            for(String url : extractedUrls){

                String currHostName = url.split("/")[2];
                if(hostName.equals(currHostName) && visitedUrls.putIfAbsent(url,true) == null){

                    urlsToBeParsed.addAndGet(1);
                    executorService.submit(new Task(url));

                }
            }

            urlsToBeParsed.addAndGet(-1);

        }
    }

    public List<String> crawl(String startUrl, HtmlParserStrategy htmlParserStrategy) {
        String hostname = startUrl.split("/")[2];
        this.htmlParserStrategy = new MockHtmlParser();

        if(hostname.equals(LinkUtils.PARSING_HOST)) {
            visitedUrls.put(startUrl, true);

            urlsToBeParsed.addAndGet(1);

            executorService.submit(new Task(startUrl));

            while(urlsToBeParsed.get() > 0){
                try{
                    Thread.sleep(10);
                } catch (InterruptedException e){

                }
            }

            executorService.shutdown();

            return new ArrayList<>(visitedUrls.keySet());
        }
        return null;
    }

}
