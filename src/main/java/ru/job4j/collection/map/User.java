//package ru.job4j.collection.map;
//
//import java.util.*;
//
//import static java.util.Objects.hash;
//
//public class User {
//    private String name;
//    private int children;
//    private Calendar birthday;
//
//    public User(String name, int children) {
//        this.name = name;
//        this.children = children;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return children == user.children
//        && Objects.equals(name, user.name)
//        && Objects.equals(birthday, user.birthday);
//    }
//
//    @Override
//    public int hashCode() {
//        return hash(name, children);
//    }
//
//    public static void main(String[] args) {
//        Calendar c = new GregorianCalendar();
//        User first = new User("Ivan", 2);
//        User second = new User("Ivan", 2);
//        map.put(first, new Object());
//        map.put(second, new Object());
//        int hCode = first.hashCode();
//        int hCode1 = second.hashCode();
//        int hash1 = hash(hCode);
//        int hash2 = hash(hCode1);
//        System.out.println(hash1);
//        System.out.println(hash2);
//        System.out.println(map);
//    }
//}
