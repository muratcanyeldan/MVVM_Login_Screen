package com.muratcanapps.mvvm_login_screen

import com.google.common.truth.Truth.assertThat
import com.muratcanapps.mvvm_login_screen.utils.isPasswordValid
import org.junit.Test

class PasswordValidatorTest {
    @Test
    fun `Empty password should return false`(){
        val emptyPassword =""
        assertThat(isPasswordValid(emptyPassword)).isFalse()
    }

    @Test
    fun `Null password should return false`(){
        val nullPassword:String? = null
        assertThat(isPasswordValid(nullPassword)).isFalse()
    }

    @Test
    fun `Correctly formed password should return true`(){
        val correctPassword = "123456"
        assertThat(isPasswordValid(correctPassword)).isTrue()
    }

    @Test
    fun `Password which's length shorter than minimum requirement should return false`(){
        val shortPassword = "12345"
        assertThat(isPasswordValid(shortPassword)).isFalse()
    }
}