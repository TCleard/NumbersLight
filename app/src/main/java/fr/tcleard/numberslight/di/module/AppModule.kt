package fr.tcleard.numberslight.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import fr.tcleard.numberslight.core.error.ErrorVisitor
import fr.tcleard.numberslight.core.error.IErrorVisitor
import fr.tcleard.numberslight.core.manager.IImageManager
import fr.tcleard.numberslight.core.manager.impl.PicassoImageManager
import fr.tcleard.numberslight.di.ApplicationScope

@Module
class AppModule(private val app: Application) {

    @Provides
    @ApplicationScope
    fun provideApplication(): Application =
            app

    @Provides
    @ApplicationScope
    fun provideImageManager(): IImageManager =
            PicassoImageManager(app)

    @Provides
    @ApplicationScope
    fun provideErrorVisitor(): IErrorVisitor =
            ErrorVisitor()

}