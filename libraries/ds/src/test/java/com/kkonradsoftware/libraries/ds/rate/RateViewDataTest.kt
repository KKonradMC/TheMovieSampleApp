package com.kkonradsoftware.libraries.ds.rate

import com.kkonradsoftware.libraries.ds.R
import org.junit.Test

class RateViewDataTest {


    @Test
    fun `should return baseline_star_24 id when for value 0,0 and index 0`() {
        //given
        val rateViewDataRes = RateViewData(
            value = 0.0
        ).getStartRes(index = 0)
        printCheck(value = rateViewDataRes)
        //expect
        assert(rateViewDataRes == R.drawable.baseline_star_outline_24)
    }

    @Test
    fun `should return baseline_star_24 id when for value 0,4 and index 0`() {
        //given
        val rateViewDataRes = RateViewData(
            value = 0.4
        ).getStartRes(index = 0)
        printCheck(value = rateViewDataRes)
        //expect
        assert(rateViewDataRes == R.drawable.baseline_star_half_24)
    }

    @Test
    fun `should return baseline_star_24 id when for value 0,5 and index 0`() {
        //given
        val rateViewDataRes = RateViewData(
            value = 0.5
        ).getStartRes(index = 0)
        printCheck(value = rateViewDataRes)
        //expect
        assert(rateViewDataRes == R.drawable.baseline_star_half_24)
    }

    @Test
    fun `should return baseline_star_24 id when for value 5 and index 4`() {
        //given
        val rateViewDataRes = RateViewData(
            value = 5.0
        ).getStartRes(index = 4)
        //expect
        assert(rateViewDataRes == R.drawable.baseline_star_24)
    }

    @Test
    fun `should return baseline_star_24 id when for value 5,2 and index 4`() {
        //given
        val rateViewDataRes = RateViewData(
            value = 5.2
        ).getStartRes(index = 4)
        //expect
        assert(rateViewDataRes == R.drawable.baseline_star_24)
    }

    @Test
    fun `should return baseline_star_24 id when for value 7 and index 4`() {
        //given
        val rateViewDataRes = RateViewData(
            value = 7.0
        ).getStartRes(index = 4)

        //expect
        assert(rateViewDataRes == R.drawable.baseline_star_24)
    }

    @Test
    fun `should return baseline_star_half_24 id when for value 4,6 and index 4`() {
        //given
        val rateViewDataRes = RateViewData(
            value = 4.6
        ).getStartRes(index = 4)

        //expect
        assert(rateViewDataRes == R.drawable.baseline_star_half_24)
    }
}

private fun printCheck(value: Int) {
    println("value : $value")
    println("baseline_star_24 : ${R.drawable.baseline_star_24}")
    println("baseline_star_half_24 : ${R.drawable.baseline_star_half_24}")
    println("baseline_star_outline_24 : ${R.drawable.baseline_star_outline_24}")
}