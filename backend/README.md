# MediFind Platform Backend

Java 17 + Spring Boot backend for MediFind.

## Prerequisites

- JDK 17 (installed).
- Maven 3.9+. If Maven is not installed, generate the wrapper once Maven is available: `mvn wrapper:wrapper`.
- A Supabase PostgreSQL project with the **PostGIS** extension enabled (Database > Extensions) before the first run.
- Network access to Maven Central for dependency download on the first build.

## Configure

The host, database, and user are set in `src/main/resources/application.yml`. Supply the password (never commit it) one of two ways.

**Option A — environment variable** (no file). Set it in the terminal before each run:

    $env:DB_PASSWORD = "<your-supabase-db-password>"
    mvnw.cmd spring-boot:run

**Option B — local config file** (gitignored, so you don't retype it):

1. Copy `src/main/resources/application-local.yml.example` to `src/main/resources/application-local.yml` (the real file is gitignored).
2. Set the password in it:

        spring:
          datasource:
            password: <your-supabase-db-password>

3. Run with the local profile so that file is loaded:

        mvnw.cmd spring-boot:run --spring.profiles.active=local

## Run

```
mvn spring-boot:run
```

Flyway runs the migrations in `src/main/resources/db/migration` on startup.

## Schema evolution

`V1__init_schema.sql` is the baseline schema; it mirrors `database/schema.sql`. Add schema changes as new versioned migrations (`V2__...sql`, `V3__...sql`). Do not edit `V1` after it has been applied to any environment.

## Status

Scaffold only: application entry point, datasource and Flyway configuration, and the V1 schema migration. Domain modules, API contracts, security, Paystack, and email will be added as each vertical slice is built. See `../docs/` for requirements and architecture.
