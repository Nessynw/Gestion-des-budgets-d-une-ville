package main;

import equipe.*;
import sacADos.*;
import solveur.glouton.GloutonAjoutSolver;
import solveur.glouton.GloutonRetraitSolver;
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
                case 4:
                    testerGloutonRetrait();
                    break;
                case 5:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Option invalide");
            }

            if (choix >= 1 && choix <= 4) {
                System.out.println("\nAppuyez sur Entrée pour continuer...");
                scanner.nextLine(); // Consomme le retour à la ligne
                scanner.nextLine(); // Attend l'entrée de l'utilisateur
            }

        } while (choix != 5);
    }

    private static void afficherMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Tester l'équipe municipale");
        System.out.println("2. Tester le sac à dos");
        System.out.println("3. Tester la conversion Projets → SacADos");
        System.out.println("4. Tester le Glouton Retrait");
        System.out.println("5. Quitter");
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

    private static void testerSacADos() {
        Objet obj1 = new Objet("Projet A", 10, new int[]{3, 2, 1});
        Objet obj2 = new Objet("Projet B", 8, new int[]{2, 2, 2});
        Objet obj3 = new Objet("Projet C", 7, new int[]{1, 3, 1});

        List<Objet> objets = new ArrayList<>();
        objets.add(obj1);
        objets.add(obj2);
        objets.add(obj3);

        int[] budget = new int[]{5, 5, 3};
        SacADos sac = new SacADos(budget, objets);

        GloutonAjoutSolver solver = new GloutonAjoutSolver(
                (o1, o2) -> o2.getUtilite() - o1.getUtilite()
        );

        List<Objet> solution = solver.resoudre(sac);

        System.out.println("Objets disponibles :");
        for (Objet o : objets) {
            System.out.println(o);
        }

        System.out.println("\nSolution trouvée par le solveur glouton :");
        for (Objet o : solution) {
            System.out.println(o);
        }
    }

    private static void testerConversion() {
        System.out.println("Conversion non implémentée pour le moment.");
    }

    private static void testerGloutonRetrait() {
        System.out.println("\n=== TEST GLOUTON RETRAIT ===");

        Objet obj1 = new Objet("Projet A", 10, new int[]{3, 2, 1});
        Objet obj2 = new Objet("Projet B", 8, new int[]{2, 2, 2});
        Objet obj3 = new Objet("Projet C", 7, new int[]{1, 3, 1});
        Objet obj4 = new Objet("Projet D", 5, new int[]{4, 1, 2});

        List<Objet> objets = new ArrayList<>();
        objets.add(obj1); objets.add(obj2); objets.add(obj3); objets.add(obj4);

        int[] budget = new int[]{5, 5, 5};
        SacADos sac = new SacADos(budget, objets);

        GloutonRetraitSolver retraitSolver = new GloutonRetraitSolver();
        List<Objet> solutionRetrait = retraitSolver.resoudre(sac);

        System.out.println("Solution après retrait :");
        for (Objet o : solutionRetrait) {
            System.out.println(" - " + o);
        }
    }
}
