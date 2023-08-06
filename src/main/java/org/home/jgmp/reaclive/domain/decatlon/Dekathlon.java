package org.home.jgmp.reaclive.domain.decatlon;

import java.util.ArrayList;

public class Dekathlon {


    // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
    public class Context {
        public Data data;
    }


    public class Group {
        public Data data;
        public Links links;
    }

    public class Images {
        public ArrayList<Data> data;
    }


    public class Medium {
        public String url;
    }

    public class Meta {
        public String copyright;
        public ArrayList<String> authors;
    }

    public class Parent {
        public Data data;
    }

    public class Relationships {
        public Object children;
        public Parent parent;
        public Group group;
        public Object related;
        public Context context;
        public Tags tags;
        public Images images;
    }

    public class Root {
        public ArrayList<Data> data;
        public Meta meta;
        public Links links;
    }

    public class Tags {
        public ArrayList<String> data;
    }

    public class Thumbnail {
        public String url;
    }

    public class Variant {
        public Thumbnail thumbnail;
        public Medium medium;
    }


}
