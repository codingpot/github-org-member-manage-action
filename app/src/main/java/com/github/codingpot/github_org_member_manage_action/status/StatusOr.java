package com.github.codingpot.github_org_member_manage_action.status;

import lombok.Value;

/**
 * StatusOr is a wrapper around Status but can hold data.
 *
 * @param <T> the type of data
 */
@Value
public class StatusOr<T> {
    Status status;
    T data;

    public static <T> StatusOr<T> createOk(T data) {
        return new StatusOr<>(Status.ok(), data);
    }

    public static <T> StatusOr<T> createError(String message) {
        return new StatusOr<>(Status.error(message), null);
    }

    public boolean hasError() {
        return getStatus().hasError();
    }

    public Exception toException() {
        return new RuntimeException(String.valueOf(status.getErrorMessage()));
    }
}
