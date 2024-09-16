package com.saitawngpha.admobkmpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private var interstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            MobileAds.initialize(this@MainActivity)
        }

        // Set the content for the composable function
        setContent {
            App(
                createBannerAd = {
                    AndroidView(
                        modifier = Modifier.fillMaxWidth(),
                        factory = { context ->
                            AdView(context).apply {
                                setAdSize(AdSize.FULL_BANNER)
                                adUnitId = "ca-app-pub-3940256099942544/6300978111"
                                loadAd(AdRequest.Builder().build())
                            }
                        }
                    )
                },
                loadFullScreenAd = {
                    InterstitialAd.load(
                        this@MainActivity,
                        "ca-app-pub-3940256099942544/1033173712",
                        AdRequest.Builder().build(),
                        object : InterstitialAdLoadCallback() {
                            override fun onAdLoaded(p0: InterstitialAd) {
                                interstitialAd = p0
                            }

                            override fun onAdFailedToLoad(p0: LoadAdError) {
                                interstitialAd = null
                            }
                        }
                    )
                },
                showFullScreenAd = {
                    interstitialAd?.show(this@MainActivity)
                }
            )
        }

    }
}

//@Preview
//@Composable
//fun AppAndroidPreview() {
//    App()
//}