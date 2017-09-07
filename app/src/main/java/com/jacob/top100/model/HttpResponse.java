package com.jacob.top100.model;

/**
 * Created by Jacob Ho
 */

public interface HttpResponse<T> {
    void onSuccess(T data);

    void onFailure(Exception e);
}
