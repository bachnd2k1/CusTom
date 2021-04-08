package example.android.custom;

public class Champion {
    private int id ;
    private int key;
    private String name;
    private  String tittle;
    private String  stats;
    private int icon;
    private String url;
    private String description;


    public Champion(int id, int key, String name, String tittle, String stats, int icon, String url, String description) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.tittle = tittle;
        this.stats = stats;
        this.icon = icon;
        this.url = url;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
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

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
