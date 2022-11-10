package com.alevel.sandbox.threads.scraper;

import java.net.URI;

public interface WebScraper<T> {

    WebPage<T> scrape(URI uri);

}
