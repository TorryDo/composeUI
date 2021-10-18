package com.torrydo.composeui.sample

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.torrydo.composeui.R
import com.torrydo.composeui.ui.theme.Blue200
import com.torrydo.composeui.ui.theme.Padding_big
import com.torrydo.composeui.utils.showShortToast


private val ICON_BUTTON_SIZE = 48.dp
private val IMAGE_ITEM_SIZE = 250.dp
private val CARD_BORDER_RADIUS = 25.dp
private val CARD_BORDER_RADIUS_BIGGER = 35.dp
private val CARD_BORDER_RADIUS_PERCENT = 10

private val ELEVATION_BIG = 15.dp
private val ELEVATION_BIGGER = 25.dp

private val CHIP_SIZE = 40.dp


private val TOP_BAR_HEIGHT = 60.dp
private val BORDER_SIZE = 4.dp
private val BORDER_COLOR = Color.White

private val mauTraSua = Color(0xFFF9F3E7)

private val CHOSE_COLOR = Color(0xFFE0AB5B)

private val FONT_BIG = 25.sp

private val FAKE_OPTIONS = listOf(
    "Cà chua",
    "Phô mai",
    "Hun khói",
    "Chua ngọt",
    "Valentine",
    "Cà chua",
    "Phô mai",
    "Hun khói",
    "Chua ngọt",
    "Valentine",
)

@ExperimentalMaterialApi
@Preview
@Composable
fun Merchandise_screen_1() {
    val context = LocalContext.current

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        Color.White,
                        Color.White,
                        Color.White,
                        Blue200
                    )
                )
            )
    ) {

        val topBarID = createRef()
        val cardItemID = createRef()
        val descriptionID = createRef()
        val amountID = createRef()
        val optionsID = createRef()
        val buttonPurchaseID = createRef()

        val H_GuideLine_50 = createGuidelineFromTop(0.5f)
        val H_GuideLine_bottom = createGuidelineFromTop(0.88f)

        // topbar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .constrainAs(topBarID) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, 10.dp)
                    end.linkTo(parent.end, 10.dp)

                    width = Dimension.fillToConstraints
                    height = Dimension.value(TOP_BAR_HEIGHT)
                }
        ) {
            IconButton(
                onClick = { OnClick(context) },
                modifier = Modifier
                    .width(ICON_BUTTON_SIZE)
                    .height(ICON_BUTTON_SIZE)
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_left_arrow),
                    contentDescription = "back icon",
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }

        // cardview

        CardItemComponent(modifier = Modifier
            .constrainAs(cardItemID) {
                start.linkTo(parent.start, margin = 30.dp)
                end.linkTo(parent.end, margin = 30.dp)
                top.linkTo(topBarID.bottom, margin = 10.dp)
                bottom.linkTo(H_GuideLine_50)

                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            })

        // description
        Column(modifier = Modifier
            .constrainAs(descriptionID) {
                start.linkTo(cardItemID.start, 10.dp)
                end.linkTo(cardItemID.end, 10.dp)
                top.linkTo(H_GuideLine_50, 30.dp)
                bottom.linkTo(amountID.top)

                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints

            }) {
            Text(
                text = "Title",
                fontWeight = FontWeight.Bold,
                fontSize = FONT_BIG,
                color = Color.Black
            )
            Text(text = "looreaskf dsfajsd vasdjfdshf cds ksdjhfajkhc jasdhf", color = Color.Gray)
        }

        // amount
        AmountComponent(modifier = Modifier
            .constrainAs(amountID) {
                start.linkTo(descriptionID.start)
                end.linkTo(descriptionID.end)
                bottom.linkTo(optionsID.top, 20.dp)

                width = Dimension.fillToConstraints
                height = Dimension.value(90.dp)
            }
        )


        // options
        OptionsComponent(modifier = Modifier.constrainAs(optionsID) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(H_GuideLine_bottom)

            width = Dimension.fillToConstraints
            height = Dimension.value(80.dp)
        })

        //
        Button(
            onClick = { OnClick(context) },
            shape = RoundedCornerShape(50),
            border = BorderStroke(BORDER_SIZE, Color.White),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
            elevation = ButtonDefaults.elevation(ELEVATION_BIG),
            modifier = Modifier
                .constrainAs(buttonPurchaseID) {
                    start.linkTo(parent.start, margin = 30.dp)
                    end.linkTo(parent.end, margin = 30.dp)
                    bottom.linkTo(parent.bottom, 10.dp)
                    top.linkTo(H_GuideLine_bottom, 10.dp)

                    width = Dimension.fillToConstraints
                    height = Dimension.value(70.dp)

                }
        ) {
            Text(text = "clickme", color = Color.White)
        }

    }
}

