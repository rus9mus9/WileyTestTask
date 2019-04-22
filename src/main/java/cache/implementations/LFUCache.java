package cache.implementations;

import cache.objects.LFUCachedObject;
import cache.utils.CacheGeneralUtils;

import java.util.LinkedHashMap;

public class LFUCache<K, V extends LFUCachedObject> extends Cache<K, LFUCachedObject>
{
    private int maxElements = getMaxSize();
    private LinkedHashMap<K, LFUCachedObject> LFUCachedObjectsMap = getCachedObjectsMap();

    public LFUCache(int maxElements)
    {
        super(maxElements);
    }

    @Override
    public void put(K key, LFUCachedObject value)
    {
        if(maxElements <= 0) return;

        if (LFUCachedObjectsMap.containsKey(key))
        {
            LFUCachedObjectsMap.put(key, value);
            get(key);
            return;
        }

        if(CacheGeneralUtils.isCacheOverflowed(LFUCachedObjectsMap.size(), maxElements))
        {
            Object lfuCachedObject = CacheGeneralUtils.getKeyOfLfuCachedObject(this);
            LFUCachedObjectsMap.remove(lfuCachedObject);
        }

        value.setAccessedCounter(1);
        LFUCachedObjectsMap.put(key, value);
    }

    @Override
    public LFUCachedObject get(K key)
    {
        if (!LFUCachedObjectsMap.containsKey(key))
            return null;

        LFUCachedObject lfuCachedObject = LFUCachedObjectsMap.get(key);
        lfuCachedObject.setAccessedCounter(lfuCachedObject.getAccessedCounter() + 1);
        LFUCachedObjectsMap.put(key, lfuCachedObject);

        return LFUCachedObjectsMap.get(key);
    }
}
