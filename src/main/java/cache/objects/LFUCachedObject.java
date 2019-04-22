package cache.objects;

public class LFUCachedObject extends CachedObject
{
    public LFUCachedObject(Object objectToBeCached)
    {
        super(objectToBeCached);
    }

    @Override
    public String toString()
    {
        return "LFUCachedObject{" +
                "accessedCounter=" + accessedCounter + ", " + super.toString() +
                '}';
    }

    private int accessedCounter;

    public int getAccessedCounter()
    {
        return accessedCounter;
    }

    public void setAccessedCounter(int accessedCounter)
    {
        this.accessedCounter = accessedCounter;
    }
}
