package com.aquidigital.synchronosstech

import android.app.Application

/**
 * We use a separate App for tests to prevent initializing dependency injection.
 *
 * See [com.aquidigital.synchronosstech.util.TestRunner].
 */
class TestApp : Application()