package example.android.custom;

import java.io.Serializable;
import java.util.Comparator;

public class Custom implements Serializable {
    private String id;
    private String key;
    private String icon;
    private String name;
    private String tittle;
    private String tags;
    private String description;

    public Custom(String id, String key, String icon, String name, String tittle, String tags, String description) {
        this.id = id;
        this.key = key;
        this.icon = icon;
        this.name = name;
        this.tittle = tittle;
        this.tags = tags;
        this.description = description;
    }

    public Custom(String description) {
        this.description = description;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String  getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public  static final Comparator<Custom> AscendingtByName =new Comparator<Custom>() {
        @Override
        public int compare(Custom o1, Custom o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public  static final Comparator<Custom> DescendingtByName =new Comparator<Custom>() {
        @Override
        public int compare(Custom o1, Custom o2) {
            return o2.getName().compareTo(o1.getName());
        }
    };
    
}
