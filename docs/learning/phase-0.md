# Phase 0 Learning Notes

Notes in my own words, one section per task. Template: what I built, new concepts, why we did it this way, what broke, one open question.

---

## Task 1: Install the toolchain (2026-11-25)

- **What I built:** a dev machine with JDK 25 (Temurin), Node.js 26, Angular CLI 22, Docker Desktop and Git, all verified from PowerShell.
- **New concepts:**
  - **LTS (Long-Term Support):** only some releases get years of security fixes. Java's LTS versions are 17, 21 and 25; Node's even-numbered versions become LTS. Companies run LTS, so I do too.
  - **Temurin:** a free OpenJDK build. Oracle's JDK has commercial licensing terms, so teams usually use Temurin or Corretto.
  - **PATH:** the ordered list of folders Windows searches when I type a command. The first match wins, which explains every "wrong version" problem.
- **Why we did it this way:** the JD asks for Java 21+, and 25 is the newest LTS, so it's current and defensible. Angular's tooling runs on Node, and the live-gateway service will be a Node app.
- **What broke and how I fixed it:**
  - `java -version` showed 24 (non-LTS) → installed Temurin 25 and removed JDK 24.
  - Angular warned "Node 26 Unsupported" → Angular CLI 20 is older than Node 26, so I upgraded to CLI 22.
  - `ng version` still said 20 → a stray local Angular CLI in my home folder's `node_modules` was taking priority over the global one. Uninstalled it.
  - `docker run` failed with "cannot find the file specified" → Docker Desktop wasn't running. The `docker` command is only a client; the engine has to be running.
- **Open question:** how do teams keep everyone on the same tool versions? (Look into `.nvmrc`, SDKMAN and toolchain files.)

---

## Task 2: Create the repo (2026-11-25)

- **What I built:** a `courtvision` monorepo on GitHub with a `.gitignore`, a README and the folder layout for every service.
- **New concepts:**
  - **Monorepo:** every service in one repo, so there's one link for recruiters and a change that spans services lands in one commit.
  - **`.gitignore`:** keeps build output (`target/`, `node_modules/`, `dist/`) and secrets (`.env`) out of Git.
  - **Conventional Commits:** `feat:`, `fix:`, `chore:`, `docs:` prefixes make history readable and let tools generate changelogs.
  - **Remotes:** `origin` is a nickname for a URL. `git remote add` creates it; `git remote set-url` changes it.
  - **Access tokens:** Git Credential Manager stores a GitHub token that acts as me, so it's as sensitive as a password.
- **Why we did it this way:** adding `.gitignore` before the first commit means secrets and build files never enter history, even by accident.
- **What broke and how I fixed it:**
  - Pushed to `YOUR-USERNAME/courtvision` → I'd copied a placeholder. Fixed with `git remote set-url`.
  - "Repository not found" again → the repo didn't exist on GitHub yet. Created an empty one, then pushed.
  - A command printed my GitHub token → revoked it immediately in GitHub settings and cleared Windows Credential Manager. Revoke first, investigate second.
- **Open question:** when does a monorepo stop being worth it, and what build tools do big companies use to manage one?

---

## Task 3: Local infrastructure with Docker Compose (2026-11-25)

- **What I built:** a `compose.yaml` that starts MySQL 8.4, Kafka (KRaft mode), Kafka UI, DynamoDB Local and Grafana with `docker compose up -d`, plus `.env` / `.env.example` for configuration.
- **New concepts:**
  - **Image vs. container:** an image is a packaged snapshot; a container is a running instance of it.
  - **Docker Compose:** one file describes the whole stack, so it's both the startup command and the documentation.
  - **Port mapping (`host:container`):** Kafka UI runs on 8085 so port 8080 stays free for Spring Boot.
  - **Named volumes:** data that survives `docker compose down`. `down -v` wipes it.
  - **Healthchecks:** prove a service is actually ready, not just started.
  - **`.env` vs `.env.example`:** real secrets stay local; the example file lists the keys and gets committed. This is the Twelve-Factor idea of config in the environment.
  - **Kafka's two listeners:** containers connect at `kafka:29092`, my code on Windows at `localhost:9092`. Each side needs an address it can reach.
  - **KRaft:** Kafka manages its own metadata, so there's no ZooKeeper container.
- **Why we did it this way:**
  - MySQL locally maps to Aurora MySQL in AWS with no code changes.
  - Kafka lets one game event trigger many reactions (box score, browser push, alerts) without the producer knowing who's listening.
  - DynamoDB fits live game state: constant writes, lookups by key, no joins. Using both databases for what each does best is called polyglot persistence.
  - Grafana now means observability is designed in, not bolted on.
- **What broke and how I fixed it:** Grafana login failed → I used `courtvision` (the MySQL user). Grafana's user is `admin`. Also, env vars like `GF_SECURITY_ADMIN_PASSWORD` only set first-run values; after that the password lives in Grafana's volume (config vs. state).
- **Open question:** how would this stack look in AWS? Probably Amazon MSK for Kafka, Aurora for MySQL and DynamoDB itself. I'll find out in Phase 4.
