package com.example.chatpage

import SampleData
import android.content.res.Configuration

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp

import com.example.chatpage.ui.theme.ChatPageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatPageTheme(darkTheme = true) {
                Conversation(messages = SampleData.conversationSample)
            }
        }
    }
}


@Composable
fun MessageCard(msg: Message){
    var isExpanded by remember {
        mutableStateOf(false)
    }
    val surfaceColor by animateColorAsState(
        if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
    )
    Row (modifier = Modifier.padding(all = 8.dp))
    {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(
                    1.5.dp, MaterialTheme.colorScheme.primary,
                    CircleShape
                ),
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "This is my photo"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column (Modifier.clickable { isExpanded = !isExpanded }){
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp, color = surfaceColor) {
                Text(modifier = Modifier.padding(all = 4.dp),
                    text = msg.body,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
            }
        }
    }
}
@Composable
fun Conversation(messages : List<Message>){
    LazyColumn (modifier = Modifier.background(MaterialTheme.colorScheme.background)){
        items(messages){message ->
            MessageCard(msg = message)
        }
    }
}

//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES,showBackground = true, name = "night Mode")
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChatPageTheme(darkTheme = true) {
        Conversation(messages = SampleData.conversationSample)
    }
}