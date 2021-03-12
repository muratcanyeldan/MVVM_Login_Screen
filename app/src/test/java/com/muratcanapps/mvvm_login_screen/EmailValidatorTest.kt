package com.muratcanapps.mvvm_login_screen

import com.google.common.truth.Truth.assertThat
import com.muratcanapps.mvvm_login_screen.utils.isEmailValid
import org.junit.Test


class EmailValidatorTest {

    @Test
    fun `Correct simple email address should return true`(){
        val simpleEmail = "name@email.com"
        assertThat(isEmailValid(simpleEmail)).isTrue()
    }

    @Test
    fun `Wrong simple email address should return false`(){
        val simpleEmail = "wrongmail.com"
        assertThat(isEmailValid(simpleEmail)).isFalse()
    }

    @Test
    fun `Correct email address which contains special characters should return true`(){
        val complexEmail = "name_surname_number@email.com"
        assertThat(isEmailValid(complexEmail)).isTrue()
    }

    @Test
    fun `Wrong email address which contains special characters should return false`(){
        val complexEmail = "name_surname_number_email.com"
        assertThat(isEmailValid(complexEmail)).isFalse()
    }

    @Test
    fun `Empty email address should return false`(){
        val emptyEmail = ""
        assertThat(isEmailValid(emptyEmail)).isFalse()
    }

    @Test
    fun `Null email address should return false`(){
        val nullEmail:String? =null
        assertThat(isEmailValid(nullEmail)).isFalse()
    }

}