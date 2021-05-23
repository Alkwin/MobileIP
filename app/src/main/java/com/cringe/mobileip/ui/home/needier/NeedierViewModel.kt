package com.cringe.mobileip.ui.home.needier

import androidx.lifecycle.*
import com.cringe.mobileip.data.managers.NeedierManager
import com.cringe.mobileip.server.model.needier.request.RequestNeedierRequest
import com.cringe.mobileip.server.model.utils.tags.*
import com.cringe.mobileip.ui.home.needier.adapters.TagStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.random.Random

class NeedierViewModel : ViewModel() {
    val needierManager = NeedierManager()

    val requestLiveData = MutableLiveData<RequestNeedierRequest>()

    val answerLiveData = requestLiveData.switchMap {
        flow {
            emit(needierManager.requestNeedier(it))
        }.flowOn(Dispatchers.IO).asLiveData()
    }

    fun sendRequest(tags: List<TagStatus>, details: String) {
        requestLiveData.postValue(
            RequestNeedierRequest(
                username = "vinaandreea27+1@gmail.com",
                tags = tags.mapNotNull {
                    if (!it.property.isSelected()) return@mapNotNull null

                    it.tag.name to it.property.getWeight()
                }.toMap(),
                details = details
            )
        )
    }

    val tags = listOf(
        "Mancare",
        "Transport",
        "Alimente",
        "Haide",
        "Medicamente",
        "Igiena",
        "Atentie",
        "Transport",
        "Alimente",
        "Haide",
        "Medicamente",
        "Igiena",
        "Atentie",
        "Transport",
        "Alimente",
        "Haide",
        "Medicamente",
        "Igiena",
        "Atentie",
        "Transport",
        "Alimente",
        "Haide",
        "Medicamente",
        "Igiena",
        "Atentie",
        "Transport",
        "Alimente",
        "Haide",
        "Medicamente",
        "Igiena",
        "Atentie",
        "Transport",
        "Alimente",
        "Haide",
        "Medicamente",
        "Igiena",
        "Atentie"
    ).map{ TagStatus(Tag(it), if (Random.nextBoolean()) Service(false) else Product(0.0)) }.toMutableList()
}