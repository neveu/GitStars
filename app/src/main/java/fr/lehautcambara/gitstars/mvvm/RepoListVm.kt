package fr.lehautcambara.gitstars.mvvm

import androidx.lifecycle.ViewModel
import fr.lehautcambara.gitstars.bus.Bus
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
    // VM state
    private val repoList = mutableListOf<RepoWithContributors>()
    private var numRepos: Int = 0
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onEvent(event: RepoResponseEvent) {
        _uiState.update { uiState ->
            uiState.copy(progress = 0F)

        }
        event.repoList?.items?.size?.let { numRepos = it }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onEvent(event: RepoWithContributorsEvent) {
        repoList.add(event.repoWithContributors)
        _uiState.update {uiState ->
            uiState.copy(
                repoWithContribList = repoList.toList().sortedByDescending{
                    it.repo.stargazers_count
                },
                progress = repoList.size.toFloat()/numRepos)
        }
    }

    override fun onCleared() {
        Bus.unregister(this)
        super.onCleared()

    }
}
