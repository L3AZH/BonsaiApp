package com.l3azh.bonsaiapp.Dialog

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.l3azh.bonsaiapp.Service.PickAndCaptureImageService
import com.l3azh.bonsaiapp.Service.TypeAction
import com.l3azh.bonsaiapp.ui.theme.transparent_b_73

@Composable
fun ChoosePickOrCaptureImageDialog(
    isShow: Boolean,
    onClose: () -> Unit,
    nameScreen: String,
    onBitmapGotFromChooseOrCapture: (Bitmap) -> Unit
) {
    if (isShow) {
        Box(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(color = transparent_b_73)
                .clickable(
                    indication = null, // disable ripple effect
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { }
                )
        ) {
            Card(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .wrapContentSize(),
                shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp),
                elevation = 20.dp
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(1f),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            modifier = Modifier
                                .size(48.dp)
                                .padding(10.dp)
                                .clickable { onClose() }
                        )
                    }
                    Text(
                        text = "Camera",
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                PickAndCaptureImageService.registerTakePickFromScreen(nameScreen) { resultBitmap ->
                                    onBitmapGotFromChooseOrCapture(resultBitmap)
                                }
                                PickAndCaptureImageService.launch(TypeAction.TakingPicture)
                                onClose()
                            }

                    )
                    Text(
                        text = "Gallery",
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                PickAndCaptureImageService.registerTakePickFromScreen(nameScreen) { resultBitmap ->
                                    onBitmapGotFromChooseOrCapture(resultBitmap)
                                }
                                PickAndCaptureImageService.launch(TypeAction.ChoosePictureFromGallery)
                                onClose()
                            }
                    )
                }
            }
        }
    }
}