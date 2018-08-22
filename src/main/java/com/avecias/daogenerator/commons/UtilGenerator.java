/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avecias.daogenerator.commons;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.log4j.Logger;

/**
 *
 * @author gvalentin Created on 16/10/2017, 02:51:42 PM
 */
public class UtilGenerator {

    private static final Logger LOG = Logger.getLogger(UtilGenerator.class);

    /**
     *
     * @param directory
     * @return la lista de los archivos
     * @throws GeneratorException
     */
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
            if (file.isFile() && file.getName().endsWith(".java")) {
                filesJava.add(file);
            }
        }
        if (filesJava.isEmpty()) {
            throw new GeneratorException("Ningun archivo de .java en " + directory.getAbsolutePath());
        }
        return filesJava;
    }

    /**
     *
     * @param fileSample
     * @return
     * @throws FileNotFoundException
     * @throws GeneratorException
     */
    public static Map<String, String> getPackages(File fileSample) throws FileNotFoundException, GeneratorException {
        Map<String, String> map = new HashMap<>();
        Scanner scanner = new Scanner(fileSample);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.contains("package")) {
                String pojosPackages = line.substring(8);
                pojosPackages = pojosPackages.substring(0, pojosPackages.length() - 1);
                map.put("pojosPackages", pojosPackages);
                String pojos = fileSample.getParentFile().getName();
                if (!pojosPackages.contains(pojos)) {
                    throw new GeneratorException("El directorio y el paquete de pojos no coincide");
                }
                String rootPackages = pojosPackages.replace("." + pojos, "");
                if (pojosPackages.equals(rootPackages)) {
                    throw new GeneratorException("No existe un paquete padre");
                }
                String parent = fileSample.getParentFile().getParentFile().getName();
                if (!rootPackages.contains(parent)) {
                    throw new GeneratorException("El directorio padre y el paquete de padre no coincide");
                }
                map.put("rootPackages", rootPackages);
                break;
            }
        }
        if (map.size() != 2) {
            throw new GeneratorException("No existe los paquetes suficientes");
        }
        return map;
    }

    public static String resourceToString(InputStream is) {
        StringBuilder builder;
        Scanner s;
        s = new Scanner(is);
        builder = new StringBuilder();
        while (s.hasNextLine()) {
            builder.append(s.nextLine()).append("\n");
        }
        return s.toString();
    }
}
