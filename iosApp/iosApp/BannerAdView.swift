//
//  BannerAdView.swift
//  iosApp
//
//  Created by Steve Pha on 16/09/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import GoogleMobileAds
import UIKit

struct BannerAdView: UIViewRepresentable {
    let adUnitID: String
    
    func makeUIView(context: Context) -> GADBannerView {
        let bannerView = GADBannerView()
        
        bannerView.adUnitID = adUnitID
        let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene
        if let rootViewController = windowScene?.windows.first?.rootViewController {
            bannerView.rootViewController = rootViewController
        }
    
        bannerView.load(GADRequest())
        return bannerView
    }
    
    func updateUIView(_ uiView: GADBannerView, context: Context) {
        
    }
}

class Coordinator: NSObject {
    var interstitialAd: GADInterstitialAd?

    func loadInterstitialAd() async {
        do {
            self.interstitialAd = try await GADInterstitialAd.load(
                withAdUnitID: "ca-app-pub-3940256099942544/4411468910",
                request: GADRequest()
            )
        } catch {
            print("Failed to load interstitial ad: \(error)")
        }
    }

    func showInterstitialAd() {
        if let interstitialAd = interstitialAd {
            // Get the root view controller
            if let rootViewController = UIApplication.shared.windows.first?.rootViewController {
                interstitialAd.present(fromRootViewController: rootViewController)
            } else {
                print("Root view controller not available")
            }
        } else {
            print("Interstitial ad is not ready")
        }
    }
}
