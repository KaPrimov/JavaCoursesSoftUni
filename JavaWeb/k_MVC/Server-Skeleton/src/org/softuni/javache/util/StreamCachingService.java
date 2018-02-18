package org.softuni.javache.util;

import org.softuni.javache.io.Reader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class StreamCachingService {
    private static final int READING_TIMEOUT = 20000;

    private String cachedInputStreamContent;

    public InputStream getOrCacheInputStream(InputStream inputStream) throws IOException {
        if (this.cachedInputStreamContent == null) {
            int i = 0;

            while(i++ < READING_TIMEOUT) {
                this.cachedInputStreamContent =
                        new Reader()
                        .readAllLines(inputStream);

                if(this.cachedInputStreamContent.length() > 0) break;
            }
        }

        return new ByteArrayInputStream(this.cachedInputStreamContent.getBytes());
    }

    public void evictCache() {
        this.cachedInputStreamContent = null;
    }
}
