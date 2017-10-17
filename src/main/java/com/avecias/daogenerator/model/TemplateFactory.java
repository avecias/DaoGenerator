/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avecias.daogenerator.model;

import java.io.InputStream;
import java.util.Scanner;
import org.apache.log4j.Logger;

/**
 *
 * @author gvalentin Created on 16/10/2017, 02:28:41 PM
 */
public class TemplateFactory {

    private static final Logger LOG = Logger.getLogger(TemplateFactory.class);
    private static TemplateFactory templateFactory;
    private static String templateDao;
    private static String templateDaoImpl;
    private static String templateControllerSpring;

    public static TemplateFactory getInstance() {
        if (templateFactory == null) {
            loadTemplates();
            return new TemplateFactory();
        }
        return templateFactory;
    }

    private static void loadTemplates() {
        InputStream templateDaoStream = TemplateFactory.class.getClassLoader().getResourceAsStream("templates/templateDao.txt");
        InputStream templateDaoImplStream = TemplateFactory.class.getClassLoader().getResourceAsStream("templates/templateDaoImpl.txt");
        InputStream templateControllerSpringStream = TemplateFactory.class.getClassLoader().getResourceAsStream("templates/templateControllerSpring.txt");
        Scanner s;
        try {
            // templateDao.txt
            s = new Scanner(templateDaoStream);
            templateDao = "";
            while (s.hasNextLine()) {
                templateDao += s.nextLine() + "\n";
            }
            // templateDaoImpl.txt
            s = new Scanner(templateDaoImplStream);
            templateDaoImpl = "";
            while (s.hasNextLine()) {
                templateDaoImpl += s.nextLine() + "\n";
            }
            // templateControllerSpring.txt
            s = new Scanner(templateControllerSpringStream);
            templateControllerSpring = "";
            while (s.hasNextLine()) {
                templateControllerSpring += s.nextLine() + "\n";
            }
        } catch (Exception e) {
            System.err.println("Error " + e);
            LOG.error("Error ", e);
        }
    }

    public String getTemplateDao() {
        return templateDao;
    }

    public String getTemplateDaoImpl() {
        return templateDaoImpl;
    }

    public String getTemplateControllerSpring() {
        return templateControllerSpring;
    }

    @Override
    public String toString() {
        return "TemplateFactory{" + '}';
    }
    
}
