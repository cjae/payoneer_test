package com.expanse.app.payoneer.model;

import static com.expanse.app.payoneer.utils.Status.ERROR;
import static com.expanse.app.payoneer.utils.Status.LOADING;
import static com.expanse.app.payoneer.utils.Status.SUCCESS;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.expanse.app.payoneer.utils.Status;

/**
 * Response holder provided to the UI
 */
public class Response {

    public final Status status;

    @Nullable
    public final Object data;

    @Nullable
    public final Throwable error;

    private Response(Status status, @Nullable Object data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static Response loading() {
        return new Response(LOADING, null, null);
    }

    public static Response success(@NonNull Object data) {
        return new Response(SUCCESS, data, null);
    }

    public static Response error(@NonNull Throwable error) {
        return new Response(ERROR, null, error);
    }
}