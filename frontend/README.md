# MediFind Frontend

React PWA built with Vite (TypeScript).

## Prerequisites

- Node.js 18+ (installed).

## Run

From `frontend/` (needs internet for the first dependency install):

```
npm install
npm run dev
```

The dev server starts on http://localhost:5173.

## Build

```
npm run build
```

Output goes to `dist/`.

## Status

Base Vite + React + TypeScript scaffold only. The PWA configuration (web manifest, service worker via vite-plugin-pwa, offline behavior) and the API client for the Spring Boot backend will be added as features are built. See `../docs/` for requirements and architecture.
