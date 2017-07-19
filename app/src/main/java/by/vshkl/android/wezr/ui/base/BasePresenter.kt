package by.vshkl.android.wezr.ui.base

import rx.Subscription
import rx.subscriptions.CompositeSubscription

open class BasePresenter<T: MvpView>: Presenter<T> {

    var mvpView: T? = null
        private set
    private val mCompositeSubscription = CompositeSubscription()

    override fun attachView(mvpView: T) {
        this.mvpView = mvpView
    }

    override fun detachView() {
        mvpView = null
        if (!mCompositeSubscription.isUnsubscribed) mCompositeSubscription.clear()
    }

    val isViewAttached: Boolean
        get() = mvpView != null

    fun checkIsViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    fun addSubscription(sub: Subscription) {
        mCompositeSubscription.add(sub)
    }

    private class MvpViewNotAttachedException internal constructor() : RuntimeException("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter")


}