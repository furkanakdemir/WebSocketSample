package net.furkanakdemir.websocketsample.util.validator

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class WebSocketMessageValidatorTest {

    private lateinit var validator: MessageValidator

    @Before
    fun setUp() {
        validator = WebSocketMessageValidator()
    }

    @Test
    fun testEmptyMessage() {
        val message = ""
        assertFalse(validator.isValidMessage(message))
    }

    @Test
    fun testValidMessage() {
        val message = "1-Foo"
        assertTrue(validator.isValidMessage(message))
    }

    @Test
    fun testInvalidMessage() {
        val message1 = "1Foo"
        val message2 = "Foo"
        val message3 = "12"
        val message4 = "-"
        val message5 = "-abc"
        val message6 = "abc-"
        val message7 = "abc----"
        val message8 = "----abc----"
        val message9 = "----a"
        assertFalse(validator.isValidMessage(message1))
        assertFalse(validator.isValidMessage(message2))
        assertFalse(validator.isValidMessage(message3))
        assertFalse(validator.isValidMessage(message4))
        assertFalse(validator.isValidMessage(message5))
        assertFalse(validator.isValidMessage(message6))
        assertFalse(validator.isValidMessage(message7))
        assertFalse(validator.isValidMessage(message8))
        assertFalse(validator.isValidMessage(message9))
    }
}