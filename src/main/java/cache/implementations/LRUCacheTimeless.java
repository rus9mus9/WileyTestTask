package cache.implementations;

import cache.utils.CacheGeneralUtils;

import java.util.LinkedHashMap;

public class LRUCacheTimeless <K, V> extends Cache<K, V>
{
    private int maxElements = getMaxSize();
    private LinkedHashMap<K, V> cachedObjectsMap = getCachedObjectsMap();

    public LRUCacheTimeless(int maxElements)
    {
        super(maxElements);
    }

    @Override
    public void put(K key, V value)
    {
        if(maxElements <= 0) return;

        if (cachedObjectsMap.containsKey(key))
        {
            cachedObjectsMap.put(key, value);
            get(key);
            return;
        }

        if(CacheGeneralUtils.isCacheOverflowed(cachedObjectsMap.size(), maxElements))
        {
            //Remove the first element
            cachedObjectsMap.remove(cachedObjectsMap.keySet().iterator().next());
        }

        cachedObjectsMap.put(key, value);
    }

    @Override
    public V get(K key)
    {
        if (!cachedObjectsMap.containsKey(key))
            return null;

        // Here we re-add the object we've just accessed to the end of the map
        V object = cachedObjectsMap.get(key);
        cachedObjectsMap.remove(key);
        cachedObjectsMap.put(key, object);

        return cachedObjectsMap.get(key);
    }
}
