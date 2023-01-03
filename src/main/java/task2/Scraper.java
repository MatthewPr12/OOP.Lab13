package task2;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Scraper implements GetHtml {
    private Document doc;
    private String url;
    private Connection connection;

    public Scraper(String url) {
        this.url = url;
        connection = Connection.getInstance();
    }

    @Override
    public String scrape() {
        String res = connection.searchQuery("SELECT * from scrapes WHERE url = '" + url + "'");
        if (res != null) {
            return res;
        } else {
            try {
                doc = Jsoup.connect(url).userAgent("Mozilla").timeout(5000).get();
                String data = doc.html();
                String sql = "INSERT INTO scrapes values (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        sql)) {
                    preparedStatement.setString(1, url);
                    preparedStatement.setString(2, data);
                    preparedStatement.executeUpdate();
                }catch (SQLException e){
                    System.out.println(e);
                }
                connection.executeQuery(sql);
                return data;
            } catch (IOException ex) {
                String sql = "INSERT INTO scrapes values (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        sql)) {
                    preparedStatement.setString(1, url);
                    preparedStatement.setString(2, null);
                    preparedStatement.executeUpdate();
                }catch (SQLException e){
                    System.out.println(e);
                }
                connection.executeQuery(sql);
                System.out.println(ex);
            }
            return null;
        }

    }


    @Override
    public String toString() {
        return "Scraper{" +
                "url='" + url + '\'' +
                '}';
    }
}