@Composable
fun AmountComponent(modifier: Modifier) {

    var amount by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
    ) {
        Text(
            text = "Amount",
            textAlign = TextAlign.Start,
            fontSize = FONT_BIG,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Card(
            shape = RoundedCornerShape(CARD_BORDER_RADIUS),
            elevation = ELEVATION_BIG,
            modifier = Modifier
                .fillMaxHeight()
                .width(200.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Button(
                    onClick = { // click minus
                        amount--
                    },
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    shape = RoundedCornerShape(
                        topStart = CARD_BORDER_RADIUS,
                        bottomStart = CARD_BORDER_RADIUS,
                        topEnd = 5.dp,
                        bottomEnd = 5.dp
                    ),
                    modifier = Modifier
                        .width(60.dp)
                        .fillMaxHeight()
                ) {
                    Text(text = "-", color = Color.White)
                }
                Spacer(modifier = Modifier.width(3.dp))
                Button(
                    onClick = { // click add
                        amount++
                    },
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    shape = RoundedCornerShape(
                        topEnd = CARD_BORDER_RADIUS,
                        bottomEnd = CARD_BORDER_RADIUS,
                        topStart = 5.dp,
                        bottomStart = 5.dp
                    ), modifier = Modifier
                        .width(60.dp)
                        .fillMaxHeight()
                ) {
                    Text(text = "+", color = Color.White)
                }
                Text(
                    text = amount.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = FONT_BIG,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp)
                )

            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun OptionsComponent(modifier: Modifier) {

    var whichOneWillUserChoose by remember { mutableStateOf(0) }

    Column(modifier = modifier) {
        Text(
            text = "Options",
            textAlign = TextAlign.Start,
            fontSize = FONT_BIG,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 40.dp)
        )

        LazyRow(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(items = FAKE_OPTIONS) { index, option ->
                Card(
                    onClick = { whichOneWillUserChoose = index },

                    backgroundColor = if (index == whichOneWillUserChoose)
                        CHOSE_COLOR else Color.White,
                    shape = RoundedCornerShape(CARD_BORDER_RADIUS),
                    elevation = ELEVATION_BIG,
                    modifier = Modifier
                        .fillMaxHeight()
                        .border(
                            BORDER_SIZE,
                            BORDER_COLOR,
                            shape = RoundedCornerShape(CARD_BORDER_RADIUS)
                        )
                ) {
                    Text(
                        text = option,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = if (index == whichOneWillUserChoose)
                            Color.Black else Color.Gray,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }

    }
}

@Composable
fun CardItemComponent(modifier: Modifier) {

    Card(
        shape = RoundedCornerShape(CARD_BORDER_RADIUS),
        elevation = ELEVATION_BIG,
        modifier = modifier
//            .border(4.dp, Color.Blue, RoundedCornerShape(CARD_BORDER_RADIUS))
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {

            val typeID = createRef()
            val imageID = createRef()
            val titleID = createRef()
            val contentID = createRef()
            val bottomCardID = createRef()

            val GL_35 = createGuidelineFromTop(0.4f)

            Box(modifier = Modifier
                .width(100.dp)
                .height(40.dp)
                .zIndex(1f)
                .background(Color.Blue)
                .constrainAs(typeID) {
                    top.linkTo(parent.top, Padding_big)
                    end.linkTo(parent.end, Padding_big)
                }
            )

            Image(
                painter = painterResource(id = R.drawable.pizza_1024x1024),
                contentDescription = "goods",
                modifier = Modifier
                    .zIndex(0.8f)
//                    .shadow(elevation = ELEVATION_BIGGER)
                    .constrainAs(imageID) {
                        start.linkTo(parent.start, margin = 40.dp)
                        end.linkTo(parent.end, margin = 40.dp)
                        top.linkTo(GL_35)
                        bottom.linkTo(GL_35)

                        width = Dimension.value(IMAGE_ITEM_SIZE)
                        height = Dimension.value(IMAGE_ITEM_SIZE)
                    }

            )

            Text(
                text = "Delicious Pizza",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .zIndex(0.5f)
                    .constrainAs(titleID) {
                        start.linkTo(contentID.start)
                        end.linkTo(contentID.end)
                        bottom.linkTo(contentID.top)

                        width = Dimension.fillToConstraints

                    }
            )

            Row(
                modifier = Modifier
                    .zIndex(0.5f)
                    .constrainAs(contentID) {
                        start.linkTo(parent.start, Padding_big)
                        end.linkTo(parent.end, Padding_big)
                        bottom.linkTo(parent.bottom, Padding_big)

                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    }
            ) {
                Text(text = "4,5", fontWeight = FontWeight.Bold)
            }

            Card(
                shape = RoundedCornerShape(CARD_BORDER_RADIUS),
                elevation = ELEVATION_BIG,
                backgroundColor = mauTraSua,
                modifier = Modifier
                    .border(
                        BORDER_SIZE,
                        BORDER_COLOR,
                        shape = RoundedCornerShape(CARD_BORDER_RADIUS)
                    )
                    .constrainAs(bottomCardID) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        top.linkTo(GL_35)

                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }

            ) {
                /** blah blah blah... */
            }
        }

    }
}

private fun OnClick(context: Context) {
    context.showShortToast("Hello compose from merchandise screen")
}
