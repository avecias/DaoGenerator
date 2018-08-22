package com.avecias.daogenerator;

import com.avecias.daogenerator.commons.GeneratorException;
import com.avecias.daogenerator.model.DaoGenerator;
import javafx.application.Application;
//import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static void openFileChooser() {
        System.out.println("With DirectoryChooser");
    }

    private static void go(String directory) {
        System.out.println("Start with arguments directory: " + directory);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setResizable(false);
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
        DaoGenerator daoGenerator = DaoGenerator.getInstance();
        if (daoGenerator == null) {
            throw new GeneratorException("Error no pudo iniciar el programa");
        }
        if (args != null && args.length == 1 && args[0] != null && !args[0].equals("")) {
            System.out.println(args[0]);
            switch (args[0]) {
                case "-g":
                    launch(args);
                    break;
                case "-c":
                    openFileChooser();
                    break;
                case "-d":
                    go(args[0]);
                    break;
                default:
                    System.out.println("Menu");
                    break;
            }
        } else {
            System.out.println("Sin argumentos");
            System.out.println("Menu");
            openFileChooser();
        }
        System.exit(0);
    }

}
