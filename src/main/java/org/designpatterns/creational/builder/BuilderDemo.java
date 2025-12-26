package org.designpatterns.creational.builder;

public class BuilderDemo {
        public static void main(String[] args) {
            User user1 = new User.Builder("Abhijit", 25).build();
            User user2 = new User.Builder("Abc", 35)
                    .email("a@email.com")
                    .premium(true)
                    .build();

            System.out.println(user1);
            System.out.println(user2);
        }
}
