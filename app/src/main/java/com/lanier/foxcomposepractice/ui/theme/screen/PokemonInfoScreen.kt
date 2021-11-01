package com.lanier.foxcomposepractice.ui.theme.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.lanier.foxcomposepractice.R
import com.lanier.foxcomposepractice.base.LocalConstant
import com.lanier.foxcomposepractice.entity.*
import com.lanier.foxcomposepractice.model.PokemonInfoModel
import com.lanier.foxcomposepractice.utils.PokemonUtil
import kotlinx.coroutines.delay
/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/19 16:23
 * Desc  : detail info
 */
@Composable
fun MainInfo(controller: NavController, id: String, viewModel: PokemonInfoModel){
    var backgroundColor by remember {
        mutableStateOf(Color.Transparent)
    }
    LaunchedEffect(Unit){
        viewModel.getInfo(id)
    }
    val state = viewModel.infoFlow.collectAsState().value
    state.detail?.color?.let {
        backgroundColor = PokemonUtil.switchPokemonBaseColor(it.name)
    }
    when {
        viewModel.loading -> {
            Column (
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .placeholder(true, highlight = PlaceholderHighlight.shimmer()))
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(Color.LightGray))
                Box(modifier = Modifier
                    .fillMaxSize()
                    .placeholder(true, highlight = PlaceholderHighlight.shimmer()))
            }
        }
        viewModel.failed -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column {
                    Text(text = "加载失败", fontWeight = FontWeight.Bold, modifier = Modifier.align(
                        Alignment.CenterHorizontally))
                    Button(onClick = {
                        viewModel.getInfo(id)
                    }) {
                        Text(text = "点击重试")
                    }
                }
            }
        }
        else -> {
            LoadFinish(
                controller = controller,
                id = id,
                state = state,
                backgroundColor = backgroundColor
            )
        }
    }
}

@Composable
private fun LoadFinish(controller: NavController, id: String, state: PokemonInfoEntity, backgroundColor: Color){
    val painter = rememberImagePainter(data = LocalConstant.getOfficialForeUrl(id))
    val backPainter = painterResource(id = R.drawable.ic_baseline_arrow_back)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .statusBarsHeight()
            .background(backgroundColor)) { }
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(painter = painter, contentDescription = "", Modifier
                .clip(shape = RoundedCornerShape(0, 0, 30, 30))
                .fillMaxWidth()
                .height(250.dp)
                .background(backgroundColor)
            )
            Text(text = when (id.length){
                1 -> "#00$id"
                2 -> "#0$id"
                else -> "#$id"
            }, fontSize = 20.sp, color = Color.White,
                modifier = Modifier
                    .padding(0.dp, 10.dp, 20.dp, 0.dp)
                    .align(Alignment.TopEnd)
            )
            Image(painter = backPainter, contentDescription = "",
                modifier = Modifier
                    .clickable { controller.popBackStack() }
                    .align(Alignment.TopStart)
                    .padding(15.dp)
            )
        }
        ShowInfo(entity = state)
    }
}

@Composable
fun ShowInfo(entity: PokemonInfoEntity){
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        val detail = entity.detail
        val base = entity.base
        detail?.let { de ->
            val name = de.names?.filter { it.language.name == LocalConstant.LANGUAGE }
            val genus = de.genera?.filter { it.language.name == LocalConstant.LANGUAGE }
            val desc = de.flavor_text_entries?.filter { it.language.name == LocalConstant.LANGUAGE }
            if (!name.isNullOrEmpty()) {
                name.get(0).let {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = it.name, color = Color.White, fontSize = 25.sp)
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
            if (!genus.isNullOrEmpty()) {
                genus.get(0).let {
                    Text(text = it.genus, color = Color.LightGray)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
            AttachTypeView(types = base?.types)
            AttachWH(base)
            if (!desc.isNullOrEmpty()) {
                desc.get(0).let {
                    Text(
                        text = it.flavor_text.replace("\n", ""),
                        color = Color.White,
                        modifier = Modifier.padding(50.dp, 0.dp, 50.dp, 0.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
            AttrsItem(value = de.base_happiness, color = Color(211, 147, 241), text = "亲密度")
            AttrsItem(value = de.capture_rate, color = Color(129, 179, 253), text = "捕获率")
        }
    }
}

@Composable
fun AttachTypeView(types: List<Type>?){
    types?.let {
        Column(Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                for (type in it){
                    val text = PokemonUtil.switchPokemonType(type.type.name)
                    Text(
                        text = text,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(10.dp)
                            .width(80.dp)
                            .wrapContentHeight()
                            .clip(RoundedCornerShape(20.dp))
                            .background(PokemonUtil.switchPokemonTypeColor(text))
                            .padding(0.dp, 2.dp, 0.dp, 2.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun AttachWH(base: PokemonBaseInfoEntity?){
    base?.let {
        Column {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "${it.weight / 10.0} KG", color = Color.White, fontSize = 22.sp)
                    Text(text = "Weight", color = Color.LightGray)
                }
                Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "${it.height / 10.0} M", color = Color.White, fontSize = 22.sp)
                    Text(text = "Height", color = Color.LightGray)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun AttrsItem(value: Int, color: Color, text: String){
    val stateValue = animateFloatAsState(
        targetValue = (value / 1000.0).toFloat(),
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec)
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
    ) {
        Text(
            text = text, color = Color.LightGray,
            modifier = Modifier
                .weight(0.3f)
                .height(25.dp)
                .padding(25.dp, 0.dp, 0.dp, 0.dp)
        )
        Box(modifier = Modifier
            .weight(0.7f)
        ){
            LinearProgressIndicator(
                progress = stateValue.value, color = color,
                backgroundColor = Color.LightGray,
                modifier = Modifier
                    .height(25.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Text(
                text = "${value / 10.0}/100",
                color = Color.White,
                modifier = Modifier
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                    .align(Alignment.CenterStart),
                fontSize = 12.sp
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 600)
@Composable
fun Test(){
    val painter = painterResource(id = R.drawable.fore)
    val backPainter = painterResource(id = R.drawable.ic_baseline_arrow_back)
    val id = "10"
    Box(modifier = Modifier.fillMaxWidth()){
        Image(painter = painter, contentDescription = "",
            Modifier
                .clip(RoundedCornerShape(0, 0, 20, 20))
                .fillMaxWidth()
                .height(250.dp)
                .background(Color.Black)
        )
        Text(text = when (id.length){
            1 -> "#00$id"
            2 -> "#0$id"
            else -> "#$id"
        }, fontSize = 20.sp, color = Color.White,
            modifier = Modifier
                .padding(0.dp, 10.dp, 10.dp, 0.dp)
                .align(Alignment.TopEnd)
        )
        Image(painter = backPainter, contentDescription = "",
            modifier = Modifier
                .padding(10.dp, 10.dp, 0.dp, 0.dp)
                .clickable {}
                .align(Alignment.TopStart))
    }
}

@Preview(showBackground = true)
@Composable
fun Test1(){
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
        .background(Color.Black)
        .fillMaxWidth()) {
        Column(modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "1 KG", color = Color.White, fontSize = 22.sp)
            Text(text = "weight", color = Color.LightGray)
        }
        Column(modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "10 CM", color = Color.White, fontSize = 22.sp)
            Text(text = "height", color = Color.LightGray)
        }
    }
}
