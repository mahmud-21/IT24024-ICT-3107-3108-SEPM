# ICT-3107-3108-SEPM-
# lecture -01 (03-10-2026,Saturday)
## The code of `Non-Staic`
```java
public class Student {
     int count=0;
    Student(){
        count++;
    }
}
public class Main{
   public static void main(String[] args) {
        Student s1=new Student();
       Student s2=new Student();
       Student s3=new Student();
       System.out.println(s2.count);

    }
}
```
## The code of `Static`
```java

public class Student {
     static int count=0;
    Student(){
        count++;
    }
}
public class Main{
   public static void main(String[] args) {
        Student s1=new Student();
       Student s2=new Student();
       Student s3=new Student();
       System.out.println(s2.count);

    }
}
```
