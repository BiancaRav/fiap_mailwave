package br.com.mailwave.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import br.com.mailwave.components.ChooseOptions
import br.com.mailwave.components.EmailSingle
import br.com.mailwave.components.Header
import br.com.mailwave.repository.EmailRepository
import br.com.mailwave.screens._home.FoldersPanel
import br.com.mailwave.screens._home.SettingsPanel
import br.com.mailwave.ui.theme.MailWaveTheme

@Composable
fun HomeScreen(navController: NavController){

    var menuDockIsVisible by remember { mutableStateOf(false) }
    var settingsDockIsVisible by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()){
        Column {

            Header(
                settingsClick = { settingsDockIsVisible = true },
                searchClick = { },
                menuClick = { menuDockIsVisible = true }
            )

            ChooseOptions(choseOption = "all")

            LazyColumn(modifier = Modifier.fillMaxWidth()) {

                items(EmailRepository.getAllEmails()){

                    EmailSingle(imageId = it.senderImage, emailSender = it.sender, emailBody = it.body, read = it.read)

                }

            }

        }


    // Body
//        Column(modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight()
//            .background(Color.Red)
//        ) {
//
//            Button(shape = RectangleShape,
//                onClick = {
//                    navController.navigate("message/create")
//                }
//            ) {
//                Text(text = "Create Message")
//            }
//
//            Button(shape = RectangleShape,
//                onClick = {
//                    navController.navigate("message/read")
//                }
//            ) {
//                Text(text = "Read Message")
//            }
//        }




    }
    FoldersPanel(menuDockIsVisible, navController) { menuDockIsVisible = false;}
    SettingsPanel(settingsDockIsVisible, navController) { settingsDockIsVisible = false;}
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview(){
    MailWaveTheme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(NavController(context = LocalContext.current))
        }
    }
}