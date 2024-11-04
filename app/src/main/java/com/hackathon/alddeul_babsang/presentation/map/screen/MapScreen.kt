package com.hackathon.alddeul_babsang.presentation.map.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.theme.Blue
import com.hackathon.alddeul_babsang.core_ui.theme.Orange900
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.presentation.map.navigation.MapNavigator
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

@Composable
fun MapRoute(
    navigator: MapNavigator
) {
    val mapViewModel: MapViewModel = hiltViewModel()
    MapScreen(
        mapViewModel = mapViewModel,
    )
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapScreen(
    mapViewModel: MapViewModel
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
    val mapEntityList = mapViewModel.mockMapList
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition(NaverMapConstants.DefaultCameraPosition.target, 10.0)
    }
    var showBabsangDetail by remember { mutableStateOf(false) }
    val context = LocalContext.current

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
                                MapData("", "", (cluster.children.first().tag as MapData).gu)
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
                            marker.subCaptionText = (info.tag as MapData).codeName
                            marker.subCaptionColor = Blue.toArgb()
                            marker.onClickListener = Overlay.OnClickListener { _ ->
                                val position = marker.position
                                val cameraUpdate = CameraUpdate
                                    .scrollAndZoomTo(position, 18.0)
                                    .animate(CameraAnimation.Easing)
                                map.moveCamera(cameraUpdate)

                                showBabsangDetail = true
                                true
                            }
                        }
                        .build()
                        .apply { this.map = map }
                }

                // MapEntity 데이터를 Clusterer에 추가
                val keyTagMap = mapEntityList.associate {
                    ItemKey(it.id, LatLng(it.latitude, it.longitude)) to MapData(
                        name = it.name,
                        codeName = it.codeName,
                        gu = it.gu
                    )
                }
                clusterManager?.addAll(keyTagMap)

                onDispose {
                    clusterManager?.clear()
                }
            }
        }
        if (showBabsangDetail) {
            Surface(
                modifier = Modifier
                    .padding(horizontal = 18.dp, vertical = 12.dp)
                    .align(Alignment.BottomCenter)
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(14.dp)
                    )
                    .clickable { context.toast("상세 페이지로 이동") }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = White,
                            shape = RoundedCornerShape(14.dp)
                        )
                        .padding(20.dp)

                ) {
                    Image(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Text(text = "식당 이름")
                }
            }
        }
    }
}

private class MapData(val name: String, val codeName: String, val gu: String)