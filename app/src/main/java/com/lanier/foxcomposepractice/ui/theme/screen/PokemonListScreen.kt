package com.lanier.foxcomposepractice.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.lanier.foxcomposepractice.R
import com.lanier.foxcomposepractice.base.LocalConstant
import com.lanier.foxcomposepractice.entity.PokemonListData
import com.lanier.foxcomposepractice.model.PokemonListModel

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/17 17:11
 * Desc  : pokemon list view
 */
@Composable
fun MainView(controller: NavController, model: PokemonListModel){
    val statusBarColor = Color(78,141,159)
    Column(modifier = Modifier
        .background(Color.Black)
        .fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(statusBarColor),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(modifier = Modifier
                .padding(0.dp, 0.dp, 0.dp, 10.dp),
                text = "Poke-Compose",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace
            )
        }
        MainList(controller = controller, model = model)
    }
}

@Composable
private fun MainList(controller: NavController, model: PokemonListModel){
    val listData = model.pager.collectAsLazyPagingItems()
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        contentPadding = PaddingValues(vertical = 5.dp, horizontal = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        listData.apply {
            //先加载列表项，否则下面的when判断状态不会向后添加加载中或者别的item
            items(listData){
                PokemonItem(entity = it!!, navController = controller)
            }
            when (loadState.append){
                LoadState.Loading -> {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                is LoadState.Error -> {
                    item {
                        Text(
                            modifier = Modifier.fillMaxWidth()
                                .clickable { retry() },
                            text = "加载失败, 点击重试",
                            color = Color.White,
                            textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonItem(navController: NavController, entity: PokemonListData){
    val id = entity.getId().toString()
    var showImg by remember {
        mutableStateOf(LocalConstant.getOfficialForeUrl(id))
    }
    val painter = rememberImagePainter(data = showImg)
    var clickNumber by remember {
        mutableStateOf(0)
    }
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            navController.navigate("detail?id=${entity.getId()}")
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painter, contentDescription = "",
                Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clickable {
                        clickNumber += 1
                        showImg = when (clickNumber) {
                            1 -> LocalConstant.getGBAForeUrl(id)
                            2 -> LocalConstant.getGBABackUrl(id)
                            3 -> LocalConstant.getGBAForeShinyUrl(id)
                            else -> {
                                clickNumber = 0
                                LocalConstant.getOfficialForeUrl(id)
                            }
                        }
                    })
            Spacer(modifier = Modifier.width(5.dp))
            Divider(
                Modifier
                    .width(1.dp)
                    .height(50.dp), color = Color.LightGray)
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(text = entity.name, fontSize = 23.sp)
                Spacer(modifier = Modifier.height(20.dp))
                var id = entity.getId().toString()
                if (id.length == 1){
                    id = "00$id"
                } else if (id.length == 2){
                    id = "0$id"
                }
                Text(text = "# $id")
            }
        }
    }
}

@Composable
fun TestShowPokemonItem(){
    val painter = painterResource(id = R.drawable.avatar)
    Row(Modifier.fillMaxWidth()) {
        Image(painter = painter, contentDescription = "",
            Modifier
                .width(100.dp)
                .height(100.dp))
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(text = "蒜头王八")
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "# 1")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Test2(){
    Column {
        Text(text = "Poke-Compose", fontFamily = FontFamily.Cursive)
        Text(text = "Poke-Compose", fontFamily = FontFamily.Default)
        Text(text = "Poke-Compose", fontFamily = FontFamily.Monospace)
        Text(text = "Poke-Compose", fontFamily = FontFamily.SansSerif)
        Text(text = "Poke-Compose", fontFamily = FontFamily.Serif)
    }
}

@Preview(showBackground = true)
@Composable
fun Test3(){
    val statusBarColor = Color(78,141,159)
    Column(modifier = Modifier
        .background(Color.Black)
        .fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(statusBarColor)
        ) {
            Text(modifier = Modifier
                .padding(0.dp, 0.dp, 0.dp, 20.dp)
                .align(Alignment.BottomCenter),
                text = "Poke-Compose",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}
