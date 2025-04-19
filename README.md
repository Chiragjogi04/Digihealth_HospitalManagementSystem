# DigiHealth - Hospital Management System

---

##  How to Run the Project

1. **Set up database credentials**  
   Open `src/main/resources/application.properties` and update:
   ```properties
   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password
2. **Initialize the database**:
   Open `src/main/resources/hospital.sql' and
    paste its contents in your MySQL client to create the database.
3. **Start your MySQL server**:
   Make sure MySQL is running before starting the project.
4. **Build the project**
    ```properties
       mvn clean install
5. **Run the project**
   ```properties
       mvn spring-boot:run
6. **Open the browser**
   Go to:
   ```properties
       http://localhost:8080
