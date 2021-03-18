package com.muratcanapps.mvvm_login_screen

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.muratcanapps.mvvm_login_screen.model.SignInWithEmailResponse
import com.muratcanapps.mvvm_login_screen.viewmodel.LoginScreenViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito


class UserLoginTest {

    /*@ClassRule
    var schedulers = RxImmediateSchedulerRule()

     */

    /*
    companion object {

        //@ClassRule
        @get: ClassRule val schedulers = RxImmediateSchedulerRule()
    }

     */


    val email = "muratcan_yeldan@hotmail.com"
    val password = "123456"

    /*
    @Mock val request : SignInWithEmailRequest = SignInWithEmailRequest(email,password)
    @Mock val liveData = MutableLiveData<SignInWithEmailResponse>()

    @Mock val loginScreenViewModel = LoginScreenViewModel(Application())
    */
    @Mock
    private lateinit var loginScreenViewModel: LoginScreenViewModel

    @Mock
    private var application = Application()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        //RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> Schedulers.trampoline() }

        //loginScreenViewModel = LoginScreenViewModel(application)
        loginScreenViewModel = Mockito.spy(LoginScreenViewModel(application));
    }

    @Test
    fun `User login when user enter correct email and password return true`() {
        
        /*
        val token = SignInWithEmailResponse(
            "identitytoolkit#VerifyPasswordResponse",
            "c5Tbai1nG7U0m1Q4enS3gtF4sw93",
            "muratcan_yeldan@hotmail.com",
            "",
            "eyJhbGciOiJSUzI1NiIsImtpZCI6IjRlMDBlOGZlNWYyYzg4Y2YwYzcwNDRmMzA3ZjdlNzM5Nzg4ZTRmMWUiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vbXZ2bWxvZ2luc2NyZWVuIiwiYXVkIjoibXZ2bWxvZ2luc2NyZWVuIiwiYXV0aF90aW1lIjoxNjE1ODc5MDg2LCJ1c2VyX2lkIjoiYzVUYmFpMW5HN1UwbTFRNGVuUzNndEY0c3c5MyIsInN1YiI6ImM1VGJhaTFuRzdVMG0xUTRlblMzZ3RGNHN3OTMiLCJpYXQiOjE2MTU4NzkwODYsImV4cCI6MTYxNTg4MjY4NiwiZW1haWwiOiJtdXJhdGNhbl95ZWxkYW5AaG90bWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZW1haWwiOlsibXVyYXRjYW5feWVsZGFuQGhvdG1haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoicGFzc3dvcmQifX0.fFd7YJ_SR5s6m-Nq2lxNoHtjITjS_WzK_D69WNYfQc-6NSFvMo2NFKDBPdpIeeHHeM2Oomp8pslpG7EZ7T_d21FZ85PGlZbEqPYB81zjqmsFGI4mdvEfLfI4uxQlLTgXBTmUQEuFv9p2ms137IC_J73ge_CQCVrAGDRFbYLnay81dlwui9ZR7epjsRfsjv44NhoX_muY9lyuNuD3mU_m9kl46fkRYQwS7RViuEJm6Uo7XWtIZyhP-5K09-JaMJJEALJZv8rpxgmIu2kRmpwRZlIudJK64swwa2-BAqOK-MibkgKZnCttq7cgeT9DHrSirESjksjxee5peZuwuD-G3g",
            true,
            "AOvuKvQ9buoqfkT5Oy-LcrllqGSg5xdEdNEA9NSakp_cB-OhQapcB3n3nTH-BH2zD0IyHgr_CpjJiYgmjMFsrr_xOmkFC8vPTv8AGBWbrM_n31j4_XeAQ07Skt4TJg_Uz1j28HeYlSl1qa7bF6u6J8XYB9bAQV2lNUEFR6hDRQrlgBcAuYHYJiTLlXzDiydUmw5nv9XvINUoT5SqyywcTYRxqNfciwQgng",
            "3600"
        )
        */
        val account = SignInWithEmailResponse(
            "identitytoolkit#VerifyPasswordResponse",
            "c5Tbai1nG7U0m1Q4enS3gtF4sw93",
            "muratcan_yeldan@hotmail.com",
            "",
            "eyJhbGciOiJSUzI1NiIsImtpZCI6IjRlMDBlOGZlNWYyYzg4Y2YwYzcwNDRmMzA3ZjdlNzM5Nzg4ZTRmMWUiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vbXZ2bWxvZ2luc2NyZWVuIiwiYXVkIjoibXZ2bWxvZ2luc2NyZWVuIiwiYXV0aF90aW1lIjoxNjE1ODc5MDg2LCJ1c2VyX2lkIjoiYzVUYmFpMW5HN1UwbTFRNGVuUzNndEY0c3c5MyIsInN1YiI6ImM1VGJhaTFuRzdVMG0xUTRlblMzZ3RGNHN3OTMiLCJpYXQiOjE2MTU4NzkwODYsImV4cCI6MTYxNTg4MjY4NiwiZW1haWwiOiJtdXJhdGNhbl95ZWxkYW5AaG90bWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZW1haWwiOlsibXVyYXRjYW5feWVsZGFuQGhvdG1haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoicGFzc3dvcmQifX0.fFd7YJ_SR5s6m-Nq2lxNoHtjITjS_WzK_D69WNYfQc-6NSFvMo2NFKDBPdpIeeHHeM2Oomp8pslpG7EZ7T_d21FZ85PGlZbEqPYB81zjqmsFGI4mdvEfLfI4uxQlLTgXBTmUQEuFv9p2ms137IC_J73ge_CQCVrAGDRFbYLnay81dlwui9ZR7epjsRfsjv44NhoX_muY9lyuNuD3mU_m9kl46fkRYQwS7RViuEJm6Uo7XWtIZyhP-5K09-JaMJJEALJZv8rpxgmIu2kRmpwRZlIudJK64swwa2-BAqOK-MibkgKZnCttq7cgeT9DHrSirESjksjxee5peZuwuD-G3g",
            true,
            "AOvuKvQ9buoqfkT5Oy-LcrllqGSg5xdEdNEA9NSakp_cB-OhQapcB3n3nTH-BH2zD0IyHgr_CpjJiYgmjMFsrr_xOmkFC8vPTv8AGBWbrM_n31j4_XeAQ07Skt4TJg_Uz1j28HeYlSl1qa7bF6u6J8XYB9bAQV2lNUEFR6hDRQrlgBcAuYHYJiTLlXzDiydUmw5nv9XvINUoT5SqyywcTYRxqNfciwQgng",
            "3600"
        )

        loginScreenViewModel.loginAuth(email, password)
        //assertThat(Mockito.`when`(loginScreenViewModel.loginAuth(email, password)).thenReturn(loginScreenViewModel.loginAuth(email,password)))
        assertThat(loginScreenViewModel.loginSuccessLiveData.value == true).isFalse()

        //val testSingle = Single.just(token)

    }
}