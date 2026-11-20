# MediFind Platform Backend

Java 17 + Spring Boot backend for MediFind.

## Prerequisites

- JDK 17 (installed).
- Maven 3.9+. If Maven is not installed, generate the wrapper once Maven is available: `mvn wrapper:wrapper`.
- A Supabase PostgreSQL project with the **PostGIS** extension enabled (Database > Extensions) before the first run.
- Network access to Maven Central for dependency download on the first build.

## Configure

Set Supabase credentials as environment variables (or edit `src/main/resources/application.yml`):

```
DB_URL=jdbc:postgresql://db.nkoqnmecepefwgyfbfom.supabase.co:5432/postgres?sslmode=require
DB_USERNAME=postgres
DB_PASSWORD=<your-password>
```

## Run

```
mvn spring-boot:run
```

Flyway runs the migrations in `src/main/resources/db/migration` on startup.

## Schema evolution

`V1__init_schema.sql` is the baseline schema; it mirrors `database/schema.sql`. Add schema changes as new versioned migrations (`V2__...sql`, `V3__...sql`). Do not edit `V1` after it has been applied to any environment.

## Status

Scaffold only: application entry point, datasource and Flyway configuration, and the V1 schema migration. Domain modules, API contracts, security, Paystack, and email will be added as each vertical slice is built. See `../docs/` for requirements and architecture.
