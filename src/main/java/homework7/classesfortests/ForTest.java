package homework7.classesfortests;

import homework7.mytesterlib.AfterSuite;
import homework7.mytesterlib.BeforeSuite;
import homework7.mytesterlib.Test;

public class ForTest {

    @BeforeSuite
    public void beforeSuiteMethod() {
        System.out.println("Метод beforeSuiteMethod. Выполняется в самом начале");
    }

    //Если раскомментировать, то будет RuntimeException:
    //@BeforeSuite
    public void beforeSuiteMethod2() {
        System.out.println("Метод beforeSuiteMethod. Выполняется в самом начале");
    }

    @Test
    public void test1() {
        System.out.println("Метод test1 с дефолтным приоритетом (5)");
    }

    @Test(9)
    public void test2() {
        System.out.println("Метод test2 с приоритетом 9");
    }

    //сделаем методы с модификаторами protected и private, затем получим к ним доступ через рефлексию:
    @Test(1)
    protected void test3() {
        System.out.println("Метод test3 с приоритетом 1");
    }

    @Test(4)
    private void test4() {
        System.out.println("Метод test4 с приоритетом 4");
    }

    @Test(4)
    private void test5() {
        System.out.println("Метод test5 с приоритетом 4");
    }

    @AfterSuite
    public void afterSuiteMethod() {
        System.out.println("Метод afterSuiteMethod. Выполняется в самом конце");
    }

    //Если раскомментировать, то будет RuntimeException:
    //@AfterSuite
    public void afterSuiteMethod2() {
        System.out.println("Метод afterSuiteMethod. Выполняется в самом конце");
    }

}
