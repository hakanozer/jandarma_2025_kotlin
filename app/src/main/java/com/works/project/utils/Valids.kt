package com.works.project.utils

class Valids {

    fun emailValids(email: String) : Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        if (email.matches(emailRegex.toRegex())) {
            return true
        } else {
            return false
        }
    }

    fun passwordValids(password: String) : Boolean {
        val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
        return password.matches(passwordRegex.toRegex())
    }

}