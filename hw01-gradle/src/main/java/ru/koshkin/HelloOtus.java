package ru.koshkin;

import com.google.common.collect.Lists;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class HelloOtus {
    public static void main(String[] args) {
        List<String> initial = List.of("first", "second", "last");
        System.out.printf("Initial list is %s \nPerforming guava reverse...", initial);
        List<String> reverse = Lists.reverse(initial);
        System.out.printf("Reversed list is %s", reverse);

    }
}