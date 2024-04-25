package com.example.rooodbadditems.Models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rooodbadditems.data.Contact
import com.example.rooodbadditems.data.ContactDao
import com.example.rooodbadditems.events.ContactEvents
import com.example.rooodbadditems.events.ContactStates
import com.example.rooodbadditems.events.sortType
import com.example.rooodbadditems.events.sortType.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class ContactViewModel(
    private val Dao : ContactDao
) : ViewModel() {

    private val _sortState = MutableStateFlow(FIRST_NAME)
    private val _contacts = _sortState.flatMapLatest {  sortType ->
        when(sortType){
            FIRST_NAME -> Dao.getContactsOrderedByFirstName()
            LAST_NAME -> Dao.getContactsOrderedByLastName()
            PHONE_NUMBER -> Dao.getContactsOrderedByPhoneNumber()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(ContactStates())
    val state = combine(_state,_sortState,_contacts){state,sortType,contacts ->
        state.copy(
            contact = contacts,
            sorttype = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactStates())

    fun onEvent(event: ContactEvents){
        when(event){
            is ContactEvents.deleteContact -> {
                viewModelScope.launch{
                    Dao.deleteContact(event.contact)
                }
            }
            ContactEvents.hideDialog -> {
                _state.update { it.copy(
                    isAddingContact = false
                ) }
            }
            ContactEvents.saveContact -> {
                val firstName = state.value.firstname
                val lastName = state.value.lastname
                val phoneNumber = state.value.phonenumber

                if (firstName.isBlank() || lastName.isBlank() || phoneNumber.isBlank()){
                    return
                }
                val contact = Contact(
                    firstName = firstName,
                    lastName = lastName,
                    phoneNumber = phoneNumber
                )
                viewModelScope.launch {
                    Dao.upsertContact(contact)
                }
                _state.update { it.copy(
                    isAddingContact = false,
                    firstname = "" ,
                    lastname = "",
                    phonenumber = ""
                ) }
            }
            is ContactEvents.setFirstName -> {
                _state.update { it.copy(
                    firstname = event.firstname
                ) }
            }
            is ContactEvents.setLastName ->{
                _state.update { it.copy(
                    lastname = event.lastname
                ) }
            }
            is ContactEvents.setPhoneNumber -> {
                _state.update { it.copy(
                    phonenumber = event.phonenumber
                ) }
            }
            ContactEvents.showDialog -> {
                _state.update { it.copy(
                    isAddingContact = true
                ) }
            }
            is ContactEvents.sortContacts -> {
                _sortState.value = event.sortype
            }
        }
    }
}