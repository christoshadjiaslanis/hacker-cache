package com.buffer.crawler.providers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ConstructionEnquirer extends ContentProvider {
    private static final String MOZILLA_USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:63.0) Gecko/20100101 Firefox/63.0";

    private static final String baseURl = "https://www.constructionenquirer.com/";

    public ConstructionEnquirer() {
        super(baseURl, null, false);
    }

    @Override
    List<WebPage> extract(String baseUrl) throws IOException {
        Document doc = Jsoup
                .connect(baseUrl)
                .userAgent(MOZILLA_USER_AGENT)
                .get();

        Elements links = doc.getElementsByClass("news-list-article");

        return links.stream()
                .map(e -> {

                    String title = e.select("h3").select("a").html();
                    String link = e.select("h3").select("a").attr("href");

                    return new WebPage(title, link);
                }).distinct()
                .collect(Collectors.toList());
    }

    @Override
    public Topic topic() {
        return Topic.CONSTRUCTION;
    }
}
