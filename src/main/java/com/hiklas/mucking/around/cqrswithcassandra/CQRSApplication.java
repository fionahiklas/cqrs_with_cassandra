package com.hiklas.mucking.around.cqrswithcassandra;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import static spark.Spark.get;


/**
 * The main entry point and structure for the application.
 */
public class CQRSApplication {

    private static final Logger log = LogManager.getLogger();

    public static void main(String... args) {
        get("/ping", (request, response) -> {
            log.debug("Ping endpoint called");
            response.status(200);
            return "Pong!";
        });
    }
}
