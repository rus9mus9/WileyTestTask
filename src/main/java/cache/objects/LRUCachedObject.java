package cache.objects;

public class LRUCachedObject extends CachedObject
{
    public LRUCachedObject(Object objectToBeCached)
    {
        super(objectToBeCached);
    }

    private long lastTimeAccessed;

    public long getLastTimeAccessed()
    {
        return lastTimeAccessed;
    }

    @Override
    public String toString()
    {
        return "LRUCachedObject{" +
                "lastTimeAccessed=" + lastTimeAccessed + ", " + super.toString() +
                '}';
    }

    public void setLastTimeAccessed(long lastTimeAccessed)
    {
        this.lastTimeAccessed = lastTimeAccessed;
    }
}
