# Microservices : School, Student & Eureka

Ce projet met en place une **architecture microservices** avec :
- **Eureka Server** : registre de services pour la découverte dynamique.
- **Microservice School** : gère les **écoles** (CRUD), données stockées en **PostgreSQL**.
- **Microservice Student** : gère les **étudiants** (CRUD), données stockées en **MongoDB**, et appelle School pour récupérer l’école associée.

## Endpoints

### School
- `GET /api/schools`
- `GET /api/schools/{id}`
- `POST /api/schools`
- `PUT /api/schools/{id}`
- `DELETE /api/schools/{id}`

### Student
- `GET /api/students`
- `GET /api/students/{id}` (retourne aussi l’école associée)
- `POST /api/students`
- `PUT /api/students/{id}`
- `DELETE /api/students/{id}`

Chaque microservice s’enregistre automatiquement auprès d’**Eureka**, permettant ainsi la **découverte** et la **communication** entre services.