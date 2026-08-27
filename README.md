# Hospital Management System

## 📌 Project Overview

The Hospital Management System (HMS) is a web-based application developed to manage and simplify the daily operations of a hospital.

The system provides a centralised platform for managing patients, doctors, appointments, medical records, laboratory tests, medicines, billing, employees, reports, and administrator authentication.

---

## 🎯 Project Objectives

The main objectives of the Hospital Management System are:

- Manage patient information
- Manage doctor information
- Schedule and manage appointments
- Maintain medical records
- Manage laboratory tests and results
- Manage medicines and pharmacy stock
- Manage hospital billing and payments
- Manage hospital employees and departments
- Generate hospital reports
- Provide secure administrator login
- Provide a centralised hospital management platform

---

## 🚀 Main Features

### 1. Patient Management

The Patient Management module allows the administrator to:

- Register patients
- View patient information
- Update patient information
- Delete patient records
- Maintain patient medical history

---

### 2. Doctor Management

The Doctor Management module allows the administrator to:

- Register doctors
- View doctor information
- Update doctor information
- Delete doctor records
- Manage doctor departments and specialisations

---

### 3. Appointment Management

The Appointment Management module provides:

- Appointment registration
- Patient selection
- Doctor selection
- Department selection
- Appointment date and time management
- Appointment status management
- Appointment viewing and management

---

### 4. Medical Records

The Medical Records module allows the administrator to manage:

- Diagnosis
- Treatment
- Prescription
- Medical reports
- Patient medical information

---

### 5. Laboratory Management

The Laboratory module allows the hospital to manage laboratory tests through different stages:

```text
REQUESTED
     ↓
SAMPLE_COLLECTED
     ↓
RESULT_ENTERED
     ↓
COMPLETED

Features include:

- Create laboratory test requests
- Select patients
- Select doctors
- Collect samples
- Enter test results
- Update test status
- View laboratory records

---

### 6. Pharmacy Management

The Pharmacy Management module provides:

- Medicine registration
- Medicine search
- Medicine category filtering
- Medicine stock management
- Medicine price management
- Medicine expiry-date checking
- Low-stock monitoring
- Medicine editing
- Medicine deletion

---

### 7. Billing Management

The Billing Management module manages hospital charges and payments.

The total bill is calculated using:

```text
Consultation Charge
        +
Laboratory Charge
        +
Pharmacy Charge
        +
Admission Charge
        =
Total Bill


Then add the Billing features and continue with the remaining README sections.

### Add this next:

```markdown

Features include:

- Create bills
- Select patients
- Calculate total charges
- Billing date management
- Payment information
- Payment status management
- Edit bills
- Delete bills
- Paid/Pending status management

---

### 8. Staff Management

The Staff Management module allows administrators to manage hospital employees.

Employee information includes:

- Employee ID
- Name
- Position
- Department
- Phone
- Email
- Attendance
- Leave records

Features include:

- Employee registration
- Employee management
- Department assignment
- Attendance management
- Leave records

---

### 9. Reports Management

The Reports Management module provides statistical information from the hospital database.

Available reports include:

- Patient Reports
- Appointment Reports
- Revenue Reports
- Pharmacy Reports
- Laboratory Reports
- Staff Reports

Staff reports include:

- Total employees
- Employees by position
- Employees by department

The report information is generated from existing hospital database records.

---

### 10. Dashboard

The Dashboard provides a summary of important hospital information.

It displays:

- Total patients
- Today's appointments
- Revenue summary
- Laboratory requests
- Reports access

---

### 11. Security

The system uses Spring Security to provide authentication and access protection.

Security features include:

- Administrator login
- Password encryption using BCrypt
- Protected application pages
- Login validation
- Logout functionality
- Unauthorized access protection

#### Demo Login

```text
Username: admin
Password: admin123

> This credential is provided for local/project demonstration only. Change the default password before using the application in a real production environment.

---

## 🛠️ Technologies Used

### Backend

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security

### Frontend

- HTML5
- CSS3
- Thymeleaf

### Database

- MySQL

### Build Tool

- Maven

### Development Tools

- Visual Studio Code
- MySQL Workbench
- Git
- Bitbucket

---

## 🗄️ Database

The application uses MySQL as the relational database management system.

The main entities/modules include:

