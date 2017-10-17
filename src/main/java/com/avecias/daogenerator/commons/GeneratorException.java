/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avecias.daogenerator.commons;

import org.apache.log4j.Logger;

/**
 *
 * @author gvalentin Created on 16/10/2017, 04:43:46 PM
 */
public class GeneratorException extends Exception {

    private static final Logger LOG = Logger.getLogger(GeneratorException.class);

    public GeneratorException() {
    }

    public GeneratorException(String message) {
        super(message);
    }

}
