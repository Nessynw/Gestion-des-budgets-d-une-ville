package sacADos;
import java.util.List;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import equipe.Projet;

public class VersSacADos{
    //A COMPLETER avec les méthodes des packages gloutons pour convertir en sacados

    public SacADos conversionParCouts(Budget budget,List<Projet> projets) {
//Les budgets concernent les 3 différents types de coûts (économique, social, et environnemental)
        int[] tabBudgets=budget.getBudgetCouts();
        SacADos sac=new SacADos(tabBudgets,projets);
        return sac;
    }

    public SacADos conversionParSecteur(Budget budget,(List<Projet> projets) {
//Les budgets concernent les 5 dfférents secteurs et seul le coût économique est considéré
        int[] tabBudgets=budget.getBudgetSecteurs();
        SacADos sac=new SacADos(tabBudgets,projets);
        return sac;
    }

    public SacADos convertir(String namefile){//pas encore fini
        try {
            FileInputStream f = new FileInputStream(new File(namefile));
            BufferedReader reader=new BufferedReader(new FileReader(new File(namefile))) ;

            String line=reader.readLine(); //premiere ligne du fichier
            String[] valeurs=line.split(" ");
            int nbObjets=Integer.parseInt(valeurs[0]);
            int nbBudgets=Integer.parseInt(valeurs[1]);
            if(valeurs.length()==3){
                int valOptimale=Integer.parseInt(valeurs[2]);
            }
            else{
                int valOptimale=0;
            }

            List<Objet> objets = new ArrayList<>();

            //lignes des utilites de chaque objet
            int i=0;
            while(i<nbObjets){
                String line=reader.readLine();
                String[] utilites=line.split(" ");
                for (int j=0;j<utilites.length();j++) {
                    int val=Integer.parseInt(utilites[j]);
                    Objet o=Objet("",val,[]);
                    objets.add(o);
                }
                i+=utilites.length();
            }

            //lire les couts pour chaque objet
            int i=0;
            while (i<nbObjets){
                int l=0;
                do{
                    String line=reader.readLine();
                    String[] couts=line.split(" ");
                    l+=couts.length();
                }
                while(couts.length()<nbBudgets);

                //A COMPLETER
                i++;
            }

            //lire les budgets
            String line=reader.readLine();
            String[] valeurs=line.split(" ");
            int[] budgets=new int[valeurs.length()];
            for (int i=0;i<valeurs.length();i++){
                budgets[i]=Integer.parseInt(valeurs[j]);
            }

            reader.close();

            SacADos sac=new SacADos(budgets,objets);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        f.close();
        return sac;
    }


}

