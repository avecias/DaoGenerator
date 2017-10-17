/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avecias.daogenerator.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.apache.log4j.Logger;

/**
 *
 * @author gvalentin Created on 16/10/2017, 02:51:42 PM
 */
public class UtilGenerator {

    private static final Logger LOG = Logger.getLogger(UtilGenerator.class);

    public static List<File> validate(File directory) throws GeneratorException {
        if (directory == null || !directory.exists() || !directory.isDirectory()) {
            throw new GeneratorException("Directorio no valido: " + directory);
        }
        if (directory.list().length == 0) {
            throw new GeneratorException("Directorio vacio: " + directory.getAbsolutePath());
        }
        File[] files = directory.listFiles();
        List<File> filesJava = new ArrayList<>();
        for (File file : files) {
            if (file.getName().endsWith(".java")) {
                filesJava.add(file);
            }
        }
        if (filesJava.isEmpty()) {
            throw new GeneratorException("Ningun archivo de .java en " + directory.getAbsolutePath());
        }
        return filesJava;
    }

    public String fileToString(File file) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            result.append(scanner.next()).append("\n");
        }
        return result.toString();
    }

    public static Map<String, String> getPackages(File get) {
        Map<String, String> map = new HashMap<>();
        return map;
    }

    public String getPackageRoot(List<File> archivos, String paquete) {
        String paqueteRoot = "";
        if (archivos.isEmpty()) {
            return paqueteRoot;
        }
        int punto = paquete.length() - 1;
        for (int i = paquete.length() - 1; i >= 0; i--) {
            if (paquete.charAt(i) == '.') {
                punto = i;
                break;
            }
        }
        paqueteRoot = paquete.substring(0, punto);
        return paqueteRoot;
    }

    public String getPaquete(List<File> archivos, JTextField textoPackageRoot) {
        String paquete = "";
        if (archivos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay archivos cargados, para agregar package", "No hay archivos", JOptionPane.ERROR_MESSAGE);
            return paquete;
        }
        try {
            Scanner scanner = new Scanner(archivos.get(0));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.contains("package")) {
                    paquete = line.substring(8);
                    break;
                }
            }
            if (paquete.equals("")) {
                JOptionPane.showMessageDialog(null, "No se encontro ningun package", "Error", JOptionPane.ERROR_MESSAGE);
                return paquete;
            }
            paquete = paquete.substring(0, paquete.length() - 1);
            textoPackageRoot.setText(paquete);
        } catch (FileNotFoundException ex) {
            System.err.println("Error " + ex);
        }
        return paquete;
    }
}
