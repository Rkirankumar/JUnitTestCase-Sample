package com.unittesting

import org.junit.Test

import org.junit.Assert.*

class EmailValidatorTest {

    @Test
    fun emailvalidate() {
        assertTrue(EmailValidator.isValidEmail("abc@kiran.com"))//Valid Email
        assertTrue(EmailValidator.isValidEmail("abc@gmail.com"))//Valid domain
        assertFalse(EmailValidator.isValidEmail("abc@kiran"))//InValid Email
        assertFalse(EmailValidator.isValidEmail("abc@gmail..com"))//InValid domain
        assertFalse(EmailValidator.isValidEmail(null))//null value
        assertFalse(EmailValidator.isValidEmail(""))//Empty value

    }

}