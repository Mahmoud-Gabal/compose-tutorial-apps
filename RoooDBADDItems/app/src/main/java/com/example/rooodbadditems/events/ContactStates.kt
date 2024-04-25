package com.example.rooodbadditems.events

import com.example.rooodbadditems.data.Contact

data class ContactStates(
    val firstname : String = "",
    val lastname : String = "",
    val phonenumber : String = "",
    val contact : List<Contact> = emptyList(),
    val sorttype : sortType = sortType.FIRST_NAME,
    val isAddingContact : Boolean = false,
)
