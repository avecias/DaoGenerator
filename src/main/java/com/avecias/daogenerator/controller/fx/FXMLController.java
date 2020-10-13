package com.avecias.daogenerator.controller.fx;

import com.avecias.daogenerator.commons.GeneratorException;
import com.avecias.daogenerator.commons.UtilGenerator;
import com.avecias.daogenerator.model.DaoGenerator;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class FXMLController implements Initializable {

    private static final Logger LOG = Logger.getLogger(FXMLController.class);
    // (?!^abstract$|^abstract\..*|.*\.abstract\..*|.*\.abstract$|^assert$|^assert\..*|.*\.assert\..*|.*\.assert$|^boolean$|^boolean\..*|.*\.boolean\..*|.*\.boolean$|^break$|^break\..*|.*\.break\..*|.*\.break$|^byte$|^byte\..*|.*\.byte\..*|.*\.byte$|^case$|^case\..*|.*\.case\..*|.*\.case$|^catch$|^catch\..*|.*\.catch\..*|.*\.catch$|^char$|^char\..*|.*\.char\..*|.*\.char$|^class$|^class\..*|.*\.class\..*|.*\.class$|^const$|^const\..*|.*\.const\..*|.*\.const$|^continue$|^continue\..*|.*\.continue\..*|.*\.continue$|^default$|^default\..*|.*\.default\..*|.*\.default$|^do$|^do\..*|.*\.do\..*|.*\.do$|^double$|^double\..*|.*\.double\..*|.*\.double$|^else$|^else\..*|.*\.else\..*|.*\.else$|^enum$|^enum\..*|.*\.enum\..*|.*\.enum$|^extends$|^extends\..*|.*\.extends\..*|.*\.extends$|^final$|^final\..*|.*\.final\..*|.*\.final$|^finally$|^finally\..*|.*\.finally\..*|.*\.finally$|^float$|^float\..*|.*\.float\..*|.*\.float$|^for$|^for\..*|.*\.for\..*|.*\.for$|^goto$|^goto\..*|.*\.goto\..*|.*\.goto$|^if$|^if\..*|.*\.if\..*|.*\.if$|^implements$|^implements\..*|.*\.implements\..*|.*\.implements$|^import$|^import\..*|.*\.import\..*|.*\.import$|^instanceof$|^instanceof\..*|.*\.instanceof\..*|.*\.instanceof$|^int$|^int\..*|.*\.int\..*|.*\.int$|^interface$|^interface\..*|.*\.interface\..*|.*\.interface$|^long$|^long\..*|.*\.long\..*|.*\.long$|^native$|^native\..*|.*\.native\..*|.*\.native$|^new$|^new\..*|.*\.new\..*|.*\.new$|^package$|^package\..*|.*\.package\..*|.*\.package$|^private$|^private\..*|.*\.private\..*|.*\.private$|^protected$|^protected\..*|.*\.protected\..*|.*\.protected$|^public$|^public\..*|.*\.public\..*|.*\.public$|^return$|^return\..*|.*\.return\..*|.*\.return$|^short$|^short\..*|.*\.short\..*|.*\.short$|^static$|^static\..*|.*\.static\..*|.*\.static$|^strictfp$|^strictfp\..*|.*\.strictfp\..*|.*\.strictfp$|^super$|^super\..*|.*\.super\..*|.*\.super$|^switch$|^switch\..*|.*\.switch\..*|.*\.switch$|^synchronized$|^synchronized\..*|.*\.synchronized\..*|.*\.synchronized$|^this$|^this\..*|.*\.this\..*|.*\.this$|^throw$|^throw\..*|.*\.throw\..*|.*\.throw$|^throws$|^throws\..*|.*\.throws\..*|.*\.throws$|^transient$|^transient\..*|.*\.transient\..*|.*\.transient$|^try$|^try\..*|.*\.try\..*|.*\.try$|^void$|^void\..*|.*\.void\..*|.*\.void$|^volatile$|^volatile\..*|.*\.volatile\..*|.*\.volatile$|^while$|^while\..*|.*\.while\..*|.*\.while$)(^(?:[a-z_]+(?:\d*[a-zA-Z_]*)*)(?:\.[a-z_]+(?:\d*[a-zA-Z_]*)*)*$)
    @FXML
    private TextField txtDirectory, txtRootPackage, txtValidations, txtDaos, txtDaosImpl, txtMappers, txtResults, txtUtils;
    @FXML
    private Label labelMessage, labelTotal;
    @FXML
    private TextArea txtArea;
    @FXML
    private Button buttonStart;
    private File directoryOrms;
//    private static final DaoGenerator DAO_GENERATOR = DaoGenerator.getInstance();
    private static final String REGEX_PACKAGE = "(?!^abstract$|^abstract\\..*|.*\\.abstract\\..*|.*\\.abstract$|^assert$|^assert\\..*|.*\\.assert\\..*|.*\\.assert$|^boolean$|^boolean\\..*|.*\\.boolean\\..*|.*\\.boolean$|^break$|^break\\..*|.*\\.break\\..*|.*\\.break$|^byte$|^byte\\..*|.*\\.byte\\..*|.*\\.byte$|^case$|^case\\..*|.*\\.case\\..*|.*\\.case$|^catch$|^catch\\..*|.*\\.catch\\..*|.*\\.catch$|^char$|^char\\..*|.*\\.char\\..*|.*\\.char$|^class$|^class\\..*|.*\\.class\\..*|.*\\.class$|^const$|^const\\..*|.*\\.const\\..*|.*\\.const$|^continue$|^continue\\..*|.*\\.continue\\..*|.*\\.continue$|^default$|^default\\..*|.*\\.default\\..*|.*\\.default$|^do$|^do\\..*|.*\\.do\\..*|.*\\.do$|^double$|^double\\..*|.*\\.double\\..*|.*\\.double$|^else$|^else\\..*|.*\\.else\\..*|.*\\.else$|^enum$|^enum\\..*|.*\\.enum\\..*|.*\\.enum$|^extends$|^extends\\..*|.*\\.extends\\..*|.*\\.extends$|^final$|^final\\..*|.*\\.final\\..*|.*\\.final$|^finally$|^finally\\..*|.*\\.finally\\..*|.*\\.finally$|^float$|^float\\..*|.*\\.float\\..*|.*\\.float$|^for$|^for\\..*|.*\\.for\\..*|.*\\.for$|^goto$|^goto\\..*|.*\\.goto\\..*|.*\\.goto$|^if$|^if\\..*|.*\\.if\\..*|.*\\.if$|^implements$|^implements\\..*|.*\\.implements\\..*|.*\\.implements$|^import$|^import\\..*|.*\\.import\\..*|.*\\.import$|^instanceof$|^instanceof\\..*|.*\\.instanceof\\..*|.*\\.instanceof$|^int$|^int\\..*|.*\\.int\\..*|.*\\.int$|^interface$|^interface\\..*|.*\\.interface\\..*|.*\\.interface$|^long$|^long\\..*|.*\\.long\\..*|.*\\.long$|^native$|^native\\..*|.*\\.native\\..*|.*\\.native$|^new$|^new\\..*|.*\\.new\\..*|.*\\.new$|^package$|^package\\..*|.*\\.package\\..*|.*\\.package$|^private$|^private\\..*|.*\\.private\\..*|.*\\.private$|^protected$|^protected\\..*|.*\\.protected\\..*|.*\\.protected$|^public$|^public\\..*|.*\\.public\\..*|.*\\.public$|^return$|^return\\..*|.*\\.return\\..*|.*\\.return$|^short$|^short\\..*|.*\\.short\\..*|.*\\.short$|^static$|^static\\..*|.*\\.static\\..*|.*\\.static$|^strictfp$|^strictfp\\..*|.*\\.strictfp\\..*|.*\\.strictfp$|^super$|^super\\..*|.*\\.super\\..*|.*\\.super$|^switch$|^switch\\..*|.*\\.switch\\..*|.*\\.switch$|^synchronized$|^synchronized\\..*|.*\\.synchronized\\..*|.*\\.synchronized$|^this$|^this\\..*|.*\\.this\\..*|.*\\.this$|^throw$|^throw\\..*|.*\\.throw\\..*|.*\\.throw$|^throws$|^throws\\..*|.*\\.throws\\..*|.*\\.throws$|^transient$|^transient\\..*|.*\\.transient\\..*|.*\\.transient$|^try$|^try\\..*|.*\\.try\\..*|.*\\.try$|^void$|^void\\..*|.*\\.void\\..*|.*\\.void$|^volatile$|^volatile\\..*|.*\\.volatile\\..*|.*\\.volatile$|^while$|^while\\..*|.*\\.while\\..*|.*\\.while$)(^(?:[a-z_]+(?:\\d*[a-zA-Z_]*)*)(?:\\.[a-z_]+(?:\\d*[a-zA-Z_]*)*)*$)";
    private String rootPackage;
    private List<File> filesjava;

    @FXML
    private void openDirectory(ActionEvent event) {
        buttonStart.setDisable(true);
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setInitialDirectory(new File("/home/gvalentin/NetBeansProjects/Test/src/main/java/mx/trillas/nit/model/entity/pojo/"));
        chooser.setTitle("Seleccione el directorio de los ORMs");
        directoryOrms = chooser.showDialog(null);
        if (directoryOrms != null) {
            try {
                txtDirectory.setText(directoryOrms.getAbsolutePath());
                filesjava = UtilGenerator.validate(directoryOrms);
                txtRootPackage.setDisable(false);
                buttonStart.setDisable(false);
                labelTotal.setText("Total de archivos: " + filesjava.size());
                guessRootPackage();
                txtRootPackage.setText(rootPackage);
                // set
                setChange();
            } catch (GeneratorException | IOException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText(ex.getMessage());
                a.show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtRootPackage.textProperty().addListener((ov, oldValue, newValue) -> {
            rootPackage = newValue;
            buttonStart.setDisable(true);
            setChange();
            if (Pattern.matches(REGEX_PACKAGE, newValue)) {
                labelMessage.setText("");
                buttonStart.setDisable(false);
            } else {
                labelMessage.setText("paquete no valido");
            }
        });
    }

    private void guessRootPackage() throws IOException, GeneratorException {
        File javaFile = filesjava.get(0);
        String content = FileUtils.readFileToString(javaFile, "UTF-8");
        if (!content.contains("package")) {
            throw new GeneratorException("No contiene paquete principal");
        }
        int beginIndex = content.indexOf("package");
        int endIndex = content.indexOf(";");
        rootPackage = content.substring(beginIndex, endIndex).replace("package ", "").replace("." + DaoGenerator.modelEntityPojo, "");
    }

    private void setChange() {
        txtValidations.setText(rootPackage + "." + DaoGenerator.modelValidations);
        txtMappers.setText(rootPackage + "." + DaoGenerator.modelMapper);
        txtDaos.setText(rootPackage + "." + DaoGenerator.modelDao);
        txtDaosImpl.setText(rootPackage + "." + DaoGenerator.modelDaoImpl);
        txtResults.setText(rootPackage + "." + DaoGenerator.modelResult);
        txtUtils.setText(rootPackage + "." + DaoGenerator.modelUtil);
    }

    private void createDir(File dir, String p) {
        String[] s = p.split("\\.");
        File f = new File(dir, ".");
        for (String d : s) {
            f = new File(f, d);
            f.mkdirs();
        }
    }

    @FXML
    public void generate(ActionEvent e) {
        txtArea.appendText("Iniciando...");
        txtArea.appendText("\n");
        rootPackage = txtRootPackage.getText();
        DaoGenerator.modelValidations = txtValidations.getText().replace(rootPackage, "");
        DaoGenerator.modelMapper = txtMappers.getText().replace(rootPackage, "");
        DaoGenerator.modelDao = txtDaos.getText().replace(rootPackage, "");
        DaoGenerator.modelDaoImpl = txtDaosImpl.getText().replace(rootPackage, "");
        DaoGenerator.modelResult = txtResults.getText().replace(rootPackage, "");
        DaoGenerator.modelUtil = txtUtils.getText().replace(rootPackage, "");
        // mkdirs
        String modelEntityPojo = DaoGenerator.modelEntityPojo;
        String[] split = modelEntityPojo.split("\\.");
        File dir = new File(directoryOrms.getParentFile(), ".");
        for (int i = 0; i < split.length; i++) {
            dir = dir.getParentFile();
        }
        System.out.println(dir.getAbsolutePath());
        createDir(dir, DaoGenerator.modelValidations);
        createDir(dir, DaoGenerator.modelMapper);
        createDir(dir, DaoGenerator.modelDao);
        createDir(dir, DaoGenerator.modelDaoImpl);
        createDir(dir, DaoGenerator.modelResult);
        createDir(dir, DaoGenerator.modelUtil);
    }

}
