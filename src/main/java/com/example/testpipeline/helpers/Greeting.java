package com.example.testpipeline.helpers;

public class Greeting {
    String salutation;

    public Greeting() {
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "salutation='" + salutation + '\'' +
                '}';
    }
}
