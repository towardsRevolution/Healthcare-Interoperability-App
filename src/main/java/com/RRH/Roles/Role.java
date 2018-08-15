package com.RRH.Roles;

import com.RRH.EntityRO.User;

public final class Role {

    public static User GET_INSTANCE(String profile_type, User user) {
        if(profile_type.equals("ROLE_ADMIN"))
            return new AdminRole(user.getRegistration_date(),user.getName(),user.getEmailID(),user.getRole());
        else
            return new UserRole(user.getRegistration_date(),user.getName(),user.getEmailID(),user.getRole());
    }
}
