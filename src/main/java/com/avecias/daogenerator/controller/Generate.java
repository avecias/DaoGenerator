/*
Generar los archivos DAO y DAOImpl
 */
package com.avecias.daogenerator.controller;

import com.avecias.daogenerator.commons.GeneratorException;
import com.avecias.daogenerator.commons.UtilGenerator;
import com.avecias.daogenerator.model.DaoGenerator;
import com.avecias.daogenerator.model.TemplateFactory;
import java.io.File;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class Generate {

    public void fromFileDirectory(File directoryPojos) {
        System.out.println(directoryPojos.getAbsolutePath());
        try {
            // 1. Validate
            List<File> filesjava = UtilGenerator.validate(directoryPojos);
            System.out.println("Total de archivos " + filesjava.size());
            // 2. Get packages
            Map<String, String> packages = UtilGenerator.getPackages(filesjava.get(0));
            // 3. for: generate all class
            DaoGenerator daoGenerator = new DaoGenerator();
            TemplateFactory factory = TemplateFactory.getInstance();
            filesjava.stream().map((fileJava) -> {
                daoGenerator.createDao(fileJava, packages, factory.getTemplateDao());
                return fileJava;
            }).map((fileJava) -> {
                daoGenerator.createDaoImpl(fileJava, packages, factory.getTemplateDaoImpl());
                return fileJava;
            }).forEachOrdered((fileJava) -> {
                daoGenerator.createControllerSpring(fileJava, packages, factory.getTemplateControllerSpring());
            });
            // 4. Finish
        } catch (GeneratorException e) {
            System.err.println("Error " + e);
        }
    }

    public void fromDirectory(String directory) {

    }

    public void hacer(File directorio, String plantillaDAO, String plantillaDAOImp, String plantillaController, List<File> archivos, String paquete, String paqueteRoot) {
        if (archivos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay cargado ningun archivo para generar", "Ningun Archivo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (paquete.equals("")) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado un package", "Sin package", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Pasarlo parametros para el genrador
        DaoGenerator generator = new DaoGenerator(directorio, plantillaDAO, plantillaDAOImp, plantillaController, archivos, paqueteRoot, paquete);
        int exitosos = generator.crearDAOS();
        // root,directorio,plantillas,archivos
        JOptionPane.showMessageDialog(null, "Total de archvos exitosos: " + exitosos);
    }

}
