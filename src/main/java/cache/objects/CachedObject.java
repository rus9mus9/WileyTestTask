package cache.objects;

public abstract class CachedObject
{
    private Object objectToBeCached;

    CachedObject(Object objectToBeCached)
    {
        this.objectToBeCached = objectToBeCached;
    }

    public Object getObjectToBeCached()
    {
        return objectToBeCached;
    }

    @Override
    public String toString()
    {
        return "objectToBeCached=" + objectToBeCached;
    }
}
