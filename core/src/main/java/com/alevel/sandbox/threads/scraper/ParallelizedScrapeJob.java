package com.alevel.sandbox.threads.scraper;

import java.net.URI;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class ParallelizedScrapeJob implements ScrapeJob {

    private final WebScraper<?> scraper;

    private final WebPageStore store;

    private final int parallelism;

    public ParallelizedScrapeJob(WebScraper<?> scraper, WebPageStore store, int parallelism) {
        this.scraper = scraper;
        this.store = store;
        this.parallelism = parallelism;
    }

    @Override
    public void scrape(URI root) {
        ExecutorService executor = Executors.newFixedThreadPool(parallelism);

        var phaser = new Phaser(2);

        executor.execute(new ScrapeTask(root, ConcurrentHashMap.newKeySet(), executor, phaser));

        phaser.arriveAndAwaitAdvance();
        executor.shutdown();
    }

    private final class ScrapeTask implements Runnable {


        private final URI uri;


        private final Set<URI> visited;

        private final ExecutorService executor;

        private final Phaser phaser;

        private ScrapeTask(URI uri, Set<URI> visited, ExecutorService executor, Phaser phaser) {
            this.uri = uri;
            this.visited = visited;
            this.executor = executor;
            this.phaser = phaser;
        }

        @Override
        public void run() {

            WebPage<?> page = scraper.scrape(uri);

            store.save(page);

            for (URI hyperlink : page.hyperlinks()) {
                if (visited.add(hyperlink)) {
                    var childPhaser = new Phaser(phaser, 1);
                    executor.execute(new ScrapeTask(hyperlink, visited, executor, childPhaser));
                }
            }

            phaser.arriveAndDeregister();
        }

    }

}
