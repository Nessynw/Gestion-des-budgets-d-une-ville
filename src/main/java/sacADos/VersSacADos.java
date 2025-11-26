package sacADos;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import equipe.Projet;
import equipe.Secteur;

public class VersSacADos {

    //pour convertir chaque projet en objet qui a 3 coûts 
    public SacADos conversionParCouts(Budget budget, List<Projet> projets) {
        if (budget == null || projets == null) {
            throw new IllegalArgumentException("Le budget et la liste des projets ne peuvent pas être null");
        }
        //Récupèrer les budgets
        int[] tabBudgets = budget.getBudgetCouts();
        
        //on convertit chaque projet en objet
        List<Objet> objets = new ArrayList<>();
        for (Projet p : projets) {
            int[] couts = new int[]{
                (int)p.getCoutEconomique(),
                (int)p.getCoutSocial(),
                (int)p.getCoutEnvironnemental()
            };
            objets.add(new Objet(p.getTitre(), (int)p.getBenefice(), couts));
        }
        
        return new SacADos(tabBudgets, objets);
}

    //selon le secteur
    public SacADos conversionParSecteur(Budget budget, List<Projet> projets) {
    if (budget == null || projets == null) {
        throw new IllegalArgumentException("Le budget et la liste des projets ne peuvent pas être null");
    }
    
    // Récupère les budgets par secteur
    int[] tabBudgets = budget.getBudgetSecteurs();
    
    List<Objet> objets = new ArrayList<>();
    for (Projet p : projets) {
        // pr chaque projet, on crée un objet avec un coût correspondant à son secteur
        int[] couts = new int[Secteur.values().length];
        couts[p.getSecteur().ordinal()] = (int)p.getCoutEconomique();
        
        objets.add(new Objet(p.getTitre(), (int)p.getBenefice(), couts));
    }
    
    return new SacADos(tabBudgets, objets);
}


    public SacADos convertir(String namefile) {
        if (namefile == null) {
            throw new IllegalArgumentException("Le nom du fichier ne peut pas être null");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(namefile)))) {
            String line = reader.readLine(); //premiere ligne du fichier
            String[] valeurs = line.split(" ");
            if (valeurs.length < 2) {
                throw new IOException("Format de fichier invalide : il manque des informations sur la première ligne");
            }
            int nbObjets = Integer.parseInt(valeurs[0]);
            int nbBudgets = Integer.parseInt(valeurs[1]);
            int valOptimale = (valeurs.length == 3) ? Integer.parseInt(valeurs[2]) : 0;

            List<Objet> objets = new ArrayList<>();

            //lignes des utilites de chaque objet
            int i = 0;
            while (i < nbObjets) {
                line = reader.readLine();
                String[] utilites = line.split(" ");
                for (int j = 0; j < utilites.length; j++) {
                    int val = Integer.parseInt(utilites[j]);
                    Objet o = new Objet("Objet_" + i, val); // Ajout d'un nom significatif
                    objets.add(o);
                }
                i += utilites.length;
            }

            //lire les couts pour chaque objet
            i = 0;
            int l = 0;
            List<String> coutslus = new ArrayList<>();
            while (i < nbObjets) {
                do {
                    line = reader.readLine();
                    if (line == null) {
                        throw new IOException("Fin de fichier inattendue");
                    }
                    String[] couts2 = line.split(" ");
                    Collections.addAll(coutslus, couts2);
                    l += couts2.length;
                } while (l < nbBudgets);

                Objet o = objets.get(i);
                for (int j = 0; j < nbBudgets; j++) {
                    int val = Integer.parseInt(coutslus.get(0));
                    o.addCout(val);
                    coutslus.remove(0);
                }

                l -= nbBudgets;
                i++;
            }

            //lire les budgets
            line = reader.readLine();
            if (line == null) {
                throw new IOException("Fin de fichier inattendue");
            }
            valeurs = line.split(" ");
            int[] budgets = new int[valeurs.length];
            for (i = 0; i < valeurs.length; i++) {
                budgets[i] = Integer.parseInt(valeurs[i]);
            }

            return new SacADos(budgets, objets);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la lecture du fichier: " + e.getMessage());
        }
    }

}
