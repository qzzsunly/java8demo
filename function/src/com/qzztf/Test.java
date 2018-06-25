package com.qzztf;

public class Test {
    public static void main(String[] args) {
        Converter<String, Integer> converter = Integer::valueOf;
        Integer integer = converter.conver("100");
        System.out.println(integer);

        PersonFactory factory = Person::new;

        Person person = factory.create("q", "zz");
        System.out.println(person);

    }
}
