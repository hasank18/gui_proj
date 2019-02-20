package gui_classes;

public class MenuItemData {
    private String ImageUrl,label;

    public MenuItemData(String imageUrl, String label) {
        ImageUrl = imageUrl;
        this.label = label;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
