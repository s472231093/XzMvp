package com.example.myapplication.model

open class BaseModel {
    protected var error: Boolean = false


    fun isError(): Boolean {
        return error
    }

    open fun isNull(): Boolean {
        return false
    }

    fun isAuthError(): Boolean {
        return false
    }

    fun isBizError(): Boolean {
        return error
    }

    fun getErrorMsg(): String? {
        return null
    }
}