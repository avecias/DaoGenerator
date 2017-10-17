package com.avecias.daogenerator;

import com.avecias.daogenerator.commons.GeneratorException;
import com.avecias.daogenerator.controller.Generate;
import com.avecias.daogenerator.model.TemplateFactory;
import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

public class MainApp extends Application {

    private static void openFileChooser() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File directory = jFileChooser.getSelectedFile();
            new Generate().fromFileDirectory(directory);
        }
    }

    private static void go(String directory) {
        System.out.println(directory);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     * @throws com.avecias.daogenerator.commons.GeneratorException
     */
    public static void main(String[] args) throws GeneratorException {
        TemplateFactory templateFactory = TemplateFactory.getInstance();
        if (templateFactory == null) {
            throw new GeneratorException("Error no pudo iniciar el programa");
        }
        if (args != null && args.length == 1 && args[0] != null && !args[0].equals("")) {
            if (args.equals("-gui")) {
                launch(args);
            } else if (args.equals("-fc")) {
                openFileChooser();
            } else if (args.equals("-d")) {
                go(args[0]);
            } else {
                System.out.println("Menu");
            }
        } else {
            System.out.println("Sin argumentos");
            System.out.println("Menu");
            openFileChooser();
        }
    }

}
