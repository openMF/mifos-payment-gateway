/**
 * File: BeyonicPaymentState.java
 * =====================================
 * This class defines the different states of a
 * particular beyonic payment transaction
 * @author vladimir fomene
 */

package org.mifos.mifospaymentbridge.PaymentProviders.Beyonic;


public enum BeyonicPaymentState {
    INVALID_ID(0, "invalid"),
    NEW(1, "new"),
    VALIDATED(2, "validated"),
    APPROVAL_NEEDED(3, "approval_needed"),
    APPROVAL_REQUESTED(4, "approval_requested"),
    APPROVED(5, "approved"),
    REJECTED(6, "rejected"),
    SCHEDULED(7, "scheduled"),
    PROCESSED(8, "processed"),
    PROCESSED_WITH_ERRORS(9, "processed_with_errors"),
    CANCELLED(10, "cancelled");

    private final Integer value;
    private final String code;

    private BeyonicPaymentState(final Integer value, final String code) {
        this.value = value;
        this.code = code;
    }

    public static BeyonicPaymentState fromInt(final Integer typeValue) {
        BeyonicPaymentState enumeration = BeyonicPaymentState.INVALID_ID;
        switch (typeValue) {
            case 1:
                enumeration = BeyonicPaymentState.NEW;
                break;
            case 2:
                enumeration = BeyonicPaymentState.VALIDATED;
                break;
            case 3:
                enumeration = BeyonicPaymentState.APPROVAL_NEEDED;
                break;
            case 4:
                enumeration = BeyonicPaymentState.APPROVAL_REQUESTED;
                break;
            case 5:
                enumeration = BeyonicPaymentState.APPROVED;
                break;
            case 6:
                enumeration = BeyonicPaymentState.REJECTED;
                break;
            case 7:
                enumeration = BeyonicPaymentState.SCHEDULED;
                break;
            case 8:
                enumeration = BeyonicPaymentState.PROCESSED;
                break;
            case 9:
                enumeration = BeyonicPaymentState.PROCESSED_WITH_ERRORS;
                break;
            case 10:
                enumeration = BeyonicPaymentState.CANCELLED;
                break;
        }

        return enumeration;
    }

    public static BeyonicPaymentState fromString(final String typeCode) {
        BeyonicPaymentState enumeration = BeyonicPaymentState.INVALID_ID;
        switch (typeCode) {
            case "new":
                enumeration = BeyonicPaymentState.NEW;
                break;
            case "validated":
                enumeration = BeyonicPaymentState.VALIDATED;
                break;
            case "approval_needed":
                enumeration = BeyonicPaymentState.APPROVAL_NEEDED;
                break;
            case "approval_requested":
                enumeration = BeyonicPaymentState.APPROVAL_REQUESTED;
                break;
            case "approved":
                enumeration = BeyonicPaymentState.APPROVED;
                break;
            case "rejected":
                enumeration = BeyonicPaymentState.REJECTED;
                break;
            case "scheduled":
                enumeration = BeyonicPaymentState.SCHEDULED;
                break;
            case "processed":
                enumeration = BeyonicPaymentState.PROCESSED;
                break;
            case "processed_with_errors":
                enumeration = BeyonicPaymentState.PROCESSED_WITH_ERRORS;
                break;
            case "cancelled":
                enumeration = BeyonicPaymentState.CANCELLED;
                break;
        }

        return enumeration;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getCode() {
        return this.code;
    }
}
