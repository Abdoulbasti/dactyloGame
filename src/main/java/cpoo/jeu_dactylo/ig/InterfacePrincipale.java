package cpoo.jeu_dactylo.ig;
import cpoo.jeu_dactylo.constantes.*;
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
        String yellowBackground = "-rtfx-background-color: yellow";
        debutColorationJaune =0;
        finColorationJaune = taillesDesMots[0];
        textArea2.setStyle(debutColorationJaune, finColorationJaune, yellowBackground);
        int i = 0;
        textArea1.setOnKeyTyped(e ->
        {
            char c = e.getCharacter().charAt(0);
            if(Integer.compare(c, 32) == 0)
            {
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

    public void gestionArrierePlanMotIncorrect()
    {
        //int i = taillesDesMots[0];
    }


    public static void main(String[] args) {
        //System.out.println("la tailles est : " +taillesDesMots[6]);
        Application.launch(args);
    }
}