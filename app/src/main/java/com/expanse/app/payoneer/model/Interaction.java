package com.expanse.app.payoneer.model;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is designed to hold interaction information providing a recommendation on how to proceed.
 */
@Getter
@Setter
public class Interaction {
    /** Simple API, always present */
    @InteractionCode.Definition
    private String code;
    /** Simple API, always present */
    @InteractionReason.Definition
    private String reason;

    /**
     * Construct an empty Interaction Object
     */
    public Interaction() {
    }

    /**
     * Construct a new Interaction Object with the predefined code and reason
     *
     * @param code the code to set.
     * @param reason the reason to set.
     */
    public Interaction(@InteractionCode.Definition String code, @InteractionReason.Definition String reason) {
        this.code = code;
        this.reason = reason;
    }
}