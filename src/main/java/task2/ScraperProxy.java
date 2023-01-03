package task2;

public class ScraperProxy implements GetHtml{
    private String url;

    public ScraperProxy(String url) {
        this.url = url;
    }

    @Override
    public String scrape() {
        Scraper realScraper = new Scraper(url);
        return realScraper.scrape();
    }
}
