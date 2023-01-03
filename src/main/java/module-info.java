module cpoo.jeu_dactylo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.fxmisc.richtext;
    requires java.base;
    requires java.compiler;

    exports cpoo.jeu_dactylo.ig;
    //requires jeu_dactylo;

    //opens cpoo.jeu_dactylo to javafx.fxml;
    //exports jeu_dactylo;
    opens cpoo.jeu_dactylo;
}