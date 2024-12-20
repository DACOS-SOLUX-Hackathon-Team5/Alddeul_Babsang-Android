package com.hackathon.alddeul_babsang.presentation.map.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.theme.Blue
import com.hackathon.alddeul_babsang.core_ui.theme.Gray300
import com.hackathon.alddeul_babsang.core_ui.theme.Orange700
import com.hackathon.alddeul_babsang.core_ui.theme.Orange800
import com.hackathon.alddeul_babsang.core_ui.theme.Orange900
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.core_ui.theme.body2Regular
import com.hackathon.alddeul_babsang.core_ui.theme.body4Regular
import com.hackathon.alddeul_babsang.core_ui.theme.head4Bold
import com.hackathon.alddeul_babsang.data.dto.response.ResponseMapStoreDetailDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseMapStoresDto
import com.hackathon.alddeul_babsang.presentation.map.navigation.MapNavigator
import com.hackathon.alddeul_babsang.util.UiState
import com.hackathon.alddeul_babsang.util.toast
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.clustering.Clusterer
import com.naver.maps.map.clustering.DefaultClusterMarkerUpdater
import com.naver.maps.map.clustering.DefaultClusterOnClickListener
import com.naver.maps.map.clustering.DefaultDistanceStrategy
import com.naver.maps.map.clustering.DefaultMarkerManager
import com.naver.maps.map.clustering.DistanceStrategy
import com.naver.maps.map.clustering.Node
import com.naver.maps.map.compose.DisposableMapEffect
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.NaverMapConstants
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.overlay.Align
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.MarkerIcons
import timber.log.Timber

