package com.company;

/*

1710 CRUD
CrUD - Create, Update, Delete
The program starts with one of the following sets of parameters:
-c name sex bd
-u id name sex bd
-d id
-i id
Parameter Values:
name - name, String
sex - gender, "m" or "f", one letter
bd - date of birth in the following format 04/15/1990
-c - adds the person with the given parameters to the end of allPeople, displays id (index) on the screen
-u - updates the data of a person with a given id
-d - logically delete a person with id, replace all his data with null
-i - displays information about a person with id: name sex (m / f) bd (format 15-Apr-1990)
id matches the index in the list
All people should be stored in allPeople
Use Locale.ENGLISH as the second parameter for SimpleDateFormat
Example parameters:
-c Mironov m 04/15/1990
Example output for the -i parameter:
Mironov m 15-Apr-1990

Requirements:
1. The Solution class must contain the public static field allPeople of type List.
2. The Solution class must contain a static block in which two people are added to the allPeople list.
3. When starting the program with the -c parameter, the program should add the person with the given parameters to the end of the allPeople list, and display id (index) on the screen.
4. When starting the program with the -u option, the program should update the person’s data with the given id in the allPeople list.
5. When starting the program with the -d parameter, the program should logically delete the person with the given id in the allPeople list.
6. When starting the program with the -i parameter, the program should display information about the person with the given id in the format specified in the task.


 */


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        DateFormat dateFormatPrt = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        //create person
        if (args[0].startsWith("-c")) {
            Date date = null;
            try {
                date = dateFormat.parse(args[3]);
            } catch (ParseException e) {
                e.getMessage();
            }

            //VALIDATOR: При запуске программы с параметром -с программа должна добавлять человека с заданными
            // параметрами в конец списка allPeople, и выводить id (index) на экран.
            if (args[2].startsWith("м")) {
                Person person = Person.createMale(args[1], date);
                allPeople.add(person);
                System.out.println(allPeople.indexOf(person));
            } else {
                Person person = Person.createFemale(args[1], date);
                allPeople.add(person);
                System.out.println(allPeople.indexOf(person));
            }
        }

        //update person
        if (args[0].startsWith("-u")) {
            int index = Integer.parseInt(args[1]);
            Person person = allPeople.get(index);

            //update name
            person.setName(args[2]);

            //udate date
            Date date = null;
            try {
                date = dateFormat.parse(args[4]);
            } catch (ParseException e) {
                e.getMessage();
            }
            person.setBirthDay(date);

            //update sex
            if (args[3].startsWith("м"))
                person.setSex(Sex.MALE);
            else
                person.setSex(Sex.FEMALE);
        }

        //remove person
        if (args[0].startsWith("-d")) {
            int index = Integer.parseInt(args[1]);
            Person person = allPeople.get(index);

            person.setName(null);
            person.setSex(null);
            person.setBirthDay(null);
        }

        //print person
        if (args[0].startsWith("-i")) {
            int index = Integer.parseInt(args[1]);
            Person person = allPeople.get(index);
            StringBuffer s = new StringBuffer();
            s.append(person.getName());
            s.append(" ");
            s.append(person.getSex() == Sex.MALE ? "м" : "ж");
            s.append(" ");
            s.append(dateFormatPrt.format(person.getBirthDay()));
            System.out.println(s);
        }
    }
}

enum Sex {
    MALE,
    FEMALE
}

class Person {
    private String name;
    private Sex sex;
    private Date birthDay;

    private Person(String name, Sex sex, Date birthDay) {
        this.name = name;
        this.sex = sex;
        this.birthDay = birthDay;
    }

    public static Person createMale(String name, Date birthDay) {
        return new Person(name, Sex.MALE, birthDay);
    }

    public static Person createFemale(String name, Date birthDay) {
        return new Person(name, Sex.FEMALE, birthDay);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}



