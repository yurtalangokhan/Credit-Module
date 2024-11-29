//package com.inghub.core.data.exception;
//
//import tr.com.havelsan.main.core.concern.exceptionHandling.BaseUncheckedException;
//import tr.com.havelsan.main.core.framework.core.dto.ExceptionDetail;
//
//import static tr.com.havelsan.main.core.data.constants.ApplicationConstants.*;
//
//public class MainEntityNotFoundException extends BaseUncheckedException {
//    public static final String code = (MAIN_ABBREVIATION
//            + UNDERLINE
//            + CORE_ABBREVIATION
//            + UNDERLINE
//            + "ENTITY_NOT_FOUND").toUpperCase();
//
//    public static final String localizationCode = (ERROR_ABBREVIATION
//            + DOT
//            + MAIN_ABBREVIATION
//            + DOT
//            + CORE_ABBREVIATION
//            + DOT
//            + "entity.not.found").toLowerCase();
//
//    public MainEntityNotFoundException() {
//        super(new ExceptionDetail(code, localizationCode));
//    }
//
//    public MainEntityNotFoundException(String entityName, String entityId) {
//        super(new ExceptionDetail(code, localizationCode, new String[]{entityName, entityId}));
//    }
//}