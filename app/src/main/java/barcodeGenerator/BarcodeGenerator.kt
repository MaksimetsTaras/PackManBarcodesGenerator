package barcodeGenerator

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException


class BarcodeGenerator {

    private val endOfTransmission = 4.toChar().toString()
    private val GS = 29.toChar().toString()
    private val RS = 30.toChar().toString()


    fun createBoxQRcode(boxInfo: BoxQRcode): ImageBitmap {

        val sb = StringBuilder()
        sb.append("[)>").append(RS)
        sb.append("06").append(GS)
        sb.append("X").append(boxInfo.packaging).append(GS)
        sb.append("3OS").append(boxInfo.article)
        sb.append("-").append(boxInfo.index).append(GS)
        sb.append("Q").append(boxInfo.quantityInBox).append(GS)
        sb.append("V").append(boxInfo.batchNumber).append(GS)
        sb.append("HM00").append(GS)
        sb.append("P").append(boxInfo.customerArticle).append(GS)
        sb.append("B12109583").append(RS).append(endOfTransmission)

        return createQRcode(sb.toString()).asImageBitmap()
    }


    private fun createQRcode(
        content: String,
        size: Dp = 150.dp,
        padding: Dp = 0.dp
    ): Bitmap {

        val width = 150
        val height = 150
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val codeWriter = MultiFormatWriter()

        try {
            val bitMatrix =
                codeWriter.encode(content, BarcodeFormat.QR_CODE, width, height)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    val color = if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
                    bitmap.setPixel(x, y, color)
                }
            }
        } catch (e: WriterException) {

            Log.d(TAG, "generateQRCode: ${e.message}")

        }
        return bitmap
    }
}