<p align="center">
  <img width="345" height="120" src="https://raw.githubusercontent.com/G00fY2/quickie/gh-pages/media/logo.png">
</p>

**quickie** is a Quick Response (QR) Code scanning library for Android that is based on CameraX and ML Kit on-device barcode detection. It's an alternative to ZXing based libraries and written in Kotlin. **quickie** features:
- Easy API for launching the QR scanner and receiving results by using the new Activity Result API
- Modern design, edge-to-edge scanning view with multilingual user hint
- Android Jetpack CameraX for communicating with the camera and showing the preview
- ML Kit Vision API for best, fully on-device barcode recognition and decoding

## Download [![Maven Central](https://img.shields.io/maven-central/v/io.github.g00fy2.quickie/quickie-unbundled)](https://search.maven.org/search?q=g:io.github.g00fy2.quickie)
There are two different flavors available on `mavenCentral()`:

| Bundled                             | Unbundled                                         |
| ----------------------------------- | ------------------------------------------------- |
| ML Kit model is bundled inside app (independent of Google Services) | ML Kit model will be automatically downloaded via Play Services (once after app install) |
| additional 1.1 MB per ABI (you should use App Bundle or ABI splitting) | smaller app size |
| V2 model is used (possibly faster, more accurate) | currently V1 model will be downloaded
```kotlin
// bundled:  
implementation("io.github.g00fy2.quickie:quickie-bundled:1.0.0")

// unbundled:
implementation("io.github.g00fy2.quickie:quickie-unbundled:1.0.0")
```

## Quick Start
To use the QR scanner simply register the `ScanQRCode()` ActivityResultContract together with a callback during `init` or `onCreate()` lifecycle of your Activity/Fragment and use the returned ActivityResultLauncher to launch the QR scanner Activity.
```kotlin
val scanQrCode = registerForActivityResult(ScanQRCode(), ::handleResult)

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    …
    binding.button.setOnClickListener { scanQrCode.launch(null) }
}

fun handleResult(result: QRResult) {
    …
```
⚠️ **You can't register the ActivityResultContract inside the OnClickListener lambda. This will fail since the code gets executed after the onCreate lifecycle!**

Check out the [sample](https://github.com/G00fY2/quickie/tree/develop/sample) inside this repo or visit the [Activity Result API documentation](https://developer.android.com/training/basics/intents/result) for more information.

### Responses
The callback you add to the `registerForActivityResult` will receive a subclass of the sealed `QRResult` class: 

1. `QRSuccess` when ML Kit successfully detected a QR code
   * wraps a `QRContent` object
1. `QRUserCanceled` when the Activity got canceled by the user
1. `QRMissingPermission` when the user didn't accept the camera permission
1. `QRError` when CameraX or ML Kit threw an exception
   * wraps the `exception`

### Content
The content type of the QR code detected by ML Kit is wrapped inside a subclass of the sealed `QRContent` class which always provides a `rawValue`.

Currently, supported subtypes are:
`Plain`, `Wifi`, `Url`, `Sms`, `GeoPoint`, `Email`, `Phone`, `ContactInfo`, `CalendarEvent`

See the ML Kit [Barcode documentation](https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/Barcode#nested-class-summary) for further details.

### Customization
The library is designed to behave and look as generic as possible (your app theme gets applied) while matching Material Design guidelines. Currently, it's not possible to change the UI, but there are plans to add customizations in future releases.

## Screenshots / Sample App
You can find the sample app APKs inside the [release](https://github.com/G00fY2/quickie/releases) assets.

![Image](https://raw.githubusercontent.com/G00fY2/quickie/gh-pages/media/quickie-device-demo.png)

## Requirements
* AndroidX
* Min SDK 21+
* (Google Play Services available on the end device if using `quickie-unbundled`)

## License
    The MIT License (MIT)

    Copyright (C) 2021 Thomas Wirth

    Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
    associated documentation files (the "Software"), to deal in the Software without restriction,
    including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
    and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
    subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all copies or substantial
    portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
    LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
    NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
    DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT
    OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

