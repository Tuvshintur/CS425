package com.smt.example.constant;

public interface Constants {

    interface Status {

        String SUCCESS = "success";

        String FAILED = "failed";

    }

    interface Desc {

        String USERNAME_PASSWORD_INVALID = "Invalid username or password";

        String TOKEN_EXPIRED = "Expired or invalid JWT token";

        String USER_NOT_FOUND = "User not found";

        String ALREADY_REGISTERED = "This record already registered";

    }

    interface Roles {

        String CLIENT = "ROLE_CLIENT";

        String ADMIN = "ROLE_ADMIN";

    }

    interface ErrorDesc {

        String CONTACT_ADMIN = "Please contact system administration";

    }

    interface ErrorType {

        String RMI = "rmi";

        String RESTRICTION = "restriction";

        String NOT_FOUND = "not found";

        String VALIDATION = "validation";

        String UNKNOWN = "unknown";

    }

}
