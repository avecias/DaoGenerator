/*
Clase para generar el archivo
 */
package com.avecias.daogenerator.model;

import com.avecias.daogenerator.commons.UtilGenerator;
import java.io.File;
import java.io.InputStream;

public class DaoGenerator {

    private static DaoGenerator DAO_GENERATOR = null;
    public File directoryOrms;
    public static String modelEntity;
    public static String modelValidations;
    public static String modelMapper;
    public static String modelDao;
    public static InputStream dao;
    public static InputStream hibernateUtil;
    public static InputStream mapper;
    public static InputStream mapperException;
    public static InputStream validationException;
    public static InputStream validator;
    public static String templateDao;
    public static String templateDaoImpl;
    public static String templateController;

    public DaoGenerator() {
        ClassLoader cl = DaoGenerator.class.getClassLoader();
        dao = cl.getResourceAsStream("Dao");
        hibernateUtil = cl.getResourceAsStream("HibernateUtil");
        mapper = cl.getResourceAsStream("Mapper");
        mapperException = cl.getResourceAsStream("MapperException");
        validationException = cl.getResourceAsStream("ValidationException");
        validator = cl.getResourceAsStream("Validator");
        templateDao = UtilGenerator.resourceToString(cl.getResourceAsStream("templates/pojoDao"));
        templateDaoImpl = UtilGenerator.resourceToString(cl.getResourceAsStream("templates/pojoDaoImpl"));
        templateController = UtilGenerator.resourceToString(cl.getResourceAsStream("templates/pojoController"));
        modelEntity = "model.entity.pojo";
    }

    public static DaoGenerator getInstance() {
        if (DAO_GENERATOR == null) {
            DAO_GENERATOR = new DaoGenerator();
        }
        return DAO_GENERATOR;
    }

}
