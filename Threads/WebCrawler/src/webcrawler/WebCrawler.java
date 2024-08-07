package webcrawler;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

public class WebCrawler {

    private static final ConcurrentHashMap<String, String> crawledData = new ConcurrentHashMap<>();
    private static final AtomicInteger pendingTasks = new AtomicInteger(0);

    static class CrawlerTask implements Runnable {
        private final String url;

        public CrawlerTask(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            try {

                String content = fetchContent(url);
                crawledData.put(url, content);
                System.out.println("Crawled: " + url);
            } catch (IOException e) {
                System.err.println("Failed to crawl: " + url);
            } finally {
                if (pendingTasks.decrementAndGet() == 0) {
                    System.out.println("All tasks completed.");
                }
            }
        }

        private String fetchContent(String url) throws IOException {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            try (java.io.InputStream in = connection.getInputStream();
                 java.util.Scanner scanner = new java.util.Scanner(in).useDelimiter("\\A")) {
                return scanner.hasNext() ? scanner.next() : "";
            }
        }
    }


    public static void main(String[] args) {

        List<String> urls = List.of(
            "https://example.com",
            "https://example.org",
            "https://example.net"
        );

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (String url : urls) {
            pendingTasks.incrementAndGet();
            executor.submit(new CrawlerTask(url));
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("Crawled Data:");
        crawledData.forEach((url, content) -> System.out.println(url + ": " + content.substring(0, Math.min(content.length(), 100)) + "..."));
    }
}


