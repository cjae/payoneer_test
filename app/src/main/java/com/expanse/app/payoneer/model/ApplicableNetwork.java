package com.expanse.app.payoneer.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is designed to hold information about applicable payment network.
 */
@Getter
@Setter
public class ApplicableNetwork {
    /** Simple API, always present */
    private String code;
    /** Simple API, always present */
    private String label;
    /** Simple API, always present */
    @PaymentMethod.Definition
    private String method;
    /** Simple API, always present */
    private String grouping;
    /** Simple API, always present */
    @NetworkOperationType.Definition
    private String operationType;
    /** Simple API, always present */
    @RegistrationType.Definition
    private String registration;
    /** Simple API, always present */
    @RegistrationType.Definition
    private String recurrence;
    /** Simple API, always present */
    private Boolean redirect;
    /** Simple API, always present */
    private Map<String, URL> links;
    /** flag that network is initially selected */
    private Boolean selected;
    /** Form elements descriptions */
    private List<InputElement> inputElements;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicableNetwork that = (ApplicableNetwork) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(label, that.label) &&
                Objects.equals(method, that.method) &&
                Objects.equals(grouping, that.grouping) &&
                Objects.equals(operationType, that.operationType) &&
                Objects.equals(registration, that.registration) &&
                Objects.equals(recurrence, that.recurrence) &&
                Objects.equals(redirect, that.redirect) &&
                Objects.equals(links, that.links) &&
                Objects.equals(selected, that.selected) &&
                Objects.equals(inputElements, that.inputElements);
    }
}