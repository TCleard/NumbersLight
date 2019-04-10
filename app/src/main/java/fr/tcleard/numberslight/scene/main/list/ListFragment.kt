package fr.tcleard.numberslight.scene.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.tcleard.numberslight.R
import fr.tcleard.numberslight.ui.viewController.AFragment
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : AFragment<ListPresenter>(), ListPresenter.ListView {

    var listener: Listener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerListComponent.builder()
                .appComponent(appComponent)
                .listModule(ListModule())
                .build()
                .inject(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listRefresh.setOnRefreshListener {
            presenter.getItems()
        }

        presenter.attach(this)

    }

    /** ListView **/

    override fun showLoading(loading: Boolean) {
        listRefresh.isRefreshing = loading
    }

    override fun showItems() {

    }

    /** Listeners**/

    interface Listener {

        fun onItemClicked()

    }

}