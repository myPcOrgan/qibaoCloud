package com.qibao.backend.common;


import com.qibao.backend.model.UserVO;

public final class UserContext {
    private static ThreadLocal<UserContext> context = new ThreadLocal<UserContext>() {
        @Override
        protected UserContext initialValue() {
            return new UserContext();
        }
    };
    private UserVO user;
    private String authKey;

    public UserVO getUser() {
        return this.user;
    }

    public String getAuthKey() {
        return this.authKey;
    }

    private UserContext() {
    }

    public static UserContext getCurrentContext() {
        return context.get();
    }

    public static UserVO getCurrentUser() {
        return getCurrentContext().getUser();
    }

    public static Long getCurrentUserId() {
        UserVO user = getCurrentContext().getUser();
        return user != null ? user.getId() : null;
    }

    public static String getCurrentUserLoginAccount() {
        UserVO user = getCurrentContext().getUser();
        return user != null ? user.getLoginAccount() : null;
    }

    public static String getCurrentAuthKey() {
        return getCurrentContext().getAuthKey();
    }

    public static void setCurrentUser(UserVO user) {
        setCurrentContext(user, null);
    }

    public static void setCurrentContext(UserVO user, String authKey) {
        UserContext userContext = getCurrentContext();
        userContext.user = user;
        userContext.authKey = authKey;
    }

    public static void clean() {
        context.remove();
    }
}

