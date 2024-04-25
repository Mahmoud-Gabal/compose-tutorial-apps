package com.example.rooodbadditems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rooodbadditems.events.ContactEvents
import com.example.rooodbadditems.events.ContactStates

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContatcDialog(
    onEvent : (ContactEvents) -> Unit,
    state : ContactStates,
    modifier: Modifier = Modifier
) {
    AlertDialog(modifier = modifier,
        onDismissRequest = { onEvent(ContactEvents.hideDialog) },
        title = { Text(text = "Add Contact")},
        confirmButton = {
             Box (contentAlignment = Alignment.CenterEnd,
                 modifier = Modifier.fillMaxWidth()){
                Button(onClick = { onEvent(ContactEvents.saveContact) }) {
                            Text(text = "Save")
                }
            }
        }
        ,text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TextField(
                    value = state.firstname,
                    onValueChange ={onEvent(ContactEvents.setFirstName(it))},
                    placeholder = { Text(text = "first name")}
                )
                TextField(
                    value = state.lastname,
                    onValueChange ={onEvent(ContactEvents.setLastName(it))},
                    placeholder = { Text(text = "last name")}
                )
                TextField(
                    value = state.phonenumber,
                    onValueChange ={onEvent(ContactEvents.setPhoneNumber(it))},
                    placeholder = { Text(text = "phone number")}
                )
            }
        }
    )

}

