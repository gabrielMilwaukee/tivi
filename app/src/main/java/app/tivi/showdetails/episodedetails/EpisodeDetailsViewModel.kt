/*
 * Copyright 2018 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.tivi.showdetails.episodedetails

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import app.tivi.trakt.calls.EpisodeDetailsCall
import app.tivi.util.Logger
import app.tivi.util.TiviViewModel
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class EpisodeDetailsViewModel @Inject constructor(
    val episodeDetailsCall: EpisodeDetailsCall,
    val logger: Logger
) : TiviViewModel() {

    var episodeId: Long? = null
        set(value) {
            if (field != value) {
                field = value
                refresh()
            }
        }

    private val _data = MutableLiveData<EpisodeDetailsViewState>()
    val data: LiveData<EpisodeDetailsViewState>
        get() = _data

    private fun refresh() {
        disposables.clear()

        val epId = episodeId
        if (epId != null) {
            setupLiveData(epId)

            launchWithParent {
                episodeDetailsCall.refresh(epId)
            }
        } else {
            _data.value = null
        }
    }

    private fun setupLiveData(episodeId: Long) {
        disposables += episodeDetailsCall.data(episodeId)
                .map(::EpisodeDetailsViewState)
                .subscribe(_data::postValue, logger::e)
    }
}