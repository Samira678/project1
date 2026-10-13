package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainScreen()
            }
        }
    }
}
data class FAQItem(
    val id: Int,
    val question: String,
    val answer: String,
    val isExpanded: MutableState<Boolean> = mutableStateOf(false)

)
@Composable
fun MainScreen() {
    val faqList = remember {
        mutableStateListOf(
            FAQItem(
                id = 1,
                question = "Что такое Jetpack Compose?",
                answer = "Jetpack Compose — это современный toolkit для создания UI на Android с помощью Kotlin"
            ),
            FAQItem(
                id = 2,
                question = "Как работает LazyColumn?",
                answer = "LazyColumn создаёт элементы списка только тогда, когда они появляются на экране, что эффективно для больших списков."
            ),
            FAQItem(
                id = 3,
                question = "Что такое состояние (State) в Compose?",
                answer = "Состояние — это любые данные, которые могут меняться со временем и должны вызывать перерисовку UI."
            )
        )
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(
            items = faqList,
            key = { it.id }
        ) { item ->
            ExpandableCard (
                faqItem = item,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Composable
fun ExpandableCard(
    faqItem: FAQItem,
    modifier: Modifier
) {
    val rotationState by animateFloatAsState(
        targetValue = if(faqItem.isExpanded.value) 180f else 0f,
        label = "arrowRotation"
    )
    Card(
        modifier=modifier
            .fillMaxWidth()
            .clickable{faqItem.isExpanded.value = !faqItem.isExpanded.value},
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){
        Column(
            modifier = Modifier.padding(16.dp)

        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = faqItem.question,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Развернуть",
                    modifier = Modifier.rotate(rotationState)
                )
            }
            AnimatedVisibility(visible = faqItem.isExpanded.value) {
                Column{
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = faqItem.answer,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}




