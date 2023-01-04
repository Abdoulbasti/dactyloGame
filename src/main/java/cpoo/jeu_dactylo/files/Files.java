package cpoo.jeu_dactylo.files;

import cpoo.jeu_dactylo.constantes.Paragraphes;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Files {

    final static int   MAX_TAILLE_FILLE_PREMIERE = 15;
    final static int   MAX_TAILLE_FILLE_SECONDAIRE = 40;
    public static int positionMots = 0; //La position de depart doit être 0

    public static String [] stringsParagraphe = Paragraphes.PARAGRAPHE_2.split(" ");

    /*Ceci contient les elements reelement taper par l'utilisateur chacun à la position corespondant au element qui est censé être taper
    * Cela va nous faciité la comparaison entre les 2 chaines stringsParagraphe[i] et chaineReelementTaper[i] et de faire la mise a jour des erreurs sur l'ecran.
    * */
    public static String [] chaineReelementTaper = new String[stringsParagraphe.length];

    //Stocke la taille de chaque de chaque mot à sa position
    final static int [] taillesMots = new int[stringsParagraphe.length];

    public static ArrayDeque<String> filePremiere = new ArrayDeque<>(MAX_TAILLE_FILLE_PREMIERE);
    public static ArrayDeque<String> fileSecondaire = new ArrayDeque<>(MAX_TAILLE_FILLE_SECONDAIRE);
    //LesFiles queue = new LesFiles();

    //final static int numeroEspace =

    //Tableau contenat les surplus de chaque qui est censé être taper
    public static String[] overSizeTableaux = new String[stringsParagraphe.length];

    public static void main(String[] args) {
        remplissagePremiereFile(filePremiere);
        //affichageMotsQueue(filePremiere);
        //System.out.println(positionMots);
        //System.out.println(stringsParagraphe[positionMots-1]);
        //actionMotSupprime();
        /*System.out.println(filePremiere.pollFirst());
        System.out.println(filePremiere.pollFirst());
        System.out.println(filePremiere.pollFirst());
        System.out.println(filePremiere.pollFirst());*/
        //System.out.println(stringsParagraphe[ positionMots -1]);

        //stockerTaillesMots();
        //System.out.println(taillesMots[2]);
        //System.out.println(stringsParagraphe[8]);
        initialiserTableauxString(chaineReelementTaper);
        ajouterCaractere(0, 'S');
        ajouterCaractere(0, 'A');
        ajouterCaractere(0, 'L');
        ajouterCaractere(0, 'U');
        ajouterCaractere(0, 'T');
        retirerCaractere(0);
        retirerCaractere(0);
        ajouterCaractere(0, 'M');
        System.out.println(chaineReelementTaper[0]);
    }


    //Test ok
    public static void remplissagePremiereFile(Queue<String> file)
    {
        //String[] words = str.split("\\s+");//str.split(" ");
        for (String word : stringsParagraphe) {
            if(file.size() < MAX_TAILLE_FILLE_PREMIERE)
            {
                file.add(word);
                positionMots++;
            }
        }
    }

    /*
    Lorsque le mot courant est validé.
    Enlever un element de la filePremiere et le rajouter dans la fileSecondaire, et ajouter l'element à l'indice suivant à la filePremiere*/
    //public static void enleverEtRemetreAutreElementFilePremiere(String[] tabTousMots)

    //Test ok
    public static void actionMotValide()
    {
        try {
            //Enlever l'element en tête de file
            String enleve = filePremiere.pollFirst();
            if(enleve!=null)
            {
                positionMots++;
                //stockerElementDansFileSecondaire(enleve); //On en a pas besoin

                //On arrête dès que positionMotsSuivant > stringsParagraphe.length
                if(positionMots <= stringsParagraphe.length)
                {
                    //Ajouter un element à la queue de la file
                    filePremiere.add(stringsParagraphe[positionMots - 1]);
                }
                else
                {
                    System.out.println("la limite des mots est atteint");
                }
            }
            else
            {
                System.out.println("la filePremiere est vide");
                System.out.println(enleve);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    /*
    Lorsque le mot est supprimer de la ligne

    Enlever un element de la fileSecondaire et le rajouter a la file premiere, et decrementer l'indice de position
    * */
    //Test ok
    public static void actionMotSupprime()
    {
        try
        {
            //Retirer la queue de la liste avec pollLast
            String enleve = filePremiere.pollLast();
            if(enleve != null)
            {
                //Si on est à 0, c'est le tous premier mot
                if(positionMots >= 2)
                {
                    positionMots--;
                }
                //Ajouter un element à la tête de la file
                int pos = positionMots -1;
                if(pos >=0)
                {
                    boolean bool = filePremiere.offerFirst(stringsParagraphe[pos]);
                    if(!bool){
                        System.out.println("L'ajout de l'element en tête de file à echoué...");
                    }
                }
            }
            else
            {
                System.out.println("La file est vide ");
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }



    public static void stockerElementDansFileSecondaire(String element)
    {
        fileSecondaire.add(element);
    }


    //test ok
    public static void affichageMotsQueue(Queue<String> file)
    {
        while (!file.isEmpty()) {
            System.out.println(file.poll());
        }
    }


    //Test ok
    public static void stockerTaillesMots()
    {

        for( int i = 0; i<stringsParagraphe.length ; i++)
        {
            taillesMots[i] = stringsParagraphe[i].length();
        }
    }


    //test ok
    public static void ajouterCaractere(int indiceString, char caractereAjouter)
    {

        chaineReelementTaper[indiceString] = chaineReelementTaper[indiceString].concat(Character.toString(caractereAjouter));
    }

    //test ok
    public static void retirerCaractere(int indiceString)
    {
        chaineReelementTaper[indiceString] = chaineReelementTaper[indiceString].substring(0, chaineReelementTaper[indiceString].length() - 1);
    }


    //test ok
    public static void initialiserTableauxString(String[] tab)
    {
        Arrays.fill(tab, "");
    }
}
