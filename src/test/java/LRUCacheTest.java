import cache.objects.LRUCachedObject;
import cache.implementations.LRUCache;
import cache.utils.CacheGeneralUtils;
import org.junit.*;

import static org.junit.Assert.*;


public class LRUCacheTest
{
    private LRUCache<String, LRUCachedObject> lruCache  = new LRUCache<>(5);
    private int i = 0;

    @Before
    public void init()
    {
        lruCache = new LRUCache<>(5);

        while(i < lruCache.getMaxSize())
        {
            lruCache.put("obj" + i, new LRUCachedObject("this is the " + i + " object"));
            i++;
        }
    }

    @Test
    public void putTestOverflow() throws InterruptedException
    {
        String keyOfLruCachedObject = (String) CacheGeneralUtils.getKeyOfLruCachedObject(lruCache);

        //Overflow occurs here
        lruCache.put("obj" + i, new LRUCachedObject("this is the " + i + " object which will be added instead of the first one"));

        //After overflow LRU object doesn't exists anymore
        assertNull(lruCache.get(keyOfLruCachedObject));
    }


}
