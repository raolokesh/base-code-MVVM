package com.vaaan.testingjetpack.utils

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Build
import android.util.TypedValue
import androidx.core.content.ContextCompat
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


fun getCurrentDateFormatted(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH)
    val currentDate = Date()
    return dateFormat.format(currentDate)
}


fun getDateFormatted(date: Long): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    return dateFormat.format(date)
}

fun getDateFormatted(date: Date): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    return dateFormat.format(date)
}


fun <T : Serializable?> getSerializable(activity: Activity, name: String, clazz: Class<T>): T {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        activity.intent.getSerializableExtra(name, clazz)!!
    else
        activity.intent.getSerializableExtra(name) as T
}


// Get Current Date and time format function 03/12/2024 09:50:00 from input date
fun getDateTimeFormat(inputDate: String): String {
    val timestamp = inputDate.substringAfter("Date(").substringBefore(")").toLong()
    // Create a Date object from the timestamp
    val date = Date(timestamp)

    // Create a SimpleDateFormat instance with the desired format
    val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    // Format the date
    val formattedDate = dateFormat.format(date)
    return formattedDate.toString()
}

fun getBothFormat(inputDate: String): String {
    val timestamp = inputDate.substringAfter("Date(").substringBefore(")").toLong()
    // Create a Date object from the timestamp
    val date = Date(timestamp)

    // Create a SimpleDateFormat instance with the desired format
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

    // Format the date
    val formattedDate = dateFormat.format(date)
    return formattedDate.toString()
}

fun getDateFormat(inputDate: String): String {
    val timestamp = inputDate.substringAfter("Date(").substringBefore(")").toLong()
    // Create a Date object from the timestamp
    val date = Date(timestamp)

    // Create a SimpleDateFormat instance with the desired format
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    // Format the date
    val formattedDate = dateFormat.format(date)
    return formattedDate.toString()
}

fun getCurrentDateTimeTranId(): String {
    val dateFormat = SimpleDateFormat("ddMMyyyyHHmmss", Locale.ENGLISH)
    val currentDate = Date()
    return dateFormat.format(currentDate)
}


fun getDateIsFuture(inputDate: String): Boolean {
//    input = "30-04-2025 13:04"
    val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
    // Create a Date object from the timestamp
    return try {
        // Parse the input date string into a Date object
        val date = dateFormat.parse(inputDate)
        val currentDate = Date()

        // Check if the parsed date is after the current date
        date?.after(currentDate) ?: false // Return false if date is null
    } catch (e: Exception) {
        // Handle parsing exceptions (e.g., invalid date format)
        false
    }
}

fun getDateIsGone(inputDate: String): Boolean {
    val timestamp = inputDate.substringAfter("Date(").substringBefore(")").toLong()
    // Create a Date object from the timestamp
    val date = Date(timestamp)
    val currentDate = Date()
    return try {
        currentDate.after(date)  // Return false if date is null
    } catch (e: Exception) {
        false
    }
}

fun getJsonDateTimeFormat(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
    val currentDate = Date()
    return dateFormat.format(currentDate)
}

fun getNextDate(daysAgo: Int): String {
    // Create a SimpleDateFormat with the specified format
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    val currentDate = Date()

    // Create a Calendar instance and set the current date
    val calendar = Calendar.getInstance()
    calendar.time = currentDate

    // Subtract the specified number of days
    calendar.add(Calendar.DAY_OF_YEAR, +daysAgo) // Use negative to subtract days

    // Format the previous date
    return formatter.format(calendar.time)
}

