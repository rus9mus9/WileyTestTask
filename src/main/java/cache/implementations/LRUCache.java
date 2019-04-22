package cache.implementations;

import cache.objects.LRUCachedObject;
import cache.utils.CacheGeneralUtils;

import java.util.LinkedHashMap;

public class LRUCache<K, V extends LRUCachedObject> extends Cache<K, LRUCachedObject>
{
    private int maxElements = getMaxSize();
    private LinkedHashMap<K, LRUCachedObject> LRUCachedObjectsMap = getCachedObjectsMap(); // main.cache K and LFUCachedObject

    public LRUCache(int maxElements)
    {
        super(maxElements);
    }

    @Override
    public void put(K key, LRUCachedObject value)
    {
        if(maxElements <= 0) return;

        if (LRUCachedObjectsMap.containsKey(key))
        {
            LRUCachedObjectsMap.put(key, value);
            get(key);
            return;
        }

        if(CacheGeneralUtils.isCacheOverflowed(LRUCachedObjectsMap.size(), maxElements))
        {
            Object lruCachedObject = CacheGeneralUtils.getKeyOfLruCachedObject(this);
            LRUCachedObjectsMap.remove(lruCachedObject);
        }

        value.setLastTimeAccessed(System.nanoTime());
        LRUCachedObjectsMap.put(key, value);
    }

    @Override
    public LRUCachedObject get(K key)
    {
        if (!LRUCachedObjectsMap.containsKey(key))
            return null;

        LRUCachedObject lruCachedObject = LRUCachedObjectsMap.get(key);
        lruCachedObject.setLastTimeAccessed(System.nanoTime());
        LRUCachedObjectsMap.put(key, lruCachedObject);

        return LRUCachedObjectsMap.get(key);
    }
}
