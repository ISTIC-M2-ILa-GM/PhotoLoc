package fr.istic.hbmlh.photoloc.manager.base;

import java.util.Map;

import static fr.istic.hbmlh.photoloc.manager.JsonUtils.parse;

public abstract class ClientManagerSync<T> implements ClientManager<T> {

    Class<T> genericType;

    ClientManagerSync(Class<T> genericType) {
        this.genericType = genericType;
    }

    public abstract String get(String url);

    public abstract String post(String url, T object);

    public abstract String put(String url, Map<String, Object> datas);

    public abstract String post(String url);

    public abstract String delete(String url);

    public T readOneSync(String url) {
        return parse(get(url), this.genericType);
    }

}
