package lesson7;

public class UnitTest {

    @BeforeSuite
    public void before() {
        System.out.println("Invoke before");
    }

    @AfterSuite
    public void after() {
        System.out.println("Invoke after");
    }

    @Test(priority = 4)
    public void test1() {
        System.out.println("Run test 1");
    }

    @Test(priority = 3)
    public void test2() {
        System.out.println("Run test 2");
    }

    @Test
    public void test3() {
        System.out.println("Run test 3");
    }

    @Test(priority = 8)
    public void test4() {
        System.out.println("Run test 4");
    }
}
