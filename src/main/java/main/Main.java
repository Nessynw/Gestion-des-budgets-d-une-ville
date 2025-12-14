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
    static EquipeMunicipale equipeGlobale = null;

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
        if (equipeGlobale != null) System.out.println("✓ Équipe municipale créée");
        if (sacGlobal != null) System.out.println("✓ Sac à dos créé");
        System.out.println();
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
        Evaluateur evalEnv = new Evaluateur("Alice", "B", "12 Rue rue",
                "0123456789", "alice.b@ville.fr", TypeCout.ENVIRONNEMENTAL);
        Evaluateur evalSoc = new Evaluateur("Claire", "D", "34 Avenue avenue",
                "0123456788", "claire.d@ville.fr", TypeCout.SOCIAL);
        Evaluateur evalEco = new Evaluateur("Eric", "F", "56 Boulevard blvd",
                "0123456787", "eric.f@ville.fr", TypeCout.ECONOMIQUE);

        Elu elu = new Elu("Elena", "R", "Mairie Principale",
                "0123456786", "E.r@ville.fr");

        EquipeMunicipale equipe = new EquipeMunicipale(elu, evalEnv, evalSoc, evalEco);
        System.out.println("\nÉquipe créée :");
        System.out.println(equipe);
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

        System.out.println("\nLancement du cycle de simulation...");
        equipe.cycleSimulation();

        System.out.println("\nÉquipe après simulation :");
        System.out.println(equipe);

        equipeGlobale = equipe;
        return equipe;
    }

    /**
     * Test du sac à dos
     * Crée l'équipe municipale (pour avoir des projets)
     * Propose 2 options pour créer le sac à dos:
     *  Option 1 : Conversion depuis clavier (par coûts OU par secteurs selon choix utilisateur)
     *  Option 2 : Chargement depuis un fichier
     */
    private static SacADos testerSacADos() {
        // Récupérer / créer l'équipe
        EquipeMunicipale equipe;
        if (equipeGlobale == null) {
            System.out.println("Création d'une nouvelle équipe..");
            equipe = testerEquipeMunicipale();
        } else {
            System.out.println("Utilisation de l'équipe existante.");
            equipe = equipeGlobale;
        }

        int choix;
        do {
            System.out.println("\n1. Entrer les données depuis le clavier");
            System.out.println("2. Entrer les données depuis un fichier");
            System.out.print("Votre choix (1 ou 2): ");
            choix = scanner.nextInt();
            scanner.nextLine();
        } while (choix < 1 || choix > 2);

        VersSacADos vs = new VersSacADos();

        if (choix == 1) {
            // Saisie du budget (demande le mode : coûts ou secteurs)
            Budget budget = new Budget();
            budget.saisieBudget();

            // Déterminer automatiquement le mode choisi par l'utilisateur
            boolean modeCouts = estModeCouts(budget);

            if (modeCouts) {
                SacADos sacParCouts = vs.conversionParCouts(budget, equipe.getProjetsEtudies());
                System.out.println("\nAffichage sac à dos par coûts:");
                sacParCouts.afficheSac();
                sacGlobal = sacParCouts;
            } else {
                SacADos sacParSecteurs = vs.conversionParSecteur(budget, equipe.getProjetsEtudies());
                System.out.println("\nAffichage sac à dos par secteurs:");
                sacParSecteurs.afficheSac();
                sacGlobal = sacParSecteurs;
            }

        } else {
            // Chargement depuis fichier
            String nomfichier;
            List<String> fichiers = new ArrayList<>(Arrays.asList("hp1.dat", "pb1.dat", "weing.dat"));
            System.out.println("\nFichiers disponibles:");
            for (String fichier : fichiers) {
                System.out.println("  - " + fichier);
            }

            do {
                System.out.print("Taper le nom du fichier: ");
                nomfichier = scanner.nextLine().trim();
                if (!fichiers.contains(nomfichier)) {
                    System.out.println("Fichier non reconnu. Veuillez réessayer.");
                }
            } while (!fichiers.contains(nomfichier));

            SacADos sacFichier = vs.convertir(nomfichier);
            System.out.println("\nAffichage sac à dos du fichier:");
            sacFichier.afficheSac();
            sacGlobal = sacFichier;
        }

        return sacGlobal;
    }

    /**
     * Détermine si le budget utilise le mode "par coûts" ou "par secteurs"
     * @param budget Le budget à analyser
     * @return true si mode par coûts, false si mode par secteurs
     */
    private static boolean estModeCouts(Budget budget) {
        int[] secteurs = budget.getBudgetSecteurs();
        int sommeSecteurs = 0;
        for (int s : secteurs) {
            sommeSecteurs += s;
        }
        return sommeSecteurs == 0;
    }

    /**
     * Test du Glouton Ajout
     * - Applique l'algorithme glouton par ajout
     * - Affiche la solution trouvée
     */
    private static void testerGloutonAjout() {
        System.out.println("\n--- Test Glouton Ajout ---");

        if (!verifierSacInitialise()) {
            return;
        }

        // Application du glouton ajout (tri par utilité décroissante)
        GloutonAjoutSolver solver = new GloutonAjoutSolver(
                (o1, o2) -> o2.getUtilite() - o1.getUtilite()
        );

        List<Objet> solution = solver.resoudre(sacGlobal);

        // Affichage de la solution
        System.out.println("\nSolution trouvée par ajout:");
        for (Objet o : solution) {
            System.out.println("  - " + o);
        }
        int utiliteTotal = solution.stream().mapToInt(Objet::getUtilite).sum();
        System.out.println("Utilité totale: " + utiliteTotal);
        if (sacGlobal.getValOptimale() != 0) {
            System.out.println("Utilité optimale selon le fichier: " + sacGlobal.getValOptimale());
        }
    }

    /**
     * Test du Glouton Retrait
     * - Applique l'algorithme glouton par retrait
     * - Affiche la solution trouvée
     */
    private static void testerGloutonRetrait() {
        System.out.println("\n--- Test Glouton Retrait ---");

        if (!verifierSacInitialise()) {
            return;
        }

        // Application du glouton retrait
        GloutonRetraitSolver solver = new GloutonRetraitSolver(
                new Comparateur.ParUtiliteCroissante()
        );

        List<Objet> solution = solver.resoudre(sacGlobal);

        // Affichage de la solution
        System.out.println("\nSolution trouvée par retrait:");
        for (Objet o : solution) {
            System.out.println("  - " + o);
        }
        int utiliteTotal = solution.stream().mapToInt(Objet::getUtilite).sum();
        System.out.println("Utilité totale: " + utiliteTotal);
        if (sacGlobal.getValOptimale() != 0) {
            System.out.println("Utilité optimale selon le fichier: " + sacGlobal.getValOptimale());
        }
    }

    /**
     * Test du Hill Climbing
     * - Génère une solution initiale avec le glouton ajout
     * - Améliore cette solution avec le Hill Climbing
     * - Affiche les résultats
     */
    private static void testerHillClimbing() {
        System.out.println("\n=== Test Hill Climbing ===");

        if (!verifierSacInitialise()) {
            return;
        }

        // Génération de la solution initiale avec glouton
        System.out.println("\nGénération de la solution initiale (glouton)...");
        GloutonAjoutSolver glouton = new GloutonAjoutSolver(
                (o1, o2) -> o2.getUtilite() - o1.getUtilite()
        );
        List<Objet> objetsGloutons = glouton.resoudre(sacGlobal);

        // Conversion en Solution (set d'indices)
        Set<Integer> indices = new HashSet<>();
        for (Objet obj : objetsGloutons) {
            for (int i = 0; i < sacGlobal.getObjets().size(); i++) {
                if (sacGlobal.getObjets().get(i) == obj) {
                    indices.add(i);
                    break;
                }
            }
        }

        if (indices.isEmpty()) {
            System.out.println("Erreur: Aucun objet sélectionné par le glouton.");
            return;
        }

        Solution solutionInitiale = new Solution(indices);
        System.out.println("Utilité initiale: " + (int) solutionInitiale.getValeur());

        // Application du Hill Climbing
        System.out.println("\nAmélioration par Hill Climbing...");
        HillClimbingSolver hillClimbing = new HillClimbingSolver(sacGlobal, 1);
        Solution solutionFinale = hillClimbing.resoudre(solutionInitiale);

        // Affichage des résultats
        System.out.println("\n--- Résultats ---");
        System.out.println("Utilité finale   : " + (int) solutionFinale.getValeur());
        if (sacGlobal.getValOptimale() != 0) {
            System.out.println("Utilité optimale : " + sacGlobal.getValOptimale());
        }
        System.out.println("Amélioration     : +" + ((int) solutionFinale.getValeur() - (int) solutionInitiale.getValeur()));
        System.out.println("Itérations       : " + hillClimbing.getIterations());
        System.out.println("Temps d'exécution: " + hillClimbing.getTempsExecution() + " ms");

        System.out.println("\nObjets sélectionnés:");
        for (int indice : solutionFinale.getObjets()) {
            System.out.println("  - " + sacGlobal.getObjets().get(indice));
        }
    }
}