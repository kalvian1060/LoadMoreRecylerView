package id.kalviansofian.belajarrecylerviewloadmore;

/**
 * Created by root on 12/05/17.
 */
public class IPresentMain implements PresentMain{
    MainView mainView;

    public IPresentMain(MainView mainView) {
        this.mainView = mainView;
        mainView.setupView();
    }
}
