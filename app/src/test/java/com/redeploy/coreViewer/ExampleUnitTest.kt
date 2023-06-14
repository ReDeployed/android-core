package com.redeploy.coreViewer

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.redeploy.coreViewer.ui.screens.LoginScreen
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Composable
    fun MyScreen() {
        var urlIn by remember { mutableStateOf(TextFieldValue()) }
        var pskIn by remember { mutableStateOf(TextFieldValue()) }

        LoginScreen(
            urlIn = urlIn.text,
            onUrlChange = { urlIn = urlIn.copy(text = it) },
            pskIn = pskIn.text,
            onPskChange = { pskIn = pskIn.copy(text = it) },
            onLoginSubmit = {}
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun LoginScreen(
        urlIn: String,
        onUrlChange: (String) -> Unit,
        pskIn: String,
        onPskChange: (String) -> Unit,
        onLoginSubmit: () -> Unit
    ) {

        // Perform text input and button click actions
        val apiUrl = "https://example.com/api"
        val psk = "mySecretKey"

        // Set URL and PSK inputs
        composeTestRule.onNodeWithText("API URL").performTextInput(apiUrl)
        composeTestRule.onNodeWithText("PSK").performTextInput(psk)

        // Click the Submit button
        composeTestRule.onNodeWithText("Submit").performClick()

        // Assert the updated input values
        assertEquals(apiUrl, urlIn.value)
        assertEquals(psk, pskIn.value)
    }
}