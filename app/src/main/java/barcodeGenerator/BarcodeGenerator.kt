package barcodeGenerator

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
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

    fun createPartQRcode(partInfo: PartQRcode): ImageBitmap {

        val sb = StringBuilder()
        sb.append("/P").append(partInfo.customerArticle)
        sb.append("/3OS").append(partInfo.article)
        sb.append("-").append(partInfo.index)

        if (partInfo.isHWpresent)
            sb.append("/HW").append(partInfo.HWversion)

        if (partInfo.isSWpresent)
            sb.append("/SW").append(partInfo.SWversion)

        sb.append("/SN").append(partInfo.serialNumber)

        return createQRcode(sb.toString()).asImageBitmap()
    }

    private fun createQRcode(content: String): Bitmap {

        val width = 70
        val height = 70
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val codeWriter = MultiFormatWriter()

        try {
            val bitMatrix = codeWriter.encode(content, BarcodeFormat.DATA_MATRIX, width, height)
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