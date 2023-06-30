package com.example.composerediexpress

class User {
    var name: String = ""
    var phoneNumber: String = ""
    var email: String = ""
    var password: String = ""

    constructor()

    constructor(name: String, phoneNumber: String, email: String, password: String) {
        this.name = name
        this.phoneNumber = phoneNumber
        this.email = email
        this.password = password
    }
}