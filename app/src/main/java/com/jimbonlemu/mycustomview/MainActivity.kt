package com.jimbonlemu.mycustomview

import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jimbonlemu.mycustomview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            supportActionBar?.hide()
            finishButton.setOnClickListener {
                seatsView.seat?.let {
                    Toast.makeText(
                        this@MainActivity,
                        "Kursi Anda nomor ${it.name}.",
                        Toast.LENGTH_SHORT
                    ).show()
                } ?: run {
                    Toast.makeText(
                        this@MainActivity,
                        "Silahkan pilih kursi terlebih dahulu.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}