fun createProfileImage(
    context: Context,
    username: String,
    size: Int,
    backgroundColorRes: Int
): Bitmap {
    // Get the first letter of the username
    val firstLetter = username.firstOrNull()?.uppercaseChar() ?: '?'

    // Create a bitmap to draw on
    val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)

    // Set up the background paint
    val backgroundPaint = Paint().apply {
        color = ContextCompat.getColor(context, backgroundColorRes)
        style = Paint.Style.FILL
    }

    // Draw the circular background
    val radius = size / 2f
    canvas.drawCircle(radius, radius, radius, backgroundPaint)

    // Set up the text paint
    val textPaint = Paint().apply {
        color = Color.WHITE // Text color
        textSize = size / 1.5f // Font size
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
        typeface = Typeface.DEFAULT_BOLD
    }

    // Draw the first letter in the center
    val xPos = radius
    val yPos = radius - ((textPaint.descent() + textPaint.ascent()) / 2)
    canvas.drawText(firstLetter.toString(), xPos, yPos, textPaint)

    return bitmap
}
//
//// Convert Content Uri to Base64 for profile Image Upload+
//fun convertContentUriToBase64(contentUri: Uri, contentResolver: ContentResolver): String? {
//    return try {
//        // Step 1: Obtain InputStream from Content URI
//        val inputStream: InputStream? = contentResolver.openInputStream(contentUri)
//        inputStream?.use { stream ->
//            // Step 2: Decode the InputStream to a Bitmap
//            val originalBitmap = BitmapFactory.decodeStream(stream)
//
//            // Step 3: Resize the Bitmap
//            val resizedBitmap = resizeBitmap(originalBitmap, 400, 600)
//
//            // Step 4: Compress the Bitmap to a ByteArray
//            val byteArrayOutputStream = ByteArrayOutputStream()
//            resizedBitmap.compress(
//                Bitmap.CompressFormat.JPEG,
//                80,
//                byteArrayOutputStream
//            ) // Adjust quality as needed
//
//            // Step 5: Convert to ByteArray
//            val byteArray = byteArrayOutputStream.toByteArray()
//
//            // Step 6: Encode to Base64
//            Base64.encodeToString(byteArray, Base64.DEFAULT)
//        }
//    } catch (e: Exception) {
//        e.printStackTrace()
//        null // Return null in case of an error
//    }
//}
//
//// Function to resize the Bitmap
//private fun resizeBitmap(bitmap: Bitmap, maxWidth: Int, maxHeight: Int): Bitmap {
//    val width = bitmap.width
//    val height = bitmap.height
//
//    // Calculate the scaling factor
//    val scaleFactor = Math.min(maxWidth.toFloat() / width, maxHeight.toFloat() / height)
//
//    // Calculate the new dimensions
//    val newWidth = (width * scaleFactor).toInt()
//    val newHeight = (height * scaleFactor).toInt()
//
//    // Create a new resized Bitmap
//    return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
//}
//
//
//// load image from internet using Glide
//fun loadImage(context: Context, imageView: ImageView, imageUrl: String) {
//    // Option 1: Append a unique query parameter
//    val uniqueImageUrl = "$imageUrl?timestamp=${System.currentTimeMillis()}"
//
//    // Option 2: Use signature
//    val signature = ObjectKey(System.currentTimeMillis().toString())
//
//    Glide.with(context)
//        .load(uniqueImageUrl) // or use imageUrl with signature
//        .override(200, 200) // URL of the image
//        .fitCenter()
//        .apply(RequestOptions.circleCropTransform())
////        .placeholder(R.drawable.person)
//        .error(R.drawable.baseline_person_24)         // Optional placeholder
//        .transform(CircleCrop())
//        .signature(signature) // Uncomment this line if using signature
//        .diskCacheStrategy(DiskCacheStrategy.NONE) // Uncomment if you want to disable disk caching
//        .skipMemoryCache(true) // Optional: Skip memory cache
//        .into(imageView)
//}
//
//fun generateQRCode(text: String): Bitmap? {
//    try {
//        val qrCodeWriter = QRCodeWriter()
//        val bitMatrix: BitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 300, 300)
//
//        val width = bitMatrix.width
//        val height = bitMatrix.height
//        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
//
//        for (x in 0 until width) {
//            for (y in 0 until height) {
//                bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
//            }
//        }
//        return bitmap
//    } catch (e: WriterException) {
//        e.printStackTrace()
//        return null
//    }
//}


fun generateDates(): List<Date> {
    val today = Calendar.getInstance()
    val dateList = mutableListOf<Date>()

    for (i in 0 until 7) {
        dateList.add(today.time)
        today.add(Calendar.DAY_OF_MONTH, 1)
    }

    return dateList
}


// Get Color From Attribute
fun getColorFromAttribute(context: Context, attr: Int): Int {
    val typedValue = TypedValue()
    val a: TypedArray = context.obtainStyledAttributes(typedValue.data, intArrayOf(attr))
    val color = a.getColor(0, Color.BLACK) // Default color if not found
    a.recycle()
    return color
}

fun resolveColorAttribute(context: Context, attr: Int): Int {
    val typedValue = TypedValue()
    context.theme.resolveAttribute(attr, typedValue, true)
    return typedValue.data
}


