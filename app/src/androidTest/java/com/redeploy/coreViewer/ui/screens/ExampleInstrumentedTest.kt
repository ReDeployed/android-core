package com.redeploy.coreViewer.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.redeploy.coreViewer.network.LoginRequest

import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoginScreen_submitButtonClick() {
        // Create a mock onLoginSubmit function
        var isLoginSubmitted = false
        var loginRequest: LoginRequest? = null
        val onLoginSubmit: (req: LoginRequest) -> Unit = { req ->
            isLoginSubmitted = true
            loginRequest = req
        }

        composeTestRule.setContent {
            LoginScreen(onLoginSubmit = onLoginSubmit)
        }

        // Perform text input and button click actions
        val apiUrl = "https://example.com/api"
        val apikey = "mySecretKey"

        // Set URL and PSK inputs
        composeTestRule.onNodeWithText("API URL").performTextInput(apiUrl)
        composeTestRule.onNodeWithText("PSK").performTextInput(apikey)

        // Click the Submit button
        composeTestRule.onNodeWithText("Submit").performClick()

        // Assert login submit behavior
        assertTrue(isLoginSubmitted)

        // Assert the expected LoginRequest
        //assertNotNull(loginRequest)
        //assertEquals(apiUrl, loginRequest?.url)
        //assertEquals(apikey, loginRequest?.key)

        // Assert input is not empty
        //assertFalse(apiUrl.isEmpty())
        //assertFalse(apikey.isEmpty())
    }
}