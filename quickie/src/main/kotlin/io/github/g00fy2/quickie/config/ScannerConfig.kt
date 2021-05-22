package io.github.g00fy2.quickie.config

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import io.github.g00fy2.quickie.R

/**
 * Builder for ScannerConfig used in ScanBarcode ActivityResultContract.
 */
@Suppress("MemberVisibilityCanBePrivate")
public class ScannerConfig internal constructor(
  internal val formats: IntArray,
  internal val stringRes: Int,
  internal val drawableRes: Int
) {

  public class Builder {
    private var barcodeFormats: List<BarcodeFormat> = listOf(BarcodeFormat.FORMAT_ALL_FORMATS)
    private var overlayStringRes: Int = R.string.quickie_scan_qr_code
    private var overlayDrawableRes: Int = R.drawable.quickie_ic_qrcode

    /**
     * Set a list of interested barcode formats. List must not be empty.
     * Reducing the number of supported formats will make the barcode scanner faster.
     */
    public fun setBarcodeFormats(formats: List<BarcodeFormat>): Builder = apply { barcodeFormats = formats }

    /**
     * Set a string resource used for the scanner overlay.
     */
    public fun setOverlayStringRes(@StringRes stringRes: Int): Builder = apply { overlayStringRes = stringRes }

    /**
     * Set a drawable resource used for the scanner overlay.
     */
    public fun setOverlayDrawableRes(@DrawableRes drawableRes: Int): Builder =
      apply { overlayDrawableRes = drawableRes }

    /**
     * Build the BarcodeConfig required by the ScanBarcode ActivityResultContract.
     */
    public fun build(func: Builder.() -> Unit): ScannerConfig = apply { func() }.build()

    /**
     * Build the BarcodeConfig required by the ScanBarcode ActivityResultContract.
     */
    public fun build(): ScannerConfig =
      ScannerConfig(barcodeFormats.map { it.value }.toIntArray(), overlayStringRes, overlayDrawableRes)
  }
}