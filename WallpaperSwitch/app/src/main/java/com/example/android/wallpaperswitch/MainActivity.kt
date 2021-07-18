package com.example.android.wallpaperswitch

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
//import android.os.Handler
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var wallpaperlist = arrayOf(R.drawable.ghost,R.drawable.noice, R.drawable.tsushima)
    private lateinit var changebutton: Button
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changebutton = findViewById(R.id.button)
        changebutton.setOnClickListener {
            setwallpaper() }

    }
    fun setwallpaper() {
        var t = Toast.makeText(this, "Wait for it boi!", Toast.LENGTH_SHORT)
        t.show()

        Handler().postDelayed(
                {
                    for (i in wallpaperlist) {
                        println(1)
                        var man: Bitmap = BitmapFactory.decodeResource(resources,i)
                    val manager = WallpaperManager.getInstance(baseContext)
//                        manager.setBitmap(man)
                    manager.setResource(i)
                    }


                }
                ,4000)


//        var m = Toast.makeText(this, "Done", Toast.LENGTH_SHORT)
//        m.show()

    }
}