package org.vie.util.log;

import org.junit.Test;
import top.test.UnitBase;

public class AnnalTk extends UnitBase {

    @Test
    public void testAnnal() {
        final Annal logger = Annal.get(AnnalTk.class);
    }
}
