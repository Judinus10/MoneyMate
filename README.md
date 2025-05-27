# **MoneyMate – Personal Finance Tracker**

**MoneyMate** is a web-based personal finance tracker that helps users manage their income and expenses while providing insights through visual analytics to promote better budgeting and saving habits.

---

## **Project Overview**

MoneyMate allows users to:

* Track income and expenses.
* Categorize transactions.
* Visualize monthly spending with pie or bar charts.
* Set budget goals and monitor financial health.

---

## **Core Features**

* **User Authentication**: Login and registration pages.
* **Track Transactions**: Add, edit, and delete income and expense records.
* **Dashboard**: Displays total balance, income, expenses, and charts of monthly spending.
* **Transaction Filters**: View expenses by date and category.
* **Analytics**: Visual reports (pie/bar charts) for better understanding of spending.
* **Budget Management**: Set and track budget limits.
* **Settings**: Manage profile information, notifications, and dark mode toggle.

---

## **Technologies Used**

* **Frontend**: HTML, CSS
* **Backend**: Spring Boot (Java)
* **Database**: MySQL
* **Charting Library**: Chart.js or D3.js for visualizations

---
## **Project Scope**
https://docs.google.com/document/d/1TE4z8oTNCldce4V4OIDJGfvcU5IhJpNJdbZ0XPKouug/edit?usp=sharing  

---
## **Project Structure**

```
money-management-app/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── moneyapp/
│   │   │               ├── MoneyAppApplication.java           # Main entry point
│   │   │               ├── controller/                        # Controllers to handle requests
│   │   │               │   └── HomeController.java            # Controller for the home page
│   │   │               │   └── TransactionController.java     # Controller for transaction management
│   │   │               │   └── AnalyticsController.java       # Controller for expense analysis
│   │   │               │   └── SettingsController.java        # Controller for settings page
│   │   │               ├── model/                              # Model classes (entities)
│   │   │               │   └── Transaction.java               # Entity for transaction data
│   │   │               │   └── Expense.java                   # Entity for expense data
│   │   │               │   └── Income.java                    # Entity for income data
│   │   │               ├── repository/                         # Repository interfaces for data access
│   │   │               │   └── TransactionRepository.java     # Repository for transaction data
│   │   │               │   └── ExpenseRepository.java         # Repository for expenses
│   │   │               │   └── IncomeRepository.java          # Repository for income
│   │   │               ├── service/                            # Service layer for business logic
│   │   │               │   └── TransactionService.java        # Service for handling transaction logic
│   │   │               │   └── ExpenseService.java            # Service for managing expenses
│   │   │               │   └── IncomeService.java             # Service for managing income
│   │   │               └── config/                             # Configuration for database and other services
│   │   │                   └── WebConfig.java                  # Web configuration (like CORS, etc.)
│   │   ├── resources/
│   │   │   ├── application.properties                          # Configuration file (H2, JPA, etc.)
│   │   │   ├── static/                                         # Static resources (CSS, JS, images)
│   │   │   │   ├── css/
│   │   │   │   ├── js/
│   │   │   │   └── img/
│   │   │   ├── templates/                                      # Thymeleaf templates for dynamic HTML
│   │   │   │   └── home.html                                   # Home page template
│   │   │   │   └── transactions.html                           # Transactions page template
│   │   │   │   └── analytics.html                              # Analytics page template
│   │   │   │   └── settings.html                               # Settings page template
│   │   │   └── application.properties                          # Spring application config
├── pom.xml                                                      # Maven configuration (dependencies)
└── README.md                                                    # Project documentation
```

### Key Components:

* **`MoneyAppApplication.java`**: The main class to run the Spring Boot application.
* **`controller/`**: Contains the controllers responsible for handling HTTP requests for each page (e.g., Home, Transactions, Analytics, Settings).
* **`model/`**: Contains the entities (model classes) like `Transaction`, `Expense`, `Income`, etc.
* **`repository/`**: Contains interfaces that extend `JpaRepository` to perform CRUD operations on the database.
* **`service/`**: Contains the business logic. This layer interacts with the repository layer and performs any needed calculations or transformations before returning the results to the controllers.
* **`resources/application.properties`**: Configuration file to set up the H2 database and Spring Boot settings.
* **`templates/`**: Thymeleaf templates for rendering dynamic HTML pages.
* **`static/`**: Contains your static resources like CSS, JS, and images.

---

## **Setup and Installation**

### Clone this repository:

```bash
git clone https://github.com/Judinus10/MoneyMate.git
```

### Backend Setup (Spring Boot):

1. Navigate to the backend folder.
2. Open the project in your favorite IDE (IntelliJ IDEA, Eclipse, etc.).
3. Build the project using Maven or Gradle.
4. Run the Spring Boot server:

   ```bash
   mvn spring-boot:run
   ```

### Frontend Setup:

1. Navigate to the frontend folder.
2. Open the HTML, CSS files in your browser or IDE.
3. Serve them through a local server (if needed).

---

## **Future Enhancements**

* Mobile app version using **Flutter** for multi-platform support (iOS/Android).
* AI-based expense prediction and personalized savings recommendations.
* Cloud sync for user data.
* Multi-currency support.
* Real-time sync with bank accounts.

---

## **Contributing**

We welcome contributions! Please feel free to fork this repository and submit pull requests with improvements, bug fixes, or new features.

---

## **License**

This project is licensed under the MIT License.

---

