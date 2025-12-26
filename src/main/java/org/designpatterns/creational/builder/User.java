package org.designpatterns.creational.builder;

public class User {

    // mandatory
    private final String name;
    private final int age;

    // optional
    private final String email;
    private final String country;
    private final boolean premium;

    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
        this.country = builder.country;
        this.premium = builder.premium;
    }

    public static class Builder {

        // mandatory
        private final String name;
        private final int age;

        // optional (defaults)
        private String email = "";
        private String country = "India";
        private boolean premium = false;

        // mandatory fields in builder constructor
        public Builder(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // optional setters
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder premium(boolean premium) {
            this.premium = premium;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}