package com.jimbonlemu.mycustomview

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Region
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.jimbonlemu.mycustomview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mBitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888)
    private val mCanvas = Canvas(mBitmap)
    private val mPaint = Paint()

    private val halfOfWidth = (mBitmap.width / 2).toFloat()
    private val halfOfHeight = (mBitmap.height / 2).toFloat()

    private val left = 150F
    private val top = 250F
    private val right = mBitmap.width - left
    private val bottom = mBitmap.height.toFloat() - 50F

    private val message = "Apakah kamu suka bermain?"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView.setImageBitmap(mBitmap)
        showText()

        binding.like.setOnClickListener {
            showEars()
            showFace()
            showMouth(true)
            showEyes()
            showNose()
            showHair()
            binding.imageView.invalidate()
        }

        binding.dislike.setOnClickListener {
            showEars()
            showFace()
            showMouth(false)
            showEyes()
            showNose()
            showHair()
            binding.imageView.invalidate()
        }

    }

    private fun showFace() {
        val face = RectF(left, top, right, bottom)
        mPaint.apply {
            mCanvas.apply {
                color = ResourcesCompat.getColor(resources, R.color.yellow_left_skin, null)
                drawArc(face, 90F, 180F, false, mPaint)

                color = ResourcesCompat.getColor(resources, R.color.yellow_right_skin, null)
                drawArc(face, 270F, 180F, false, mPaint)
            }
        }
    }

    private fun showEyes() {
        mPaint.apply {
            mCanvas.apply {
                color = ResourcesCompat.getColor(resources, R.color.black, null)
                drawCircle(halfOfWidth - 100F, halfOfHeight - 10F, 50F, mPaint)
                drawCircle(halfOfWidth + 100F, halfOfHeight - 10F, 50F, mPaint)

                color = ResourcesCompat.getColor(resources, R.color.white, null)
                drawCircle(halfOfWidth - 120F, halfOfHeight - 20F, 15F, mPaint)
                drawCircle(halfOfWidth + 80F, halfOfHeight - 20F, 15F, mPaint)
            }
        }
    }

    private fun showMouth(isHappy: Boolean) {
        mPaint.apply {
            mCanvas.apply {
                when (isHappy) {
                    true -> {
                        color = ResourcesCompat.getColor(resources, R.color.black, null)
                        val lip = RectF(
                            halfOfWidth - 200F,
                            halfOfHeight - 100F,
                            halfOfWidth + 200F,
                            halfOfHeight + 400F
                        )
                        drawArc(lip, 25F, 130F, false, mPaint)

                        color = ResourcesCompat.getColor(resources, R.color.white, null)
                        val mouth =
                            RectF(
                                halfOfWidth - 180F,
                                halfOfHeight,
                                halfOfWidth + 180F,
                                halfOfHeight + 380F
                            )
                        drawArc(mouth, 25F, 130F, false, mPaint)
                    }

                    false -> {
                        color = ResourcesCompat.getColor(resources, R.color.black, null)
                        val lip = RectF(
                            halfOfWidth - 200F,
                            halfOfHeight + 250F,
                            halfOfWidth + 200F,
                            halfOfHeight + 350F
                        )
                        drawArc(lip, 0F, -180F, false, mPaint)

                        color = ResourcesCompat.getColor(resources, R.color.white, null)
                        val mouth = RectF(
                            halfOfWidth - 180F,
                            halfOfHeight + 260F,
                            halfOfWidth + 180F,
                            halfOfHeight + 330F
                        )
                        drawArc(mouth, 0F, -180F, false, mPaint)
                    }
                }
            }
        }
    }

    private fun showText() {
        val mPaintText = Paint(Paint.FAKE_BOLD_TEXT_FLAG).apply {
            textSize = 50F
            color = ResourcesCompat.getColor(resources, R.color.black, null)
        }

        val mBounds = Rect()
        mPaintText.getTextBounds(message, 0, message.length, mBounds)

        val x: Float = halfOfWidth - mBounds.centerX()
        val y = 50F
        mCanvas.drawText(message, x, y, mPaintText)
    }

    private fun showNose() {
        mPaint.color = ResourcesCompat.getColor(resources, R.color.black, null)
        mCanvas.apply {
            drawCircle(halfOfWidth - 40F, halfOfHeight + 140F, 15F, mPaint)
            drawCircle(halfOfWidth + 40F, halfOfHeight + 140F, 15F, mPaint)
        }
    }

    private fun showEars() {
        mPaint.apply {
            mCanvas.apply {
                color = ResourcesCompat.getColor(resources, R.color.brown_left_hair, null)
                drawCircle(halfOfWidth - 300F, halfOfHeight - 100F, 100F, mPaint)

                color = ResourcesCompat.getColor(resources, R.color.brown_right_hair, null)
                drawCircle(halfOfWidth + 300F, halfOfHeight - 100F, 100F, mPaint)

                color = ResourcesCompat.getColor(resources, R.color.red_ear, null)
                drawCircle(halfOfWidth - 300F, halfOfHeight - 100F, 60F, mPaint)
                drawCircle(halfOfWidth + 300F, halfOfHeight - 100F, 60F, mPaint)
            }
        }
    }

    private fun showHair() {
        mCanvas.apply {

            save()

            val path = Path()
            path.apply {
                addCircle(halfOfWidth - 100F, halfOfHeight - 10F, 150F, Path.Direction.CCW)
                addCircle(halfOfWidth + 100F, halfOfHeight - 10F, 150F, Path.Direction.CCW)

                val mouth =
                    RectF(halfOfWidth - 250F, halfOfHeight, halfOfWidth + 250F, halfOfHeight + 500F)
                addOval(mouth, Path.Direction.CCW)

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                    mCanvas.clipPath(path, Region.Op.DIFFERENCE)
                } else {
                    mCanvas.clipOutPath(path)
                }
            }

            val face = RectF(left, top, right, bottom)

            mPaint.apply {
                mCanvas.apply {
                    color = ResourcesCompat.getColor(resources, R.color.brown_left_hair, null)
                    drawArc(face, 90F, 180F, false, mPaint)

                    color = ResourcesCompat.getColor(resources, R.color.brown_right_hair, null)
                    drawArc(face, 270F, 180F, false, mPaint)
                }
            }
            restore()
        }
    }

}