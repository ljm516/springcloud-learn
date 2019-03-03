package com.virgoconsumer.domain.learning;

import rx.Observable;
import rx.Subscriber;

/**
 * 命令模式
 */


public class Client {

    public static class Receiver {
        public void action() {
            System.out.println("receiver action");
        }
    }

    interface Command {
        void execute();
        void redo();
        void undo();

    }

    public static class ConcreteCommand implements Command {

        private Receiver receiver;
        ConcreteCommand(Receiver receiver) {
            this.receiver = receiver;
        }

        @Override
        public void execute() {
            this.receiver.action();
        }

        @Override
        public void redo() {
            this.receiver.action();
        }

        @Override
        public void undo() {
            this.receiver.action();
        }
    }

    public static class Invoker {
        private Command command;
        void setCommand(Command command) {
            this.command = command;
        }

        void action() {
            this.command.execute();
        }
        void redo() { this.command.redo();}
        void undo() { this.command.undo();}
    }

    static Observable<String> observable = Observable.create(subscriber -> {
        subscriber.onNext("hello RxJava");
        subscriber.onNext("i am ljm");
        subscriber.onCompleted();
    });

    static Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            System.out.println("Subscriber completed");
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            System.out.println("Subscriber: " + s);
        }
    };


    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
        Invoker invoker = new Invoker();
        invoker.setCommand(command);

        invoker.action();

        observable.subscribe(subscriber);
    }
}
