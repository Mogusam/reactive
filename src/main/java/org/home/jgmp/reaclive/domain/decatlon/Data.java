package org.home.jgmp.reaclive.domain.decatlon;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {

    public int id;
    public String type;
    public Attributes attributes;
    public Dekathlon.Relationships relationships;
    public Links links;
    public String url;
    public ArrayList<Dekathlon.Variant> variants;

    public Data() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public Dekathlon.Relationships getRelationships() {
        return relationships;
    }

    public void setRelationships(Dekathlon.Relationships relationships) {
        this.relationships = relationships;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<Dekathlon.Variant> getVariants() {
        return variants;
    }

    public void setVariants(ArrayList<Dekathlon.Variant> variants) {
        this.variants = variants;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", attributes=" + attributes +
                ", relationships=" + relationships +
                ", links=" + links +
                ", url='" + url + '\'' +
                ", variants=" + variants +
                '}';
    }
}