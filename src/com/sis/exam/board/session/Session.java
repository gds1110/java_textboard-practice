package com.sis.exam.board.session;

import java.util.HashMap;
import java.util.Map;

public class Session {

    private Map<String,Object> store;

    public Session() {
        this.store = new HashMap<>();
    }

    public Object getAttribute(String key)
    {
        return  store.get(key);
    }

    public void removeAttribute(String key)
    {
        store.remove(key);
    }

    public boolean hasAttribute(String key)
    {
        return store.containsKey(key);
    }

    public void setAttribute(String key, Object value) {
        store.put(key,value);
    }
}
