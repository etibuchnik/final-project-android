package com.example.shift_manager.Logic;

import java.util.ArrayList;
import java.util.List;

public class User {
    public String username;
    public String email;
    public String fullname;

    public int manager; //0 - no , 1- yes
    public int salary;
    public int numshifts;
    public List<Integer> shiftsIWant;






    public User(String username, String fullname, String email, int manager, int salary) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.manager = manager;
        this.salary = salary;
        shiftsIWant = new ArrayList<Integer>();
        shiftsIWant.add(0,0);
        shiftsIWant.add(1,0);
        shiftsIWant.add(2,0);
        shiftsIWant.add(3,0);
        shiftsIWant.add(4,0);
        shiftsIWant.add(5,0);
        shiftsIWant.add(6,0);
        shiftsIWant.add(7,0);
        shiftsIWant.add(8,0);
        shiftsIWant.add(9,0);
        shiftsIWant.add(10,0);
        shiftsIWant.add(11,0);
        numshifts = 0;
    }

    public int getManager() {
        return manager;
    }

    public User setManager(int manager) {
        this.manager = manager;
        return this;
    }

    public int getSalary() {
        return salary;
    }

    public User setSalary(int salary) {
        this.salary = salary;
        return this;
    }

    public User() {
    }


    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullname() {
        return fullname;
    }

    public User setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }
}

