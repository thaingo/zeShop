package bzh.zomzog.cart.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import bzh.zomzog.cart.CartServerApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CartServerApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ResourceServerIntegrationTest {

    @Test
    public void whenLoadApplication_thenSuccess() {

    }
}
