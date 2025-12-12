package sacados;
import java.io.*;
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
        int[] tabBudgets = budget.getBudgetCouts();
        
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
            String line = reader.readLine().trim(); //premiere ligne du fichier en supprimant les tabulations
            String[] valeurs = line.split("\\s+");
            if (valeurs.length < 2) {
                throw new IOException("Format de fichier invalide : il manque des informations sur la première ligne");
            }
            int nbObjets = Integer.parseInt(valeurs[0]);
            int nbBudgets = Integer.parseInt(valeurs[1]);
            int valOptimale = (valeurs.length == 3) ? Integer.parseInt(valeurs[2]) : 0;

            //on lit les utilités
            List<Integer> utilites = new ArrayList<>();
            while (utilites.size() < nbObjets) {
                line = reader.readLine();
                if(line == null){
                    throw new IOException("Fin de fichier inattendue pour utilités");
                }
                line = line.trim();
                if (line.isEmpty()){
                    continue;
                }

                for (String s : line.split("\\s+")) {
                        utilites.add(Integer.parseInt(s));
                        if (utilites.size() == nbObjets) {
                            break;
                        }
                }
            }

            //on attribue chaque utilité à un objet
            List<Objet> objets = new ArrayList<>();
            for (int i = 0; i < nbObjets; i++) {
                objets.add(new Objet("Objet" + i, utilites.get(i), new int[nbBudgets]));
            }


            //lire tous les couts
            List<Integer> coutslus = new ArrayList<>();
            while ((coutslus.size() < nbObjets*nbBudgets)&&((line=reader.readLine())!=null)) {
                line = line.trim();//pr supprimer les tabulations
                if(line.isEmpty()){
                    continue;
                }
                String[] couts2 = line.split("\\s+");//\\s+ sont \n,\t,\r
                for (String s : couts2) {
                    coutslus.add(Integer.parseInt(s));
                    if (coutslus.size() == nbBudgets * nbObjets) {
                        break;
                    }
                }
            }

            if (coutslus.size() < nbObjets*nbBudgets) {
                throw new IOException("Fin de fichier inattendue pour les coûts");
            }


            //attribuer les coûts pour chaque objet
            int i=0;
            while(i<nbObjets){
                Objet o = objets.get(i);
                for(int j=0;j<nbBudgets;j++){
                    int val = coutslus.get(i+nbObjets*j);
                    if(j==0){ //pour le premier coût
                        int[] premier=new int[]{val};
                        o.setCouts(premier);
                    }
                    else{
                        o.addCout(val);
                    }
                }
                i++;
            }

            //lire les budgets
            line = reader.readLine();
            if (line == null) {
                throw new IOException("Fin de fichier inattendue pour les budgets");
            }
            line=line.trim();
            valeurs = line.split("\\s+");
            if (valeurs.length != nbBudgets) {
                throw new IOException("Nombre incorrect de budgets dans le fichier");
            }
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
