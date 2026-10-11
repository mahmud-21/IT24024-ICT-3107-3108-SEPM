# When to Stack and When to Heap

Demonstrating **Stack** and **Heap** memory in Java.

| | |
|---|---|
| **Name** | Md Mahmudur Rahman |
| **ID** | IT24024 |
| **Course** | ICT 3108 |

---

## Table of Contents

1. [Quick Summary: Stack vs Heap](#1-quick-summary-stack-vs-heap)
2. [Project Structure](#2-project-structure)
3. [How to Compile and Run](#3-how-to-compile-and-run)
4. [Problem 1: Student.java](#4-problem-1-studentjava)
5. [Problem 2: Employee.java](#5-problem-2-employeejava)
6. [Answers to Common Confusions](#6-answers-to-common-confusions)
7. [Conclusion](#7-conclusion)

---

## 1. Quick Summary: Stack vs Heap

| Feature | Stack | Heap |
|---|---|---|
| **What lives here** | Local variables, method parameters, object *references* | Objects and their instance fields |
| **Created when** | A method is called (a *stack frame* is pushed) | `new` is executed |
| **Removed when** | The method returns (frame is popped) | The Garbage Collector finds no references to the object |
| **Shared between threads?** | No, each thread has its own stack | Yes, shared by all threads |
| **Speed** | Very fast | Slower |
| **Size** | Small | Large |

**Simple rule of thumb**

- If a variable is declared **inside a method** (including its parameters), it lives on the **stack**.
- If something is created with **`new`**, the object lives on the **heap**. The variable that points to it is just a reference, and that reference sits on the stack (when it is a local variable).
- An **instance variable** (declared in the class but outside any method) lives **inside its object**, so it is on the **heap**.

---

## 2. Project Structure

```
.
├── Student.java      # Problem 1
├── Employee.java     # Problem 2
└── README.md         # This report
```

---

## 3. How to Compile and Run

```bash
# Problem 1
javac Student.java
java Student

# Problem 2
javac Employee.java
java Employee
```

Both programs ask for input from the keyboard (`Scanner`).

---

## 4. Problem 1: Student.java

### Goal

Show where a **local variable**, an **instance variable**, and an **object** are stored.

### Code

```java
import java.util.Scanner;
public class Student{

    int age;

    Student(int age){
        this.age = age;
        System.out.println("Student Object Created");
    }

    public void display(int m){
        int marks;
        marks = m;
        System.out.println("Instance Variable (age): " + age);
        System.out.println("Local Variable (marks): " + marks);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Age: ");
        int age = sc.nextInt();
        System.out.print("Marks: ");
        int marks = sc.nextInt();

        Student s1 = new Student(age);
        System.out.println("Object Identity Hashcode: " + s1.hashCode());
        s1.display(marks);
    }
}
```

### Where is each variable stored?

| Variable | Declared in | Memory area | Why |
|---|---|---|---|
| `sc` (reference) | `main()` | **Stack** | Local variable of `main` |
| `Scanner` object | created by `new Scanner(...)` | **Heap** | Created with `new` |
| `age` (in `main`) | `main()` | **Stack** | Local primitive |
| `marks` (in `main`) | `main()` | **Stack** | Local primitive |
| `s1` (reference) | `main()` | **Stack** | Local variable holding a reference |
| `Student` object | created by `new Student(age)` | **Heap** | Created with `new` |
| `age` (field) | inside `Student` | **Heap** | Instance variable, stored inside the object |
| `m` (parameter) | `display()` | **Stack** | Method parameter, in `display`'s frame |
| `marks` (in `display`) | `display()` | **Stack** | Local variable, in `display`'s frame |

> Note: there are **two different `age`s** and **two different `marks`**. The ones in `main` are stack locals; the `age` field is an instance variable on the heap; the `marks` in `display` is a separate local in a different frame.

### Memory diagram (while `display()` is running)

```
            STACK                                   HEAP
   ┌──────────────────────────┐          ┌─────────────────────────┐
   │ display() frame          │          │  Student object         │
   │   this  ─────────────────┼────────► │    age = (your input)   │
   │   m     = (marks input)  │          └─────────────────────────┘
   │   marks = (marks input)  │                     ▲
   ├──────────────────────────┤                     │
   │ main() frame             │                     │
   │   args                   │          ┌──────────┴──────────────┐
   │   sc    ─────────────────┼────────► │  Scanner object         │
   │   age   = (age input)    │          └─────────────────────────┘
   │   marks = (marks input)  │
   │   s1    ─────────────────┼──────────► (the Student object above)
   └──────────────────────────┘
```

### Program flow

1. `main()` starts and its stack frame is created.
2. The `Scanner` object is created on the heap; `sc` on the stack points to it.
3. The user enters `age` and `marks`, which are stored on the stack.
4. `new Student(age)` creates the object on the heap; `s1` on the stack points to it.
5. `hashCode()` prints the object's identity hash code.
6. `display(marks)` is called. A new frame is pushed with `m` and `marks`, then popped when the method ends.

### Sample output

> Paste your **actual** output here (the hash code will be different on every machine and run).

```
Age: <your input>
Marks: <your input>
Student Object Created
Object Identity Hashcode: <number>
Instance Variable (age): <your input>
Local Variable (marks): <your input>
```

*(Add a screenshot of your terminal or IntelliJ run window here.)*

---

## 5. Problem 2: Employee.java

### Goal

Show **stack frames**, **heap objects**, and how an **inner class** object relates to its outer object.

### Code

```java
import java.util.Scanner;

public class Employee {
    double salary;

    Employee(double salary){
        this.salary = salary;
        System.out.println("Employee Object Created");
    }

    public void display(double b){
        System.out.println("\nInside the display() method:");
        double bonus;
        bonus = b;
        System.out.println("Salary (Instance Variable): " + salary);
        System.out.println("Bonus (Local Variable): " + bonus);
    }

    class Report{

        Report(){
            System.out.println("\nReport Object Created");
        }

        public void show(){
            System.out.println("Report Identity Hash Code: " + this.hashCode());
            System.out.println("Report: Employee Salary is " + salary);

            System.out.println("\nMemory Concept:");
            System.out.println("Employee object -> Heap");
            System.out.println("Report object -> Heap");
            System.out.println("salary -> Instance field");
            System.out.println("bonus -> Local variable in display()");
            System.out.println("show() -> Executes in its own method frame");
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();
        System.out.print("Enter Bonus: ");
        double bonus = sc.nextDouble();
        Employee e1 = new Employee(salary);
        System.out.println("Employee Identity Hash Code: " + e1.hashCode());
        e1.display(bonus);

        Employee.Report r1 = e1.new Report();
        r1.show();
    }
}
```

### Where is each variable stored?

| Variable / Object | Memory area | Why |
|---|---|---|
| `sc`, `salary`, `bonus`, `e1`, `r1` (in `main`) | **Stack** | Local variables of `main()` |
| `Employee` object | **Heap** | Created with `new Employee(...)` |
| `salary` (field) | **Heap** | Instance variable inside the `Employee` object |
| `b` (parameter), `bonus` (in `display`) | **Stack** | Live in `display()`'s frame, gone when it returns |
| `Report` object | **Heap** | Created with `e1.new Report()` |
| `this` inside `show()` | **Stack** | A reference stored in `show()`'s frame |

### The inner class idea

`Report` is an **inner (non-static) class**. Every `Report` object secretly keeps a reference to the `Employee` object that created it. That is why `show()` can print `salary` directly, without writing `e1.salary`.

```
            STACK                                    HEAP
   ┌──────────────────────────┐         ┌───────────────────────────┐
   │ show() frame             │         │  Report object            │
   │   this ──────────────────┼───────► │   (hidden link to outer)──┼──┐
   ├──────────────────────────┤         └───────────────────────────┘  │
   │ main() frame             │                                        ▼
   │   e1 ────────────────────┼───────► ┌───────────────────────────┐
   │   r1 ──► (Report above)  │         │  Employee object          │
   │   salary = (input)       │         │    salary = (your input)  │
   │   bonus  = (input)       │         └───────────────────────────┘
   └──────────────────────────┘
```

Because `Report` needs an `Employee` to exist first, we create it with `e1.new Report()`, not `new Report()`.

### Program flow

1. `main()` reads `salary` and `bonus` (stack variables).
2. `new Employee(salary)` creates the Employee on the heap; `e1` references it.
3. `e1.display(bonus)` pushes a frame holding `b` and `bonus`; the frame is popped when it ends.
4. `e1.new Report()` creates the Report on the heap, linked to `e1`.
5. `r1.show()` pushes a new frame, prints the hash code and `salary`, and prints the memory notes.

### Sample output

> Paste your **actual** output here (hash codes differ on each run).

```
Enter Salary: <your input>
Enter Bonus: <your input>
Employee Object Created
Employee Identity Hash Code: <number>

Inside the display() method:
Salary (Instance Variable): <your input>
Bonus (Local Variable): <your input>

Report Object Created
Report Identity Hash Code: <number>
Report: Employee Salary is <your input>

Memory Concept:
Employee object -> Heap
Report object -> Heap
salary -> Instance field
bonus -> Local variable in display()
show() -> Executes in its own method frame
```

*(Add a screenshot here.)*

---

## 6. Answers to Common Confusions

**Q1. Is `s1` stored on the heap or the stack?**
The variable `s1` is a local variable, so it is on the **stack**. It only holds a *reference*. The `Student` object it points to is on the **heap**.

**Q2. Where do primitive instance variables like `age` live?**
Inside the object, so on the **heap**. Only primitive *local* variables live on the stack.

**Q3. What does `hashCode()` print? Is it the memory address?**
It prints the object's *identity hash code*. It identifies the object, but it is not guaranteed to be the real memory address. It can change from run to run.

**Q4. What happens to a stack frame when a method ends?**
The frame is popped and all its local variables and parameters disappear (like `m` and `marks` in `display()`).

**Q5. When is a heap object removed?**
When no variable references it any more, the Garbage Collector can free it later. You do not delete it manually in Java.

**Q6. Why is there a different value for `age` in `main` and in `Student`?**
The `age` in `main` is a stack local. The `age` inside the object is an instance field on the heap. The constructor copies the first into the second with `this.age = age`.

**Q7. Why can `Report` use `salary` directly?**
An inner class object holds a hidden reference to its outer `Employee` object, so it can read the outer object's fields.

---

## 7. Conclusion

- **Stack** holds local variables, parameters, and references. It is created and removed automatically with each method call.
- **Heap** holds objects (everything created with `new`), including their instance fields. It lasts as long as something references the object.
- A **reference variable** is a small stack value that points to a big heap object.
- An **inner class** object lives on the heap and keeps a link to its outer object.

*Submitted by Ishtiak Ahmed Ayon (ID: 24039), ICT 3108.*
