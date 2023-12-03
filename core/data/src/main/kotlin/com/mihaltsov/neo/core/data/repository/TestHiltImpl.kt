package com.mihaltsov.neo.core.data.repository

import javax.inject.Inject

class TestHiltImpl @Inject constructor() : TestHilt {

    override fun text(): String = "12345"
}