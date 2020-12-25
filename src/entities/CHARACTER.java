package entities;

public enum CHARACTER {
    FOX("/images/foxCharacter.png"),
    POLARBEAR("/images/polarBearCharacter.png");

    private String urlCharacter;

    private CHARACTER(String urlCharacter) {
        this.urlCharacter = urlCharacter;
    }

    public String getUrl() {
        return this.urlCharacter;
    }

}
