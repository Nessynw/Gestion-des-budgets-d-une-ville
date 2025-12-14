package equipe;

/**
 * énumération des différents types de coûts évalués pour un projet
 */
public enum TypeCout {
    ECONOMIQUE("Économique"),
    SOCIAL("Social"),
    ENVIRONNEMENTAL("Environnemental");

    private final String libelle;

    /**
     * constructeur d'une valeur d'énumération
     * @param libelle
     */
    TypeCout(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }

}
