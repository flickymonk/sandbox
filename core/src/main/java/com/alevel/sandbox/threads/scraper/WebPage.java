package com.alevel.sandbox.threads.scraper;

import java.net.URI;
import java.util.Objects;
import java.util.Set;

public record WebPage<T>(URI uri, T content, String rawHTML, Set<URI> hyperlinks) {

    public WebPage {
        Objects.requireNonNull(this.uri());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WebPage<?> webPage = (WebPage<?>) o;

        return uri.equals(webPage.uri);
    }

    @Override
    public int hashCode() {
        return uri.hashCode();
    }

    @Override
    public String toString() {
        return "WebPage{" +
                "uri=" + uri +
                '}';
    }
}