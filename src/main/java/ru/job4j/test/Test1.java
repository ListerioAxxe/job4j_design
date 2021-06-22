//package ru.job4j.test;
//
//import ru.job4j.analize.Info;
//import ru.job4j.analize.User;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
//public class Analize {
//    public static Info diff(Set<User> previous, Set<User> current) {
//        Info info = new Info(0, 0, 0);
//        int rsl = 0;
//        int rsl1 = 0;
//        Map<Integer, User> prevUs = new HashMap<>();
//
//        for (var prev : previous) {
//            prevUs.put(prev.getId(), prev);
//        }
//
//        for (var user : current) {
//            var currUser = prevUs.remove(user.getId());
//
//            if (currUser == null) {
//                info.setAdded(++rsl);
//
//            } else if (!(currUser.getName().equals(user.getName()))) {
//                info.setChanged(++rsl1);
//            }
//        }
//
//        info.setDeleted(prevUs.size());
//
//        return info;
//    }
//}
