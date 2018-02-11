package com.qibao.frontend.api.user.utils;

import com.qibao.user.context.vo.UserAccountVO;

public final class UserContext {
    private static ThreadLocal<UserContext> context = new ThreadLocal<UserContext>() {
        protected UserContext initialValue() {
            return new UserContext();
        }
    };
    private UserAccountVO user;
    private String authKey;

    public UserAccountVO getUser() {
        return this.user;
    }

    public String getAuthKey() {
        return this.authKey;
    }

    private UserContext() {
    }

    public static UserContext getCurrentContext() {
        return (UserContext)context.get();
    }

//    public static UserAccountVO getCurrentUser() {
//        return getCurrentContext().getUser();
//    }

    public static Long getCurrentUserId() {
        UserAccountVO user = getCurrentContext().getUser();
        return user != null ? user.getId() : null;
    }

//    public static String getCurrentUserLoginAccount() {
//        UserAccountVO user = getCurrentContext().getUser();
//        return user != null ? user.getUserAccount() : null;
//    }

    public static String getCurrentAuthKey() {
        return getCurrentContext().getAuthKey();
    }

    public static void setCurrentUser(UserAccountVO user) {
        setCurrentContext(user, (String)null);
    }

    public static void setCurrentContext(UserAccountVO user, String authKey) {
        UserContext userContext = getCurrentContext();
        userContext.user = user;
        userContext.authKey = authKey;
    }

    public static void clean() {
        context.remove();
    }
}

