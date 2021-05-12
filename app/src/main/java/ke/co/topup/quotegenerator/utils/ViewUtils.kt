package ke.co.topup.quotegenerator.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.palette.graphics.Palette
import okio.IOException
import java.net.URL

object ViewUtils {
    fun createPaletteSync(bitmap: Bitmap): Palette = Palette.from(bitmap).generate()

    fun URL.toBitmap(): Bitmap? {
        return try {
            BitmapFactory.decodeStream(openStream())
        } catch (e: IOException) {
            null
        }
    }
}