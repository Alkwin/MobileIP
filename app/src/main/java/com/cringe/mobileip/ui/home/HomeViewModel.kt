package com.cringe.mobileip.ui.home

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cringe.mobileip.R
import com.cringe.mobileip.data.managers.AuthenticationManager
import com.cringe.mobileip.data.managers.HelperManager
import com.cringe.mobileip.data.managers.NeedierManager
import com.cringe.mobileip.data.managers.TagsManager
import com.cringe.mobileip.server.model.helper.form.HelperFormRequest
import com.cringe.mobileip.server.model.needier.database.RequestNeedierRequest
import com.cringe.mobileip.server.model.needier.selectHelper.SelectHelperRequest
import com.cringe.mobileip.server.model.utils.HelperData
import com.cringe.mobileip.server.model.utils.tags.*
import com.cringe.mobileip.ui.home.adapters.HelperAdapter
import com.cringe.mobileip.ui.home.adapters.TagStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import mu.KotlinLogging
import java.util.*
import kotlin.random.Random

private val logger = KotlinLogging.logger {  }

class HomeViewModel : ViewModel() {
    private val needierManager = NeedierManager()
    private val tagManager = TagsManager()
    private val helperManager = HelperManager()

    private val requestLiveData = MutableLiveData<RequestNeedierRequest>()
    private val tagRequestLiveData = MutableLiveData<String>()

    val tagAnswerLiveData = tagRequestLiveData.switchMap {
        flow {
            emit(tagManager.requestTags())
        }.flowOn(Dispatchers.IO).asLiveData()
    }
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

    val selectHelperRequest = MutableLiveData<SelectHelperRequest>()

    val selectHelperAnswer = selectHelperRequest.switchMap {
        flow {
            emit(needierManager.selectHelper(it))
        }.flowOn(Dispatchers.IO).asLiveData()
    }

    private val sendHelperForm = MutableLiveData<HelperFormRequest>()

    val sendHelperAnswer = sendHelperForm.switchMap {
        flow {
            emit(helperManager.sendForm(it))
        }.flowOn(Dispatchers.IO).asLiveData()
    }

    fun requestTags() {
        tagRequestLiveData.postValue(
            ""
        )
    }

    fun sendNeederRequest(tags: List<TagStatus>, details: String) {
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

    fun sendHelperRequest(tags: List<TagStatus>, details: String, distanceAccepted: String) {
        sendHelperForm.postValue(
            HelperFormRequest(
                username = AuthenticationManager.userName,
                distanceAccepted = distanceAccepted,
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

    var selectedHelper: HelperData? = null

    private fun helperSelected(helper: HelperData) {
        selectedHelper = helper
        logger.info { "Selected helper: $selectedHelper" }
        selectHelperRequest.postValue(
            SelectHelperRequest(
                AuthenticationManager.userName,
                helper.username
            )
        )
    }

    companion object {
        var tags = listOf(
            "N/A"
        ).map { TagStatus(Tag(it), if (Random.nextBoolean()) Service(false) else Product(0.0)) }.toMutableList()
    }
}