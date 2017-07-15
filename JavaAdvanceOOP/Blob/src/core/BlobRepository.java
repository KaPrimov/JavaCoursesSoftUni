package core;

import interfaces.Blobable;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlobRepository {

    Map<String, Blobable> blobs;

    public BlobRepository() {
        this.blobs = new LinkedHashMap<>();
    }

    public Blobable returnBlob(String name) {
        return this.blobs.get(name);
    }

    public void addBlob(Blobable blob) {
        this.blobs.put(blob.getName(), blob);
    }

    public void passTurn() {
        for (Blobable blobable : blobs.values()) {
            blobable.update();
        }
    }

    public Iterable<Blobable> returnAllBlobs() {
        return this.blobs.values();
    }
}
