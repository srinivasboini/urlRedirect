package com.test.file;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerFileReader {

    public static final String REQUEST_MESSAGE = "Request Message:" ;
    public static final String FILE_NAME = "/Users/srinivasboini/Downloads/urlRedirect/src/test/test.txt";

    public static void main(String[] args) throws Exception{
        List<Customer> lines = Files.lines(Paths.get(FILE_NAME))
                .filter( line -> line.contains(REQUEST_MESSAGE))
                .map( line -> line.substring(line.indexOf(REQUEST_MESSAGE)))
                .map(line -> line.split(" "))
                .map(lineArr -> Arrays.stream(lineArr).filter( el -> !el.equals("")).collect(Collectors.toList()))
                .map( list -> new Customer(list.get(2), list.get(4).substring(0,10)))
                .collect(Collectors.toList());


        Map<String, List<String>> result = lines.stream().
                collect(Collectors.groupingBy(Customer::getId, Collectors.mapping(Customer::getAccount, Collectors.toList()))) ;

        result.entrySet().forEach( ele -> System.out.println(String.format("%s %2s %3s",
                ele.getKey(),
                ele.getValue().size(),
                String.join(",",ele.getValue()))));

    }




}

class Customer {
    private String id ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    private String account ;

    public Customer(String id, String account){
        this.id = id ;
        this.account = account ;
    }


}
