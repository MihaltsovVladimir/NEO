package by.mihaltsov.core.data.repository

import javax.inject.Inject

class TestHiltImpl @Inject constructor() : TestHilt {

    override fun text(): String = "12345"
}