package com.my_app.weathernowlater.utils

import com.my_app.weathernowlater.utils.DateUtils.dateFormat
import com.my_app.weathernowlater.utils.DateUtils.isToday
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Calendar

class DateUtilsTest {

    @Test
    fun `test dateFormat() when valid date string is provided`() {
        val inputDate = "2025-04-12 10:30:00"
        val expectedOutput = "12/04/2025 10:30 AM"

        val result = inputDate.dateFormat()

        assertEquals(expectedOutput, result)
    }

    @Test
    fun `test dateFormat() when invalid date string is provided`() {
        val invalidDate = "invalid date"

        val result = invalidDate.dateFormat()

        assertNull(result)
    }

    @Test
    fun `test isToday() when date is today`() {
        val todayDate = Calendar.getInstance().timeInMillis / 1000

        val result = todayDate.toInt().isToday()
        println(result)

        assertTrue(result)
    }

    @Test
    fun `test isToday() when date is not today`() {
        val notTodayDate = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, -1)
        }.timeInMillis / 1000

        val result = notTodayDate.toInt().isToday()

        assertFalse(result)
    }
}
