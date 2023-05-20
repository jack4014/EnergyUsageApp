package com.example.energyusageapp;

public interface Action<T> {
    public void invoke(T value);
}
