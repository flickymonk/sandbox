package com.alevel.sandbox.threads.scraper;

public interface WebPageStore {

    <T> void save(WebPage<T> page);

}
