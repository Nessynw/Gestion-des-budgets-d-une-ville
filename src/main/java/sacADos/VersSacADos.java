package sacADos;
import java.io.*;
import java.util.*;
import  java.lang.*;
import equipe.Projet;


public class VersSacADos{

    public SacADos conversionParCouts(Budget budget,List<Projet> projets) {
//Les budgets concernent les 3 différents types de coûts (économique, social, et environnemental)
        int[] tabBudgets=budget.getBudgetCouts();
        SacADos sac = new SacADos(tabBudgets,projets);
        return sac;
    }

    public SacADos conversionParSecteur(Budget budget,List<Projet> projets) {
//Les budgets concernent les 5 dfférents secteurs et seul le coût économique est considéré
        int[] tabBudgets=budget.getBudgetSecteurs();
        SacADos sac=new SacADos(tabBudgets,projets);
        return sac;
    }

    public SacADos convertir(String namefile){
        try {
            FileInputStream f = new FileInputStream(new File(namefile));
            BufferedReader reader = new BufferedReader(new FileReader(new File(namefile)));

            String line = reader.readLine(); //premiere ligne du fichier
            String[] valeurs = line.split(" ");
            int nbObjets = Integer.parseInt(valeurs[0]);
            int nbBudgets = Integer.parseInt(valeurs[1]);
            if (valeurs.length == 3) {
                int valOptimale = Integer.parseInt(valeurs[2]);
            } else {
                int valOptimale = 0;
            }

            List<Objet> objets = new ArrayList<>();

            //lignes des utilites de chaque objet
            int i = 0;
            while (i < nbObjets) {
                line = reader.readLine();
                String[] utilites = line.split(" ");
                for (int j = 0; j < utilites.length; j++) {
                    int val = Integer.parseInt(utilites[j]);
                    Objet o = new Objet("", val);
                    objets.add(o);
                }
                i += utilites.length;
            }

            //lire les couts pour chaque objet
            i = 0;
            int l = 0;//compteur nombre de couts selon le flux
            //int[] couts=new int[nbBudgets];
            List<String> coutslus = new ArrayList<>();
            while (i < nbObjets) {

                do {
                    line = reader.readLine();
                    String[] couts2 = line.split(" ");
                    Collections.addAll(coutslus, couts2);
                    l += coutslus.size();
                }
                while (l < nbBudgets);

                Objet o = objets.get(i);
                for (int j = 0; j < nbBudgets; j++) {
                    int val = Integer.parseInt(coutslus.get(j));
                    o.addCout(val);
                    coutslus.removeFirst();//on retire un cout a chaque fois qu'on le rajoute a l'objet
                }

                l -= nbBudgets;
                i++;
            }

            //lire les budgets
            line = reader.readLine();
            valeurs = line.split(" ");
            int[] budgets = new int[valeurs.length];
            for (i = 0; i < valeurs.length; i++) {
                budgets[i] = Integer.parseInt(valeurs[i]);
            }

            reader.close();
            f.close();
            SacADos sac = new SacADos(budgets, objets);
            return sac;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        SacADos sacvide=new SacADos();
        return sacvide;
    }



}



