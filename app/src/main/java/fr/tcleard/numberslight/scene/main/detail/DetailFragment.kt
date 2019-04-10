package fr.tcleard.numberslight.scene.main.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.tcleard.numberslight.R
import fr.tcleard.numberslight.core.manager.IImageManager
import fr.tcleard.numberslight.core.model.Item
import fr.tcleard.numberslight.ui.viewController.AFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment : AFragment<DetailPresenter>(), DetailPresenter.DetailView {

    @Inject
    lateinit var imageManager: IImageManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerDetailComponent.builder()
                .appComponent(appComponent)
                .detailModule(DetailModule())
                .build()
                .inject(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attach(this)
    }

    fun setItem(item: Item) {
        presenter.setItem(item)
    }

    /** DetailView **/

    private var imageRequest: IImageManager.ImageRequest? = null

    override fun showImage(url: String) {
        if (imageRequest?.url != url) {
            imageRequest?.cancel()
            detailImage.setImageBitmap(null)
            detailImage.visibility = View.VISIBLE
            imageRequest = if (url.isNotBlank()) {
                imageManager.loadImage(url, detailImage)
            } else {
                detailImage.visibility = View.GONE
                null
            }
        }
    }

    override fun showText(text: String) {
        detailText.text = text
    }
}