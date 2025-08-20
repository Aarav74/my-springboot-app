# 🗺️ Road Snapping API (Spring Boot + FastAPI Integration)

This project is a **Spring Boot backend service** that interacts with a **FastAPI service** to snap GPS coordinates to the nearest road.  
It exposes REST APIs for providing sample coordinates, handling frontend requests, and returning snapped road data.

---

## 🚀 Features

- 🌍 **Coordinates Provider** – `/api/coordinates` returns sample GPS coordinates (for frontend testing).  
- 🛰️ **Road Snapping** – `/api/generate-map` accepts frontend coordinates and forwards them to the FastAPI service for road alignment.  
- 🖥️ **Frontend Integration** – Serves `index.html` from the root endpoint.  
- ⚡ Reactive Programming with **Project Reactor** (`Mono<ResponseEntity<T>>`).  
- 🔗 Clean separation of **Controller → Service → DTO**.

---

## 📂 Project Structure

```
src/main/java/com/example/springboot/
├── controller/
│   └── MapController.java      # REST API controller
├── dto/
│   └── FastApiResponse.java    # Response wrapper for FastAPI
├── service/
│   └── RoadSnappingService.java # Service for calling FastAPI
```

---

## 🛠️ Tech Stack

- **Spring Boot** (REST API backend)  
- **Spring WebFlux** (reactive stack with `Mono`)  
- **FastAPI** (external service providing road snapping)  
- **Maven/Gradle** (build tool)  
- **Java 17+**  

---

## 📌 API Endpoints

### 1. Root Endpoint
```http
GET /api/
```
👉 Returns `index.html` (for frontend UI integration).

---

### 2. Get Sample Coordinates
```http
GET /api/coordinates
```
👉 Returns a list of predefined GPS coordinates:
```json
[
  [52.5200, 13.4050],
  [52.5205, 13.4055],
  [52.5210, 13.4060]
]
```

---

### 3. Generate Snapped Road Map
```http
POST /api/generate-map
Content-Type: application/json
```

**Request Body:**
```json
{
  "coordinates": [
    [52.5200, 13.4050],
    [52.5205, 13.4055],
    [52.5210, 13.4060]
  ]
}
```

**Successful Response:**
```json
{
  "status": "success",
  "snappedPoints": [...]
}
```

**Error Response:**
```json
{
  "status": "error",
  "snappedPoints": null
}
```

---

## 🚀 Getting Started

### 1. Clone the repo
```bash
git clone https://github.com/your-username/road-snapping-api.git
cd road-snapping-api
```

### 2. Build the project
```bash
./mvnw clean install
```
_or (if using Gradle):_
```bash
./gradlew build
```

### 3. Run the application
```bash
./mvnw spring-boot:run
```

Server will start at:  
👉 `http://localhost:8080/api`

---

## 📌 Roadmap

- [ ] Add authentication for API access 🔑  
- [ ] Integrate map rendering (Leaflet / Mapbox) 🗺️  
- [ ] Store snapped routes in database 📦  
- [ ] Dockerize Spring Boot + FastAPI setup 🐳  

---

## 🤝 Contributing

Contributions are welcome!  
1. Fork the repo  
2. Create a new branch (`feature/new-feature`)  
3. Commit your changes  
4. Push and open a PR  

---

## 📜 License

This project is licensed under the **MIT License** – you are free to use, modify, and distribute it.
