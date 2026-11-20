# MediFind — Project Overview

> Status labels: **CONFIRMED** (stated by the group), **INITIAL DESIGN** (in the original materials but not yet confirmed as final), **OPEN** (the group has not specified it). Full question-and-answer record: `MediFind_Requirements_QA.md`.

## What MediFind is

MediFind is a pharmacy and medicine discovery, ordering, and delivery platform. Customers find nearby pharmacies and medicines, build carts, upload prescriptions, pay, and track delivery. Pharmacy staff manage inventory and review prescriptions; delivery riders collect and deliver orders; platform administrators oversee verification and operations.

The confirmed target market is **Ghana**, with an **English-only** interface.

## Who uses it

- **Customer** — discovers medicines and licensed pharmacies, uploads prescriptions, orders and pays, tracks delivery, sets medication reminders.
- **Pharmacy staff / pharmacist** — manages inventory and opening hours, reviews prescriptions, fulfills orders.
- **Delivery rider** — accepts delivery offers, collects sealed orders, confirms handover.
- **Platform administrator** — vets and approves pharmacy and rider credentials, monitors orders, configures commissions/zones/fees, handles escalations and refunds, audits logs.
- **Super Admin** — confirmed role. Admin hierarchy and regional managers are OPEN.

The exact role list and permission matrix are OPEN.

## A typical order (end to end)

1. Customer searches by medicine or by nearby pharmacy (PostGIS distance query).
2. Customer builds a cart against one pharmacy. Carts are per-pharmacy.
3. If any item requires a prescription, the customer uploads the prescription.
4. A verified pharmacist reviews the prescription and records a decision.
5. At checkout the order is recorded; each item's name and price are snapshotted into `order_items` so the order stays readable if the catalogue changes later.
6. A payment attempt is recorded via **Paystack** (confirmed). Card numbers and mobile-money PINs are never stored.
7. A delivery is created against one of the customer's saved addresses. Rider offers are tracked one row at a time, so declined and timed-out offers are preserved.
8. The rider collects and delivers; handover is confirmed with a one-time code (only a hash is stored), and the rider's location at confirmation is logged.
9. A successful payment is split in the `financial_ledger` across pharmacy, rider, and platform. A database CHECK constraint ensures exactly one destination is set.
10. Rider earnings accrue until payout; stock movements and an audit log record changes and actors.

## Technology (confirmed)

- **Frontend:** React Progressive Web App.
- **Backend:** Java with Spring Boot. Authentication is handled by the Spring Boot backend, not Supabase Auth.
- **Database:** PostgreSQL on Supabase, with the PostGIS extension for location points and nearby searches.
- **Architecture:** Modular monolith to start, with domain areas kept separable.
- **Payments:** Paystack.
- **Customer notifications:** Email sent through the team's own email account, using an app password kept secret on the backend. SMS and push are not otherwise specified (OPEN).

## Data model at a glance

The schema (`database/schema.sql`) groups into seven domains: identity, pharmacy directory, catalogue and inventory, prescriptions, cart and orders, payments and settlement, and delivery. See `MediFind_Platform_Architecture_Summary.md` for the domain breakdown and `MediFind_Complete_System_Architecture.drawio` for the diagrams.

## Current state

Only the database schema and documentation exist. The draw.io diagram has been updated to reflect the confirmed decisions. Frontend, backend, build tooling, and API contracts are not yet established; confirm team decisions before scaffolding them.
