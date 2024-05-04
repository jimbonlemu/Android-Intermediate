package com.jimbonlemu.mycustomview

import java.util.Random

internal object NumberGenerator {
    fun generate(max: Int): Int = Random().nextInt(max)

}