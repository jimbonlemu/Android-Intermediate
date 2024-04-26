package com.jimbonlemu.mycustomview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jimbonlemu.mycustomview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
    }
}