package com.starwars.core.presentation.util;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public class RxTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
        RxJavaPlugins.setIoSchedulerHandler(scheduler-> Schedulers.trampoline());
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        RxAndroidPlugins.reset();
    }

}
