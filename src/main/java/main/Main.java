package main;

import equipe.*;
import sacADos.*;
import solveur.glouton.*;
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
                case 3 :
                    testerGloutonAjout();
                    break;
                case 4:
                    testerGloutonRetrait();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Option invalide");
            }

            if (choix >= 1 && choix <= 4) {
                System.out.println("\nAppuyez sur Entrée pour continuer");
                scanner.nextLine();
                scanner.nextLine();
            }

        } while (choix != 5);
    }

    private static void afficherMenu() {
        System.out.println("\n MENU");
        System.out.println("1. Tester l'équipe municipale");
        System.out.println("2. Tester le sac à dos");
        System.out.println("3. Tester le Glouton Ajout");
        System.out.println("4. Tester le Glouton Retrait");
        System.out.println("5. Quitter");
        System.out.print("\nVotre choix : ");
    }

    private static EquipeMunicipale testerEquipeMunicipale() {
        Evaluateur evalEnv = new Evaluateur("A", "B", "HOME ", "0123456789", "A.B@mail.com", TypeCout.ENVIRONNEMENTAL);
        Evaluateur evalSoc = new Evaluateur("C", "D", "HOUSE ", "0123456788", "C.D@mail.com", TypeCout.SOCIAL);
        Evaluateur evalEco = new Evaluateur("E", "F", "MAISON ", "0123456787", "E.F@mail.com", TypeCout.ECONOMIQUE);
        Elu elu = new Elu("G", "H", "Mairie", "0123456786", "D@ville.fr");
        //tester les excepitons
//      Evaluateur evalEnv = new Evaluateur("A", "B", "Home", "0123", "a@mail.com", TypeCout.ECONOMIQUE);
//
//      EquipeMunicipale equipe = new EquipeMunicipale(elu, evalEnvIncorrect, evalSoc, evalEco);


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
        return equipe;
    }

    private static void testerSacADos() {
        EquipeMunicipale equipe=testerEquipeMunicipale();
        Budget budget=new Budget();
        budget.setBudgetTotal();
        budget.setBudgetCouts();
        budget.setBudgetSecteurs();
        VersSacADos vs=new VersSacADos();


        SacADos sacParCouts=vs.conversionParCouts(budget,equipe.getProjetsEtudies());
        System.out.println("Affichage sac a dos par coûts:\n");
        sacParCouts.afficheSac();
        System.out.println("\n\n");


        SacADos sacParSecteurs=vs.conversionParSecteur(budget,equipe.getProjetsEtudies());
        System.out.println("Affichage sac a dos par secteurs:\n");
        sacParSecteurs.afficheSac();
        System.out.println("\n\n");


        Scanner scanner=new Scanner(System.in);
        vs=new VersSacADos();
        System.out.println("Chemin d'accès au fichier de données");
        String nomfichier=scanner.nextLine();
        SacADos sacFichier=vs.convertir(nomfichier);
        System.out.println("Affichage sac a dos du fichier:\n");
        sacFichier.afficheSac();
    }


    private static void testerGloutonAjout() {
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

        System.out.println("Solution trouvée par ajout:");
        for (Objet o : solution) {
            System.out.println("  " + o);
        }
        System.out.println();
    }

    private static void testerGloutonRetrait() {

        Objet obj1 = new Objet("Projet A", 10, new int[]{3, 2, 1});
        Objet obj2 = new Objet("Projet B", 8, new int[]{2, 2, 2});
        Objet obj3 = new Objet("Projet C", 7, new int[]{1, 3, 1});
        Objet obj4 = new Objet("Projet D", 5, new int[]{4, 1, 2});

        List<Objet> objets = new ArrayList<>();
        objets.add(obj1); objets.add(obj2); objets.add(obj3); objets.add(obj4);

        int[] budget = new int[]{5, 5, 5};
        SacADos sac = new SacADos(budget, objets);

        GloutonRetraitSolver retraitSolver = new GloutonRetraitSolver(
                new Comparateur.ParUtiliteCroissante()
        );
        List<Objet> solutionRetrait = retraitSolver.resoudre(sac);

        System.out.println("Solution après retrait :");
        for (Objet o : solutionRetrait) {
            System.out.println(" - " + o);
        }
    }
}