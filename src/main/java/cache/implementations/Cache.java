package cache.implementations;

import java.util.LinkedHashMap;

public abstract class Cache<K, V>
{
    private int maxElements;
    private LinkedHashMap<K, V> cachedObjectsMap;

    public Cache(int maxElements)
    {
        this.maxElements = maxElements;
        this.cachedObjectsMap = new LinkedHashMap<>();
    }

    public abstract void put(K key, V value);

    public abstract V get(K key);

    public void remove(K key)
    {
        cachedObjectsMap.remove(key);
    }

    public int size()
    {
        return cachedObjectsMap.size();
    }

    public void flushCache()
    {
        cachedObjectsMap.clear();
    }

    public int getMaxSize()
    {
        return maxElements;
    }

    public LinkedHashMap<K, V> getCachedObjectsMap()
    {
        return cachedObjectsMap;
    }
}
