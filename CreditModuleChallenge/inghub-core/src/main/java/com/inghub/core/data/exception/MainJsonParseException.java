//package com.inghub.core.data.exception;
//
//import tr.com.havelsan.main.core.concern.exceptionHandling.BaseUncheckedException;
//import tr.com.havelsan.main.core.framework.core.dto.ExceptionDetail;
//
//import static tr.com.havelsan.main.core.data.constants.ApplicationConstants.*;
//
//public class MainJsonParseException extends BaseUncheckedException {
//    public static final String code = (MAIN_ABBREVIATION
//            + UNDERLINE
//            + CORE_ABBREVIATION
//            + UNDERLINE
//            + "EXCEPTION_QUERY_PARSING").toUpperCase();
//
//    public static final String localizationCode = (ERROR_ABBREVIATION
//            + DOT
//            + MAIN_ABBREVIATION
//            + DOT
//            + CORE_ABBREVIATION
//            + DOT
//            + "exception.query.parsing").toLowerCase();
//
//    public MainJsonParseException(String message, String localizationMessage) {
//        super(new ExceptionDetail(code, localizationCode, new String[]{message, localizationMessage}));
//    }
//}