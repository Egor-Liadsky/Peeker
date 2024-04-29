package com.lyadsky.peeker.android.ui.views.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.gilroy
import com.lyadsky.peeker.data.model.Product

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductCardView(modifier: Modifier = Modifier, product: Product) {

    Card(
        modifier = modifier.height(260.dp),
        onClick = {},
        elevation = 0.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            Modifier
                .background(Color.ProductCard.background)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(136.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.ProductCard.imageBackground),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.iphone),
                    contentDescription = "iphone",
                    modifier = Modifier.size(104.dp)
                )
            }

            Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {

                    AsyncImage(
                        model = product.market.icon,
                        contentDescription = "market icon",
                        modifier = Modifier.size(15.dp)
                    )

                    Text(
                        text = product.market.name,
                        style = TextStyle(
                            color = Color.Base.black,
                            fontFamily = gilroy,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp,
                        ),
                    )
                }

                Text(
                    text = product.name,
                    style = TextStyle(
                        color = Color.Base.black,
                        fontFamily = gilroy,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "star",
                        modifier = Modifier.size(15.dp),
                        tint = Color.Base.gray
                    )

                    Text(
                        text = "4.5 (1200)",
                        style = TextStyle(
                            color = Color.Base.gray,
                            fontFamily = gilroy,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp
                        ),
                    )
                }

                Text(
                    text = "${product.price.toInt()} ₽",
                    style = TextStyle(
                        color = Color.Base.black,
                        fontFamily = gilroy,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp
                    ),
                )
            }
        }
    }
}