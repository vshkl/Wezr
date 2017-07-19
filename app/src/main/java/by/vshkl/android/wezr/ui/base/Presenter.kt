package by.vshkl.android.wezr.ui.base


interface Presenter<in V: MvpView> {
    fun attachView(mvpView: V)
    fun detachView()
}
