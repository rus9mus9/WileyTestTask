import cache.implementations.LRUCacheTimeless;
import cache.utils.CacheGeneralUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Random;

import static org.junit.Assert.*;

public class LRUTimelessCacheTest
{
    private LRUCacheTimeless<String, Object> lruTimelessCache;
    private LinkedHashMap<String, Object> mapOfLruCache;
    private int i = 0;

    @Before
    public void init()
    {
        lruTimelessCache = new LRUCacheTimeless<>(5);

        mapOfLruCache = lruTimelessCache.getCachedObjectsMap();

        while(i < lruTimelessCache.getMaxSize())
        {
            lruTimelessCache.put("obj" + i, "this is the " + i + " object");
            i++;
        }
    }

    @Test
    public void putTestOverflow() throws InterruptedException
    {
        String keyOfLruCachedObject = (String) mapOfLruCache.get(mapOfLruCache.entrySet().iterator().next());

        //Overflow occurs here
        lruTimelessCache.put("obj" + i, "this is the " + i + " object which will be added instead of the first one");

        //After overflow LRU object doesn't exists anymore
        assertNull(lruTimelessCache.get(keyOfLruCachedObject));
    }

    @Test
    public void checkObjectMovedToTheEnd()
    {
        //Here we access a random object in the cache, which will be moved to the end of the it
        Object objectToBeMovedToTheEnd = lruTimelessCache.get("obj" + new Random().nextInt(i-0));

        Object lastObjectInTheCache = CacheGeneralUtils.getLatestObjectInMap(mapOfLruCache);

        // and check that this element has been successfully moved to the end of the cache
        assertSame(objectToBeMovedToTheEnd, lastObjectInTheCache);
    }
}
