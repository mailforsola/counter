package com.novak.counter;

import java.util.List;

public interface Counter {

    String create();

    Long increment(String counter);

    Long getValue(String counter);

    Long delete(String counter);

    Long sum();

    List<String> list();

}
