# Pahan Edu Book Shop System

A comprehensive web-based book shop management system built using Java Enterprise Edition (JEE) with a three-tier architecture. This system provides role-based access control for administrators and cashiers to manage books, customers, and sales operations.

##  Features

### Admin Features
- **User Management**: Add, edit, and delete cashiers
- **Customer Management**: Add, edit, and delete customer records
- **Inventory Management**: Add, edit, and delete items
- **Billing System**: Create and manage bills
- **Reporting**: View comprehensive sales reports
- **Bill History**: Access complete billing history
- **Help Section**: User assistance and documentation

### Cashier Features
- **Customer Management**: Add and edit customer information
- **Inventory View**: Browse available items
- **Billing System**: Create bills for customers
- **Bill History**: View billing records
- **Help Section**: User assistance and documentation

##  Architecture

The system follows a **3-Tier Architecture** with clean separation of concerns:

### Presentation Layer (Web Layer)
- **Controllers**: Handle HTTP requests and responses
- **WebApps**: JSP pages for user interface
- **Frontend**: HTML, CSS, JavaScript for user interaction

### Business Logic Layer (Service Layer)
- **Services**: Business logic implementation
- **DTOs (Data Transfer Objects)**: Data transfer between layers
- **Mappers**: Convert between DTOs and Models
- **Design Patterns**: Implementation of various design patterns

### Data Access Layer (Persistence Layer)
- **DAOs (Data Access Objects)**: Database operations
- **Models**: Entity classes representing database tables
- **Database**: MySQL database for data persistence

##  Technology Stack

- **Backend**: Java Enterprise Edition (JEE)
- **Web Server**: Apache Tomcat
- **Database**: MySQL (via WAMP Server)
- **Database Management**: phpMyAdmin
- **Build Tool**: Maven
- **IDE Support**: IntelliJ IDEA, VS Code
- **Frontend**: HTML, CSS, JavaScript, JSP

##  Prerequisites

Before setting up the project, ensure you have the following installed:

- Java Development Kit (JDK) 8 or higher
- Apache Tomcat Server
- WAMP Server (Windows) or equivalent LAMP/MAMP stack
- Maven
- IntelliJ IDEA or VS Code
- Web browser

##  Installation & Setup

### Step 1: Clone the Project
```bash
git clone <repository-url>
cd pahan-edu-book-shop-system
```

### Step 2: IDE Setup
1. Open the project in **IntelliJ IDEA** or **VS Code**
2. Ensure Maven dependencies are resolved
3. Configure project SDK to use JDK 8+

### Step 3: Database Setup
1. **Start WAMP Server**
   - Launch WAMP and ensure all services are running
   - Verify that Apache and MySQL services are active

2. **Create Database**
   - Open your web browser and navigate to `http://localhost/phpmyadmin`
   - Create a new database for the project
   - Import the database schema using the `database.sql` file located in the codebase
   - Execute all SQL commands to create tables and initial data

### Step 4: Build the Project
1. **Open Terminal** in your IDE or navigate to project directory
2. **Clean and Package** the project:
   ```bash
   mvn clean package
   ```
3. This command will create a `.war` file in the `target` folder

### Step 5: Deploy to Tomcat
1. **Locate the WAR file** in the `target` directory
2. **Copy the .war file** to Tomcat's `webapps` folder
   - Default location: `<TOMCAT_HOME>/webapps/`
3. **Start Tomcat Service** if not already running
4. Tomcat will automatically deploy the application

### Step 6: Access the Application
1. Open your web browser
2. Navigate to: `http://localhost:8080/demo`
3. The application login page should appear

##  Default Credentials

### Administrator Login
- **Username**: `admin`
- **Password**: `admin123`

### Initial Setup
After logging in as admin, you can:
- Add new cashiers
- Configure system settings
- Set up initial inventory

##  System Workflow

### Admin Workflow
1. Login with admin credentials
2. Dashboard overview of system statistics
3. Manage cashiers (Create accounts, assign permissions)
4. Manage customer database
5. Maintain book inventory
6. Process sales and generate bills
7. View reports and analytics
8. Access help documentation

### Cashier Workflow
1. Login with assigned credentials
2. Access customer management features
3. Browse book inventory
4. Process customer purchases
5. Generate bills and receipts
6. View transaction history
7. Access help section

##  Project Structure

```
pahan-edu-book-shop-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── controllers/     # Request controllers
│   │   │   ├── services/        # Business logic services
│   │   │   ├── dao/             # Data Access Objects
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── models/          # Entity models
│   │   │   ├── mappers/         # DTO-Model mappers
│   │   │   └── utils/           # Utility classes
│   │   ├── resources/           # Configuration files
│   │   └── webapp/              # Web application files
│   │       ├── WEB-INF/         # Web configuration
│   │       ├── css/             # Stylesheets
│   │       ├── js/              # JavaScript files
│   │       └── jsp/             # JSP pages
├── target/                      # Build output
├── database.sql                 # Database schema
├── pom.xml                      # Maven configuration
└── README.md                    # Project documentation
```

##  Design Patterns Used

The system implements several design patterns:
- **MVC (Model-View-Controller)**: Separation of concerns
- **DAO Pattern**: Database abstraction
- **DTO Pattern**: Data transfer optimization
- **Mapper Pattern**: Object transformation
- **Factory Pattern**: Object creation
- **Singleton Pattern**: Single instance management

##  Security Features

- Role-based access control (Admin/Cashier)
- Session management
- Input validation and sanitization
- SQL injection prevention
- Password encryption

##  User Interface

The system features a responsive web interface with:
- Clean and intuitive design
- Role-based navigation menus
- Data tables with search and filtering
- Form validation
- Modal dialogs for confirmations
- Print-friendly bill formats

##  Configuration

### Database Configuration
Update database connection settings in the configuration files:
- Database URL: `jdbc:mysql://localhost:3306/pahanedu_bookshop`
- Username and password for MySQL connection

### Tomcat Configuration
- Ensure proper port configuration (default: 8080)
- Verify context path settings
- Configure session timeout settings

##  Troubleshooting

### Common Issues

**1. Application doesn't load**
- Verify Tomcat is running
- Check if the .war file is properly deployed
- Ensure database connection is active

**2. Database connection errors**
- Verify WAMP server is running
- Check MySQL service status
- Validate database credentials

**3. Build failures**
- Ensure Maven dependencies are resolved
- Check Java version compatibility
- Verify project structure

**4. Login issues**
- Confirm database is properly initialized
- Verify admin credentials
- Check session configuration

##  Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## Developer

**Pahan Edu Book Shop System**
- A comprehensive solution for book shop management
- Built with enterprise-grade architecture and best practices

##  Support

For support and queries:
- Check the Help section within the application
- Review the troubleshooting guide above
- Create an issue in the repository

---

