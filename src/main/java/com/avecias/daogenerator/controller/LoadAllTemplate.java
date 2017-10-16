/*
Cargar las plantillas
 */
package com.avecias.daogenerator.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class LoadAllTemplate {

    private String platillaDAO;
    private String plantillaDAOImpl;
    private String plantillaController;

    public String hacerDao() {
        platillaDAO = "";
        try {
            File pd = new File("plantillas/plantilla_DAO.txt");
            Scanner s = new Scanner(pd);
            while (s.hasNextLine()) {
                platillaDAO += s.nextLine() + "\n";
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Error en la lectura de las plantillas " + ex);
            JOptionPane.showMessageDialog(null, "No se encontro la plantilla de DAO", "Error, archivo no encontrado", JOptionPane.ERROR_MESSAGE);
        }
        return platillaDAO;
    }

    public String hacerDaoImpl() {
        plantillaDAOImpl = "";
        try {
            File pdi = new File("plantillas/plantilla_DAOImpl.txt");
            Scanner s = new Scanner(pdi);
            while (s.hasNextLine()) {
                plantillaDAOImpl += s.nextLine() + "\n";
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Error en la lectura de las plantillas " + ex);
            JOptionPane.showMessageDialog(null, "No se encontro la plantilla de DAOImpl", "Error, archivo no encontrado", JOptionPane.ERROR_MESSAGE);
        }
        return plantillaDAOImpl;
    }
    
    
    public String hacerController() {
        plantillaController = "";
        try {
            File pdi = new File("plantillas/plantilla_Controller.txt");
            Scanner s = new Scanner(pdi);
            while (s.hasNextLine()) {
                plantillaController += s.nextLine() + "\n";
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Error en la lectura de las plantillas " + ex);
            JOptionPane.showMessageDialog(null, "No se encontro la plantilla de Controller", "Error, archivo no encontrado", JOptionPane.ERROR_MESSAGE);
        }
        return plantillaController;
    }

}
