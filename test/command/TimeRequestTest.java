package command;

import org.junit.jupiter.api.Test;

class TimeRequestTest {
    @Test
    void TimeRequestTest_logicTest(){
        TimeRequestCommand timeRequest = new TimeRequestCommand();
        timeRequest.execute();
    }


}