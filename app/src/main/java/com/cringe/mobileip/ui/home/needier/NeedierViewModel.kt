package com.cringe.mobileip.ui.home.needier

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cringe.mobileip.R
import com.cringe.mobileip.data.managers.AuthenticationManager
import com.cringe.mobileip.data.managers.NeedierManager
import com.cringe.mobileip.server.model.needier.database.RequestNeedierRequest
import com.cringe.mobileip.server.model.needier.selectHelper.SelectHelperRequest
import com.cringe.mobileip.server.model.utils.HelperData
import com.cringe.mobileip.server.model.utils.tags.*
import com.cringe.mobileip.ui.home.needier.adapters.HelperAdapter
import com.cringe.mobileip.ui.home.needier.adapters.TagStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import kotlin.random.Random

class NeedierViewModel : ViewModel() {
    private val needierManager = NeedierManager()

    private val databaseRequest = MutableLiveData<RequestNeedierRequest>()

    val databaseAnswer = databaseRequest.switchMap {
        flow {
            emit(needierManager.registerRequest(it))
        }.flowOn(Dispatchers.IO).asLiveData()
    }

    private val matchRequest = MutableLiveData<String>()

    val matchAnswer = matchRequest.switchMap {
        flow {
            emit(needierManager.requestHelpers(it))
        }.flowOn(Dispatchers.IO).asLiveData()
    }

    private val selectHelperRequest = MutableLiveData<SelectHelperRequest>()

    val selectHelperAnswer = selectHelperRequest.switchMap {
        flow {
            emit(needierManager.selectHelper(it))
        }.flowOn(Dispatchers.IO).asLiveData()
    }

    fun sendRequest(tags: List<TagStatus>, details: String) {
        databaseRequest.postValue(
            RequestNeedierRequest(
                username = AuthenticationManager.userName,
                tags = tags.mapNotNull {
                    if (!it.property.isSelected()) return@mapNotNull null

                    it.tag.name to it.property.getWeight()
                }.toMap(),
                details = details
            )
        )
    }

    fun sendMatchRequest() {
        matchRequest.postValue(
            AuthenticationManager.userName
        )
    }

    fun getChoseHelperAlert(context: Context, helpers: List<HelperData>): AlertDialog {
        val view = LayoutInflater.from(context).inflate(R.layout.chose_helper_alert, null)

        val recyclerView = view.findViewById<RecyclerView>(R.id.helpersRecyclerView)

        val alert = AlertDialog.Builder(context).setView(view).create()

        recyclerView.adapter = HelperAdapter(helpers) {
            helperSelected(it)
            alert.dismiss()
        }

        recyclerView.layoutManager = LinearLayoutManager(context)

        return alert
    }

    private fun helperSelected(helper: HelperData) {
        selectHelperRequest.postValue(
            SelectHelperRequest(
                AuthenticationManager.userName,
                helper.username
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