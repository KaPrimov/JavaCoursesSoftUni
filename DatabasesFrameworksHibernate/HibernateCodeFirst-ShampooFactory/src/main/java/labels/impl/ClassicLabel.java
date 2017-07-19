package labels.impl;

import labels.api.Label;

public class ClassicLabel implements Label {

    private long id;

    private String title;

    private String subtitle;

    public ClassicLabel(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public ClassicLabel() {
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getSubtitle() {
        return subtitle;
    }

    @Override
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
