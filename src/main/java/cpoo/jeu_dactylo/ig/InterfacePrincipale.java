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
    public static int [] taillesDesMots = Files.stockerTaillesMots();
    public static int debutColorationJaune = 0;
    public static int finColorationJaune = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try
        {
            VBox root = new VBox();

            StyleClassedTextArea textArea1 = new StyleClassedTextArea();
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

            gestionArrierePlanTexteCorrecte(textArea1, textArea2);

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


    public void gestionArrierePlanTexteCorrecte(StyleClassedTextArea textArea1, InlineCssTextArea textArea2)
    {
        int[] k ={0};
        int i = 0;
        String yellowBackground = "-rtfx-background-color: yellow";
        debutColorationJaune =0;
        finColorationJaune = taillesDesMots[0];
        textArea2.setStyle(debutColorationJaune, finColorationJaune, yellowBackground);

        textArea1.setOnKeyTyped(e ->
        {
            String s = e.getCharacter();    char c = s.charAt(0);
            //Récupération de la position du curseur
            int caretPosition = textArea1.getCaretPosition();


            ignorerCaractereENTREE(textArea1,c, 13);
            ignorerCaractereESPACE(textArea1, c, 32,caretPosition);
            if(Integer.compare(c, 32) == 0)
            {
                //ignorerEntree(textArea1, c, 32, avantDernierChar);
                debutColorationJaune    =   debutColorationJaune     +      taillesDesMots[k[0]]+1;
                finColorationJaune      =   debutColorationJaune     +      taillesDesMots[k[0] +1];
                textArea2.setStyle(debutColorationJaune, finColorationJaune, yellowBackground);
                k[0]++;
                if(k[0] == Files.stringsParagraphe.length-1)
                {
                    System.out.println("Limite des mots est atteint !!");
                }
            }
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

        if (caretPosition <= 2 && caretPosition >=0)
        {
            String text = textArea1.getText();
            char avantDernierChar = text.charAt(caretPosition-1);

            if(Character.toString(text.charAt(0)).equals(" "))
            {
                textArea1.insertText(caretPosition -1, "");
                textArea1.deleteText(caretPosition-1, caretPosition);
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

    public void gestionArrierePlanMotIncorrect()
    {
        //int i = taillesDesMots[0];
    }


    public static void main(String[] args) {
        //System.out.println("la tailles est : " +taillesDesMots[6]);
        Application.launch(args);
    }
}