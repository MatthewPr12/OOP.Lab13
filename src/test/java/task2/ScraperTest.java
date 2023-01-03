package task2;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScraperTest {
    private Scraper scraper;

    @BeforeEach
    void setUp() {
        scraper = new Scraper("https://stackoverflow.com/questions/56818651/check-if-couple-of-values-exists-in-sqlite-database");
    }

    @Test
    void scrape() {
        System.out.println(scraper.scrape());
    }
}