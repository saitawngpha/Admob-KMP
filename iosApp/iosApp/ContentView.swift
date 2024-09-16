import UIKit
import SwiftUI
import ComposeApp
import GoogleMobileAds

struct ComposeView: UIViewControllerRepresentable {
    func makeCoordinator() -> Coordinator {
        Coordinator()
    }

    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(
            createBannerAd: { () -> UIViewController in
                let adBannerView = VStack {
                    BannerAdView(adUnitID: "ca-app-pub-3940256099942544/2435281174")
                }
                return UIHostingController(rootView: adBannerView)
            },
            loadFullScreenAd: {
                Task { [weak coordinator = context.coordinator] in
                    await coordinator?.loadInterstitialAd()
                }
            },
            showFullScreenAd: {
                context.coordinator.showInterstitialAd()
            }
        )
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}



