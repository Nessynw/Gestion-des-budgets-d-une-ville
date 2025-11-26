package main;
import equipe.*;
import sacADos.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            afficherMenu();
            choix = scanner.nextInt();
            
            switch (choix) {
                case 1:
                    testerEquipeMunicipale();
                    break;
                case 2:
                    testerSacADos();
                    break;
                case 3:
                    testerConversion();
                    break;
                default:
                    System.out.println("Option invalide");
            }
            
            System.out.println("\nAppuyez sur Entrée pour continuer...");
            scanner.nextLine(); // Consomme le retour à la ligne
            scanner.nextLine(); // Attend l'entrée de l'utilisateur
            
        } while (choix >= 1 && choix <= 3);
    }
    private static void afficherMenu() {
        System.out.println("1. Tester l'équipe municipale");
        System.out.println("2. Tester le sac à dos");
        System.out.println("3. Tester la conversion Projets → SacADos");
        System.out.println("4. Quitter");
        System.out.print("\nVotre choix : ");
    }
    private static void testerEquipeMunicipale() {
    Evaluateur evalEnv = new Evaluateur("A", "B", "HOME ", "0123456789", "A.B@mail.com", TypeCout.ENVIRONNEMENTAL);
    Evaluateur evalSoc = new Evaluateur("C", "D", "HOUSE ", "0123456788", "C.D@mail.com", TypeCout.SOCIAL);
    Evaluateur evalEco = new Evaluateur("E", "F", "MAISON ", "0123456787", "E.F@mail.com", TypeCout.ECONOMIQUE);
    Elu elu = new Elu("G", "H", "Mairie", "0123456786", "D@ville.fr");
    EquipeMunicipale equipe = new EquipeMunicipale(elu, evalEnv, evalSoc, evalEco);
    System.out.println(equipe);
    
    System.out.println("\nAjout des experts...");
    List<Secteur> competencesSante = new ArrayList<>();
    competencesSante.add(Secteur.SANTE);
    Expert expertSante = new Expert("EX", "sante", "homouse", "0123456785", "expert.ex@mail.com", competencesSante);
    equipe.ajouterExpert(expertSante);
    
    List<Secteur> competencesSport = new ArrayList<>();
    competencesSport.add(Secteur.SPORT);
    Expert expertSport = new Expert("EX", "SPORT", "5 Sport", "0123456784", "sport@mail.com", competencesSport);
    equipe.ajouterExpert(expertSport);
    
    System.out.println("\nÉquipe après ajout des experts :");
    System.out.println(equipe);

    System.out.println("\nLancement du cycle de simulation...");
    equipe.cycleSimulation();
    
    System.out.println("\nÉquipe après simulation :");
    System.out.println(equipe);
}
    private static void testerSacADos() {}
    private static void testerConversion() {}

}