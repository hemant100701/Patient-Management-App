# Patient-Management-App
A microservices-based patient management system backend built in Java, featuring billing, patient, and auth services communicating via gRPC and Kafka, fully containerized with Docker and deployed in AWS cloud (EC2 Instances).


# Patient Management System — Backend
A scalable, microservices-based backend for a patient management application, built with Java. The system is composed of independently deployable services that communicate through gRPC (synchronous) and Kafka (asynchronous/event-driven), enabling reliable and loosely-coupled service interactions.

## Architecture
- API Gateway Service - Controls the access from the end User to different microservices after validating the request.
- Patient Service – Manages patient records, profiles, and medical data
- Billing Service – Handles invoicing, payments, and billing history
- Auth Service – Manages authentication, authorization, and access control
- Integration Test Service – Automated end-to-end tests validating service interactions

## Tech Stack
- Language: Java
- Messaging: Apache Kafka (event-driven communication), gRPC (inter-service RPC)
- Containerization: Docker (each service runs in its own container)
- Architecture Style: Microservices
- Deployment: AWS(EC2 Instances)

## Key Highlights
- Event-driven design using Kafka for decoupled, asynchronous workflows
- High-performance service-to-service communication via gRPC
- Dockerized services for consistent deployment across environments
- Dedicated integration test service ensures end-to-end reliability across microservices
