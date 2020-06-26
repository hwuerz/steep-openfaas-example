package de.fhg.igd.steep.faas;

import model.processchain.Executable;

public class Handler implements IHandler {

    public String Handle(Executable req) throws Exception {
        return "Hello, world! I am an example for a steep openfaas function.";
    }
}
