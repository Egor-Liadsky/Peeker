package com.lyadsky.peeker.android.ui.views.topBar

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.defaultSemibold
import com.lyadsky.peeker.android.ui.theme.peekerTitle
import com.lyadsky.peeker.android.ui.theme.textField
import com.lyadsky.peeker.android.ui.views.divider.DashedDivider
import com.lyadsky.peeker.android.ui.views.textField.CommonTextField
import kotlin.math.roundToInt

@Composable
fun HomeTopBar(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Base.white)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = peekerTitle
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_map),
                        contentDescription = "Select city",
                        modifier = Modifier.size(20.dp)
                    )

                    Column {
                        Text(
                            text = stringResource(id = R.string.select_city_default),
                            style = defaultSemibold,
                            color = Color.Base.black
                        )

                        DashedDivider(
                            Modifier.fillMaxWidth().padding(top = 5.dp),
                            color = Color.Base.black,
                            thickness = 1.dp,
                            phase = 5f,
                            intervals = floatArrayOf(8f, 8f)
                        )
                    }
                }
            }

            CommonTextField(
                Modifier.padding(top = 24.dp),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_product_placeholder),
                        style = textField,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search"
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_qr_code_scan),
                            contentDescription = "QrCodeScanner"
                        )
                    }
                },
            ) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
