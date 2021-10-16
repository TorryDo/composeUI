package com.torrydo.composeui.itmc

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.core.graphics.toColorInt
import com.torrydo.composeui.R
import com.torrydo.composeui.utils.showShortToast


private const val CARD_BOTTOM_RADIUS_PERCENT = 15

private val SPACE_BETWEEN_ICONS = 5.dp

private const val DONT_HAVE_AN_ACCOUNT = "Don't have an account?"
private const val SIGN_UP_HERE = "Sign up here!"

private fun getMainColor() = Color("#F4A200".toColorInt())

@Preview
@Composable
fun ITMC_Login_screen_1() {

    val str_image = "imageView"
    val str_txt_account = "textFieldAccount"
    val str_txt_password = "textFieldPassword"
    val str_txt_forgot_password = "textForgotPassword"
    val str_btn_login = "buttonLogin"

    Box(
        modifier = Modifier
            .background(color = Color("#443769".toColorInt()))
            .fillMaxSize()
    ) {

        var accountText by remember { mutableStateOf("") }
        var passwordText by remember { mutableStateOf("") }

        val card_constraints = ConstraintSet {

            val imageViewID = createRefFor(str_image)
            val textFieldAccountID = createRefFor(str_txt_account)
            val textFieldPasswordID = createRefFor(str_txt_password)
            val textForgotID = createRefFor(str_txt_forgot_password)
            val buttonLoginID = createRefFor(str_btn_login)

            constrain(imageViewID) {
                top.linkTo(parent.top)
                bottom.linkTo(textFieldAccountID.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

            }
            constrain(textFieldAccountID) {
                top.linkTo(imageViewID.bottom)
                start.linkTo(imageViewID.start, margin = 15.dp)
                end.linkTo(imageViewID.end, margin = 15.dp)
            }

            createVerticalChain(textFieldAccountID, textFieldPasswordID,
                chainStyle = ChainStyle.Packed(0.5f)
            )

            constrain(textFieldPasswordID) {
                bottom.linkTo(textForgotID.top)
                start.linkTo(textFieldAccountID.start)
                end.linkTo(textFieldAccountID.end)
            }
            constrain(textForgotID) {
                top.linkTo(textFieldPasswordID.bottom, margin = 25.dp)
                bottom.linkTo(buttonLoginID.top)
                start.linkTo(textFieldAccountID.start)
                end.linkTo(textFieldAccountID.end)

                width = Dimension.fillToConstraints

            }
            constrain(buttonLoginID) {
                top.linkTo(textForgotID.bottom, margin = 25.dp)
                start.linkTo(textFieldAccountID.start)
                end.linkTo(textFieldAccountID.end)

                width = Dimension.fillToConstraints
                height = Dimension.value(50.dp)
            }


        }

        val context = LocalContext.current

        Column() {
            // BLOCK 1
            Card(
                shape = RoundedCornerShape(
                    bottomEndPercent = CARD_BOTTOM_RADIUS_PERCENT,
                    bottomStartPercent = CARD_BOTTOM_RADIUS_PERCENT
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                ConstraintLayout(
                    constraintSet = card_constraints,
                    modifier = Modifier.fillMaxSize()
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.ic_vacuum),
                        contentDescription = "vacuum",
                        modifier = Modifier
                            .layoutId(str_image)
                            .fillMaxWidth()
                            .height(200.dp)
                    )

                    OutlinedTextField(
                        value = accountText,
                        onValueChange = { accountText = it },
                        label = { Text(text = "Email") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_email),
                                contentDescription = "email icon"
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier
                            .layoutId(str_txt_account)
                            .wrapContentHeight()
                    )
                    OutlinedTextField(
                        value = passwordText,
                        onValueChange = { passwordText = it },
                        label = { Text(text = "Password") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_lock),
                                contentDescription = "email lock"
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier
                            .layoutId(str_txt_password)
                            .wrapContentHeight()
                    )
                    Text(
                        text = "Forgot password?",
                        color = Color.Gray,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .layoutId(str_txt_forgot_password)
                            .wrapContentHeight()
                    )
                    Button(
                        modifier = Modifier
                            .layoutId(str_btn_login),
                        colors = ButtonDefaults.buttonColors(getMainColor()),
                        onClick = {
                            buttonClick(context)
                        },
                    ) {
                        Text(text = "Log in", color = Color.White)
                    }
                }

            }

            // BLOCK 2

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
                    .padding(bottom = 15.dp)
                    ,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_fb),
                    contentDescription = "fb"
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "gg"
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_twitter),
                    contentDescription = "tw"
                )
            }

            // BLOCK 3
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = DONT_HAVE_AN_ACCOUNT, color = Color.White)
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = SIGN_UP_HERE, color = Color("#D98C00".toColorInt()))
            }

        }

    }
}

private fun buttonClick(context: Context) {
    context.showShortToast("Hello Compose!")
}