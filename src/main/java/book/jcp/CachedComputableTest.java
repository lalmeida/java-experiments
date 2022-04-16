package book.jcp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CachedComputableTest {

    private ComputableStub computableStub = new ComputableStub();

    CachedComputable cachedComputable = new CachedComputable<String, String>(computableStub);

    @Test
    void delegates_to_Computable() throws InterruptedException {
        assertEquals("B1", cachedComputable.compute("A1"));
    }

    @Test
    @Timeout(1) // the computation is cached, hence several calls should still take less than a second
    void same_request_several_times_should_hit_compute_service_only_once_and_be_fast() throws InterruptedException {
        cachedComputable.compute("A1");
        cachedComputable.compute("A1");
        cachedComputable.compute("A1");
        cachedComputable.compute("A1");
        assertEquals(1 , computableStub.serviceCalls);
    }

    public CachedComputable getCachedComputable() {
        return cachedComputable;
    }

    private static class ComputableStub implements Computable<String, String> {
        int serviceCalls = 0;

        @Override
        public String compute(String arg) throws InterruptedException {
            serviceCalls++;
            Thread.sleep(700); //emulate long service processing
            if (arg.equals("A1")) return "B1";

            return null;
        }
    }
}