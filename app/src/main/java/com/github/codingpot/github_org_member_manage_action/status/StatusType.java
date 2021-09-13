package com.github.codingpot.github_org_member_manage_action.status;

/**
 * StatusType is meant to be used with {@link Status} or {@link StatusOr}, representing its status
 * type.
 */
public enum StatusType {
    OK,
    ERROR,
    ;

    public static StatusType merge(StatusType type, StatusType type1) {
        if (type == OK && type1 == OK) {
            return OK;
        }
        return ERROR;
    }
}
