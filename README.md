# 🛒 E-commerce System

A didactic e-commerce project built in **Java** with a **web frontend**, designed to demonstrate proficiency in **OOP**, **CRUD operations**, **authentication**, **frontend-backend integration**, and **Linux deployment**.

## 🚀 Objective
This portfolio project is fully functional and intended to be testable by recruiters, showcasing clean code, documentation, and a clear roadmap for development.

## 🧱 Technology Stack 
- **Backend:** Java 
- **Frontend:** HTML, CSS, JavaScript
- **Database:** MySQL
- **Infrastructure:** Ubuntu Server, Apache

## 📍 Roadmap
- **Phase 1:** Project setup, configuration files, and README 
- **Phase 2:** Basic backend in Java (models + in-memory CRUD)
- **Phase 3:** Initial frontend (layout + interactions)
- **Phase 4:** REST API and frontend-backend integration
- **Phase 5:** Database integration (real persistence)
- **Phase 6:** Admin panel (product CRUD, access control)
- **Phase 7:** Linux deployment + documentation
- **Phase 8:** Automated tests

## 🗺️ Initial architecture diagram
```mermaid
flowchart LR
A[User] -->|HTTP| B[Frontend (HTML/CSS/JS)]
B -->|fetch/REST| C[Java Backend]
C --> D[(Database)]
C --> E[Authentication/Authorization]

---

👤 Author: Endrius-Albert