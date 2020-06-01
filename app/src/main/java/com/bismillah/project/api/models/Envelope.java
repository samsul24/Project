package com.bismillah.project.api.models;

public class Envelope<T> {
    T data;

    public Envelope(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
