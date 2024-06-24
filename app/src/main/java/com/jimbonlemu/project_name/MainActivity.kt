package com.jimbonlemu.project_name

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jimbonlemu.project_name.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
    }
}