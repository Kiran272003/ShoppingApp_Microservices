# 🛍️ E-Commerce Microservices Project

A Spring Boot-based Microservices Application with REST APIs, API Gateway, Role-based Security, and global exception handling.

This project demonstrates microservices architecture, service-to-service communication, and best practices in layered design.

---

## ✨ Features

- **📦 Product Service:** Manage products (CRUD, quantity, price updates)
- **👥 Customer Service:** Manage customer profiles and details
- **🛒 Order Service:** Place and manage orders
- **🌐 API Gateway:** Single entry point for all services
- **🛡️ Global Exception Handling:** Custom error responses
- **⚡ Built with Spring Boot & Gradle**

---

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Spring Security**
- **Spring Cloud Gateway (API Gateway)**
- **Gradle for build and dependency management**
- **Database:** Configurable in `application.properties` (e.g., MySQL/Postgres)

---

## 📂 Project Structure

```
API_Gateway/
│── build.gradle
│── src/main/java/com/example/gateway
│   └── GatewayApplication.java      # Entry point for API Gateway

ProductService/
│── build.gradle
│── src/main/java/com/example/demo
│   ├── ProductServiceApplication.java   # Main Spring Boot application
│   ├── SecurityConfig.java              # Security configuration
│   │
│   ├── Controller/
│   │   └── ProductController.java       # REST endpoints for products
│   │
│   ├── Service/
│   │   └── ProductService.java          # Business logic
│   │
│   ├── Repo/
│   │   ├── Product.java                 # Entity
│   │   └── ProductRepository.java       # JPA repository
│   │
│   ├── Exceptions/
│   │   ├── GlobalExceptions.java        # Handles exceptions
│   │   ├── ResourceNotFoundException.java
│   │   └── BadRequestException.java

CustomerService/
│── CustomerServiceApplication.java      # Customer microservice

OrdersService/
│── OrdersServiceApplication.java        # Orders microservice
```

---

## ⚙️ Setup & Installation

**1️⃣ Clone the Repository**
```bash
git clone https://github.com/your-username/Ecommerce-Microservices.git
cd Ecommerce-Microservices
```

**2️⃣ Build the Services**

**3️⃣ Run the Services**

Each microservice can be started individually:

- **API Gateway** will start on `http://localhost:9080`
- **ProductService**, **CustomerService**, and **OrdersService** will start on their configured ports.

---

## 🔑 API Endpoints

| Service             | Method | Endpoint           | Description                                         |
| ------------------- | ------ | ------------------ | --------------------------------------------------- |
| **ProductService**  | GET    | `/product`         | Fetch all products                                  |
|                     | POST   | `/addProduct`      | Add a new product                                   |
|                     | DELETE | `/deleteProduct`   | Delete a product by ID (`pid`)                      |
|                     | GET    | `/getById`         | Fetch product by ID                                 |
|                     | PUT    | `/updateProduct`   | Update product details by ID                        |
|                     | GET    | `/getQtyById`      | Get product quantity & price by ID                  |
|                     | POST   | `/updateQty`       | Update stock quantity of a product                  |
| **CustomerService** | GET    | `/customer`        | Fetch all customers                                 |
|                     | POST   | `/addCustomer`     | Add a new customer                                  |
|                     | GET    | `/getMyOrders`     | Fetch orders of a specific customer                 |
|                     | GET    | `/getProducts`     | Fetch all products (via ProductService)             |
|                     | POST   | `/placeOrder`      | Place a new order (via OrdersService)               |
|                     | DELETE | `/deleteCustomer`  | Delete a customer by ID                             |
|                     | GET    | `/getById`         | Fetch customer by ID                                |
|                     | PUT    | `/updateCustomer`  | Update customer details                             |
| **OrdersService**   | GET    | `/order`           | Fetch all orders                                    |
|                     | POST   | `/addOrder`        | Place a new order (checks stock & updates quantity) |
|                     | GET    | `/getByCustomerId` | Fetch orders by customer ID                         |
|                     | GET    | `/getOrderBy`      | Fetch order by ID                                   |
|                     | DELETE | `/deleteOrder`     | Delete order by ID (and restore stock)              |

---


## 🔒 Security
**OAuth**
Configured via `SecurityConfig.java` in ProductService and other services.

---

