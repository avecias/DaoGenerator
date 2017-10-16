/*
Cargar el directorio y mandar un aviso de el total de clases que contiene
 */
package com.avecias.daogenerator.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LoadDirectory {

    public File hacer(JTextField textoDirectorioPojos) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            textoDirectorioPojos.setText(fileChooser.getSelectedFile().getAbsolutePath());
            return fileChooser.getSelectedFile();
        } else {
            JOptionPane.showMessageDialog(null, "Ningun archivo se selecciono", "Ningun archivo", JOptionPane.ERROR_MESSAGE);
            textoDirectorioPojos.setText("");
            return null;
        }
    }

    public List<File> validar(File f, JLabel etiquetaEstado) {
        File[] files = f.listFiles();
        List<File> filesJava = new ArrayList<>();
        for (File file : files) {
            if (file.getName().contains("java")) {
                filesJava.add(file);
            }
        }
        if (filesJava.isEmpty()) {
            JOptionPane.showConfirmDialog(null, "No se encontro ningun archivo POJO", "NIngun Archivo", JOptionPane.ERROR_MESSAGE);
            etiquetaEstado.setText("No se encontro ningun archivo POJO");
        } else {
            JOptionPane.showMessageDialog(null, "Total de archivos cargados " + filesJava.size());
            etiquetaEstado.setText("Total de archivos cargados " + filesJava.size());
        }
        return filesJava;
    }

    public String getPackageRoot(List<File> archivos, String paquete, JLabel etiquetaDAO, JLabel etiquetaDAOImpl) {
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
        etiquetaDAO.setText("PackageDAO: " + paqueteRoot + ".DAO");
        etiquetaDAOImpl.setText("PackageDAOImpl: " + paqueteRoot + ".DAOImpl");
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
