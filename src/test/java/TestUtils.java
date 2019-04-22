import cache.implementations.Cache;

import java.util.Iterator;
import java.util.Random;

public final class TestUtils
{
    private TestUtils()
    {

    }
    public static void accessElementsRandomTimes(Cache cache, int maxTimesAccessed)
    {
        Iterator iterator = cache.getCachedObjectsMap().keySet().iterator();

        while(iterator.hasNext())
        {
            int randomAccessTime = new Random().nextInt(maxTimesAccessed);
            Object currentKey =  iterator.next();

            for(int i = 0; i < randomAccessTime; i++)
            {
                cache.get(currentKey);
            }

        }
    }
}
