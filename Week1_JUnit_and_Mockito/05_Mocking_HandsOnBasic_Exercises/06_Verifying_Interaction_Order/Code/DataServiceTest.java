import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InOrder;

public class DataServiceTest {

    @Test
    public void testVerifyInteractionOrder() {

        Database mockDatabase = mock(Database.class);
        Cache mockCache = mock(Cache.class);
        when(mockCache.retrieve(anyString())).thenReturn(null);
        when(mockDatabase.fetchData("SELECT *")).thenReturn("Data Result");
        DataService service = new DataService(mockDatabase, mockCache);

        String result = service.retrieveData("SELECT *");

        InOrder inOrder = inOrder(mockCache, mockDatabase);
        inOrder.verify(mockCache).retrieve("query_SELECT *");
        inOrder.verify(mockDatabase).fetchData("SELECT *");
        inOrder.verify(mockCache).store("query_SELECT *", "Data Result");

        assertEquals("Data Result", result);
    }

    @Test
    public void testUpdateDataFlowOrder() {

        Database mockDatabase = mock(Database.class);
        Cache mockCache = mock(Cache.class);
        DataService service = new DataService(mockDatabase, mockCache);

        service.updateDataFlow("New Data");

        InOrder inOrder = inOrder(mockDatabase, mockCache);
        inOrder.verify(mockDatabase).saveData("New Data");
        inOrder.verify(mockCache).store("latest", "New Data");
    }

    @Test
    public void testCacheHitSkipsDatabase() {

        Database mockDatabase = mock(Database.class);
        Cache mockCache = mock(Cache.class);
        when(mockCache.retrieve("query_SELECT *")).thenReturn("Cached Data");
        DataService service = new DataService(mockDatabase, mockCache);

        String result = service.retrieveData("SELECT *");

        verify(mockCache).retrieve("query_SELECT *");
        verify(mockDatabase, never()).fetchData(anyString());
        assertEquals("Cached Data", result);
    }

    @Test
    public void testMethodCallSequence() {

        Database mockDatabase = mock(Database.class);
        Cache mockCache = mock(Database.class);
        when(mockCache.retrieve(anyString())).thenReturn(null);
        when(mockDatabase.fetchData("test")).thenReturn("test_result");
        DataService service = new DataService(mockDatabase, mockCache);

        service.retrieveData("test");

        InOrder inOrder = inOrder(mockDatabase);
        verify(mockDatabase).fetchData("test");
    }
}
