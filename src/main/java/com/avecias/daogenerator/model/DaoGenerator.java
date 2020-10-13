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
    public static String modelEntityPojo;
    public static String modelValidations;
    public static String modelMapper;
    public static String modelDao;
    public static String modelDaoImpl;
    public static String modelResult;
    public static String modelUtil;
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
        // FILES TO COPY
        // interface Dao
        dao = cl.getResourceAsStream("Dao");
        // hibernateUtill
        hibernateUtil = cl.getResourceAsStream("HibernateUtil");
        // interface Mapper
        mapper = cl.getResourceAsStream("Mapper");
        // Exception Mapper
        mapperException = cl.getResourceAsStream("MapperException");
        validationException = cl.getResourceAsStream("ValidationException");
        validator = cl.getResourceAsStream("Validator");
        // TEAMPLATES
        templateDao = UtilGenerator.resourceToString(cl.getResourceAsStream("templates/pojoDao"));
        templateDaoImpl = UtilGenerator.resourceToString(cl.getResourceAsStream("templates/pojoDaoImpl"));
        templateController = UtilGenerator.resourceToString(cl.getResourceAsStream("templates/pojoController"));
        // VARIABLES
        modelEntityPojo = "model.entity.pojo";
        modelValidations = "model.validation";
        modelResult = "model.entity.result";
        modelMapper = "model.mapper";
        modelDao = "model.dao";
        modelDaoImpl = "model.dao.impl";
        modelUtil = "model.util";
    }

    public static DaoGenerator getInstance() {
        if (DAO_GENERATOR == null) {
            DAO_GENERATOR = new DaoGenerator();
        }
        return DAO_GENERATOR;
    }

}
