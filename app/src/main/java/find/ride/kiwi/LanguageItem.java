package find.ride.kiwi;

public class LanguageItem {

    private String name;
    private int image;
    private String langCode;

    public LanguageItem(String name, int image,String langCode) {
        this.name = name;
        this.image = image;
        this.langCode = langCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }
}
