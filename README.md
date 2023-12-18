# Course Name
TIJO Project

# Author
Bartłomiej Kotapka

# Project Topic
Calendar

# Project Description
The project involves creating a system for adding tasks to the calendar.

# Running the Project
- to run frontent: download node modules and then npm start.
- to run backend: you have to add database and configure properties in application.properties, then you can run project in InzApplication file.

# Running Tests
- to run test: go to file with test in Intelij and click run.

# Technologies Used in the Project
- Java 17
- Spring Boot
- Groovy
- Spock
- Maven
- JavaScript

# Api documentation
- project uses swagger on http://localhost:8080/swagger-ui.html

## MANUAL TESTS

### 1. Adding category properly
   - Step 1: Click on the add category button on the side panel
   - Step 2: Complete data correctly
   - Step 3: Refresh the side panel
   - Step 4: The new category should be visible on the side panel

### 2. Adding category wrongly
   - Step 1: Click on the add category button on the side panel
   - Step 2: Complete data wrongly
   - Step 3: The new category should not be visible on the side panel

### 3. Adding task properly
   - Step 1: Click on the add task button on the side panel
   - Step 2: Complete data correctly
   - Step 3: Refresh the side panel
   - Step 4: The new task should be visible on the side panel

### 4. Adding task wrongly
   - Step 1: Click on the add task button on the side panel
   - Step 2: Complete data wrongly
   - Step 3: The new task should not be visible on the side panel

### 5. Assign task to a specific day properly
   - Step 1: Click on a specific day
   - Step 2: Complete data correctly
   - Step 3: The new task should be visible on that specific day

### 6. Assign task to a specific day wrongly
   - Step 1: Click on a specific day
   - Step 2: Complete data wrongly
   - Step 3: The new task should not be visible on that specific day

### 7. Delete task from a specific day
   - Step 1: Click on the task in a specific day
   - Step 2: Click on the delete button
   - Step 3: The task should not be visible on that specific day

### 8. Should not add the same category
   - Step 1: Click on the add category button on the side panel
   - Step 2: Complete data correctly
   - Step 3: The new category should be visible on the side panel
   - Step 4: Complete the same data
   - Step 5: Only one category should be visible on the side panel

### 9. Should not add a task with the same name to the category again
   - Step 1: Click on the add task button on the side panel
   - Step 2: Complete data correctly
   - Step 3: The new task should be visible on the side panel
   - Step 4: Complete data again
   - Step 5: Only one task in a specific category should be visible

### 10. Should add a task with the same name to different categories
   - Step 1: Click on the add task button on the side panel
   - Step 2: Complete data correctly
   - Step 3: The new task should be visible on the side panel
   - Step 4: Complete data again but with a different category
   - Step 5: Both tasks should be visible but with different categories
