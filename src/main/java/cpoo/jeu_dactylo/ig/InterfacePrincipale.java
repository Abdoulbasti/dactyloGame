package cpoo.jeu_dactylo.ig;

import cpoo.jeu_dactylo.constantes.*;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.*;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.fxmisc.richtext.StyledTextArea;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.net.URL;

public class InterfacePrincipale extends Application {

    public int postionDebut;
    public int postionFin;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try
        {
            VBox root = new VBox();

            StyleClassedTextArea textArea1 = new StyleClassedTextArea();
            StyleClassedTextArea textArea2 = new StyleClassedTextArea();
            textArea2.replaceText(Paragraphes.PARAGRAPHE_2);

            //textArea1.setStyle("-fx-font-size: 20px; -fx-background-color: white; -fx-text-fill: red");
            //textArea2.setStyle("-fx-font-size: 20px; -fx-background-color: gray");
            textArea1.setId("textformes1");
            textArea2.setId("textformes2");

            textArea1.setPrefSize(280, 280);
            textArea2.setPrefSize(280, 280);

            textArea2.setEditable(false);
            textArea1.setWrapText(true);
            textArea2.setWrapText(true);

            textArea2.setStyleClass(0, 50, "couleurTextMotsTaper");


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

    //public void actionSupprimerCaractere()
    public void actionSupprimerCaractere()
    {

    }

    public void action(KeyEvent e)
    {
        System.out.println("Un caractère est taper");
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}





          /*int start = textArea2.getSelection().getStart();
            int end = textArea2.getSelection().getEnd();

            //textArea1.setOnKeyPressed((e) ->action(e));

            textArea1.textProperty().addListener(
                    (observable, ancienValeur, nouvelleValeur)->
                    {
                        System.out.println(observable.getValue());
                        //System.out.println(observable.);
                        if (nouvelleValeur.length() < ancienValeur.length()) {
                            System.out.println("Un caractère a été supprimé !");
                        }
                        else
                        {
                            System.out.println("Un caractère est ajouté !");
                        }
                    }
            );
            textArea1.setOnInputMethodTextChanged(event -> {
                System.out.println("Du texte a été entré dans la zone de texte");
            });

            //Important recuperer les elements caractère par caractère
            textArea1.setOnKeyTyped(e ->
                    {
                        char entree = 13;
                        if( (Character.compare(entree, ((e.getCharacter()).charAt(0)))) == 0 )
                        {
                            System.out.println("entree ......");
                        }
                    });

            textArea1.setOnInputMethodTextChanged(event -> {
                System.out.println("Du texte a été entré dans la zone de texte");
            });*/