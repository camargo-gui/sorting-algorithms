import BinaryFile.Table;
import Tests.SortingTests;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SortingTests tests = new SortingTests();
//        Table table = new Table();
//        table.run();
//        tests.ArrayTest();
//        tests.ListTest();
        tests.FileTest();
    }
}
