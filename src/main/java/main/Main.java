package main;

import equipe.*;
import sacados.*;
import solveur.glouton.*;
import solveur.hillclimbing.*;
import java.util.*;

/**
 * Classe principale pour tester le système de gestion de budgets municipaux.
 * Permet de tester l'équipe municipale et les différents algorithmes de résolution
 * du problème du sac à dos multidimensionnel.
 */
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static SacADos sacGlobal = null; // Sac à dos partagé pour tous les algorithmes


    public static void main(String[] args) {
        int choix;

        do {
            afficherMenu();
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    testerEquipeMunicipale();
                    break;
                case 2:
                    testerSacADos();
                    break;
                case 3:
                    testerGloutonAjout();
                    break;
                case 4:
                    testerGloutonRetrait();
                    break;
                case 5:
                    testerHillClimbing();
                    break;
                case 6:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Option invalide");
            }

            if (choix >= 1 && choix <= 5) {
                System.out.println("\nAppuyez sur Entrée pour continuer");
                scanner.nextLine();
            }

        } while (choix != 6);

        scanner.close();
    }

    /**
     * Affiche le menu principal.
     */
    private static void afficherMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Tester l'équipe municipale");
        System.out.println("2. Tester le sac à dos");
        System.out.println("3. Tester le Glouton Ajout");
        System.out.println("4. Tester le Glouton Retrait");
        System.out.println("5. Tester le Hill Climbing");
        System.out.println("6. Quitter");
        System.out.print("\nVotre choix : ");
    }
    private static boolean verifierSacInitialise() {
        if (sacGlobal == null) {
            System.out.println("\nErreur : Aucun sac à dos n'a été créé !");
            System.out.println("Veuillez d'abord utiliser l'option 2.");
            return false;
        }
        return true;
    }
    /**
     * Test de l'équipe municipale:
     * - Crée les évaluateurs (économique, social, environnemental)
     * - Crée l'élu
     * - Crée l'équipe municipale
     * - Ajoute des experts
     * - Lance un cycle de simulation pour générer des projets
     */
    private static EquipeMunicipale testerEquipeMunicipale() {
        System.out.println("\n--- Test de l'équipe municipale ---");

        // Création des évaluateurs
        Evaluateur evalEnv = new Evaluateur("Alice", "B", "12 Rue rue",
                "0123456789", "alice.b@ville.fr", TypeCout.ENVIRONNEMENTAL);
        Evaluateur evalSoc = new Evaluateur("Claire", "D", "34 Avenue avenue",
                "0123456788", "claire.d@ville.fr", TypeCout.SOCIAL);
        Evaluateur evalEco = new Evaluateur("Eric", "F", "56 Boulevard blvd",
                "0123456787", "eric.f@ville.fr", TypeCout.ECONOMIQUE);

        // Création de l'élu
        Elu elu = new Elu("Elena", "R", "Mairie Principale",
                "0123456786", "E.r@ville.fr");

        // Création de l'équipe municipale
        EquipeMunicipale equipe = new EquipeMunicipale(elu, evalEnv, evalSoc, evalEco);
        System.out.println("\nÉquipe créée :");
        System.out.println(equipe);

        // Ajout des experts
        System.out.println("\nAjout des experts...");
        List<Secteur> compSante = new ArrayList<>();
        compSante.add(Secteur.SANTE);
        Expert expertSante = new Expert("Ines", "Z", "78 Rue chezElle",
                "0123456785", "i.z@ch.fr", compSante);
        equipe.ajouterExpert(expertSante);

        List<Secteur> compSport = new ArrayList<>();
        compSport.add(Secteur.SPORT);
        Expert expertSport = new Expert("Lydia", "N", "90 Avenue chezElle",
                "0123456784", "l.n@ch.fr", compSport);
        equipe.ajouterExpert(expertSport);

        System.out.println("\nÉquipe après ajout des experts :");
        System.out.println(equipe);

        // Lancement du cycle de simulation
        System.out.println("\nLancement du cycle de simulation...");
        equipe.cycleSimulation();

        System.out.println("\nÉquipe après simulation :");
        System.out.println(equipe);

        return equipe;
    }

    /**
     * Test du sac à dos
     * - Crée l'équipe municipale (pour avoir des projets)
     * - Propose 2 options :
     *   Option 1 : Conversion par coûts (économique, social, environnemental)
     *              ET conversion par secteurs (les 5 secteurs)
     *   Option 2 : Chargement depuis un fichier
     */
    private static void testerSacADos() {
        EquipeMunicipale equipe = testerEquipeMunicipale();

        int choix;
        do {
            System.out.println("1.Entrer les données depuis le clavier");
            System.out.println("2.Entrer les données depuis un fichier");
            System.out.println("Votre choix(1 ou 2):");
            choix = scanner.nextInt();
        } while (choix < 1 || choix > 2);

        VersSacADos vs = new VersSacADos();

        if (choix == 1) { //teste conversionParCouts et ParSecteurs
            Budget budget = new Budget();
            budget.saisieBudget();

            SacADos sacParCouts = vs.conversionParCouts(budget, equipe.getProjetsEtudies());
            System.out.println("Affichage sac a dos par coûts:\n");
            sacParCouts.afficheSac();
            System.out.println("\n\n");
            sacGlobal= sacParCouts;

            SacADos sacParSecteurs = vs.conversionParSecteur(budget, equipe.getProjetsEtudies());
            System.out.println("Affichage sac a dos par secteurs:\n");
            sacParSecteurs.afficheSac();
            System.out.println("\n\n");
            sacGlobal=sacParSecteurs;

        } else { //teste convertir
            String nomfichier;
            System.out.println("Fichiers disponibles:");
            System.out.println("hp1.dat");
            System.out.println("pb1.dat");
            System.out.println("weing1.dat");
            System.out.println("Taper le nom du fichier:");
            scanner.nextLine();//pour vider le buffer et ne pas lire une chaine vide
            nomfichier = scanner.nextLine().trim();
            SacADos sacFichier = vs.convertir(nomfichier);
            System.out.println("Affichage sac a dos du fichier:\n");
            sacFichier.afficheSac();
            sacGlobal = sacFichier;
        }
    }

    /**
     *Test du Glouton Ajout
     * - Crée un problème  avec 3 objets
     * - Applique l'algorithme glouton par ajout
     * - Affiche la solution trouvée
     */
    private static void testerGloutonAjout() {
        System.out.println("\n--- Test Glouton Ajout ---");

        // Création d'un problème
        Objet obj1 = new Objet("Projet A", 10, new int[]{3, 2, 1});
        Objet obj2 = new Objet("Projet B", 8, new int[]{2, 2, 2});
        Objet obj3 = new Objet("Projet C", 7, new int[]{1, 3, 1});

        List<Objet> objets = new ArrayList<>();
        objets.add(obj1);
        objets.add(obj2);
        objets.add(obj3);

        int[] budget = new int[]{5, 5, 3};
        SacADos sac = new SacADos(budget, objets);

        // Application du glouton ajout (tri par utilité décroissante)
        GloutonAjoutSolver solver = new GloutonAjoutSolver(
                (o1, o2) -> o2.getUtilite() - o1.getUtilite()
        );

        List<Objet> solution = solver.resoudre(sacGlobal);

        // Affichage de la solution
        System.out.println("\nSolution trouvée par ajout :");
        for (Objet o : solution) {
            System.out.println("  - " + o);
        }
        int utiliteTotal = solution.stream().mapToInt(Objet::getUtilite).sum();
        System.out.println("Utilité totale : " + utiliteTotal);
    }

    /**
     * Test du Glouton Retrait
     * - Crée un problème  avec 3 objets
     * - Applique l'algorithme glouton par retrait
     * - Affiche la solution trouvée
     */
    private static void testerGloutonRetrait() {
        System.out.println("\n--- Test Glouton Retrait ---");

        // Création d'un problème simple
        Objet obj1 = new Objet("Projet A", 10, new int[]{3, 2, 1});
        Objet obj2 = new Objet("Projet B", 8, new int[]{2, 2, 2});
        Objet obj3 = new Objet("Projet C", 7, new int[]{1, 3, 1});

        List<Objet> objets = new ArrayList<>();
        objets.add(obj1);
        objets.add(obj2);
        objets.add(obj3);

        int[] budget = new int[]{5, 5, 5};
        SacADos sac = new SacADos(budget, objets);

        // Application du glouton retrait
        GloutonRetraitSolver solver = new GloutonRetraitSolver(
                new Comparateur.ParUtiliteCroissante()
        );

        List<Objet> solution = solver.resoudre(sacGlobal);

        // Affichage de la solution
        System.out.println("\nSolution trouvée par retrait :");
        for (Objet o : solution) {
            System.out.println("  - " + o);
        }
        int utiliteTotal = solution.stream().mapToInt(Objet::getUtilite).sum();
        System.out.println("Utilité totale : " + utiliteTotal);
    }

    /**
     * Test du Hill Climbing
     * - Crée un problème avec 4 objets
     * - Génère une solution initiale avec le glouton ajout
     * - Améliore cette solution avec le Hill Climbing
     * - Affiche les résultats
     */
    private static void testerHillClimbing() {
        System.out.println("\n=== Test Hill Climbing ===");

        // Création d'un problème
        Objet obj1 = new Objet("Projet A", 10, new int[]{3, 2, 1});
        Objet obj2 = new Objet("Projet B", 8, new int[]{2, 2, 2});
        Objet obj3 = new Objet("Projet C", 7, new int[]{1, 3, 1});
        Objet obj4 = new Objet("Projet D", 5, new int[]{4, 1, 2});

        List<Objet> objets = new ArrayList<>();
        objets.add(obj1);
        objets.add(obj2);
        objets.add(obj3);
        objets.add(obj4);

        int[] budget = new int[]{5, 5, 5};
        SacADos sac = new SacADos(budget, objets);

        // Génération de la solution initiale avec glouton
        System.out.println("\nGénération de la solution initiale (glouton)...");
        GloutonAjoutSolver glouton = new GloutonAjoutSolver(
                (o1, o2) -> o2.getUtilite() - o1.getUtilite()
        );
        List<Objet> objetsGloutons = glouton.resoudre(sacGlobal);

        // Conversion en Solution (set d'indices)
        Set<Integer> indices = new HashSet<>();
        for (Objet obj : objetsGloutons) {
            // Chercher l'indice dans la liste originale du sac
            for (int i = 0; i < sacGlobal.getObjets().size(); i++) {
                if (sacGlobal.getObjets().get(i) == obj) {
                    indices.add(i);
                    break;
                }
            }
        }

        if (indices.isEmpty()) {
            System.out.println("Erreur : Aucun objet sélectionné par le glouton.");
            return;
        }
        Solution solutionInitiale = new Solution(indices);

        System.out.println("Utilité initiale : " + (int) solutionInitiale.getValeur());

        // Application du Hill Climbing
        System.out.println("\nAmélioration par Hill Climbing...");
        HillClimbingSolver hillClimbing = new HillClimbingSolver(sacGlobal, 1);
        Solution solutionFinale = hillClimbing.resoudre(solutionInitiale);

        // Affichage des résultats
        System.out.println("\n--- Résultats ---");
        System.out.println("Utilité finale    : " + (int) solutionFinale.getValeur());
        System.out.println("Amélioration      : +" + ((int) solutionFinale.getValeur() - (int) solutionInitiale.getValeur()));
        System.out.println("Itérations        : " + hillClimbing.getIterations());
        System.out.println("Temps d'exécution : " + hillClimbing.getTempsExecution() + " ms");

        System.out.println("\nObjets sélectionnés :");
        for (int indice : solutionFinale.getObjets()) {
            System.out.println("  - " + sacGlobal.getObjets().get(indice));
        }
    }
}