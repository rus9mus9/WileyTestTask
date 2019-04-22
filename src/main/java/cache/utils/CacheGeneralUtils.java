package cache.utils;



import cache.implementations.LFUCache;
import cache.implementations.LRUCache;
import cache.objects.LFUCachedObject;
import cache.objects.LRUCachedObject;

import java.util.Iterator;
import java.util.LinkedHashMap;

public final class CacheGeneralUtils
{
    private CacheGeneralUtils()
    {

    }

    public static Object getKeyOfLfuCachedObject(LFUCache<?, ? extends LFUCachedObject> lfuCache)
    {
        int min = Integer.MAX_VALUE;
        Object lfuCachedObjectKey = null;
        LinkedHashMap<?, LFUCachedObject> cachedObjectsMap = lfuCache.getCachedObjectsMap();

        for(Object k : cachedObjectsMap.keySet())
        {
            LFUCachedObject lfuCachedObject = cachedObjectsMap.get(k);
            int lfuCachedObjectAccessedCounter = lfuCachedObject.getAccessedCounter();

            if(lfuCachedObjectAccessedCounter < min)
            {
                min = lfuCachedObjectAccessedCounter;
                lfuCachedObjectKey = k;
            }
        }

        return lfuCachedObjectKey;
    }

    public static Object getKeyOfLruCachedObject(LRUCache<?, ? extends LRUCachedObject> lruCache)
    {
        long min = Long.MAX_VALUE;
        Object lruCachedObjectKey = null;
        LinkedHashMap<?, LRUCachedObject> cachedObjectsMap = lruCache.getCachedObjectsMap();

        for(Object k : cachedObjectsMap.keySet())
        {
            LRUCachedObject lruCachedObject = cachedObjectsMap.get(k);
            long lruCachedObjectLastTimeAccessed = lruCachedObject.getLastTimeAccessed();

            if(lruCachedObjectLastTimeAccessed < min)
            {
                min = lruCachedObjectLastTimeAccessed;
                lruCachedObjectKey = k;
            }
        }

        return lruCachedObjectKey;
    }


    public static boolean isCacheOverflowed(int cacheMapSize, int maxElements)
    {
        return cacheMapSize >= maxElements;
    }

    public static Object getLatestObjectInMap(LinkedHashMap map)
    {
        Iterator iterator = map.keySet().iterator();

        Object lastKey = null;

        while(iterator.hasNext())
        {
            lastKey = iterator.next();
        }

        return map.get(lastKey);
    }

}