```text
Users
Patients
Doctors
Departments
Appointments
Medical Records
Laboratory Tests
Medicines
Billing
Employees

---

## 📂 Project Structure

```text
hospital-management-system
│
├── .mvn
│   └── wrapper
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── hospital
│   │   │           └── hms
│   │   │               ├── controller
│   │   │               ├── entity
│   │   │               ├── repository
│   │   │               └── security
│   │   │
│   │   └── resources
│   │       ├── static
│   │       ├── templates
│   │       └── application.properties
│   │
│   └── test
│
├── .gitattributes
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md

---

## ▶️ How to Run the Project

### Prerequisites

Before running the project, make sure you have installed:

- Java 17 or later
- MySQL 8.0 or later
- Maven or Maven Wrapper
- Visual Studio Code or another Java IDE

### Step 1 — Clone the Repository

Clone the Hospital Management System repository from Bitbucket.

After cloning, open the project folder in Visual Studio Code.

### Step 2 — Create the Database

Open MySQL Workbench and create the database:

```sql
CREATE DATABASE hospital_management_system;

---

### Step 3 — Configure the Database

Open the following file:

```text
src/main/resources/application.properties

Configure the MySQL connection according to your local MySQL installation.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospital_management_system
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD

```markdown
Replace `YOUR_MYSQL_PASSWORD` with your own MySQL password.

> Do not add your real MySQL password to the README or commit it to Bitbucket.

---

### Step 4 — Run the Application

Open the VS Code terminal inside the project folder and run:

```text
.\mvnw.cmd spring-boot:run

```markdown
Wait until the Spring Boot application starts successfully.

---

### Step 5 — Open the Application

Open your web browser and go to:

```text
http://localhost:8080/login

```markdown
The Hospital Management System login page should appear.

---

## 🔐 Login

Use the following administrator credentials for the local project demonstration:

```text
Username: admin
Password: admin123

After successful login, the administrator will be redirected to the Dashboard.

---

## 📊 Dashboard

The Dashboard provides an overview of important hospital information.

It displays:

- Total patients
- Today's appointments
- Revenue summary
- Laboratory requests
- Reports access

The Dashboard can be accessed at:

```text
http://localhost:8080/dashboard

---

## 📋 Reports

The Reports section provides statistical information from the hospital database.

The main Reports page can be accessed at:

```text
http://localhost:8080/reports

Available reports include:

- Patient Reports
- Appointment Reports
- Revenue Reports
- Pharmacy Reports
- Laboratory Reports
- Staff Reports

The available report pages are:

```text
http://localhost:8080/reports/patients
http://localhost:8080/reports/appointments
http://localhost:8080/reports/revenue
http://localhost:8080/reports/pharmacy
http://localhost:8080/reports/laboratory
http://localhost:8080/reports/staff

The reports are generated using information stored in the hospital database.

---

## 🔄 Git Workflow

Git is used for version control, while Bitbucket is used as the remote repository.

After making changes to the project, use:

```bash
git status
git add .
git commit -m "Describe your changes"
git push

For example, after making changes to the project:

```bash
git add .
git commit -m "Updated billing management"
git push

The normal Git workflow is:

```text
Make changes
     ↓
git status
     ↓
git add .
     ↓
git commit -m "Description"
     ↓
git push
     ↓
Bitbucket updated

---

## ☁️ Bitbucket

The Hospital Management System source code is maintained in Bitbucket.

The repository contains:

- Java source code
- Spring Boot configuration
- HTML templates
- CSS
- Maven configuration
- Database configuration
- Test files
- Project documentation

---

## 🧪 Testing

The following modules have been tested:

- Patient Management
- Doctor Management
- Appointment Management
- Medical Records
- Laboratory Management
- Pharmacy Management
- Billing Management
- Staff Management
- Reports Management
- Dashboard
- Login
- Logout
- Security

Testing includes:

- Page navigation
- Create operations
- Read operations
- Update operations
- Delete operations
- Database integration
- Report statistics
- Login authentication
- Logout functionality
- Protected pages
- Error handling

---

## 🔒 Security Notes

The application uses Spring Security and BCrypt password encoding.

For production deployment:

- Use environment variables for database credentials
- Do not expose database passwords
- Change the default administrator password
- Use HTTPS
- Use a production-ready database
- Use secure session management
- Do not expose sensitive information in source code

---

## 📈 Future Improvements

Possible future improvements include:

- Role-based access for administrators, doctors, nurses, and staff
- Online appointment booking
- Email and SMS notifications
- Patient self-service portal
- Online payment integration
- Advanced analytics and dashboards
- Cloud database integration
- Mobile application
- Automated backup system

---

## 👩‍💻 Developer

**Manushi Manoli**

Hospital Management System

Developed as an academic/project application.

---

## 📄 License

This project was developed for educational and academic purposes.