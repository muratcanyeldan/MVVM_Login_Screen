package com.muratcanapps.mvvm_login_screen

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Supplier
import io.reactivex.rxjava3.internal.schedulers.ExecutorScheduler.ExecutorWorker
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

class RxImmediateSchedulerRule : TestRule {
    private val immediate: Scheduler = object : Scheduler() {
        override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
            // this prevents StackOverflowErrors when scheduling with a delay
            return super.scheduleDirect(run, 0, unit)
        }

        override fun createWorker(): Worker {
            return ExecutorWorker({ obj: Runnable -> obj.run() }, false, false)
        }
    }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins.setInitIoSchedulerHandler { scheduler: Supplier<Scheduler?>? -> immediate }
                RxJavaPlugins.setInitComputationSchedulerHandler { scheduler: Supplier<Scheduler?>? -> immediate }
                RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler: Supplier<Scheduler?>? -> immediate }
                RxJavaPlugins.setInitSingleSchedulerHandler { scheduler: Supplier<Scheduler?>? -> immediate }
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }
        }
    }
}