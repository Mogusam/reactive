package org.home.jgmp.reaclive.domain.decatlon;

import java.util.ArrayList;

public class Attributes {
    public String name;
    public String description;
    public Object parent_id;
    public int decathlon_id;
    public String slug;
    public String locale;
    public ArrayList<Object> weather;
    public String icon;

    public Attributes() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getParent_id() {
        return parent_id;
    }

    public void setParent_id(Object parent_id) {
        this.parent_id = parent_id;
    }

    public int getDecathlon_id() {
        return decathlon_id;
    }

    public void setDecathlon_id(int decathlon_id) {
        this.decathlon_id = decathlon_id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public ArrayList<Object> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Object> weather) {
        this.weather = weather;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Attributes{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", parent_id=" + parent_id +
                ", decathlon_id=" + decathlon_id +
                ", slug='" + slug + '\'' +
                ", locale='" + locale + '\'' +
                ", weather=" + weather +
                ", icon='" + icon + '\'' +
                '}';
    }
}