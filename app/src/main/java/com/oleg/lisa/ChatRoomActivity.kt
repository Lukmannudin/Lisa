package com.oleg.lisa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oleg.lisa.ui.theme.LisaTheme

class ChatRoomActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LisaTheme {
                Conversation(messages = SampleData.conversationSample)
            }
        }
    }

    data class Message(
        val author: String,
        val message: String
    )

    @Composable
    fun MessageCard(message: Message) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.dump),
                contentDescription = "just   a dump picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            var isExpanded by remember { mutableStateOf(false) }
            val surfaceColor by animateColorAsState(
                targetValue = if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
            )

            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    text = message.author,
                    color = MaterialTheme.colors.secondaryVariant
                )
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                    color = surfaceColor,
                    modifier = Modifier.animateContentSize().padding(1.dp)
                ) {
                    Text(
                        text = message.message,
                        modifier = Modifier.padding(all = 4.dp),
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }

    @Composable
    fun Conversation(messages: List<Message>) {
        LazyColumn {
            items(messages) { message ->
                MessageCard(message = message)
            }
        }
    }

    @Preview
    @Composable
    fun PreviewConversation() {
        LazyColumn {
            items(SampleData.conversationSample) { message ->
                MessageCard(message = message)
            }
        }
    }

//    @Preview
//    @Composable
//    fun PreviewMessageCard() {
//        Row(modifier = Modifier.padding(all = 8.dp)) {
//            Image(
//                painter = painterResource(id = R.drawable.dump),
//                contentDescription = "just   a dump picture",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .size(40.dp)
//                    .clip(CircleShape)
//                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Column {
//                Text(
//                    text = "message.author",
//                    color = MaterialTheme.colors.secondaryVariant
//                )
//                Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
//                    Text(
//                        text = "message.message",
//                        modifier = Modifier.padding(all = 4.dp),
//                        style = MaterialTheme.typography.body2
//                    )
//                }
//            }
//        }
//    }
}