@Composable
fun MapRoute(
    navigator: MapNavigator
) {
    val mapViewModel: MapViewModel = hiltViewModel()
    val systemUiController = rememberSystemUiController()
    val getMapStoresState by mapViewModel.getMapStoresState.collectAsStateWithLifecycle(UiState.Empty)
    var data = emptyList<ResponseMapStoresDto>()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = White,
        )
    }

    LaunchedEffect(Unit) {
        mapViewModel.getMapStores()
    }

    when (getMapStoresState) {
        is UiState.Success -> {
            data = (getMapStoresState as UiState.Success).data
        }

        is UiState.Failure -> {
            Timber.e((getMapStoresState as UiState.Failure).msg)
        }

        else -> {}
    }

    MapScreen(
        mapEntityList = data,
        mapViewModel = mapViewModel,
        onDetailClick = { id -> navigator.navigateDetail(id) }
    )
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapScreen(
    mapEntityList: List<ResponseMapStoresDto> = emptyList(),
    mapViewModel: MapViewModel,
    onDetailClick: (Long) -> Unit,
) {
    var mapProperties by remember {
        mutableStateOf(
            MapProperties(
                maxZoom = 100.0,
                minZoom = 5.0,
                locationTrackingMode = LocationTrackingMode.Follow,
            )
        )
    }
    var mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(isLocationButtonEnabled = true)
        )
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition(NaverMapConstants.DefaultCameraPosition.target, 10.0)
    }
    var showBabsangDetail by remember { mutableStateOf(false) }
    var selectedBabsangDetail by remember { mutableStateOf<ResponseMapStoreDetailDto?>(null) }
    val context = LocalContext.current
    val postMapStoreDetailState by mapViewModel.postMapStoreDetailState.collectAsStateWithLifecycle(
        UiState.Empty
    )

    when (postMapStoreDetailState) {
        is UiState.Success -> {
            val data = (postMapStoreDetailState as UiState.Success).data
            Timber.d("Map store detail success: $data")
            if (data != null) {
                selectedBabsangDetail = data
            }
        }

        is UiState.Failure -> {
            Timber.e("Map store detail failed: ${(postMapStoreDetailState as UiState.Failure).msg}")
            context.toast((postMapStoreDetailState as UiState.Failure).msg)
        }

        else -> {}
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        NaverMap(
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = mapUiSettings,
            onMapClick = { _, _ -> showBabsangDetail = false }
        ) {
            var clusterManager by remember { mutableStateOf<Clusterer<ItemKey>?>(null) }
            DisposableMapEffect(mapEntityList) { map ->
                if (clusterManager == null) {
                    clusterManager = Clusterer.ComplexBuilder<ItemKey>()
                        .minClusteringZoom(9)
                        .maxClusteringZoom(16)
                        .maxScreenDistance(200.0)
                        .thresholdStrategy { zoom ->
                            if (zoom <= 11) {
                                0.0
                            } else {
                                70.0
                            }
                        }
                        .distanceStrategy(object : DistanceStrategy {
                            private val defaultDistanceStrategy = DefaultDistanceStrategy()
                            override fun getDistance(zoom: Int, node1: Node, node2: Node): Double {
                                return if (zoom <= 9) {
                                    -1.0
                                } else if ((node1.tag as MapData).gu == (node2.tag as MapData).gu) {
                                    if (zoom <= 11) {
                                        -1.0
                                    } else {
                                        defaultDistanceStrategy.getDistance(zoom, node1, node2)
                                    }
                                } else {
                                    10000.0
                                }
                            }
                        })
                        .tagMergeStrategy { cluster ->
                            if (cluster.maxZoom <= 9) {
                                null
                            } else {
                                MapData(
                                    -1,
                                    "",
                                    "",
                                    (cluster.children.first().tag as MapData).gu,
                                    ""
                                )
                            }
                        }
                        .markerManager(object : DefaultMarkerManager() {
                            override fun createMarker() = super.createMarker().apply {
                                subCaptionTextSize = 10f
                                subCaptionColor = White.toArgb()
                                subCaptionHaloColor = Transparent.toArgb()
                            }
                        })
                        .clusterMarkerUpdater { info, marker ->
                            val size = info.size
                            marker.icon = when {
                                info.minZoom <= 10 -> MarkerIcons.CLUSTER_HIGH_DENSITY
                                size < 10 -> MarkerIcons.CLUSTER_LOW_DENSITY
                                else -> MarkerIcons.CLUSTER_MEDIUM_DENSITY
                            }
                            marker.subCaptionText =
                                if (info.minZoom == 10) {
                                    (info.tag as MapData).gu
                                } else {
                                    ""
                                }
                            marker.anchor = DefaultClusterMarkerUpdater.DEFAULT_CLUSTER_ANCHOR
                            marker.captionText = size.toString()
                            marker.setCaptionAligns(Align.Center)
                            marker.captionColor = White.toArgb()
                            marker.captionHaloColor = Transparent.toArgb()
                            marker.subCaptionColor = White.toArgb()
                            marker.onClickListener = DefaultClusterOnClickListener(info)
                        }
                        .leafMarkerUpdater { info, marker ->
                            marker.icon = OverlayImage.fromResource(R.drawable.ic_map_marker)
                            marker.anchor = Marker.DEFAULT_ANCHOR
                            marker.captionText = (info.tag as MapData).name
                            marker.setCaptionAligns(Align.Bottom)
                            marker.captionColor = Orange900.toArgb()
                            marker.captionHaloColor = White.toArgb()
                            marker.subCaptionText = when ((info.tag as MapData).codeName) {
                                "KOREAN" -> "한식"
                                "CHINESE" -> "중식"
                                "WESTERN_JAPANESE" -> "경양식/일식"
                                else -> "기타외식업"
                            }
                            marker.subCaptionColor = Blue.toArgb()
                            marker.onClickListener = Overlay.OnClickListener { _ ->
                                val position = marker.position
                                val cameraUpdate = CameraUpdate
                                    .scrollAndZoomTo(position, 18.0)
                                    .animate(CameraAnimation.Easing)
                                map.moveCamera(cameraUpdate)

                                mapViewModel.postMapStoreDetail((info.tag as MapData).id)
                                // 클릭된 마커의 정보
                                showBabsangDetail = true
                                true
                            }
                        }
                        .build()
                        .apply { this.map = map }
                }

                // MapEntity 데이터를 Clusterer에 추가
                val keyTagMap = mapEntityList.associate {
                    ItemKey(it.storeId.toInt(), LatLng(it.latitude, it.longitude)) to MapData(
                        id = it.storeId,
                        name = it.name,
                        codeName = it.category,
                        gu = it.region,
                        address = it.address
                    )
                }
                clusterManager?.addAll(keyTagMap)

                onDispose {
                    clusterManager?.clear()
                }
            }
        }
        if (showBabsangDetail && selectedBabsangDetail != null) {
            val data = selectedBabsangDetail!!

            Surface(
                modifier = Modifier
                    .padding(horizontal = 18.dp, vertical = 12.dp)
                    .align(Alignment.BottomCenter)
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(14.dp)
                    )
                    .clickable { onDetailClick(data.storeId) }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = White,
                            shape = RoundedCornerShape(14.dp)
                        )
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .background(color = Orange700, shape = CircleShape),
                        model = data.imageUrl?: when (data.category) {
                            "KOREAN" -> R.drawable.ic_korean_food
                            "CHINESE" -> R.drawable.ic_chinese_food
                            "WESTERN_JAPANESE" -> R.drawable.ic_japanese_food
                            else -> R.drawable.ic_etc_food
                        },
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = data.name,
                                style = head4Bold,
                                color = Orange900
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = when (data.category) {
                                    "KOREAN" -> "한식"
                                    "CHINESE" -> "중식"
                                    "WESTERN_JAPANESE" -> "경양식/일식"
                                    else -> "기타외식업"
                                },
                                style = body2Regular,
                                color = Orange800
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = data.address,
                            style = body4Regular,
                            color = Gray300
                        )
                        Text(
                            modifier = Modifier.padding(top = 4.dp),
                            text = data.contact.ifBlank { "연락처 정보 없음" },
                            style = body4Regular,
                            color = Gray300
                        )
                    }
                }
            }
        }
    }
}


private class MapData(
    val id: Long,
    val name: String,
    val codeName: String,
    val gu: String,
    val address: String
)

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    MapScreen(
        mapViewModel = hiltViewModel(),
        onDetailClick = { }
    )
}