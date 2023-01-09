package cpoo.jeu_dactylo.ig;
import cpoo.jeu_dactylo.constantes.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.fxmisc.richtext.InlineCssTextArea;
import org.fxmisc.richtext.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import cpoo.jeu_dactylo.files.*;

import java.net.URL;
import java.util.Arrays;

public class InterfacePrincipale extends Application {

    public int postionDebut;
    public int postionFin;
    public static int RETOUR = 13;
    public static int ESPACE = 32;
    public static int SUPPRIMER = 127;
    public static int [] taillesDesMots = Files.stockerTaillesMots();
    public static int debutColorationJaune = 0, finColorationJaune = 0;
    public static int debutColorationRouge = 0, finColorationRouge = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try
        {
            Files.initialiserTableauxString(Files.chaineReelementTaper);
            VBox root = new VBox();
            //StyleClassedTextArea textArea1 = new StyleClassedTextArea();
            InlineCssTextArea textArea1 = new InlineCssTextArea();
            InlineCssTextArea textArea2 = new InlineCssTextArea(Paragraphes.PARAGRAPHE_1);
            //textArea1.setStyle("-fx-font-size: 20px; -fx-background-color: white; -fx-text-fill: red");
            //textArea2.setStyle("-fx-font-size: 20px; -fx-background-color: gray");
            textArea1.setId("textformes1");
            textArea2.setId("textformes2");
            //field.setId("textformes3");

            textArea1.setPrefSize(280, 280);
            textArea2.setPrefSize(280, 280);

            textArea2.setEditable(false);
            textArea1.setWrapText(true);
            textArea2.setWrapText(true);


            //gestionArrierePlanTextes(textArea1, textArea2);


            root.getChildren().addAll(textArea1, textArea2);
            Scene scene = new Scene(root, 900, 600);


            URL url = getClass().getResource("/application.css");
            String path = url.toExternalForm();
            scene.getStylesheets().add(path);
            primaryStage.setTitle("DACTYLO-GAME");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void actionOnprecced()
    {

    }

    public void actionSupprimerCaractere()
    {

    }

    public void action(KeyEvent e)
    {
        System.out.println("Un caractère est taper");
    }


    public void gestionArrierePlanTextes(StyleClassedTextArea textArea1, InlineCssTextArea textArea2)
    {

            /*String redBackground = "-rtfx-background-color: red";
            debutColorationRouge =0;
            finColorationRouge = taillesDesMots[0];
            textArea2.setStyle(debutColorationJaune, finColorationJaune, redBackground);*/
            String yellowBackground = "-rtfx-background-color: yellow";
            debutColorationJaune =0;
            finColorationJaune = taillesDesMots[0];
            textArea2.setStyle(debutColorationJaune, finColorationJaune, yellowBackground);


            textArea1.setOnKeyTyped(e ->
            {
                String s = e.getCharacter();    char c = s.charAt(0);
                String chaine = textArea1.getText();
                int positionCurseur = textArea1.getCaretPosition();
                if (Integer.compare(c, RETOUR) == 0) {
                    textArea1.deletePreviousChar();
                }
                if(positionCurseur == 1)
                {
                    if (s.equals(" ")) {
                        textArea1.deletePreviousChar();
                    }
                }
                if(positionCurseur >= 2)
                {
                    char charAtPosAnterieur = chaine.charAt(positionCurseur-2);
                    if(Integer.compare(charAtPosAnterieur, ESPACE) == 0)
                    {
                        if(s.equals(" ")){
                            textArea1.deletePreviousChar();
                            e.consume();//empechement de se propager

                            System.out.println("Test...");
                        }
                    }
                }

                /*if(Integer.compare(c, ESPACE) == 0)
                {

                }*/

                //AJOUT DU CARACTERE
                /*if(Files.positionMots < Files.stringsParagraphe.length &&
                        Integer.compare(c, RETOUR)      !=0)
                {
                    if(Integer.compare(c, SUPPRIMER) ==0)
                    {
                        Files.retirerCaractere(Files.positionMots);
                        System.out.println(Files.chaineReelementTaper[Files.positionMots]);
                    }
                    else
                    {
                        Files.ajouterCaractere(Files.positionMots, c);
                        System.out.println(Files.chaineReelementTaper[Files.positionMots]);
                    }
                }*/
            });
    }

    /*On ignore le char ayant le code ascci codeAscciCharAIgnore*/
    //test ok
    public void ignorerCaractereENTREE(StyleClassedTextArea textArea1, char c,  int codeAscciCharAIgnore)
    {

        if(Integer.compare(c, codeAscciCharAIgnore) == 0)
        {
            //Récupération de la position du curseur
            int caretPosition = textArea1.getCaretPosition();
            // Insertion du nouveau caractère à la position du curseur
            textArea1.insertText(caretPosition-1, "");
            textArea1.deleteText(caretPosition -1, caretPosition);
            //System.out.println("pos est : " +caretPosition);
        }
    }

    //Test ok
    public void ignorerCaractereESPACE(StyleClassedTextArea textArea1, char c,  int codeAscciCharAIgnore , int caretPosition)
    {
        if(caretPosition >=2 )
        {
            String text = textArea1.getText();
            char avantDernierChar = text.charAt(caretPosition-2);

            //Verifer que le qu'il 2 espace successive...
            if(Integer.compare(c, codeAscciCharAIgnore) == 0 && Integer.compare(avantDernierChar, codeAscciCharAIgnore) == 0)
            {
               // System.out.println("on 2 espace qui se suivent...");
                textArea1.insertText(caretPosition-1, "");
                textArea1.deleteText(caretPosition -1, caretPosition);
            }
        }
        else if (caretPosition <= 2 && caretPosition >=0)
        {
            String text = textArea1.getText();
            //char avantDernierChar = text.charAt(caretPosition-1);
            char avantDernierChar = text.charAt(caretPosition);

            //if(Character.toString(text.charAt(0)).equals(" "))
            if(Character.toString(text.charAt(0)).equals(" "))
            {
                //textArea1.insertText(caretPosition -1, "");
                //textArea1.deleteText(caretPosition-1, caretPosition);

                textArea1.insertText(caretPosition, "");
                textArea1.deleteText(caretPosition, caretPosition);
            }
        }
    }

    public void ignorerCaractereEFFACE()
    {

    }



    /*Empêcher de faire */
    public void espaceUneFois()
    {

    }


    void ignorerCertainCaractere()
    {

    }

    //Arriere plan rouge pour les mots incorrects
    public void gestionArrierePlanMotIncorrect(InlineCssTextArea  textArea2, char c, String redBackground)
    {
        if(Integer.compare(c, ESPACE) == 0)
        {
            debutColorationRouge   =   debutColorationRouge     +      taillesDesMots[Files.positionMots]+1;
            finColorationRouge      =   debutColorationRouge     +      taillesDesMots[Files.positionMots +1];
            textArea2.setStyle(debutColorationJaune, finColorationJaune, redBackground);
            System.out.println("Le mot reelement taper est : " +Files.chaineReelementTaper[Files.positionMots]);
            Files.positionMots++;
            if(Files.positionMots == Files.stringsParagraphe.length-1)
            {
                System.out.println("Limite des mots est atteint !!");
            }
        }
    }

    //Arriere plan jaune pour mot à taper et qui est correct
    public void gestionArrierePlanMotCorrect(InlineCssTextArea  textArea2, char c, String yellowBackground)
    {
        if(Integer.compare(c, ESPACE) == 0)
        {
            //ignorerEntree(textArea1, c, 32, avantDernierChar);
            debutColorationJaune    =   debutColorationJaune     +      taillesDesMots[Files.positionMots]+1;
            finColorationJaune      =   debutColorationJaune     +      taillesDesMots[Files.positionMots +1];
            textArea2.setStyle(debutColorationJaune, finColorationJaune, yellowBackground);
            System.out.println("Le mot reelement taper est : " +Files.chaineReelementTaper[Files.positionMots]);
            Files.positionMots++;
            if(Files.positionMots == Files.stringsParagraphe.length-1)
            {
                System.out.println("Limite des mots est atteint !!");
            }
        }
    }


    public static void main(String[] args) {
        //System.out.println("la tailles est : " +taillesDesMots[6]);
        Application.launch(args);
    }
}




/*
*
                /*GESTION COULEUR JAUNE
                if()
                {

                }*/



