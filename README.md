# Student Course Management System

A console-based Java application for recording and managing a semester's course list — built for the COS 201 (Programming I) mid-semester lab assessment at MIVA Open University.

## Overview

This program lets a student add courses, view all recorded courses, search for a course by code, compute total credit units, and save/load the course list to and from a file — all through a simple text menu.

## Features

- **Add Course** — record a course code, title, and unit value, with validation for empty fields, invalid units, and duplicate codes
- **View All Courses** — display every recorded course in a clean formatted list
- **Search Course by Code** — find a course using a recursive search
- **Compute Total Units** — sum up all credit units for the semester
- **Save to File** — persist the course list to `courses.txt`
- **Load from File** — reload a previously saved course list, skipping any corrupted lines
- **Exit Program**

## Project Structure

```
├── Course.java                 # Model class: course code, title, units
├── CourseManager.java           # Core logic: add, display, search, totals, file I/O
├── InvalidCourseException.java  # Custom exception for invalid course data
└── Main.java                    # Console menu and program entry point
```

## How It Works

- **Recursion** is used in two places: `findCourseRecursive()` walks the course list to locate a course by code, and `runMenu()` recursively redisplays the menu after each operation until the user exits.
- **Iteration** (standard loops) is used for displaying all courses and computing total units.
- **Exception handling** covers both invalid user input (non-numeric values) and invalid course data (via the custom `InvalidCourseException`), so the program never crashes on bad input.
- **File I/O** uses `PrintWriter`/`BufferedReader` to save and load courses as comma-separated lines.

## Getting Started

### Requirements
- Java JDK 17 or later

### Compile and Run

```bash
javac *.java
java Main
```

### Sample Interaction

```
====================================
 STUDENT COURSE MANAGEMENT SYSTEM
====================================

1. Add Course
2. View All Courses
3. Search Course by Code
4. Compute Total Units
5. Save to File
6. Load from File
7. Exit Program
Enter your choice (1-7): 1
Enter course code (e.g., COS201): COS201
Enter course title: Programming I
Enter course units: 3
Course added successfully: COS201
```

## Author

**Ademola Utibe Afolabi**
Department of Computer Science, MIVA Open University
[codewithdemmy.netlify.app](https://codewithdemmy.netlify.app) · [@CodewithDemmy](https://github.com/CodewithDemmy)
