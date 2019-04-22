import cache.objects.LFUCachedObject;
import cache.implementations.LFUCache;
import cache.utils.CacheGeneralUtils;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class LFUCacheTest
{
    private LFUCache<String, LFUCachedObject> lfuCache;
    private int i = 0;

    @Before
    public void init()
    {
        lfuCache = new LFUCache<>(5);

        while(i < lfuCache.getMaxSize())
        {
            lfuCache.put("obj" + i, new LFUCachedObject("this is the " + i + " object"));
            i++;
        }
    }

    @Test
    public void putTestOverflow() throws InterruptedException
    {
        TestUtils.accessElementsRandomTimes(lfuCache, 10);

        String keyOfLruCachedObject = (String) CacheGeneralUtils.getKeyOfLfuCachedObject(lfuCache);

        //Overflow occurs here
        lfuCache.put("obj" + i, new LFUCachedObject("this is the " + i + " object which will be added instead of the first one"));

        //After overflow LFU object doesn't exists anymore
        assertNull(lfuCache.get(keyOfLruCachedObject));
    }

}
