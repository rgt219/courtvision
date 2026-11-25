# ADR-001: Ingest vendor NBA data into our own database instead of proxying the vendor API

- **Status:** Accepted
- **Date:** 2026-11-25
- **Deciders:** <your name>

## Context

CourtVision needs NBA teams, players, games and per-game stats going back decades.
The data source is the balldontlie API:

- The free tier allows 5 requests per minute and covers teams, players and games only.
- Per-game player stats need the ALL-STAR tier ($9.99/month, 60 requests per minute).
- A single page view (for example, a player profile with a career table) could need
  several API calls, and the site may have many visitors at once.
- The official stats.nba.com API is undocumented and blocks requests from cloud
  providers like AWS, so it can't be the production source.

If the browser or our API called balldontlie on every request, we would hit the rate
limit almost immediately, every page would depend on a third party being up, and
response times would be out of our control.

## Decision

CourtVision will **ingest** vendor data into its own MySQL (Aurora in AWS) database
using a scheduled, rate-limited, idempotent ingestion job. All user-facing APIs read
only from our database. The browser never calls the vendor.

- Historical data is backfilled once, then refreshed incrementally (nightly during
  the season).
- Derived stats (season averages, TS%, standings) are computed in our own SQL
  instead of being bought from paid endpoints.
- The API key lives only in server-side configuration (`.env` locally, AWS Secrets
  Manager in production).

## Consequences

**Positive**

- Page loads don't depend on vendor uptime or rate limits.
- Fast, predictable responses from our own indexed database.
- Computing our own derived stats saves money and shows SQL skill.
- The vendor can be swapped later by changing only the ingestion job.

**Negative**

- Data can be up to one refresh cycle stale (acceptable for historical stats; live
  games are handled separately via Kafka, see a future ADR).
- We own more infrastructure: storage, the ingestion job, its monitoring and retries.
- The backfill takes time at 60 requests per minute and needs a paid month.

**Follow-ups**

- Monitor ingestion failures and alert after repeated failures.
- Track the API key's expiry and plan rotation.
