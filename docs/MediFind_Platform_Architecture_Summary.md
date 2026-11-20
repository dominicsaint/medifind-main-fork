# MediFind — Platform Architecture Summary

> Status labels: **CONFIRMED**, **INITIAL DESIGN**, **OPEN**. Full Q&A record: `MediFind_Requirements_QA.md`. Diagrams: `MediFind_Complete_System_Architecture.drawio`.

## Stack (confirmed)

| Layer | Choice |
|---|---|
| Frontend | React PWA |
| Backend | Java + Spring Boot (modular monolith) |
| Auth | Spring Boot backend (not Supabase Auth) |
| Database | PostgreSQL on Supabase + PostGIS |
| Payments | Paystack |
| Notifications | Email via team email account (app password secret on backend); SMS/push OPEN |
| Market / language | Ghana / English only |

## Architecture pages (draw.io)

The diagram has 14 pages:

1. System Context
2. High-Level Architecture
3. Customer Module Architecture
4. Pharmacist Module Architecture
5. Rider Module Architecture
6. Admin Module Architecture
7. Unified Use-Case Diagram
8. End-to-End Order Activity
9. Prescription Workflow
10. Rider Assignment Workflow
11. Unified Database ERD
12. API & Module Integration
13. Security Architecture
14. Deployment Architecture

The System Context and Deployment pages now name Paystack and email notifications as the confirmed providers. The Maps provider remains "to be selected" (OPEN).

## Domain modules

Kept separable for future evolution toward services:

- **Identity & access** — `users`, `roles`, `user_roles`; `customers` and `riders` as separate profiles off the shared account. Super Admin confirmed; full role matrix OPEN. Auth by Spring Boot.
- **Pharmacy directory** — `pharmacies` (PostGIS location), `pharmacy_staff`, `pharmacy_hours`. Business/branch parent relationship OPEN. Onboarding by admin + license checks (INITIAL DESIGN).
- **Catalogue & inventory** — `medicine_categories`, `medicines`, `pharmacy_inventory`, `inventory_batches` (per-batch expiry), `stock_movements` (audit trail). Master-catalogue ownership OPEN; controlled-drug rules OPEN.
- **Prescriptions** — `prescriptions` (private storage key, not the file), `prescription_reviews`. Storage backend and retention OPEN; reuse and validity OPEN.
- **Cart & orders** — per-pharmacy `carts`/`cart_items`; `orders` snapshot price + name into `order_items`. Mixed Rx/OTC cart is INITIAL DESIGN; cancellation/refund rules OPEN.
- **Payments & settlement** — `payments` (Paystack; no card/PIN storage), `financial_ledger` (three-way split enforced by CHECK). Commission model and refund flow OPEN.
- **Delivery** — `deliveries`, `delivery_assignments` (one row per offer), `delivery_proofs` (OTP hash + handover location), `rider_earnings`. Assignment model, timeout, radius, and `rider_type` are INITIAL DESIGN / OPEN.

## Location features

PostGIS `geography(POINT, 4326)` on `pharmacies`, `customer_addresses`, and `riders.current_location`, with GiST spatial indexes. The nearby-search example uses Accra coordinates. An initial 3-5 km radius is suggested; the final value is OPEN.

## Security

- Sensitive documents (prescriptions, rider documents, delivery photos) are stored as private storage keys, never inline.
- Delivery codes are stored as hashes only.
- Card numbers and mobile-money PINs are never stored.
- Settlement ambiguity is prevented by a database CHECK constraint.
- Spring Boot authorizes private-file access before issuing temporary storage links.
- Audit logs (`stock_movements`, `system_audit_logs`) are append-only.

## Offline (PWA)

Cached, read-only browsing may work offline. Ordering, payment, prescription upload, and status changes require a connection. The exact offline-capable screens are OPEN.

## Deployment

Deployment details live on the draw.io Deployment page. Specifics (hosting, regions, data residency) are OPEN. Regulatory references (Ghana FDA, Pharmacy Council, NIA, Data Protection Commission, Acts 843/857) appear in the original materials and are not legal validation.
