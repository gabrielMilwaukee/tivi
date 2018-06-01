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

import android.content.Context
import app.tivi.epDetailsSummary
import app.tivi.ui.epoxy.TotalSpanOverride
import com.airbnb.epoxy.TypedEpoxyController

class EpisodeDetailsEpoxyController(
    private val context: Context,
    private val callbacks: Callbacks
) : TypedEpoxyController<EpisodeDetailsViewState>() {

    interface Callbacks {
        // TODO
    }

    override fun buildModels(viewState: EpisodeDetailsViewState) {
        epDetailsSummary {
            id("episode_summary")
            episode(viewState.episode)
            spanSizeOverride(TotalSpanOverride)
        }
    }
}