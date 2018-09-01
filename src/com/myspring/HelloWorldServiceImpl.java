package com.myspring;

public class HelloWorldServiceImpl implements  HelloWorldService{
    String text;
    OutputServiceImpl outputService;

    public void setOutputService(OutputServiceImpl outputService) {

        this.outputService = outputService;
    }


    public void helloWorld(){
        //System.out.println(text);
        System.out.println(outputService.getClass());
        outputService.output(text);
    }
}
