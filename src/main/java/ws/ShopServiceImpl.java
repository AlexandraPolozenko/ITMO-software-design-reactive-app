package ws;

import api.ShopService;
import application.ShopServiceManager;
import domain.ReactiveMongoDriver;
import rx.Observable;

import java.util.List;
import java.util.Map;

public class ShopServiceImpl implements ShopService {
    ShopServiceManager shopServiceManager;

    public ShopServiceImpl(final ReactiveMongoDriver reactiveMongoDriver) {
        this.shopServiceManager = new ShopServiceManager(reactiveMongoDriver);
    }

    @Override
    public Observable<String> addUser(final Map<String, List<String>> params) {
        return shopServiceManager.addUser(params);
    }

    @Override
    public Observable<String> getUsers(final Map<String, List<String>> params) {
        return shopServiceManager.getUsers(params);
    }

    @Override
    public Observable<String> addProduct(final Map<String, List<String>> params) {
        return shopServiceManager.addProduct(params);
    }

    @Override
    public Observable<String> getProducts(final Map<String, List<String>> params) {
        return shopServiceManager.getProducts(params);
    }
}
