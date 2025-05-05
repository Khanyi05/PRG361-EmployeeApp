Employee Management System
A Full-Stack Java Application with Web Login & Desktop Management

Java
PostgreSQL
Apache Tomcat
JavaDB
Swing

A two-part enterprise system featuring:
1️⃣ Web-based login/registration (JSP/Servlets + PostgreSQL)
2️⃣ Desktop employee management (Java Swing + JavaDB) with full CRUD operations

📌 Key Features
🌐 Milestone 1: Web Application
✅ User Authentication

Employee registration with validation (unique ID/email)

Secure login with session management

✅ PostgreSQL Integration

Employee and Login tables with proper relationships

Servlets for database operations

✅ JSP Pages

Interactive forms (register.jsp, login.jsp)

Protected dashboard.jsp with logout

💻 Milestone 2: Desktop Application
✅ Swing GUI

NetBeans-built interface with:

Employee/Department/Payroll management tabs

Intuitive CRUD operations

✅ JavaDB Database

Tables for employees, departments, roles, payroll

JDBC connectivity with transaction support

✅ OOP Implementation

Proper use of inheritance, polymorphism

Collections framework for data handling

Comprehensive exception handling

🛠️ Tech Stack
Component	Technology Used
Frontend	JSP, Swing
Backend	Java Servlets, Core Java
Databases	PostgreSQL, JavaDB
Servers	Apache Tomcat/GlassFish
Build Tool	Maven (recommended)

🚀 Deployment
Web Application
Configure PostgreSQL with tables:

sql
CREATE TABLE employees (
  emp_id VARCHAR(10) PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL
  -- Additional fields
);
Deploy WAR file to Tomcat:

bash
mvn clean package && cp target/*.war $TOMCAT_HOME/webapps/
Desktop Application
Configure JavaDB in NetBeans

Run Main.java from the GUI package

📜 License
MIT License - See LICENSE for details

🔍 Future Enhancements
🔸 Password encryption (BCrypt)
🔸 Email verification for registration
🔸 PDF payslip generation in desktop app
🔸 REST API for mobile compatibility

