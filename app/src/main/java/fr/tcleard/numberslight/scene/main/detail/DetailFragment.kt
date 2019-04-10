package fr.tcleard.numberslight.scene.main.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.tcleard.numberslight.R
import fr.tcleard.numberslight.ui.viewController.AFragment
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : AFragment<DetailPresenter>(), DetailPresenter.DetailView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerDetailComponent.builder()
                .appComponent(appComponent)
                .detailModule(DetailModule())
                .build()
                .inject(this)

        presenter.attach(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}