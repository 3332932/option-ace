package com.cn.dynamic.utils;


import com.cn.entity.User;

public class ThreadLocals {

    public static final ThreadLocal<User> currentUserThl = new ThreadLocal();


    public ThreadLocals() {
    }


    public static User getCurrentUser() {
        if (currentUserThl.get() == null) {
            User user = new User();
            user.setUserId(0);
            user.setUserName("visitor");
            user.setNickName("visitor");
            currentUserThl.set(user);
        }

        return (User)currentUserThl.get();
    }

    public static void setCurrentUser(User currentUser) {
        currentUserThl.set(currentUser);
    }

}
