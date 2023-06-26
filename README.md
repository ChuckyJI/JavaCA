### 1. CAPS's Frontend Framework  

Part | Language | Port
 :-----: | :-----:  | :----:
**Admin** | React  | 3000
**Lecturer** | HTML | 8080
**Student** | HTML | 8080

### 2. CAPS's Basic Logic

#### 2.1 Database
Database Name|                       Admin*                        | Lecturer | Student 
 :----: |:---------------------------------------------------:|:--------:|  :----:
Student(Stu/Lec)|                       C/R/U/D                       |    -     |-
Course|                       C/R/U/D                       |    -     |-
Role*|                          R                          |    -     |-
College*|                          R                          |    -     |-
Enrollment|    D(only happpened when delete course/Student)     |   R/U    |C/R/U
Grade|    D(only happpened when delete course/Student)     |  C/R/U   |R
Lecturer_Course*(Generated automaticaly)| C/R/U/D(only happpened when delete course/Lecturer) |R|R

**Admin**: This admin actor is a user who can only arrange current resources in the university. If the university would like to add a new college or new role, this CAPS's admin does not have enough authorization to do that.

**Role / College** : These two tables need inheritate from university super database. They can only read and assign value to students or lacturers.

**Lecturer_Course** : This table is generated automatically when we set many-to-many relationship between course and lecturer.

#### 2.2 Grading 
Testing times |         <40 Mark          | <40 GPA |     ≥40 Mark     | ≥40 GPA
:----:|:-------------------------:|:---:|:----------------:|:----:
1| RED(When enrolling again) |F|      GREEN       |GPA Rules
2|           GRAY            |F| (GREEN) if  ≥ 50 | D+
2|           GRAY            |F| (GREEN) if < 50  |D

**SAMPLE**
![SAMPLE](src/main/resources/static/readme-1.png)

#### 2.3 Visiting across roles (for typing link manually only)
~ |Admin|Lecturer|Student
:----:|:----:|:----:|:----:
Admin| - |Current Page|Current Page
Lecturer|Admin(Without data)|-|Lecturer Main Page (View Course Taught)
Student|Admin(Without data)| Student Main Page(View Enrolled Course)|-

### 3. HOW TO RUN
1. Connect your MySQL database, then create your own database and remember the name
2. Link to [application.properties](src/main/resources/application.properties) to modify your database name (Line 2)
3. Run your [JavaCaApplication](src/main/java/com/example/javaca/JavaCaApplication.java)
4. Open the [testDB.sql](testDB.sql), then insert the test data
5. Make sure you have installed Node. if not, please visit [Nodejs.org](https://nodejs.org/en)
6. Open your Terminal / Command, and open this folder by using "cd folder_name"
7. Run "npm install" (**first run only**)
8. Run "npm start" (No data, for no login any actors) and quit the browser
9. Re-Run your [JavaCaApplication](src/main/java/com/example/javaca/JavaCaApplication.java) (not necessary)
10. Type [http://localhost:8080](http://localhost:8080/)
11. Enjor your test!

### 4.Test Account
For Student: 
>Email : **javaCAstudent@gmail.com**
>
> Password: javaca
>
> >In order to test the Email-Service:  
> >Email Address: **javaCAstudent@gmail.com**  
> >Email Password:cba321#@!

For Lecturer:
>Email : **test1@lec.nus.edu**
> 
> Password:test1

For Admin:
>Email : **test@admin.nus.edu**
> 
> Password:test
