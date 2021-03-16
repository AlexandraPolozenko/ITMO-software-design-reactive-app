package domain.dto;

import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

import static domain.dto.CurrencyDto.EUR;
import static domain.dto.CurrencyDto.RUB;
import static domain.dto.CurrencyDto.USD;


public class ProductDto {

    private final int id;
    private final String name;
    private final Map<CurrencyDto, String> prices;

    private final static String ID_FIELD = "id";
    private final static String NAME_FIELD = "name";

    public ProductDto(int id, String name, Map<CurrencyDto, String> prices) {
        this.id = id;
        this.name = name;
        this.prices = prices;
    }

    public ProductDto(Document doc) {
        this(doc.getInteger(ID_FIELD),
                doc.getString(NAME_FIELD),
                new HashMap<>() {{
                    put(RUB, doc.getString(RUB.toString()));
                    put(EUR, doc.getString(EUR.toString()));
                    put(USD, doc.getString(USD.toString()));
                }});
    }

    public Document toDocument() {
        return new Document(ID_FIELD, id)
                .append(NAME_FIELD, name)
                .append(RUB.toString(), prices.get(RUB))
                .append(EUR.toString(), prices.get(EUR))
                .append(USD.toString(), prices.get(USD));
    }

    public String toString(CurrencyDto currency) {
        return String.format("Product: {\n" +
                "\tid: %s,\n" +
                "\tname: %s,\n" +
                "\t%s: %s\n" +
                "}", id, name, currency.toString(), prices.get(currency));
    }

    @Override
    public String toString() {
        return String.format("Product: {\n\tid: %s,\n" +
                "\tname: %s,\n" +
                "\t%s: %s,\n" +
                "\t%s: %s,\n" +
                "\t%s: %s\n" +
                "}", id, name, RUB.toString(), prices.get(RUB), EUR.toString(), prices.get(EUR), USD.toString(), prices.get(USD)
        );
    }
}
