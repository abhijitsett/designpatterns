package org.designpatterns.behavioural.chainofresponsibility;

public class Level3SupportHandler implements SupportHandler{

    @Override
    public void handleRequest(Request request) {
        if (request.getPriority() == Priority.CRITICAL) {
            System.out.println("Level 3 Support handled the request.");
        } else {
            System.out.println("Request cannot be handled.");
        }
    }

    @Override
    public void setNextHandler(SupportHandler nextHandler) {
        // Do nothing for this handler
    }
}
