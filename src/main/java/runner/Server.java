package runner;

import api.ShopService;

import domain.ReactiveMongoDriver;
import io.reactivex.netty.protocol.http.server.HttpServer;
import rx.Observable;
import ws.ShopServiceImpl;

import java.util.List;
import java.util.Map;

public class Server {

    private final static int PORT = 8080;

    private final static String ADD_USER_METHOD = "addUser";
    private final static String GET_USERS_METHOD = "getUsers";
    private final static String ADD_PRODUCT_METHOD = "addProduct";
    private final static String GET_PRODUCTS_METHOD = "getProducts";
    private final static String WRONG_METHOD_MESSAGE = "Wrong method, available methods: "
        + ADD_USER_METHOD + ", "
        + GET_USERS_METHOD + ", "
        + ADD_PRODUCT_METHOD + ", "
        + GET_PRODUCTS_METHOD;

    ShopService shopServiceManager;

    public Server(ReactiveMongoDriver mongoDriver) {
        this.shopServiceManager = new ShopServiceImpl(mongoDriver);
    }

    public void run() {
        HttpServer.newServer(PORT).start((request, response) -> {
            String method = request.getDecodedPath().substring(1);
            Map<String, List<String>> params = request.getQueryParameters();
            if (ADD_USER_METHOD.equals(method)) {
                return response.writeString(shopServiceManager.addUser(params));
            }
            if (GET_USERS_METHOD.equals(method)) {
                return response.writeString(shopServiceManager.getUsers(params));
            }
            if (ADD_PRODUCT_METHOD.equals(method)) {
                return response.writeString(shopServiceManager.addProduct(params));
            }
            if (GET_PRODUCTS_METHOD.equals(method)) {
                return response.writeString(shopServiceManager.getProducts(params));
            }

            return response.writeString(
                Observable.just(WRONG_METHOD_MESSAGE)
            );
        }).awaitShutdown();
    }
}
