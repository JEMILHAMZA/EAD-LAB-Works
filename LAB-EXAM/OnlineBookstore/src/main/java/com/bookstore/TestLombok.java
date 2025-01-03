package com.bookstore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TestLombok {
    private String message;

    public static void main(String[] args) {
        TestLombok obj = new TestLombok();
        obj.setMessage("Lombok is working!");
        System.out.println(obj.getMessage());
    }
}
