package example.android.custom;

public class ItemMenu {
    private  String ten_menu;

    public String getTen_menu() {
        return ten_menu;
    }

    public void setTen_menu(String ten_menu) {
        this.ten_menu = ten_menu;
    }

    public ItemMenu(String ten_menu) {
        this.ten_menu = ten_menu;
    }

    @Override
    public String toString() {
        return ten_menu;
    }
}
