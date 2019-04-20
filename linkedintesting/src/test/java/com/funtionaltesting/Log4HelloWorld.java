package com.funtionaltesting;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
public class Log4HelloWorld {
static final Logger logger = Logger.getLogger(Log4HelloWorld.class);
public static void main(String[] args) {
	
		 //Configure logger
        PropertyConfigurator.configure("log4j.properties");
        logger.debug("This is DEBUG");
        logger.info("This is INFO");
        logger.warn("This is WARN");
        logger.error("This is ERROR");
        logger.fatal("This is FATAL");
	}

}
