package fr.tcleard.numberslight.core.manager.impl


import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import fr.tcleard.numberslight.core.manager.IImageManager
import javax.inject.Inject

class PicassoImageManager @Inject constructor(
        private val context: Context
) : IImageManager {

    private val picasso: Picasso
        get() = Picasso.get()

    override fun loadImage(url: String?, imageView: ImageView, onFinished: (() -> Unit)?): IImageManager.ImageRequest {
        picasso.load(url)
                .into(imageView, object : Callback {

                    override fun onSuccess() {
                        onFinished?.invoke()
                    }

                    override fun onError(e: Exception?) {
                        onFinished?.invoke()
                    }

                })
        return PicassoImageRequest(imageView, url)
    }

    inner class PicassoImageRequest(
            private val imageView: ImageView,
            url: String?
    ) : IImageManager.ImageRequest(url) {

        override fun cancel() {
            picasso.cancelRequest(imageView)
        }

    }

}