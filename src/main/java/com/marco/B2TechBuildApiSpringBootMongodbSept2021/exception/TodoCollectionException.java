package com.marco.B2TechBuildApiSpringBootMongodbSept2021.exception;

/**
 * @author ncast
 * @version 1.0
 * @created 4/10/2022
 */
public class TodoCollectionException extends Exception {

    public TodoCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "Todo with " + id + " not found.";
    }

    public static String TodoAlreadyExists() {
        return "Todo with given name already exists.";
    }
}
