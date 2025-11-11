package equipe;

public enum TypeCout {
    ECONOMIQUE("Économique"),
    SOCIAL("Social"),
    ENVIRONNEMENTAL("Environnemental");

    private final String libelle;

    TypeCout(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }

}
