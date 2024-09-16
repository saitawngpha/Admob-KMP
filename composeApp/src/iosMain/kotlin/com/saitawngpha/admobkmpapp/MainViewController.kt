package com.saitawngpha.admobkmpapp

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitViewController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIViewController

@OptIn(ExperimentalForeignApi::class)
fun MainViewController(
    createBannerAd: () -> UIViewController,
    loadFullScreenAd: () -> Unit,
    showFullScreenAd: () -> Unit
) = ComposeUIViewController {
    val swiftUiBannerAd = @Composable {
        UIKitViewController(
            // If you don't set 50.dp height it occurs error
            modifier = Modifier.fillMaxWidth()
                .height(50.dp),
            factory = createBannerAd
        )
    }

    App(
        createBannerAd = swiftUiBannerAd,
        loadFullScreenAd = loadFullScreenAd,
        showFullScreenAd = showFullScreenAd
    )
}