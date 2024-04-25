package com.example.rooodbadditems

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rooodbadditems.data.Contact
import com.example.rooodbadditems.events.ContactEvents
import com.example.rooodbadditems.events.ContactStates
import com.example.rooodbadditems.events.sortType

@Composable
fun ContactScreen(
    onEvent : (ContactEvents) -> Unit,
    state : ContactStates
) {
    if (state.isAddingContact){
        ContatcDialog(onEvent = onEvent,state = state)
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(ContactEvents.showDialog) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },

    ) {padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    sortType.values().forEach { sortType ->
                        Row (
                            modifier = Modifier.clickable {
                                onEvent(ContactEvents.sortContacts(sortType))
                            },
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            RadioButton(
                                selected = state.sorttype == sortType,
                                onClick = { onEvent(ContactEvents.sortContacts(sortType)) }
                            )
                            Text(text = sortType.name)
                        }
                    }
                }
            }
            items(state.contact){contact ->
                Row (modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)){
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "${contact.firstName} ${contact.lastName}",
                            fontSize = 20.sp
                        )
                        Text(
                            text = contact.phoneNumber,
                            fontSize = 12.sp
                        )
                    }
                    IconButton(onClick = { onEvent(ContactEvents.deleteContact(contact)) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "delete contact"
                        )
                    }
                }
            }
        }
    }
}