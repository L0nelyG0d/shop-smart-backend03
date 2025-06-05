ShopSmart Backend

A Spring Boot backend for the ShopSmart application, designed to support real-time collaboration on shopping lists with WebSocket, database integration, and user management.

⸻

🧠 Purpose

To create a collaborative smart shopping list system where users can:
	•	Create named shopping lists
	•	Share them with other users
	•	Sync updates in real-time

⸻

🚀 Tech Stack
	•	Java 17
	•	Spring Boot 3.x
	•	Spring Web, Spring Data JPA
	•	MySQL / PostgreSQL (configurable)
	•	WebSocket (STOMP over SockJS)
	•	Hibernate ORM

⸻

📜 Attempts So Far

✅ Setup Progress
	1.	Initialized GitHub repo: shop-smart-backend01
	2.	Connected Spring Boot to MySQL using application.properties
	3.	Tried deploying to Render (PostgreSQL default) — switched dialects
	4.	Refactored project to support user creation & retrieval
	5.	Added .gitignore for target, .idea/, and secrets

⚠️ Issues Encountered
	•	Render PostgreSQL connection failed: connection attempt failed
	•	Initial MySQL setup failed due to missing database and JDBC mismatch
	•	Added support for WebSocket but no frontend yet
	•	Git syncing needed force-push due to reset

🛠 Solutions Attempted
	•	Changed dialect between MySQL8Dialect and PostgreSQLDialect
	•	Updated application.properties with different DB setups
	•	Used git rev-list to get and checkout the first commit for a clean base
	•	Added Entity-
