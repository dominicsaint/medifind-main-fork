package com.medifind.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the MediFind platform backend.
 *
 * The application is a modular monolith. Domain areas (identity, pharmacy,
 * catalogue, prescriptions, orders, payments, delivery) are kept in separate
 * packages so they can evolve independently. See ../docs/ for requirements and
 * architecture, and src/main/resources/db/migration for the database schema.
 */
@SpringBootApplication
public class MediFindPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediFindPlatformApplication.class, args);
    }
}
