package com.developersbreach.composeactors.data.repository.search

import com.developersbreach.composeactors.data.datasource.network.NetworkDataSource
import com.developersbreach.composeactors.data.model.Actor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource
) {

    suspend fun getSearchableActorsData(query: String): List<Actor> {
        return networkDataSource.getSearchableActorsData(query)
    }
}