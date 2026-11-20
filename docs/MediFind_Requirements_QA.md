# MediFind — Requirements Questions and Answers

Answers from group discussions, original project materials, and the current schema.

Status labels:

- **CONFIRMED** — stated by the group.
- **INITIAL DESIGN** — shown in the original materials but not yet confirmed as final.
- **OPEN** — the group has not specified it.

## A. Scope and audience

1. **Is the target market Ghana only, or are there planned expansion markets?**
   - CONFIRMED: Ghana is the target market.
   - OPEN: future expansion markets have not been specified.

2. **Languages — English only, or local languages too?**
   - CONFIRMED: English only.

## B. Roles and accounts

3. **What is the full role list and each role's permissions?**
   - CONFIRMED: include a Super Admin role.
   - INITIAL DESIGN: Customer, pharmacy staff/pharmacist, rider, and platform admin workflows.
   - OPEN: exact role list and permission matrix; a separate support-agent role has not been confirmed.

4. **Auth approach — Supabase Auth, or custom Spring Security using password_hash?**
   - CONFIRMED: authentication is handled by the Java / Spring Boot backend, rather than Supabase Auth.
   - OPEN: the specific Spring Security configuration and credential flow.

5. **Can one person hold multiple roles?**
   - The schema supports multiple roles through `user_roles`.
   - OPEN: whether one person may actually hold multiple roles.

## C. Pharmacies

6. **One pharmacy business with multiple branches, or each branch its own pharmacies row?**
   - The current schema stores a location on each pharmacy row, which can represent a physical branch. It has no parent-business relationship.
   - OPEN: the intended business/branch model.

7. **Pharmacy onboarding/verification flow — who approves, and what documents are required?**
   - INITIAL DESIGN: platform-admin review and pharmacy-license checks.
   - OPEN: exact approval steps and required documents.

8. **Opening hours — Ghana time only, or could a pharmacy be in another timezone?**
   - Ghana is the confirmed target market; Ghana-local operating hours are the initial assumption.
   - OPEN: timezone behavior for future markets.

## D. Catalogue and medicines

9. **Who maintains the shared medicines catalogue?**
   - The schema separates a shared medicines catalogue from pharmacy-specific inventory and prices.
   - OPEN: who creates and approves master catalogue entries.

10. **Controlled/scheduled drugs — any handling beyond prescription_required?**
    - No final handling policy has been confirmed. The original design mentions controlled medicines and additional review.
    - OPEN: quantity limits and record-keeping rules.

## E. Prescriptions

11. **Where are prescription files stored? Any retention period?**
    - INITIAL DESIGN: private encrypted object storage. Supabase Storage is a possible fit, but not confirmed as the final file store.
    - OPEN: retention period.

12. **Who reviews prescriptions?**
    - INITIAL DESIGN: a verified pharmacist reviews a prescription, apparently at the pharmacy fulfilling the order.
    - OPEN: whether any verified pharmacist may review it.

13. **Is a prescription reusable across pharmacies/orders, or one-time per order?**
    - OPEN: reuse rules have not been specified.

14. **Does a prescription have a validity window?**
    - The initial design includes an expired-prescription outcome.
    - OPEN: the validity period.

## F. Ordering

15. **Can one order contain both prescription and over-the-counter items?**
    - INITIAL DESIGN suggests this may be allowed: the cart detects prescription-required medicines.
    - OPEN: confirm the final mixed-cart rule.

16. **Cancellation — who can cancel, up to what stage, and how are refunds handled?**
    - Cancellation, returns, and refunds appear in the design.
    - OPEN: permissions, order-stage cutoffs, and refund rules.

17. **If stock changes between order placement and fulfillment, what happens?**
    - INITIAL DESIGN: pharmacy confirms availability and substitutions may be proposed for customer approval.
    - OPEN: stock reservation and out-of-stock rules.

## G. Payments and settlement

18. **Which payment providers?**
    - CONFIRMED: Paystack.

19. **Platform fee/commission model?**
    - The design mentions configurable platform commissions.
    - OPEN: whether fees are percentage-based, flat, or individually agreed.

20. **Who sets the delivery fee and how is it computed?**
    - Distance-based estimation is proposed in the design.
    - OPEN: the fee owner and final calculation.

21. **Refund flow on failed or cancelled orders?**
    - Refunds are part of the design.
    - OPEN: the detailed flow and timing.

## H. Delivery and riders

22. **Rider assignment model?**
    - INITIAL DESIGN proposes timed offers to nearby riders, then reassignment if an offer times out.
    - OPEN: confirm whether this is the final model.

23. **rider_type values — employment type or vehicle type?**
    - The schema uses `rider_type`. The initial design suggests employed/partner categories.
    - OPEN: confirmed values; vehicle type is a separate unanswered choice.

24. **Maximum delivery radius/geofence?**
    - The diagram suggests a 3-5 km initial search radius.
    - OPEN: not confirmed as final.

25. **Rider onboarding/verification — documents and approver?**
    - INITIAL DESIGN mentions Ghana Card and rider-license checks with admin review.
    - OPEN: exact required documents and final approval policy.

26. **If no rider accepts, what happens?**
    - INITIAL DESIGN proposes a 45-second offer timeout, trying another rider, then escalation.
    - OPEN: confirm the timing and escalation policy.

## I. Notifications

27. **Which channels and SMS gateway?**
    - CONFIRMED: send customer messages by email through the group's email account using an app password. Keep the app password secret on the backend.
    - OPEN: SMS or push messaging, and the email provider/SMTP configuration.

## J. Admin and ops

28. **Admin scope — platform-wide admin or regional managers too?**
    - CONFIRMED: include a Super Admin role.
    - INITIAL DESIGN: platform administration.
    - OPEN: regional managers and the exact admin role hierarchy.

29. **Support ticket workflow and SLA?**
    - The schema includes support tickets.
    - OPEN: who handles them and response-time targets.

## K. PWA and offline

30. **Which screens are offline-capable versus server-required?**
    - OPEN: the group has not confirmed offline screens. The project guidance suggests cached read-only browsing may work offline, while ordering, payment, prescription upload, and status changes need a connection.

## L. Non-functional requirements

31. **Expected scale — pharmacies and customers?**
    - OPEN: no expected counts have been provided.
    - INITIAL DESIGN: start with a modular monolith.

32. **Regulatory constraints and data residency?**
    - The original materials reference Ghana FDA, Pharmacy Council, NIA, Data Protection Commission, and Acts 843/857. These references are not legal validation.
    - OPEN: exact applicable obligations and data-residency requirements.

## Confirmed implementation decisions

React PWA; Java / Spring Boot backend and authentication; Supabase-hosted PostgreSQL with PostGIS; Paystack; email sent using the group's email account and an app password; Ghana target market; English-only interface; Super Admin role.

## Handling conflicts

When a project PDF appears to conflict with an idea or requirement the group states in a prompt, ask the group to confirm before changing files based on that conflict.
