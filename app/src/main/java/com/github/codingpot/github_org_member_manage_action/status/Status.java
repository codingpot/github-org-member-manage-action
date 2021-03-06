package com.github.codingpot.github_org_member_manage_action.status;

import java.util.Optional;
import lombok.Value;

/** Status is a simple data class holding Status and errorMessage. */
@Value
public class Status {
    /** Contains the type of the Status. */
    StatusType type;
    /** This should be only populated when {@link #type} is not {@link StatusType#OK}. */
    Optional<String> errorMessage;

    public static Status ok() {
        return new Status(StatusType.OK, Optional.empty());
    }

    public static Status error(String errorMessage) {
        return new Status(StatusType.ERROR, Optional.ofNullable(errorMessage));
    }

    public boolean hasError() {
        return getType() != StatusType.OK;
    }

    public Status merge(Status status) {
        String m1 = errorMessage.orElse("");
        String m2 = status.getErrorMessage().orElse("");
        return new Status(
                StatusType.merge(getType(), status.getType()), Optional.of(m1 + " and " + m2));
    }
}
