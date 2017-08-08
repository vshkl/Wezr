package by.vshkl.android.wezr.ui.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<T : MvpView> : Presenter<T> {

    var mvpView: T? = null
        private set
    private val compositeSubscription = CompositeDisposable()

    override fun attachView(mvpView: T) {
        this.mvpView = mvpView
    }

    override fun detachView() {
        mvpView = null
        if (!compositeSubscription.isDisposed) {
            compositeSubscription.clear()
        }
    }

    val isViewAttached: Boolean
        get() = mvpView != null

    fun checkIsViewAttached() {
        if (!isViewAttached) {
            throw MvpViewNotAttachedException()
        }
    }

    fun addSubscription(disposable: Disposable) {
        compositeSubscription.add(disposable)
    }

    private class MvpViewNotAttachedException internal constructor() : RuntimeException("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter")
}