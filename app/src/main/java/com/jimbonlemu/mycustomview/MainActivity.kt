package com.jimbonlemu.mycustomview

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jimbonlemu.mycustomview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            setMyButtonEnable()
            myEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                    setMyButtonEnable()
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
            myButton.setOnClickListener {
                Toast.makeText(
                    this@MainActivity,
                    myEditText.text,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun ActivityMainBinding.setMyButtonEnable() {
        val result = myEditText.text
        myButton.isEnabled = result != null && result.toString().isNotEmpty()
    }
}