                /*if(positionCurseur == 0)
                {
                    if (s.equals(" ")) {
                        textArea1.deletePreviousChar();
                    }
                }*/

                    /*String s = e.getCharacter();    char c = s.charAt(0);
                    //Récupération de la position du curseur
                    int caretPosition = textArea1.getCaretPosition();


                    ignorerCaractereENTREE(textArea1,c, RETOUR);
                    ignorerCaractereESPACE(textArea1, c, ESPACE, caretPosition);

                    if(Files.positionMots < Files.stringsParagraphe.length &&
                            Integer.compare(c, RETOUR)!=0 &&
                            Integer.compare(c, ESPACE)!=0 &&
                            Integer.compare(c, SUPPRIMER)!=0    )
                    {
                        //System.out.println("TEST...");
                        Files.ajouterCaractere(Files.positionMots, c);
                    }

                    int tailleChaineTape = Files.chaineReelementTaper[Files.positionMots].length();
                    System.out.println("LA TAILLE EST :" +tailleChaineTape);

                    if (tailleChaineTape < Files.stringsParagraphe[Files.positionMots].length())
                    {
                        String sousChaineATaper = Files.stringsParagraphe[Files.positionMots].substring(0, tailleChaineTape);
                        if(sousChaineATaper.equals(Files.chaineReelementTaper[Files.positionMots]))
                        {
                            gestionArrierePlanMotCorrect(textArea2, c, yellowBackground);
                        }
                        else
                        {
                            System.out.println("LE MOT EST EN ROUGE...");
                        }
                    }
                    else
                    {
                        System.out.println("VOUS AVEZ DEPASSER LA TAILLE REELLE DU LA CHAINE....");
                    }*/

                    /*if(     Files.stringsParagraphe[Files.positionMots].equals(Files.chaineReelementTaper[Files.positionMots])
                            ||
                            Files.chaineReelementTaper[Files.positionMots].equals(""))*/

                    /*if(sousChaineATaper.equals((Files.chaineReelementTaper[Files.positionMots]))    )
                    {
                        gestionArrierePlanMotCorrect(textArea2, c, yellowBackground);
                    }*/


                    /*if(!sousChaineATaper.equals((Files.chaineReelementTaper[Files.positionMots]))    )
                    {
                        gestionArrierePlanMotIncorrect(textArea2, c, redBackground);
                    }*/


                    /*if(             Files.positionMots < Files.stringsParagraphe.length &&
                                    !(Files.chaineReelementTaper[Files.positionMots].equals(Files.stringsParagraphe[Files.positionMots]))   )*/

                    /*if(             Files.positionMots < Files.stringsParagraphe.length &&
                            !(Files.chaineReelementTaper[Files.positionMots].equals(sousChaineATaper))   )
                    {
                        //Mettre au rouge
                        //System.out.println("LES CHAINES NE SONT PAS PAREILS...");
                        gestionArrierePlanMotIncorrect(textArea2, redBackground);
                    }*/
