# eBank MS App

Application de démonstration orientée **microservices** pour la gestion de clients et de comptes bancaires, avec:

- un backend Spring Boot/Spring Cloud,
- un frontend Angular,
- un assistant IA (Spring AI + Ollama) connecté aux microservices via MCP.

## 🧱 Architecture du projet

Le dépôt contient les modules suivants :

- `discovery-service` : registre Eureka (service discovery).
- `gateway-service` : API Gateway Spring Cloud Gateway.
- `customer-service` : gestion des clients (CRUD simple + outil MCP).
- `ebank-service` : gestion des comptes bancaires (CRUD + enrichissement client via Feign + outil MCP).
- `ebank-bot` : assistant IA (endpoint chat/chatStream, intégration MCP, Discord/Telegram).
- `ebank-ang-front` : interface Angular (liste des comptes + interface chatbot).

## ⚙️ Stack technique

### Backend
- Java 21
- Spring Boot 3.5.13
- Spring Cloud 2025.0.2
- Spring AI 1.1.4
- H2 in-memory
- OpenFeign, Resilience4j, Eureka, Gateway

### Frontend
- Angular 21
- Bootstrap + Bootstrap Icons
- RxJS
- ngx-markdown

## 🔌 Ports & URLs par défaut

- Eureka Discovery: `http://localhost:8761`
- API Gateway: `http://localhost:9999`
- Customer Service: `http://localhost:8056`
- eBank Service: `http://localhost:8057`
- eBank Bot: `http://localhost:8058`
- Front Angular: `http://localhost:4200`

## 🚀 Lancer le projet en local

> Ouvrir un terminal par service (ou utiliser votre IDE avec des configurations multi-run).

### 1) Démarrer Discovery Service

```bash
cd discovery-service
./mvnw spring-boot:run
```

### 2) Démarrer Gateway Service

```bash
cd gateway-service
./mvnw spring-boot:run
```

### 3) Démarrer Customer Service

```bash
cd customer-service
./mvnw spring-boot:run
```

### 4) Démarrer eBank Service

```bash
cd ebank-service
./mvnw spring-boot:run
```

### 5) Démarrer eBank Bot

```bash
cd ebank-bot
./mvnw spring-boot:run
```

### 6) Démarrer le frontend Angular

```bash
cd ebank-ang-front
npm install
npm start
```

## 🧪 Endpoints utiles

### Customer Service
- `GET /customers`
- `GET /customers/{id}`
- `POST /customers`

Exemple:
```bash
curl http://localhost:8056/customers
```

### eBank Service
- `GET /account`
- `GET /account/{id}`
- `POST /account`

Exemple:
```bash
curl http://localhost:8057/account
```

### Via Gateway

Les routes dynamiques passent par le nom du service Eureka (en majuscules), par exemple:

- `http://localhost:9999/CUSTOMER-SERVICE/customers`
- `http://localhost:9999/EBANK-SERVICE/account`
- `http://localhost:9999/EBANK-BOT/chat?query=Bonjour`

## 🤖 Assistant IA (ebank-bot)

L’assistant expose:

- `GET /chat?query=...`
- `GET /chatStream?query=...`

Le bot est configuré pour utiliser:

- Ollama (`spring.ai.ollama.base-url=http://localhost:11434`)
- modèle `qwen3-vl:latest`
- connexions MCP vers:
  - `http://localhost:8056/mcp` (customer-service)
  - `http://localhost:8057/mcp` (ebank-service)

### Pré-requis IA

Installer et démarrer Ollama localement, puis télécharger le modèle:

```bash
ollama pull qwen3-vl:latest
```

## 🗃️ Données de démonstration

Au démarrage:

- `customer-service` crée 3 clients (`Mohammed`, `Imane`, `Yassine`).
- `ebank-service` crée 15 comptes (5 par client, IDs clients 1..3).

## 📌 Notes importantes

- Les bases H2 sont en mémoire : les données sont réinitialisées à chaque redémarrage.
- Le frontend pointe actuellement vers le Gateway en dur (`http://localhost:9999/...`).
- Pour Discord/Telegram, renseigner les tokens dans `ebank-bot/src/main/resources/application.properties`.

## ✅ Vérification rapide

Quand tout est démarré:

```bash
curl http://localhost:8761
curl http://localhost:9999/EBANK-SERVICE/account
curl "http://localhost:9999/EBANK-BOT/chat?query=Liste%20les%20clients"
```
