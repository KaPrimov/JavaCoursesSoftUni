package com.neckandelbows.labels;

import com.neckandelbows.domain.shampoos.BasicShampoo;

import javax.persistence.*;

@Entity
@Table(name = "labels")
public class ClassicLabel implements Label{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    private String title;

    @Basic
    private String subtitle;

    @OneToOne(mappedBy = "label", targetEntity = BasicShampoo.class)
    private BasicShampoo basicShampoo;

    public ClassicLabel() {
    }

    public ClassicLabel(String title, String subtitle) {
        this.setTitle(title);
        this.setSubtitle(subtitle);
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getSubtitle() {
        return this.subtitle;
    }

    @Override
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
