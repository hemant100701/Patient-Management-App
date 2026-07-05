package com.hemendra.stack;

import software.amazon.awscdk.*;

public class LocalStack extends Stack {

    public LocalStack(final App app, final String id, final StackProps props){
        super(app,id, props);
    }

    public static void main(final String[] args){

        App app = new App(AppProps.builder().outdir("./cdk.out").build());

        StackProps props = StackProps.builder()
                .synthesizer(new BootstraplessSynthesizer())
                .build();

        new LocalStack(app,"localstack",props);
    }
}
