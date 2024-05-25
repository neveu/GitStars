package fr.lehautcambara.gitstars.mvvm

import androidx.lifecycle.ViewModel
import fr.lehautcambara.gitstars.bus.Bus
import fr.lehautcambara.gitstars.bus.events.RepoRequestEvent
import fr.lehautcambara.gitstars.bus.events.RepoResponseEvent
import fr.lehautcambara.gitstars.bus.events.RepoWithContributorsEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class RepoListVm : ViewModel() {
    init {
        Bus.register(this)
    }

    private var _uiState: MutableStateFlow<RepoListUIState> = MutableStateFlow(RepoListUIState())
    val uiState: StateFlow<RepoListUIState> = _uiState.asStateFlow()

    private val repoList = mutableListOf<RepoWithContributors>()
    private var numRepos: Int = 0
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onEvent(event: RepoRequestEvent) {
        _uiState.update {uiState ->
            uiState.copy(loading = true)
        }
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onEvent(event: RepoResponseEvent) {
        event.repoList?.items?.size?.let { numRepos = it }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onEvent(event: RepoWithContributorsEvent) {
        repoList.add(event.repoWithContributors)
        val loading: Boolean = repoList.size != numRepos
        val progress = repoList.size.toFloat()/numRepos.toFloat()
        _uiState.update {uiState ->
            uiState.copy(repoWithContribList = repoList.toList(), loading = loading, progress = progress)
        }
    }

    override fun onCleared() {
        Bus.unregister(this)
        super.onCleared()

    }
}
