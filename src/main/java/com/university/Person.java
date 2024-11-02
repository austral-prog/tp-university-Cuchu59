package com.university;

public abstract class Person {
    private String name;
    private String email;

    public Person (String name,String  email) {
        this.name = name;
        this.email = email.toLowerCase();
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    
    public boolean equals(Person person) {
        return (person.getName().toLowerCase()).equals(this.getName().toLowerCase());
    }

}