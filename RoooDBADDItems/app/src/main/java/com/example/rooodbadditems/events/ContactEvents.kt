package com.example.rooodbadditems.events

import com.example.rooodbadditems.data.Contact

sealed interface ContactEvents {
    object saveContact : ContactEvents
    data class setFirstName(val firstname : String) : ContactEvents
    data class setLastName(val lastname : String) : ContactEvents
    data class setPhoneNumber(val phonenumber : String) : ContactEvents
    object showDialog : ContactEvents
    object hideDialog : ContactEvents
    data class sortContacts(val sortype : sortType) : ContactEvents
    data class deleteContact(val contact: Contact) :ContactEvents
}