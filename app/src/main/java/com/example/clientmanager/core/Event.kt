package com.example.clientmanager.core

import androidx.compose.runtime.Immutable

@Immutable

//The Event class is generic, meaning it can be used to wrap any type of value (T).
// The type T is constrained to Any, ensuring that the event value cannot be null.
data class Event<T: Any>(

    //This holds the actual event value (e.g., a message, navigation command, or other data).
    //It is private because you don't want external code to modify it directly.
    private val value: T
) {

    //This boolean flag tracks whether the event has already been consumed or handled.
    // It ensures that the event is only processed once.
    private var handled = false


    //This function allows you to access the event's value without marking it as handled.
    //This is useful if you need to inspect the value but don’t want to consume the event.
    fun peek(): T = value

    //This function returns the event value only if it has not already been handled.
    // If the event has already been consumed (i.e., handled == true), it returns null.
    //If the value is being accessed for the first time, it marks the event as handled (handled = true),
    // ensuring that subsequent calls will return null.
    fun get(): T? = if (handled) null else value.also { handled = true }

}