package api;

import rx.Observable;

import java.util.List;
import java.util.Map;

public interface ShopService {
    Observable<String> addUser(Map<String, List<String>> params);

    Observable<String> getUsers(Map<String, List<String>> params);

    Observable<String> addProduct(Map<String, List<String>> params);

    Observable<String> getProducts(Map<String, List<String>> params);
}
